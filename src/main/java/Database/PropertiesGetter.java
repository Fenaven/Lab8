package Database;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesGetter {
    private static String url;
    private static String user;
    private static String password;
    protected static String getUrl() {
        return url;
    }
    protected static String getUser() {
        return user;
    }
    protected static String getPassword() {
        return password;
    }
    static  {
        Properties properties = new Properties();
        try {
            properties.load(PropertiesGetter.class.getResourceAsStream("/db.cfg"));
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }
}