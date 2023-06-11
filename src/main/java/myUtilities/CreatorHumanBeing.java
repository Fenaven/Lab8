package myUtilities;

import Classes.*;
import myUtilities.allForReaders.Reader;

/**
 * The type Creator human being.
 */
public class CreatorHumanBeing {
    private Reader reader;

    /**
     * Instantiates a new Creator human being.
     *
     * @param reader the reader
     */
    public CreatorHumanBeing(Reader reader) {
        this.reader = reader;
    }

    /**
     * Create human being.
     *
     * @return the human being
     */
    public HumanBeing create() {
        System.out.println("Введите значение имени");
        String possibleField = reader.getNewLine();
        while(possibleField.equals("") || possibleField == "") {
            System.out.println("name не может быть null, Строка не может быть пустой");
            possibleField = reader.getNewLine();
        }
        String name = possibleField;
        System.out.println("Введите значение координаты x");
        boolean condition = true;
        while(condition) {
            try {
                if (!((possibleField = reader.getNewLine()).matches("^[+-]?\\d+$")) || Long.parseLong(possibleField) > 357) {
                    System.out.println("Неверный ввод данных, координата x должна быть целым числом. Максимальное значение x = 357");
                }
                else {
                    condition = false;
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Некорректный ввод данных (значение не принадлежит допустимому диапазону). Введите ещё раз!");
            }
        }
        Long x = Long.parseLong(possibleField);
        condition = true;
        System.out.println("Введите значение координаты y");
        while(condition) {
            if (!((possibleField = reader.getNewLine()).matches("^[+-]?\\d+$"))) {
                System.out.println("Неверный ввод данных, координата y должна быть целым числом");
            }
            else {
                condition = false;
            }
            try {
                Long.parseLong(possibleField);
            }
            catch (NumberFormatException e) {
                condition = true;
                System.out.println("Некорректный ввод данных (значение не принадлежит допустимому диапазону). Введите ещё раз!");
            }
        }
        Long y = Long.parseLong(possibleField);
        condition = true;
        System.out.println("Является ли он настоящим героем? Введите true, если да. При любом другом вводе - false");
        Boolean realHero = Boolean.parseBoolean(reader.getNewLine());
        System.out.println("Есть ли у него зубочистка? Введите true, если да. При любом другом вводе - false");
        Boolean hasToothpick = Boolean.parseBoolean(reader.getNewLine());
        System.out.println("Введите значение скорости");
        while(condition) {
            try {
                if (!((possibleField = reader.getNewLine()).matches("^[+-]?\\d+$"))) {
                    System.out.println("Неверный ввод данных, скорость должна быть целым числом");
                }
                else {
                    condition = false;
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Некорректный ввод данных (значение не принадлежит допустимому диапазону). Введите ещё раз!");
            }
        }
        //добавить исключения
        Integer speed = Integer.parseInt(possibleField);
        System.out.println("Введите значение WeaponType");
        System.out.println("1 или pistol для PISTOL, 2 или KNIFE для KNIFE, 3 или machine_gun для MACHINE_GUN");
        String[] validInputsWT = {"1", "2", "3", "pistol", "knife", "machine_gun"};
        boolean isValidWT = false;
        while(!(isValidWT)) {
            possibleField = reader.getNewLine().toLowerCase();
            for (String validInput : validInputsWT) {
                if (possibleField.equals(validInput)) {
                    isValidWT = true;
                    break;
                }
            }
            if (!(isValidWT)) {
                System.out.println("Введенная строка не является допустимой.");
            }
            possibleField = possibleField.toLowerCase();
            switch (possibleField) {
                case "1", "pistol": possibleField = "1"; break;
                case "2", "knife": possibleField = "2"; break;
                case "3", "machine_gun": possibleField = "3"; break;
                default: break;
            }
        }
        Integer intWeaponType = Integer.parseInt(possibleField);
        WeaponType weaponType = WeaponType.getWeaponType(intWeaponType);
        System.out.println("Введите значение Mood");
        System.out.println("1 или sadness для SADNESS, 2 или sorrow для SORROW, 3 или longing для LONGING");
        String[] validInputsM = {"1", "2", "3", "sorrow", "sadness", "longing"};
        boolean isValidM = false;
        while(!(isValidM)) {
            possibleField = reader.getNewLine().toLowerCase();
            for (String validInput : validInputsM) {
                if (possibleField.equals(validInput)) {
                    isValidM = true;
                    break;
                }
            }
            if (!(isValidM)) {
                System.out.println("Введенная строка не является допустимой.");
            }
            possibleField = possibleField.toLowerCase();
            switch (possibleField) {
                case "1", "sadness": possibleField = "1"; break;
                case "2", "sorrow": possibleField = "2"; break;
                case "3", "longing": possibleField = "3"; break;
                default: break;
            }
        }
        Integer intMood = Integer.parseInt(possibleField);
        Mood mood = Mood.getMood(intMood);
        System.out.println("Крутая ли у него машина. Введите true, если да. При любом другом вводе - false");
        Boolean cool = Boolean.parseBoolean(reader.getNewLine());
        return (new HumanBeing(name, new Coordinates(x, y), realHero, hasToothpick, speed, weaponType, mood, new Car(cool)));
    }
}