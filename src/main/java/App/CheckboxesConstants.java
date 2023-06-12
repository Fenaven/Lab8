package App;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CheckboxesConstants {
    private static final ObservableList<String> BOOLEAN_TYPES = FXCollections
            .observableArrayList("false", "true");
    private static final ObservableList<String> WEAPON_TYPES = FXCollections
            .observableArrayList(  "pistol", "knife", "machine_gun");
    private static final ObservableList<String> MOOD_TYPES = FXCollections
            .observableArrayList("sadness", "sorrow", "longing");

    public static ObservableList<String> getBooleanTypes(){
        return BOOLEAN_TYPES;
    }

    public static ObservableList<String> getWeaponTypes(){
        return WEAPON_TYPES;
    }

    public static ObservableList<String> getMoodTypes(){
        return MOOD_TYPES;
    }


}
