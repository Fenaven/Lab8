package Commands.myCommands;

import Classes.Colors;
import Classes.HumanBeing;
import Classes.HumanBeingCollection;
import Commands.CommandEater;
import Commands.CommandPattern;
import Database.Authentication;
import Database.Connection;
import myUtilities.allForReaders.Reader;
import validators.Errors;
import validators.commands.RemoveElementValidator;
import validators.fields.HumanForUserValidator;

/**
 * The type Remove by id command.
 */
public class RemoveByIdCommand implements CommandPattern {
    private Reader reader;

    /**
     * Instantiates a new Remove by id command.
     *
     * @param reader the reader
     */
    public RemoveByIdCommand(Reader reader) {
        this.reader = reader;
    }
    @Override
    public void execute() {
        if (CommandEater.getIsProgramRunning()) {
            if (CommandEater.getSplit().length > 2) {
                System.out.println(Colors.YELLOW + "Такое количество параметров невозможно для этой команды" + Colors.RESET);
            }
            if (CommandEater.getSplit().length < 2 || !(CommandEater.getSplit()[1].matches("^[+-]?\\d+$"))) {
                System.out.println(Colors.YELLOW + "Введён некорректный id" + Colors.RESET);
            }
            else {
                try {
                    Long id = Long.parseLong(CommandEater.getSplit()[1]);
                    Boolean isHumanBeingDelete = false;
                    if (!HumanBeingCollection.getHumanBeings().isEmpty()) {
                        for (HumanBeing humanBeing : HumanBeingCollection.getHumanBeings()) {
                            if (humanBeing.getId().equals(id) && humanBeing.getUser().equals(Authentication.getCurrentUser())) {
                                Connection.executeStatement("delete from human_beings where id = '" + humanBeing.getId() + "'");
                                HumanBeingCollection.updateFromDB();
                                isHumanBeingDelete = true;
                                System.out.println(Colors.BLUE + "Элемент с Id:" + Colors.RESET + " " + id + " " + Colors.BLUE + "удалён из коллекции" + Colors.RESET);
                            }
                        }
                        if (!isHumanBeingDelete) {
                            System.out.println(Colors.YELLOW + "Такой HumanBeing не найден в коллекции или Вы не являетесь его создателем" + Colors.RESET);
                        }
                    } else {
                        System.out.println(Colors.YELLOW + "Коллекция не содержит данных" + Colors.RESET);
                    }
                }
                catch (NumberFormatException e) {
                    System.out.println("Некорректный ввод данных (значение не принадлежит допустимому диапазону). Введите ещё раз!");
                }
            }
        }
    }
    public Errors isExecute(String argument){
        RemoveElementValidator removeElementValidator = new RemoveElementValidator(argument);
        Errors error = removeElementValidator.validateAll();
        if(error == Errors.NOTHAVEERRORS){
            Long id = Long.parseLong(argument);
            HumanBeing human = HumanBeingCollection.getHumanBeings().stream().filter(humanBeing -> humanBeing.getId() == id).toList().get(0);
            HumanForUserValidator hsev = new HumanForUserValidator(human);
            error = hsev.validateAll();
            if(error == Errors.NOTHAVEERRORS){
                Connection.executeStatement("delete from human_beings where id = '" + human.getId() + "'");
                    HumanBeingCollection.getHumanBeings().remove(human);
                }
            }
        return error;
    }
}