package myUtilities.allForReaders;

import java.util.Scanner;

/**
 * The type Reader from console.
 */
public class ReaderFromConsole extends Reader {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String getNewLine(){
        return scanner.nextLine();
    }
}
