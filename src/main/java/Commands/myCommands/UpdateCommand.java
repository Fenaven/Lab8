package Commands.myCommands;

import Classes.*;
import Commands.CommandEater;
import Commands.CommandPattern;
import Database.Authentication;
import Database.Connection;


/**
 * The type Update command.
 */
public class UpdateCommand implements CommandPattern {
    @Override
    public void execute() {
        if (CommandEater.getIsProgramRunning()) {
            if (!(CommandEater.getSplit().length == 4)) {
                System.out.println(Colors.YELLOW + "Такое количество параметров невозможно для этой команды" + Colors.RESET);
            }
            else {
                if (!(CommandEater.getSplit()[1].matches("^[+]?\\d+$"))) {
                    System.out.println(Colors.YELLOW + "Введён некорректный id" + Colors.RESET);
                }
                if (!(CommandEater.getSplit()[2].matches("^[+]?\\d+$"))) {
                    System.out.println(Colors.YELLOW + "Введён некорректный параметр" + Colors.RESET);
                } else {
                    try {
                        Long id = Long.parseLong(CommandEater.getSplit()[1]);
                        Integer parameter = Integer.parseInt(CommandEater.getSplit()[2]);
                        if (parameter > 10 || parameter < 1) {
                            System.out.println(Colors.YELLOW + "Введён некорректный параметр сортировки" + Colors.RESET);
                        }
                        else {
                            String newParameter = CommandEater.getSplit()[3];
                            if (!HumanBeingCollection.getHumanBeings().isEmpty()) {
                                for (HumanBeing humanBeing : HumanBeingCollection.getHumanBeings()) {
                                    if (humanBeing.getId().equals(id)) {
                                        if (parameter.equals(1)) {
                                            System.out.println(Colors.YELLOW + "Невозможно изменить id элемента" + Colors.RESET);
                                        }
                                        if (parameter.equals(2)) {
                                            Connection.executeStatement("update human_beings set name = '" + newParameter + "' where id = '" + humanBeing.getId() + "' and creator = '" + Authentication.getCurrentUser() + "'");
                                            HumanBeingCollection.updateFromDB();
                                        }
                                        if (parameter.equals(3)) {
                                            if (newParameter.matches("^[+-]?\\d+$")) {
                                                Connection.executeStatement("update human_beings set x = '" + Long.valueOf(newParameter) + "' where id = '" + humanBeing.getId() + "' and creator = '" + Authentication.getCurrentUser() + "'");
                                                HumanBeingCollection.updateFromDB();
                                            } else {
                                                System.out.println(Colors.YELLOW + "Некорректный новый параметр" + Colors.RESET);
                                            }
                                        }
                                        if (parameter.equals(4)) {
                                            if (newParameter.matches("^[+-]?\\d+$")) {
                                                Connection.executeStatement("update human_beings set y = '" + Long.valueOf(newParameter) + "' where id = '" + humanBeing.getId() + "' and creator = '" + Authentication.getCurrentUser() + "'");
                                                HumanBeingCollection.updateFromDB();
                                            } else {
                                                System.out.println(Colors.YELLOW + "Некорректный новый параметр" + Colors.RESET);
                                            }
                                        }
                                        if (parameter.equals(5)) {
                                            Connection.executeStatement("update human_beings set real_hero = '" + Boolean.valueOf(newParameter) + "' where id = '" + humanBeing.getId() + "' and creator = '" + Authentication.getCurrentUser() + "'");
                                            HumanBeingCollection.updateFromDB();
                                        }
                                        if (parameter.equals(6)) {
                                            Connection.executeStatement("update human_beings set has_toothpick = '" + Boolean.valueOf(newParameter) + "' where id = '" + humanBeing.getId() + "' and creator = '" + Authentication.getCurrentUser() + "'");
                                            HumanBeingCollection.updateFromDB();
                                        }
                                        if (parameter.equals(7)) {
                                            if (newParameter.matches("^[+-]?\\d+$")) {
                                                Connection.executeStatement("update human_beings set impact_speed = '" + Integer.valueOf(newParameter) + "' where id = '" + humanBeing.getId() + "' and creator = '" + Authentication.getCurrentUser() + "'");
                                                HumanBeingCollection.updateFromDB();
                                            } else {
                                                System.out.println(Colors.YELLOW + "Некорректный новый параметр" + Colors.RESET);
                                            }
                                        }
                                        if (parameter.equals(8)) {
                                            String str = newParameter.toUpperCase();
                                            if (str.equals("PISTOL") || str.equals("KNIFE") || str.equals("MACHINE_GUN")) {
                                                Connection.executeStatement("update human_beings set weapon_type = '" + WeaponType.valueOf(str) + "' where id = '" + humanBeing.getId() + "' and creator = '" + Authentication.getCurrentUser() + "'");
                                            } else if (newParameter.equals("1")) {
                                                Connection.executeStatement("update human_beings set weapon_type = '" + WeaponType.PISTOL + "' where id = '" + humanBeing.getId() + "' and creator = '" + Authentication.getCurrentUser() + "'");
                                            } else if (newParameter.equals("2")) {
                                                Connection.executeStatement("update human_beings set weapon_type = '" + WeaponType.KNIFE + "' where id = '" + humanBeing.getId() + "' and creator = '" + Authentication.getCurrentUser() + "'");
                                            } else if (newParameter.equals("3")) {
                                                Connection.executeStatement("update human_beings set weapon_type = '" + WeaponType.MACHINE_GUN + "' where id = '" + humanBeing.getId() + "' and creator = '" + Authentication.getCurrentUser() + "'");
                                            } else {
                                                System.out.println(Colors.YELLOW + "Некорректный новый параметр" + Colors.RESET);
                                            }
                                            HumanBeingCollection.updateFromDB();
                                        }
                                        if (parameter.equals(9)) {
                                            String str = newParameter.toUpperCase();
                                            if (str.equals("SORROW") || str.equalsIgnoreCase("SADNESS") || str.equals("LONGING")) {
                                                Connection.executeStatement("update human_beings set mood = '" + Mood.valueOf(str) + "' where id = '" + humanBeing.getId() + "' and creator = '" + Authentication.getCurrentUser() + "'");
                                            } else if (newParameter.equals("1")) {
                                                Connection.executeStatement("update human_beings set mood = '" + Mood.SADNESS + "' where id = '" + humanBeing.getId() + "' and creator = '" + Authentication.getCurrentUser() + "'");
                                            } else if (newParameter.equals("2")) {
                                                Connection.executeStatement("update human_beings set mood = '" + Mood.SORROW + "' where id = '" + humanBeing.getId() + "' and creator = '" + Authentication.getCurrentUser() + "'");
                                            } else if (newParameter.equals("3")) {
                                                Connection.executeStatement("update human_beings set mood = '" + Mood.LONGING + "' where id = '" + humanBeing.getId() + "' and creator = '" + Authentication.getCurrentUser() + "'");
                                            } else {
                                                System.out.println(Colors.YELLOW + "Некорректный новый параметр" + Colors.RESET);
                                            }
                                            HumanBeingCollection.updateFromDB();
                                        }
                                        if (parameter.equals(10)) {
                                            Connection.executeStatement("update human_beings set car = '" + Boolean.valueOf(newParameter) + "' where id = '" + humanBeing.getId() + "' and creator = '" + Authentication.getCurrentUser() + "'");
                                            HumanBeingCollection.updateFromDB();
                                        }
                                    }
                                }
                            }
                        }
                    }
                    catch (NumberFormatException e) {
                        System.out.println("Некорректный ввод данных (значение не принадлежит допустимому диапазону). Введите ещё раз!");
                    }
                }
            }
        }
    }
}