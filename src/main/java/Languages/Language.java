package Languages;

import java.util.Locale;
import java.util.ResourceBundle;

public class Language {

    private static final String path = "localization/GuiLabels";
    public static final ResourceBundle ru = ResourceBundle.getBundle(path, new Locale("ru", "RU"));
}
