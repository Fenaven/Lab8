package Database;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connection {
    private static java.sql.Connection connection;
    private static void getConnect() {
        try {
            connection = DriverManager.getConnection(PropertiesGetter.getUrl(), PropertiesGetter.getUser(), PropertiesGetter.getPassword());
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }
    public static void executeStatement(String statement) {
        try {
            getConnect();
            connection.createStatement().execute(statement);
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
    }
    public static ResultSet executePreparedStatement(String sqlRequest) {
        getConnect();
        try {
            return connection.prepareStatement(sqlRequest).executeQuery();
        } catch (SQLException sqlException) {
            return null;
        } finally {
            try {
                connection.close();
            } catch (SQLException sqlException) {
                System.out.println(sqlException.getMessage());
            }
        }
    }
}