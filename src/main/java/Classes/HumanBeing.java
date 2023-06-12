package Classes;

import Database.Authentication;
import Database.Connection;
import validators.fields.CoordinatesValidator;
import validators.fields.ImpactSpeedValidator;
import validators.fields.NameValidator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * The type Human being.
 */
public class HumanBeing {
    private Long id;
    private String name;
    private Coordinates coordinates;
    private Date creationDate;
    private Boolean realHero;
    private Boolean hasToothpick;
    private Integer impactSpeed;
    private WeaponType weaponType = null;
    private Mood mood;
    private Car car;
    private String user;

    private final Map<Fields, Predicate<String>> notNullSetters = new LinkedHashMap<>(); // Все сеттеры, устанавливающие поля, которые не должны быть null
    private final Map<Fields, Consumer<String>> setters = new LinkedHashMap<>(); // Все сеттеры, устанавливающие поля, которые могут быть null


    {
        addNotNullSetter(Fields.NAME, this::isSetName);
        addNotNullSetter(Fields.COORDINATES, this::isSetCoordinates);
        addNotNullSetter(Fields.IMPACTSPEED, this::isSetImpactSpeed);
        addSetter(Fields.REALHERO, this::setRealHero);
        addSetter(Fields.HASTOOTHPICK, this::setHasToothpick);
        addSetter(Fields.WEAPONTYPE, this::setWeaponType);
        addSetter(Fields.MOOD, this::setMood);
        addSetter(Fields.CARCOOL, this::setCar);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<Fields, Predicate<String>> getNotNullSetters() {
        return notNullSetters;
    }

    public Map<Fields, Consumer<String>> getSetters() {
        return setters;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getUser() {
        return user;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    public HumanBeing(){
        this.creationDate = new Date();
        this.user = Authentication.getCurrentUser();
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("name не может быть null, Строка не может быть пустой");
        }
        else {
            this.name = name;
        }
    }



    /**
     * Sets coordinates.
     *
     * @param coordinates the coordinates
     */
    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("coordinates не может быть null");
        }
        else {
            this.coordinates = coordinates;
        }
    }

    /**
     * Sets real hero.
     *
     * @param realHero the real hero
     */
    public void setRealHero(Boolean realHero) {
        if (realHero == null) {
            throw new IllegalArgumentException("realHero не может быть null");
        }
        else {
            this.realHero = realHero;
        }
    }

    public void setRealHero(String realHero) {
        if(realHero.equals("1")) this.realHero = true;
        else this.realHero = Boolean.parseBoolean(realHero);
    }

    public void setHasToothpick(String hasToothpick) {
        if(hasToothpick.equals("1")) this.hasToothpick = true;
        else this.hasToothpick = Boolean.parseBoolean(hasToothpick);
    }

    public void setMood(String mood) {
        this.mood = Mood.getMood(mood);
    }
    public void setWeaponType(String weaponType) {
        this.weaponType = WeaponType.getWeaponType(weaponType);
    }


    public void setCar(String isCarCool) {
        boolean coolCar = Boolean.parseBoolean(isCarCool);
        this.car = new Car(coolCar);
    }
    /**
     * Sets has toothpick.
     *
     * @param hasToothpick the has toothpick
     */
    public void setHasToothpick(Boolean hasToothpick) {
        if (hasToothpick == null) {
            throw new IllegalArgumentException("hasToothpick не может быть null");
        }
        else {
            this.hasToothpick = hasToothpick;
        }
    }

    /**
     * Sets impact speed.
     *
     * @param impactSpeed the impact speed
     */
    public void setImpactSpeed(Integer impactSpeed) {
        if (impactSpeed == null) {
            throw new IllegalArgumentException("impactSpeed не может быть null");
        }
        else {
            this.impactSpeed = impactSpeed;
        }
    }

    /**
     * Sets weapon type.
     *
     * @param weaponType the weapon type
     */
    public void setWeaponType(WeaponType weaponType) {
        if (weaponType == null) {
            setWeaponType(WeaponType.PISTOL);
        }
        else {
            this.weaponType = weaponType;
        }
    }

