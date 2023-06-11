package Commands.myCommands;

import Classes.Colors;
import Classes.HumanBeing;
import Classes.HumanBeingCollection;
import Commands.CommandEater;
import Commands.CommandPattern;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * The type Print field ascending impact speed command.
 */
public class PrintFieldAscendingImpactSpeedCommand implements CommandPattern {
    @Override
    public void execute() {
        if (CommandEater.getIsProgramRunning() && CommandEater.getSplit().length == 1) {
            if (HumanBeingCollection.getHumanBeings().isEmpty()) {
                System.out.println(Colors.YELLOW + "Коллекция не содержит данных" + Colors.RESET);
            }
            else {
                ArrayList<HumanBeing> humanBeingArrayList = (ArrayList<HumanBeing>) HumanBeingCollection.getHumanBeings().stream().sorted(new HumanBeing.SortByImpactSpeed()).collect(Collectors.toList());
                humanBeingArrayList.stream().map(HumanBeing::getImpactSpeed).forEach(speed -> System.out.println(Colors.BLACK + speed + Colors.RESET));
                humanBeingArrayList.clear();
            }
        }
        else {
            System.out.println(Colors.YELLOW + "Такое количество параметров невозможно для этой команды" + Colors.RESET);
        }
    }
}
