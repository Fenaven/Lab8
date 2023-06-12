package App;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ModalSceneHandler implements EventHandler<ActionEvent> {

    private final String pathToScene;
    private final Scene lastScene;

    public ModalSceneHandler(String pathToScene, Scene lastScene){
        this.pathToScene = pathToScene;
        this.lastScene = lastScene;
    }

    @Override
    public void handle(ActionEvent event) {
        Stage modalStage = new Stage();
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.initStyle(StageStyle.TRANSPARENT);
        modalStage.initOwner(lastScene.getWindow()); // Установка родительского окна

        // Загрузка FXML для модального окна
        FXMLLoader loader = new FXMLLoader(getClass().getResource(pathToScene));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Получение контроллера AddElementController
        ModalController controller = loader.getController();

// Передача ссылки на Stage в контроллер
        controller.setModalStage(modalStage);

        // Создание сцены для модального окна
        Scene modalScene = new Scene(root, Color.TRANSPARENT);
        // Установка сцены для модального окна и отображение его
        modalStage.setScene(modalScene);
        BoxBlur blurEffect = new BoxBlur(10, 10, 3);
        Region scene = (Region) lastScene.getRoot();
        scene.setEffect(blurEffect);
        modalStage.showAndWait();
        scene.setEffect(null);
    }

}
