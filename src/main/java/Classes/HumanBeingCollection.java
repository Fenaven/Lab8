package Classes;

import Database.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;

/**
 * The type Human being collection.
 */
public class HumanBeingCollection {
    private static LinkedList<HumanBeing> humanBeings = new LinkedList<>();
    private static Date dateOfInitialization = new Date();
    /**
     * Gets info.
     */
    public static void getInfo() {
        System.out.println(Colors.GREEN + "Тип коллекции:" + Colors.RESET + " " + Colors.WHITE + humanBeings.getClass().getTypeName() + Colors.RESET);
        System.out.println(Colors.GREEN + "Дата инициализации:"+ Colors.RESET + " " + Colors.WHITE + dateOfInitialization + Colors.RESET);
        System.out.println(Colors.GREEN + "Количество элементов:" + Colors.RESET + " " + Colors.WHITE + humanBeings.size() + Colors.RESET);
    }

    /**
     * Gets human beings.
     *
     * @return the human beings
     */
    public static LinkedList<HumanBeing> getHumanBeings() {
        return humanBeings;
    }

    /**
     * Add.
     *
     * @param humanBeing the human being
     */
    public static void add(HumanBeing humanBeing){
        humanBeings.add(humanBeing);
    }
    public static void getFromDatabase() {
        try {
            ResultSet resultSet = Connection.executePreparedStatement("SELECT * FROM human_beings");
            while (resultSet.next()) {
                try {
                    long id = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    double x = resultSet.getDouble(3);
                    double y = resultSet.getDouble(4);
                    Date date = resultSet.getDate(5);
                    boolean realHero = resultSet.getBoolean(6);
                    boolean hasToothpick = resultSet.getBoolean(7);
                    int impactSpeed = resultSet.getInt(8);
                    String weaponTypeString = resultSet.getString(9);
                    WeaponType weaponType = WeaponType.getWeaponType(weaponTypeString.toLowerCase());
                    String moodString = resultSet.getString(10);
                    Mood mood = Mood.getMood(moodString.toLowerCase());
                    if (moodString.equals("null")) throw new IllegalArgumentException();
                    boolean cool = resultSet.getBoolean(11);
                    String user = resultSet.getString(12);
                    humanBeings.add(new HumanBeing(id, name, new Coordinates(x, y), date, realHero, hasToothpick, impactSpeed, weaponType, mood, new Car(cool), user));
                } catch (SQLException | NullPointerException | IllegalArgumentException exception) {
                    Connection.executeStatement("delete * from human_beings where id = " + resultSet.getLong(1));
                }
            }
        } catch (SQLException ignored) {}
    }
    public static void updateFromDB() {
        humanBeings.clear();
        getFromDatabase();
    }
    public static void addToDB(HumanBeing humanBeing) {
        Connection.executeStatement("insert into human_beings (id, name, x, y, creation_date, real_hero, has_toothpick, impact_speed, weapon_type, mood, car, creator) values ('" + humanBeing.getId() + "', '" + humanBeing.getName() + "', '" + humanBeing.getCoordinates().getX() + "', '" + humanBeing.getCoordinates().getY() + "', '" + humanBeing.getCreationDate().toString() + "', '" + humanBeing.getRealHero() + "', '" + humanBeing.getHasToothpick() + "', '" + humanBeing.getImpactSpeed() + "', '" + humanBeing.getWeaponType() + "', '" + humanBeing.getMood() + "', '" + humanBeing.getCar().getCool() + "', '" + humanBeing.getUser() + "')");

    }
    public static void addDB(HumanBeing humanBeing) {
        addToDB(humanBeing);
        updateFromDB();
    }

}