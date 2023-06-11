package Commands.myCommands;

import Classes.Colors;
import Classes.HumanBeing;
import Classes.HumanBeingCollection;
import Classes.Mood;
import Commands.CommandEater;
import Commands.CommandPattern;

import java.util.HashSet;
import java.util.Set;

/**
 * The type Print unique mood command.
 */
public class PrintUniqueMoodCommand implements CommandPattern {
    @Override
    public void execute() {
        if (CommandEater.getIsProgramRunning() && CommandEater.getSplit().length == 1) {
            Set<Mood> set = new HashSet();
            if (HumanBeingCollection.getHumanBeings().isEmpty()) {
                System.out.println(Colors.YELLOW + "Коллекция не содержит данных" + Colors.RESET);
            }
            else {
                HumanBeingCollection.getHumanBeings().stream().map(HumanBeing::getMood).forEach(set::add);
                set.stream().forEach(mood -> System.out.println(Colors.BLACK + mood + Colors.RESET));
                set.clear();
            }
        }
        else {
            System.out.println(Colors.YELLOW + "Такое количество параметров невозможно для этой команды" + Colors.RESET);
        }
    }
}
