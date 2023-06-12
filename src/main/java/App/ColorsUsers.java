package App;

import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

public class ColorsUsers {
    private static Map<String, Color> colorsUsers = new HashMap<>();

    public static Map<String, Color> getColorsUsers() {
        return colorsUsers;
    }

    public static void setColorsUsers(Map<String, Color> colorsUsers) {
        ColorsUsers.colorsUsers = colorsUsers;
    }
}
