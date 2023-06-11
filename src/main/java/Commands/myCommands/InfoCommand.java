package Commands.myCommands;

import Classes.Colors;
import Classes.HumanBeingCollection;
import Commands.CommandEater;
import Commands.CommandPattern;

/**
 * The type Info command.
 */
public class InfoCommand implements CommandPattern {
    @Override
    public void execute() {
        if (CommandEater.getIsProgramRunning() && CommandEater.getSplit().length == 1) {
            HumanBeingCollection.getInfo();
        }
        else {
            System.out.println(Colors.YELLOW + "Такое количество параметров невозможно для этой команды" + Colors.RESET);
        }
    }
}
