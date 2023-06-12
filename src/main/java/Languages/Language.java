package Languages;

import java.util.Locale;
import java.util.ResourceBundle;

public class Language {

    private static final String path = "localization/GuiLabels";
    public static final ResourceBundle ru = ResourceBundle.getBundle(path, new Locale("ru", "RU"));
    public static final ResourceBundle be = ResourceBundle.getBundle(path, new Locale("be", "BE"));
    public static final ResourceBundle hu = ResourceBundle.getBundle(path, new Locale("hu", "HU"));
    public static final ResourceBundle en = ResourceBundle.getBundle(path, new Locale("en", "EN"));



}
