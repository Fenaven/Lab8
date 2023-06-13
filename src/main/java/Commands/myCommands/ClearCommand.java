package Commands.myCommands;

import Classes.Colors;
import Classes.HumanBeingCollection;
import Commands.CommandEater;
import Commands.CommandPattern;
import Database.Authentication;
import Database.Connection;

import java.sql.ResultSet;

/**
 * The type Clear command.
 */
public class ClearCommand implements CommandPattern {
    @Override
    public void execute() {
        if (CommandEater.getIsProgramRunning() && CommandEater.getSplit().length == 1) {
            Connection.executeStatement("delete from human_beings where creator = '" + Authentication.getCurrentUser() + "'");
            ResultSet resultSet = Connection.executePreparedStatement("SELECT setval('id', 1, FALSE)"); //CAREFUL
            HumanBeingCollection.updateFromDB();
            System.out.println(Colors.BLUE + "Созданная Вами часть коллекции очищена" + Colors.RESET);
        }
        else {
            System.out.println(Colors.YELLOW + "Такое количест`во параметров невозможно для этой команды" + Colors.RESET);
        }
    }
}