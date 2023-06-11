package Commands.myCommands;

import Classes.Colors;
import Classes.HumanBeing;
import Classes.HumanBeingCollection;
import Commands.CommandEater;
import Commands.CommandPattern;


/**
 * The type Sort command.
 */
public class SortCommand implements CommandPattern {
    @Override
    public void execute() {
        if (CommandEater.getIsProgramRunning()) {
            if (CommandEater.getSplit().length > 2) {
                System.out.println(Colors.YELLOW + "Такое количество параметров невозможно для этой команды" + Colors.RESET);
            }
            else if (CommandEater.getSplit().length < 2 || !(CommandEater.getSplit()[1].matches("^[+]?\\d+$"))) {
                System.out.println(Colors.YELLOW + "Введён некорректный параметр сортировки" + Colors.RESET);
            }
            else if (HumanBeingCollection.getHumanBeings().isEmpty()) {
                System.out.println(Colors.YELLOW + "Коллекция не содержит данных" + Colors.RESET);
            }
            else {
                Integer parameter = Integer.parseInt(CommandEater.getSplit()[1]);
                if (parameter > 10 || parameter < 1) {
                    System.out.println(Colors.YELLOW + "Введён некорректный параметр сортировки" + Colors.RESET);;
                }
                else {
                    System.out.println(Colors.BLUE + "Отсортированная коллекция" + Colors.RESET);
                    if (parameter.equals(1)) {
                        HumanBeingCollection.getHumanBeings().stream().sorted(new HumanBeing.SortById()).forEach(System.out::println);
                    }
                    if (parameter.equals(2)) {
                        HumanBeingCollection.getHumanBeings().stream().sorted(new HumanBeing.SortByName()).forEach(System.out::println);
                    }
                    if (parameter.equals(3)) {
                        HumanBeingCollection.getHumanBeings().stream().sorted(new HumanBeing.SortByX_Coordinate()).forEach(System.out::println);
                    }
                    if (parameter.equals(4)) {
                        HumanBeingCollection.getHumanBeings().stream().sorted(new HumanBeing.SortByY_Coordinate()).forEach(System.out::println);
                    }
                    if (parameter.equals(5)) {
                        HumanBeingCollection.getHumanBeings().stream().sorted(new HumanBeing.SortByRealHero()).forEach(System.out::println);
                    }
                    if (parameter.equals(6)) {
                        HumanBeingCollection.getHumanBeings().stream().sorted(new HumanBeing.SortByHasToothpick()).forEach(System.out::println);
                    }
                    if (parameter.equals(7)) {
                        HumanBeingCollection.getHumanBeings().stream().sorted(new HumanBeing.SortByImpactSpeed()).forEach(System.out::println);
                    }
                    if (parameter.equals(8)) {
                        HumanBeingCollection.getHumanBeings().stream().sorted(new HumanBeing.SortByWeaponType()).forEach(System.out::println);
                    }
                    if (parameter.equals(9)) {
                        HumanBeingCollection.getHumanBeings().stream().sorted(new HumanBeing.SortByWeaponType()).forEach(System.out::println);
                    }
                    if (parameter.equals(10)) {
                        HumanBeingCollection.getHumanBeings().stream().sorted(new HumanBeing.SortByCar()).forEach(System.out::println);
                    }
                }
            }
        }
    }
}