    /**
     * Sets mood.
     *
     * @param mood the mood
     */
    public void setMood(Mood mood) {
        if (mood == null) {
            throw new IllegalArgumentException("mood не может быть null");
        }
        else {
            this.mood = mood;
        }
    }

    /**
     * Sets car.
     *
     * @param car the car
     */
    public void setCar(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("car не может быть null");
        }
        else {
            this.car = car;
        }
    }
    @Override
    public String toString() {
        return String.format(Colors.GREEN + "HumanBeing:" + Colors.RESET + "\n" +
                Colors.WHITE + "Id - " + Colors.RED + id + "\n" +
                Colors.WHITE + " Name - " + Colors.RED + name + "\n" +
                Colors.WHITE + " Coordinates " + Colors.RED + coordinates + "\n" +
                Colors.WHITE + " Creation date - " + Colors.RED + creationDate + "\n" +
                Colors.WHITE + " RealHero - " + Colors.RED + realHero + "\n" +
                Colors.WHITE + " HasToothpick - " + Colors.RED + hasToothpick + "\n" +
                Colors.WHITE + " ImpactSpeed = " + Colors.RED + impactSpeed + "\n" +
                Colors.WHITE + " WeaponType - " + Colors.RED + weaponType + "\n" +
                Colors.WHITE + " Mood - " + Colors.RED + mood + "\n" +
                Colors.WHITE + " Car - " + Colors.RED + car + "\n" +
                Colors.WHITE + " User - " + Colors.RED + user + Colors.RESET + "\n");
    }

    /**
     * Instantiates a new Human being.
     *
     * @param name         the name
     * @param coordinates  the coordinates
     * @param realHero     the real hero
     * @param hasToothpick the has toothpick
     * @param impactSpeed  the impact speed
     * @param weaponType   the weapon type
     * @param mood         the mood
     * @param car          the car
     */
    public HumanBeing(String name, Coordinates coordinates, Boolean realHero, Boolean hasToothpick, Integer impactSpeed, WeaponType weaponType, Mood mood, Car car) {
        setName(name);
        setCoordinates(coordinates);
        setRealHero(realHero);
        setHasToothpick(hasToothpick);
        setImpactSpeed(impactSpeed);
        setWeaponType(weaponType);
        setMood(mood);
        setCar(car);
        creationDate = new Date();
        id = setId();
        user = Authentication.getCurrentUser();
    }

    public HumanBeing(Long id, String name, Coordinates coordinates, Date creationDate, Boolean realHero, Boolean hasToothpick, Integer impactSpeed, WeaponType weaponType, Mood mood, Car car, String user) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.realHero = realHero;
        this.hasToothpick = hasToothpick;
        this.impactSpeed = impactSpeed;
        this.weaponType = weaponType;
        this.mood = mood;
        this.car = car;
        this.user = user;
    }

    private long setId() {
        long newId = 0;
        ResultSet resultSet = Connection.executePreparedStatement("SELECT nextval('id')");
        try {
            resultSet.next();
            newId = resultSet.getLong(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return newId;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets coordinates.
     *
     * @return the coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Gets real hero.
     *
     * @return the real hero
     */
    public Boolean getRealHero() {
        return realHero;
    }

    /**
     * Gets has toothpick.
     *
     * @return the has toothpick
     */
    public Boolean getHasToothpick() {
        return hasToothpick;
    }

    /**
     * Gets impact speed.
     *
     * @return the impact speed
     */
    public Integer getImpactSpeed() {
        return impactSpeed;
    }

    /**
     * Gets weapon type.
     *
     * @return the weapon type
     */
    public WeaponType getWeaponType() {
        return weaponType;
    }

    /**
     * Gets mood.
     *
     * @return the mood
     */
    public Mood getMood() {
        return mood;
    }

    /**
     * Gets car.
     *
     * @return the car
     */
    public Car getCar() {
        return car;
    }

    /**
     * The type Sort by name.
     */
    public static class SortByName implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            HumanBeing humanBeingOne = (HumanBeing) o1;
            HumanBeing humanBeingTwo = (HumanBeing) o2;
            return humanBeingOne.getName().compareTo(humanBeingTwo.getName());
        }
    }

    /**
     * The type Sort by id.
     */
    public static class SortById implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            HumanBeing humanBeingOne = (HumanBeing) o1;
            HumanBeing humanBeingTwo = (HumanBeing) o2;
            return humanBeingOne.getId().compareTo(humanBeingTwo.getId());
        }
    }

    /**

    /**
     * The type Sort by real hero.
     */
    public static class SortByRealHero implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            HumanBeing humanBeingOne = (HumanBeing) o1;
            HumanBeing humanBeingTwo = (HumanBeing) o2;
            return humanBeingOne.getRealHero().compareTo(humanBeingTwo.getRealHero());
        }
    }

    /**
     * The type Sort by has toothpick.
     */
    public static class SortByHasToothpick implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            HumanBeing humanBeingOne = (HumanBeing) o1;
            HumanBeing humanBeingTwo = (HumanBeing) o2;
            return humanBeingOne.getHasToothpick().compareTo(humanBeingTwo.getHasToothpick());
        }
    }

    public boolean isSetCoordinates(String coordinates){
        String[] coords = coordinates.split(",");
        CoordinatesValidator coordinatesValidator = new CoordinatesValidator(coords);
        if(coordinatesValidator.isValid()){
            Long x = Long.parseLong(coords[0]);
            Long y = Long.parseLong(coords[1]);
            setCoordinates(new Coordinates(x,y));
            return true;
        }
        return false;
    }

    public boolean isSetName(String name){
        NameValidator nameValidator = new NameValidator(name);
        if(nameValidator.isValid()){
            setName(name);
            return true;
        }
        return false;
    }
    public boolean isSetImpactSpeed(String impactSpeed){
        if(impactSpeed.equals("")) {
            setImpactSpeed(null);
            return true;
        }
        else{
            ImpactSpeedValidator impactSpeedValidator = new ImpactSpeedValidator(impactSpeed);
            if(impactSpeedValidator.isValid()){
                setImpactSpeed(Integer.parseInt(impactSpeed));
                return true;
            }
        }
        return false;
    }

    /**
     * The type Sort by impact speed.
     */
    public static class SortByImpactSpeed implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            HumanBeing humanBeingOne = (HumanBeing) o1;
            HumanBeing humanBeingTwo = (HumanBeing) o2;
            return humanBeingOne.getImpactSpeed().compareTo(humanBeingTwo.getImpactSpeed());
        }
    }

    /**
     * The type Sort by weapon type.
     */
    public static class SortByWeaponType implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            HumanBeing humanBeingOne = (HumanBeing) o1;
            HumanBeing humanBeingTwo = (HumanBeing) o2;
            return humanBeingOne.getWeaponType().compareTo(humanBeingTwo.getWeaponType());
        }
    }

    /**
     * The type Sort by mood.
     */
    public static class SortByMood implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            HumanBeing humanBeingOne = (HumanBeing) o1;
            HumanBeing humanBeingTwo = (HumanBeing) o2;
            return humanBeingOne.getMood().compareTo(humanBeingTwo.getMood());
        }
    }

    /**
     * The type Sort by car.
     */
    public static class SortByCar implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            HumanBeing humanBeingOne = (HumanBeing) o1;
            HumanBeing humanBeingTwo = (HumanBeing) o2;
            return humanBeingOne.getCar().getCool().compareTo(humanBeingTwo.getCar().getCool());
        }
    }
    public void addNotNullSetter(Fields field, Predicate<String> predicate){
        notNullSetters.put(field, predicate);
    }

    /**
     * Add setter.
     *
     * @param field    the field
     * @param consumer the consumer
     */
    public void addSetter(Fields field, Consumer<String> consumer){
        setters.put(field, consumer);
    }

}