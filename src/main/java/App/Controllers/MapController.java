    package App.Controllers;


    import App.ChangeSceneHandler;
    import App.ColorsUsers;
    import App.Localization;
    import Classes.Coordinates;
    import Classes.HumanBeing;
    import Classes.HumanBeingCollection;
    import Commands.myCommands.Update;
    import Database.Authentication;
    import Languages.CurrentLanguage;
    import javafx.animation.ScaleTransition;
    import javafx.animation.StrokeTransition;
    import javafx.animation.TranslateTransition;
    import javafx.fxml.FXML;
    import javafx.fxml.Initializable;
    import javafx.scene.Group;
    import javafx.scene.Scene;
    import javafx.scene.control.Alert;
    import javafx.scene.control.Button;
    import javafx.scene.control.ButtonType;
    import javafx.scene.control.Label;
    import javafx.scene.effect.DropShadow;
    import javafx.scene.input.MouseButton;
    import javafx.scene.input.MouseEvent;
    import javafx.scene.layout.AnchorPane;
    import javafx.scene.paint.Color;
    import javafx.scene.shape.Circle;
    import javafx.scene.shape.Polygon;
    import javafx.scene.shape.Shape;
    import javafx.util.Duration;

    import java.net.URL;
    import java.util.*;

    public class MapController implements Initializable, Localization {

        @FXML
        private AnchorPane mapPane;
        @FXML
        private Label coordinateLabel;
        @FXML
        private Button closeMapButton;
        private static final float SCREEN_WIDTH = 1080;
        private static final Integer SCREEN_HEIGHT = 648;
        private Group selectedObject = null;
        private double originalScaleX = 1;
        private double originalScaleY = 1;

        private Map<Long, Group> selectedObjects = new HashMap<>();


        @Override
        public void initialize(URL location, ResourceBundle resources) {
            for (HumanBeing human : HumanBeingCollection.getHumanBeings()) {
                String userLogin = human.getUser();

                Color color = ColorsUsers.getColorsUsers().get(userLogin);
                if (color == null) {
                    color = generateRandomColor();
                    ColorsUsers.getColorsUsers().put(userLogin, color);
                }
                Group people = drawPeople(color);
                //if(!human.equals(Authentication.getCurrentUser())) people.setDisable(true);
                people.setUserData(human);
                people.setLayoutX(normalizeX(human.getCoordinates().getX()));
                people.setLayoutY(normalizeY(human.getCoordinates().getY()));
                people.setOnMouseClicked(event -> {
                    if (Authentication.getCurrentUser().equals(human.getUser())) {
                        if (selectedObjects.containsKey(human.getId())) {
                            unselectHuman(human.getId());
                        }
                        selectHuman(human.getId(), people);
                        event.consume(); // Stop the event from propagating further
                    }else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle(CurrentLanguage.getCurrentLanguage().getString("impossibleEditObject"));
                        alert.setHeaderText(null);
                        alert.setContentText(CurrentLanguage.getCurrentLanguage().getString("notCreatedThisUser"));
                        ButtonType okButton = new ButtonType(CurrentLanguage.getCurrentLanguage().getString("ok"));
                        alert.getButtonTypes().setAll(okButton);
                        alert.showAndWait();
                    }
                });
                mapPane.getChildren().add(people);
            }
            mapPane.setOnMouseMoved(event -> {
                coordinateLabel.setText("X: " + denormalizeX( (int) event.getX()) + ", Y: " + denormalizeY(event.getY()));
            });



            mapPane.setOnMouseClicked(event -> {
                for (Map.Entry<Long, Group> entry : selectedObjects.entrySet()) {
                    Group selectedObject = entry.getValue();
                    double deltaX = event.getX() - selectedObject.getLayoutX();
                    double deltaY = event.getY() - selectedObject.getLayoutY();

                    // Создаем и настраиваем перемещение
                    int impactSpeed = ((HumanBeing) selectedObject.getUserData()).getImpactSpeed();
                    TranslateTransition tt = new TranslateTransition(Duration.millis(5000/impactSpeed), selectedObject);
                    tt.setToX(deltaX);
                    tt.setToY(deltaY);
                    tt.setOnFinished(e -> {
                        // Обновляем позицию объекта после перемещения
                        selectedObject.setLayoutX(selectedObject.getLayoutX() + deltaX);
                        selectedObject.setLayoutY(selectedObject.getLayoutY() + deltaY);
                        selectedObject.setTranslateX(0);
                        selectedObject.setTranslateY(0);

                        // Обновляем координаты в объекте HumanBeing
                        HumanBeing human = (HumanBeing) selectedObject.getUserData();
                        human.getCoordinates().setX(denormalizeX(selectedObject.getLayoutX()));
                        human.getCoordinates().setY(denormalizeY(selectedObject.getLayoutY()));
                        Update update = new Update(human);
                        update.updateCollection();

                        // Восстанавливаем исходный размер объекта
                        ScaleTransition st = new ScaleTransition(Duration.millis(500), selectedObject);
                        st.setToX(originalScaleX);
                        st.setToY(originalScaleY);
                        st.play();

                        // Убираем обводку
                        DropShadow borderGlow = new DropShadow();
                        borderGlow.setOffsetY(0f);
                        borderGlow.setOffsetX(0f);
                        borderGlow.setColor(Color.TRANSPARENT);
                        borderGlow.setWidth(30);
                        borderGlow.setHeight(30);

                        selectedObject.setEffect(borderGlow);
                    });
                    tt.play();
                }
                selectedObjects.clear(); // Clear the selectedObjects map after moving them.
            });



            closeMapButton.setOnAction(new ChangeSceneHandler("/scenes/main/main.fxml"));
            setLanguage();
        }

        public void setLanguage(){
            ResourceBundle currentLanguage = CurrentLanguage.getCurrentLanguage();
            closeMapButton.setText(currentLanguage.getString("toTable"));

        }
        private Color generateRandomColor() {
            Random random = new Random();
            double red = random.nextDouble();
            double green = random.nextDouble();
            double blue = random.nextDouble();

            return new Color(red, green, blue, 1.0);
        }

        private double normalizeX(double x){
            float minValue = -Float.MAX_VALUE;
            float maxValue = 357;
            return ((x - minValue) / (maxValue - minValue)) * SCREEN_WIDTH;
        }
        private double normalizeY(double y){
            double minValue = Integer.MIN_VALUE;
            double maxValue = Integer.MAX_VALUE;
            double res =  (y - minValue) /   (maxValue - minValue);
            return res*SCREEN_HEIGHT;
        }
        private double denormalizeX(double normalizedX) {
            float minValue = -Float.MAX_VALUE;
            float maxValue = 357;
            double denormalizedX = normalizedX / SCREEN_WIDTH * (maxValue - minValue) + minValue;
            return denormalizedX;
        }

        private double denormalizeY(double normalizedY) {
            double minValue = Integer.MIN_VALUE;
            double maxValue = Integer.MAX_VALUE;
            double denormalizedY = normalizedY / SCREEN_HEIGHT * (maxValue - minValue) + minValue;
            return denormalizedY;
        }

        private Group drawPeople(Color color){
            Group root = new Group();

            // Создаем полигон для звездочки
            Polygon star = new Polygon(
                    0, -50,
                    10, -20,
                    40, -20,
                    15, 0,
                    25, 30,
                    0, 10,
                    -25, 30,
                    -15, 0,
                    -40, -20,
                    -10, -20
            );

            star.setFill(color);
            // Создаем круг для вершины
            Circle circle = new Circle(0, -50, 10);
            circle.setFill(color);
            // Добавляем полигон и круг в группу



            root.getChildren().addAll(star, circle);

            return root;
        }
        private void selectHuman(Long id, Group human) {
            selectedObjects.put(id, human);

            ScaleTransition st = new ScaleTransition(Duration.millis(500), human);
            st.setToX(1.2);
            st.setToY(1.2);
            st.play();

            DropShadow borderGlow = new DropShadow();
            borderGlow.setOffsetY(0f);
            borderGlow.setOffsetX(0f);
            borderGlow.setColor(Color.BLUE);
            borderGlow.setWidth(30);
            borderGlow.setHeight(30);

            human.setEffect(borderGlow);
        }

        private void unselectHuman(Long id) {
            Group human = selectedObjects.get(id);

            ScaleTransition st = new ScaleTransition(Duration.millis(500), human);
            st.setToX(originalScaleX);
            st.setToY(originalScaleY);
            st.play();

            DropShadow borderGlow = new DropShadow();
            borderGlow.setOffsetY(0f);
            borderGlow.setOffsetX(0f);
            borderGlow.setWidth(30);
            borderGlow.setHeight(30);

            human.setEffect(borderGlow);

            selectedObjects.remove(id);
        }

        }



