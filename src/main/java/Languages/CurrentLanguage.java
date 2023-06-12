package Languages;

import java.util.Locale;
import java.util.ResourceBundle;

public class CurrentLanguage {
    private static ResourceBundle currentLanguage = Language.ru;

    private static String currentLanguageString = Locale.getDefault().toString().split("_")[0];

    public static String getCurrentLanguageString() {
        return currentLanguageString;
    }

    public static void setCurrentLanguageString(String currentLanguageString) {
        CurrentLanguage.currentLanguageString = currentLanguageString;
    }

    public static ResourceBundle getCurrentLanguage() {
        return currentLanguage;
    }

    public static void setCurrentLanguage(ResourceBundle currentLanguage) {
        CurrentLanguage.currentLanguage = currentLanguage;
    }
}

