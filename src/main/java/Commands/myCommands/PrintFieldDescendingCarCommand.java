package Commands.myCommands;

import Classes.Colors;
import Classes.HumanBeing;
import Classes.HumanBeingCollection;
import Commands.CommandEater;
import Commands.CommandPattern;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * The type Print field descending car command.
 */
public class PrintFieldDescendingCarCommand implements CommandPattern {
    @Override
    public void execute() {
        if (CommandEater.getIsProgramRunning() && CommandEater.getSplit().length == 1) {
            if (HumanBeingCollection.getHumanBeings().isEmpty()) {
                System.out.println(Colors.YELLOW + "Коллекция не содержит данных" + Colors.RESET);
            }
            else {
                ArrayList<HumanBeing> humanBeingArrayList = (ArrayList<HumanBeing>) HumanBeingCollection.getHumanBeings().stream().sorted(new HumanBeing.SortByCar().reversed()).collect(Collectors.toList());
                humanBeingArrayList.forEach(humanBeing -> System.out.println(Colors.BLACK + humanBeing.getCar().getCool() + Colors.RESET));
                humanBeingArrayList.clear();
            }
        }
        else {
            System.out.println(Colors.YELLOW + "Такое количество параметров невозможно для этой команды" + Colors.RESET);
        }
    }
}
