package Commands.myCommands;

import Classes.Colors;
import Classes.HumanBeing;
import Classes.HumanBeingCollection;
import Commands.CommandEater;
import Commands.CommandPattern;
import myUtilities.CreatorHumanBeing;
import myUtilities.allForReaders.Reader;


/**
 * The type Insert at command.
 */
public class InsertAtCommand implements CommandPattern {
    private Reader reader;

    /**
     * Instantiates a new Insert at command.
     *
     * @param reader the reader
     */
    public InsertAtCommand(Reader reader) {
        this.reader = reader;
    }

    @Override
    public void execute() {
        if (CommandEater.getIsProgramRunning()) {
            if (CommandEater.getSplit().length > 2) {
                System.out.println(Colors.YELLOW + "Такое количество параметров невозможно для этой команды" + Colors.RESET);
            }
            else if (CommandEater.getSplit().length < 2 || !(CommandEater.getSplit()[1].matches("^[+]?\\d+$"))) {
                System.out.println(Colors.YELLOW + "Введён некорректный индекс сортировки" + Colors.RESET);
            }
            else {
                try {
                    Integer index = Integer.parseInt(CommandEater.getSplit()[1]);
                    if (index > HumanBeingCollection.getHumanBeings().size()) {
                        System.out.println(Colors.YELLOW + "Индекс превосходит максимально допустимый для данной коллекции, так как в ней содержится" + Colors.RESET + " " + HumanBeingCollection.getHumanBeings().size() + " элементов");
                    } else {
                        CreatorHumanBeing creatorHumanBeing = new CreatorHumanBeing(reader);
                        HumanBeing humanBeing = creatorHumanBeing.create();
                        HumanBeingCollection.getHumanBeings().add(index, humanBeing);
                        HumanBeingCollection.addToDB(humanBeing);
                        System.out.println(Colors.BLUE + "Новый элемент добавлен на позицию под номером:" + Colors.RESET + " " + index);
                    }
                }
                catch (NumberFormatException e) {
                    System.out.println("Некорректный ввод данных (значение не принадлежит допустимому диапазону). Введите ещё раз!");
                }
            }
        }
    }
}