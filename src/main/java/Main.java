import App.MainApplication;
import Classes.Colors;
import Classes.HumanBeingCollection;
import Commands.CommandEater;
import Database.Authentication;

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
            MainApplication.open();
            /*Authentication.userAuthentication();
            if (Authentication.getCurrentUser() != null) {
                HumanBeingCollection.getFromDatabase();
                System.out.println("Из базы данных добавлено объектов в коллекцию: " + HumanBeingCollection.getHumanBeings().size());
                CommandEater.commandEat();
            } else {
                System.out.println("Выполнение команд неавторизованными пользователями запрещено, работа программы остановлена");
            }*/
        } catch (NoSuchElementException noSuchElementException) {
            System.out.println(Colors.YELLOW + "Неверный ввод. Продолжение работы программы невозможно" + Colors.RESET);
        }
    }
}