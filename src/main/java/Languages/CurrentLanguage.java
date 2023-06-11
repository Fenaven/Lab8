package Languages;

import java.util.ResourceBundle;

public class CurrentLanguage {
    private static ResourceBundle currentLanguage = Language.ru;



    public static ResourceBundle getCurrentLanguage() {
        return currentLanguage;
    }

    public static void setCurrentLanguage(ResourceBundle currentLanguage) {
        CurrentLanguage.currentLanguage = currentLanguage;
    }
}

