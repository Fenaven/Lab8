package App.Controllers;

import App.CheckboxesConstants;
import App.ModalController;
import Commands.myCommands.InsertHumanBeing;
import Languages.CurrentLanguage;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import validators.Errors;

import java.net.URL;
import java.util.ResourceBundle;

public class AddElementController implements Initializable, ModalController {
    private Stage modalStage;

    private final ObservableList<String> BOOLEAN_TYPES = CheckboxesConstants.getBooleanTypes();
    private final ObservableList<String> WEAPON_TYPES = CheckboxesConstants.getWeaponTypes();
    private final ObservableList<String> MOOD_TYPES = CheckboxesConstants.getMoodTypes();
    @FXML private ChoiceBox<String> realHeroChoice;
    @FXML private ChoiceBox<String> hasToothPickChoice;
    @FXML private ChoiceBox<String> weaponTypeChoice;
    @FXML private ChoiceBox<String> moodChoice;
    @FXML private ChoiceBox<String> carChoice;
    @FXML private TextField nameField;
    @FXML private TextField impactSpeedField;
    @FXML private Button submitButton;
    @FXML private TextField coordinateXField;
    @FXML private TextField coordinateYField;
    @FXML private Text errorText;
    @FXML private Button closeButton;

    @FXML private Label createObject;

    @FXML private Label nameLabel;

    @FXML private Label realHeroLabel;

    @FXML private Label hasToothPickLabel;

    @FXML private Label impactSpeedLabel;

    @FXML private Label carCoolLabel;

    @FXML private Label moodLabel;

    @FXML private Label weaponTypeLabel;

    @FXML private Label coordinateXLabel;
    @FXML private Label coordinateYLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorText.setVisible(false);

        realHeroChoice.setValue(BOOLEAN_TYPES.get(0));
        realHeroChoice.setItems(BOOLEAN_TYPES);
        hasToothPickChoice.setValue(BOOLEAN_TYPES.get(0));
        hasToothPickChoice.setItems(BOOLEAN_TYPES);
        carChoice.setValue(BOOLEAN_TYPES.get(0));
        carChoice.setItems(BOOLEAN_TYPES);
        weaponTypeChoice.setValue(WEAPON_TYPES.get(0));
        weaponTypeChoice.setItems(WEAPON_TYPES);
        moodChoice.setValue(MOOD_TYPES.get(0));
        moodChoice.setItems(MOOD_TYPES);

        submitButton.setOnAction(event -> {
            String name = nameField.getText();
            String coordinateX = coordinateXField.getText().replace(",", ".");
            String coordinateY = coordinateYField.getText().replace(",", ".");
            String coordinates = coordinateX + "," + coordinateY;
            String impactSpeed = impactSpeedField.getText();
            String realHero = realHeroChoice.getValue();
            String hasToothPick = hasToothPickChoice.getValue();
            String weaponType = weaponTypeChoice.getValue();
            String mood = moodChoice.getValue();
            String carCool = carChoice.getValue();
            InsertHumanBeing insertHumanBeing = new InsertHumanBeing();
            Errors error = insertHumanBeing.execute(name, coordinates, impactSpeed, realHero, hasToothPick,
                    weaponType, mood, carCool);
            if(error == Errors.NOTHAVEERRORS){
                modalStage.close();

            }else {
                errorText.setText(error.getError());
                errorText.setStyle("-fx-fill: red");
            }
            errorText.setVisible(true);
        });

        closeButton.setOnAction(event -> modalStage.close());
        setLanguages();
    }

    public void setLanguages(){
        ResourceBundle currentLanguage = CurrentLanguage.getCurrentLanguage();
        createObject.setText(currentLanguage.getString("createObject"));
        nameLabel.setText(currentLanguage.getString("name"));
        coordinateXLabel.setText(currentLanguage.getString("coordinateXLabel"));
        coordinateYLabel.setText(currentLanguage.getString("coordinateYLabel"));
        realHeroLabel.setText(currentLanguage.getString("isRealHero"));
        hasToothPickLabel.setText(currentLanguage.getString("hasToothPick"));
        weaponTypeLabel.setText(currentLanguage.getString("weaponType"));
        moodLabel.setText(currentLanguage.getString("mood"));
        carCoolLabel.setText(currentLanguage.getString("carCool"));
        impactSpeedLabel.setText(currentLanguage.getString("impactSpeed"));
        submitButton.setText(currentLanguage.getString("addButton"));
        closeButton.setText(currentLanguage.getString("closeTableFieldButton"));
    }

    public void setModalStage(Stage modalStage) {
        this.modalStage = modalStage;
    }
}
