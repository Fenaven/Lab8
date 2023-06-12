import App.MainApplication;
import Classes.Colors;
import Classes.HumanBeingCollection;
import Commands.CommandEater;
import Commands.myCommands.AddCommand;
import Database.Authentication;
import myUtilities.allForReaders.ReaderFromConsole;

import java.util.NoSuchElementException;

/**
 * The type Main.
 */
public class Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        try {

            HumanBeingCollection.getFromDatabase();
            MainApplication.open();
        } catch (NoSuchElementException noSuchElementException) {
            System.out.println(Colors.YELLOW + "Неверный ввод. Продолжение работы программы невозможно" + Colors.RESET);
        }
    }
}