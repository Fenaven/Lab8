package myUtilities.allForReaders;

/**
 * The type Reader.
 */
public abstract class Reader {

    private NameOfReader nameOfReader;

    /**
     * Gets new line.
     *
     * @return the new line
     */
    public abstract String getNewLine();

    /**
     * Gets name reader.
     *
     * @return the name reader
     */
    public NameOfReader getNameOfReader() {
        return nameOfReader;
    }
}
