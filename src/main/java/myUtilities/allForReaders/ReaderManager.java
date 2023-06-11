package myUtilities.allForReaders;

import Classes.Colors;

/**
 * The type Reader manager.
 */
public class ReaderManager extends Reader {
    private Reader reader;
    private NameOfReader nameOfReader;

    /**
     * Instantiates a new Reader manager.
     *
     * @param reader     the reader
     * @param nameOfReader the name reader
     */
    public ReaderManager(Reader reader, NameOfReader nameOfReader) {
        this.reader = reader;
        this.nameOfReader = nameOfReader;
    }

    @Override
    public String getNewLine(){
        String request = reader.getNewLine();
        if (request == null){
            System.out.println(Colors.YELLOW + "В файле закончились строки. Продолжите ввод данных с консоли" + Colors.RESET);
            setReader(new ReaderFromConsole());
            setNameReader(NameOfReader.CONSOLEREADER);
            request = reader.getNewLine();
        }
        return request;
    }

    /**
     * Gets reader.
     *
     * @return the reader
     */
    public Reader getReader() {
        return reader;
    }

    /**
     * Sets reader.
     *
     * @param reader the reader
     */
    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public NameOfReader getNameOfReader() {
        return nameOfReader;
    }

    /**
     * Sets name reader.
     *
     * @param nameOfReader the name reader
     */
    public void setNameReader(NameOfReader nameOfReader) {
        this.nameOfReader = nameOfReader;
    }
}
