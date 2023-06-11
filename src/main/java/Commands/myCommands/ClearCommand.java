package Commands.myCommands;

import Classes.Colors;
import Classes.HumanBeingCollection;
import Commands.CommandEater;
import Commands.CommandPattern;
import Database.Authentication;
import Database.Connection;

/**
 * The type Clear command.
 */
public class ClearCommand implements CommandPattern {
    @Override
    public void execute() {
        if (CommandEater.getIsProgramRunning() && CommandEater.getSplit().length == 1) {
            Connection.executeStatement("delete from human_beings where creator = '" + Authentication.getCurrentUser() + "'");
            HumanBeingCollection.updateFromDB();
            System.out.println(Colors.BLUE + "Созданная Вами часть коллекции очищена" + Colors.RESET);
        }
        else {
            System.out.println(Colors.YELLOW + "Такое количество параметров невозможно для этой команды" + Colors.RESET);
        }
    }
}