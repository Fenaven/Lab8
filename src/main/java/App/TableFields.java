package App;

import Classes.HumanBeing;
import Classes.HumanBeingInformation;
import Languages.CurrentLanguage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class TableFields {
    private final HumanBeing humanBeing;

    public TableFields(HumanBeing humanBeing) {
        this.humanBeing = humanBeing;
    }

    public ObservableList<HumanBeingInformation> getTableFields(){
        final ObservableList<String> BOOLEAN_TYPES = CheckboxesConstants.getBooleanTypes();
        final ObservableList<String> WEAPON_TYPES = CheckboxesConstants.getWeaponTypes();
        final ObservableList<String> MOOD_TYPES = CheckboxesConstants.getMoodTypes();
        ChoiceBox<String> choiceBoxIsRealHero = new ChoiceBox<>(BOOLEAN_TYPES);
        choiceBoxIsRealHero.setValue(String.valueOf(humanBeing.getRealHero()));
        ChoiceBox<String> choiceBoxIsToothPick = new ChoiceBox<>(BOOLEAN_TYPES);
        choiceBoxIsToothPick.setValue(String.valueOf(humanBeing.getHasToothpick()));
        ChoiceBox<String> choiceBoxIsCarCool = new ChoiceBox<>(BOOLEAN_TYPES);
        choiceBoxIsCarCool.setValue(String.valueOf(humanBeing.getCar().getCool()));
        ChoiceBox<String> choiceBoxWeaponTypes = new ChoiceBox<>(WEAPON_TYPES);
        choiceBoxWeaponTypes.setValue(String.valueOf(humanBeing.getWeaponType()));
        ChoiceBox<String> choiceBoxMoodTypes = new ChoiceBox<>(MOOD_TYPES);
        choiceBoxMoodTypes.setValue(String.valueOf(humanBeing.getMood()));

        ResourceBundle currentLanguage = CurrentLanguage.getCurrentLanguage();
        String x = String.valueOf(humanBeing.getCoordinates().getX());
        String y = String.valueOf(humanBeing.getCoordinates().getY());


        final SimpleDateFormat dateFormat = new SimpleDateFormat(currentLanguage.getString("dataFormat"));

        if(CurrentLanguage.getCurrentLanguageString().equals("en")){
            x = x.replace(".", ",");
            y = y.replace(".", ",");
        }

        return FXCollections.observableArrayList(
                new HumanBeingInformation(currentLanguage.getString("name"), humanBeing.getName(), new TextField()),
                new HumanBeingInformation(currentLanguage.getString("coordinates"),
                        x + ";" +
                                y, new TextField()),
                new HumanBeingInformation(currentLanguage.getString("impactSpeed"), String.valueOf(humanBeing.getImpactSpeed()),
                        new TextField()),
                new HumanBeingInformation(currentLanguage.getString("isRealHero"), String.valueOf(humanBeing.getRealHero()),
                        choiceBoxIsRealHero),
                new HumanBeingInformation(currentLanguage.getString("hasToothPick"), String.valueOf(humanBeing.getHasToothpick()),
                        choiceBoxIsToothPick),
                new HumanBeingInformation(currentLanguage.getString("weaponType"), String.valueOf(humanBeing.getWeaponType()),
                        choiceBoxWeaponTypes),
                new HumanBeingInformation(currentLanguage.getString("mood"), String.valueOf(humanBeing.getMood()),
                        choiceBoxMoodTypes),
                new HumanBeingInformation(currentLanguage.getString("carCool"), String.valueOf(humanBeing.getCar().getCool()),
                        choiceBoxIsCarCool),
                new HumanBeingInformation(currentLanguage.getString("creationDate"), dateFormat.format(humanBeing.getCreationDate()), new Label()),
                new HumanBeingInformation(currentLanguage.getString("userLogin"), humanBeing.getUser(), new Label())
        );
    }


}
