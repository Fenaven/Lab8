package Commands;

import Classes.HumanBeing;
import Commands.myCommands.*;
import myUtilities.allForReaders.Reader;
import myUtilities.allForReaders.ReaderFromConsole;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Command eater.
 */
public class CommandEater {

    private static Boolean isProgramRunning = true;
    /**
     * The Split.
     */
    protected static String[] split;

    /**
     * The constant reader.
     */
    protected static Reader reader = new ReaderFromConsole();

    /**
     * The constant commandPatternHashMap.
     */
    protected static Map<String, CommandPattern> commandPatternHashMap = new HashMap<>();

    /**
     * Sets is program running.
     *
     * @param isProgramRunning the is program running
     */
    public static void setIsProgramRunning(Boolean isProgramRunning) {
        CommandEater.isProgramRunning = isProgramRunning;
    }

    /**
     * Gets is program running.
     *
     * @return the is program running
     */
    public static Boolean getIsProgramRunning() {
        return isProgramRunning;
    }

    /**
     * Get split string [ ].
     *
     * @return the string [ ]
     */
    public static String[] getSplit() {
        return split;
    }

    public static void setSplit(String[] split) {
        CommandEater.split = split;
    }

    /**
     * Command eat.
     */
    public static void commandEat() {
        loadCommands();
        while (getIsProgramRunning()) {
            String request = reader.getNewLine();
            if(request == null) {
                CommandEater.setReader(new ReaderFromConsole());
                break;
            }
            split = request.split(" ");
            if (split.length == 0) {
            }
            else {
                CommandPattern commandPattern = commandPatternHashMap.get(split[0]);
                if (commandPattern == null){}
                     else commandPattern.execute();
            }
        }
    }

    /**
     * loadCommands.
     */
    protected static void loadCommands() {
        commandPatternHashMap.put("help", new HelpCommand());
        commandPatternHashMap.put("exit", new ExitCommand());
        commandPatternHashMap.put("show", new ShowCommand());
        commandPatternHashMap.put("add", new AddCommand(reader));
        commandPatternHashMap.put("remove_by_id", new RemoveByIdCommand(reader));
        commandPatternHashMap.put("clear", new ClearCommand());
        commandPatternHashMap.put("sort", new SortCommand());
        commandPatternHashMap.put("info", new InfoCommand());
        commandPatternHashMap.put("insert_at", new InsertAtCommand(reader));
        commandPatternHashMap.put("print_field_ascending_impact_speed", new PrintFieldAscendingImpactSpeedCommand());
        commandPatternHashMap.put("print_field_descending_car", new PrintFieldDescendingCarCommand());
        commandPatternHashMap.put("print_unique_mood", new PrintUniqueMoodCommand());
        commandPatternHashMap.put("add_if_max", new AddIfMaxCommand(reader));
        commandPatternHashMap.put("execute_script", new ExecuteScriptCommand());
    }
    /**
     * Sets reader.
     *
     * @param reader the reader
     */
    public static void setReader(Reader reader) {
        CommandEater.reader = reader;
        loadCommands();
    }

    public static CommandPattern getCommand(String command){
        return commandPatternHashMap.get(command);
    }
}