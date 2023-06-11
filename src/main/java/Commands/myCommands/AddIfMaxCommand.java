package Commands.myCommands;

import Classes.Colors;
import Classes.HumanBeing;
import Classes.HumanBeingCollection;
import Commands.CommandEater;
import Commands.CommandPattern;
import myUtilities.CreatorHumanBeing;
import myUtilities.allForReaders.Reader;

import java.util.LinkedList;

/**
 * The type Add if max command.
 */
public class AddIfMaxCommand implements CommandPattern {
    private Reader reader;

    /**
     * Instantiates a new Add if max command.
     *
     * @param reader the reader
     */
    public AddIfMaxCommand(Reader reader) {
        this.reader = reader;
    }
    @Override
    public void execute() {
        if (CommandEater.getIsProgramRunning()) {
            if (CommandEater.getSplit().length > 2) {
                System.out.println(Colors.YELLOW + "Такое количество параметров невозможно для этой команды" + Colors.RESET);
            }
            if (CommandEater.getSplit().length < 2 || !(CommandEater.getSplit()[1].matches("^[+]?\\d+$"))) {
                System.out.println(Colors.YELLOW + "Введён некорректный параметр для сравнения" + Colors.RESET);
            }
            else {
                Integer parameter = Integer.parseInt(CommandEater.getSplit()[1]);
                if (parameter > 10 || parameter < 1) {
                    System.out.println(Colors.YELLOW + "Введён некорректный параметр сортировки" + Colors.RESET);;
                }
                else {
                    CreatorHumanBeing creatorHumanBeing = new CreatorHumanBeing(reader);
                    HumanBeing humanBeing = creatorHumanBeing.create();
                    System.out.println(Colors.BLACK + "Элемент добавлен в коллекцию для сравнения" + Colors.RESET);
                    LinkedList<HumanBeing> humanBeingLinkedList = new LinkedList<>(HumanBeingCollection.getHumanBeings());
                    if (humanBeingLinkedList.isEmpty()) {
                        HumanBeingCollection.addDB(humanBeing);
                        System.out.println(Colors.BLUE + "Элемент добавлен в основную коллекцию" + Colors.RESET);
                    } else {
                        if (parameter.equals(1)) {
                            if (humanBeingLinkedList.stream().filter(human -> human.getId() > humanBeing.getId()).toArray().length == 0) {
                                HumanBeingCollection.addDB(humanBeing);
                            }
                        }
                        if (parameter.equals(2)) {
                            if (humanBeingLinkedList.stream().filter(human -> human.getName().compareTo(humanBeing.getName()) < 0).toArray().length == 0) {
                                HumanBeingCollection.addDB(humanBeing);
                            }
                        }
                        if (parameter.equals(3)) {
                            if (humanBeingLinkedList.stream().filter(human -> human.getCoordinates().getX() > humanBeing.getCoordinates().getX()).toArray().length == 0) {
                                HumanBeingCollection.addDB(humanBeing);
                            }
                        }
                        if (parameter.equals(4)) {
                            if (humanBeingLinkedList.stream().filter(human -> human.getCoordinates().getY() > humanBeing.getCoordinates().getY()).toArray().length == 0) {
                                HumanBeingCollection.addDB(humanBeing);
                            }
                        }
                        if (parameter.equals(5)) {
                            if (humanBeingLinkedList.stream().filter(human -> human.getRealHero().compareTo(humanBeing.getRealHero()) >= 0).toArray().length == 0) {
                                HumanBeingCollection.addDB(humanBeing);
                            }
                        }
                        if (parameter.equals(6)) {
                            if (humanBeingLinkedList.stream().filter(human -> human.getHasToothpick().compareTo(humanBeing.getHasToothpick()) >= 0).toArray().length == 0) {
                                HumanBeingCollection.addDB(humanBeing);
                            }
                        }
                        if (parameter.equals(7)) {
                            if (humanBeingLinkedList.stream().filter(human -> human.getImpactSpeed() > humanBeing.getImpactSpeed()).toArray().length == 0) {
                                HumanBeingCollection.addDB(humanBeing);
                            }
                        }
                        if (parameter.equals(8)) {
                            if (humanBeingLinkedList.stream().filter(human -> human.getWeaponType().compareTo(humanBeing.getWeaponType()) >= 0).toArray().length == 0) {
                                HumanBeingCollection.addDB(humanBeing);
                            }
                        }
                        if (parameter.equals(9)) {
                            if (humanBeingLinkedList.stream().filter(human -> human.getMood().compareTo(humanBeing.getMood()) >= 0).toArray().length == 0) {
                                HumanBeingCollection.addDB(humanBeing);
                            }
                        }
                        if (parameter.equals(10)) {
                            if (humanBeingLinkedList.stream().filter(human -> human.getCar().getCool().compareTo(humanBeing.getCar().getCool()) >= 0).toArray().length == 0) {
                                HumanBeingCollection.addDB(humanBeing);
                            }
                        }
                    }
                    humanBeingLinkedList.clear();
                }
            }
        }
    }
}