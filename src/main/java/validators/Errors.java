package validators;


import Languages.CurrentLanguage;

/**
 * The enum Errors.
 */
public enum Errors {
    /**
     * Nothaveenvironment errors.
     */
    NOTHAVEENVIRONMENT("При запуске программы переменная окружения не была передана," +
            " запустите программу заново, передав переменную окружения"),
    /**
     * Wrongenvironment errors.
     */
    WRONGENVIRONMENT("При запуске программы переменная окружения не имеет никакого значения, " +
            "либо было передано пустое значение переменной окружения, запустите программу заново с указанием переменной окружения"),
    /**
     * Emptyenvironment errors.
     */
    EMPTYENVIRONMENT("Данная переменная окружения имеет пустое значение, " +
            "задайте правильное расположение файла в переменной и запустите программу заново с указанием переменной окружения"),
    /**
     * Notexistfile errors.
     */
    NOTEXISTFILE("not exist file"),
    /**
     * Notcanreadfile errors.
     */
    NOTCANREADFILE("not can read file"),
    /**
     * Notcanwritefile errors.
     */
    NOTCANWRITEFILE("not can write file"),
    /**
     * Notcsvfile errors.
     */
    NOTCSVFILE("Данный файл не является csv файлом, передайте в переменную окружения csv-файл"),
    /**
     * Nothaveerrors errors.
     */
    NOTHAVEERRORS("notHaveErrors"),
    /**
     * Nothasargument errors.
     */
    NOTHASARGUMENT("Данная команда принимает на вход 1 аргумент"),
    /**
     * Notcantransformtouuid errors.
     */
    NOTCANTRANSFORMTOUUID("Невозможно преобразовать данный ключ к типу UUID"),
    /**
     * Nothaselement errors.
     */
    NOTHASELEMENT("notHasElement"),
    /**
     * Notcantransformtoint errors.
     */
    NOTCANTRANSFORMTOINT("notInt"),
    /**
     * Emptyfield errors.
     */
    EMPTYFIELD("emptyField"),
    /**
     * Biggerx errors.
     */
    BIGGERX("biggerX"),
    /**
     * Nothasfield errors.
     */
    NOTHASFIELD("notHasField"),
    /**
     * Impossiblereadfile errors.
     */
    IMPOSSIBLEREADFILE("impossible read file"),
    /**
     * Impossiblewritetofile errors.
     */
    IMPOSSIBLEWRITETOFILE("Невозможно записать данные в файл"),
    /**
     * Manyrecursions errors.
     */
    RECURSION("recursion"),
    /**
     * Nothastwocoordinates errors.
     */
    NOTHASTWOCOORDINATES("notHasTwoCoords"),

    /**
     * Isnotexistentoption errors.
     */
    ISNOTEXISTENTOPTION("notExistOption"),

    /**
     * Ishavespace errors.
     */
    ISHAVESPACE("notSpace"),

    /**
     * Notcreatethisuser errors.
     */
    NOTCREATETHISUSER("notCreateThisUser"),
    /**
     * Usedid errors.
     */
    USEDLOGIN("usedLogin"),

    UNKNOWNERROR("unknownError"),

    USEDID("existId");
    /**
     * The Error.
     */
    final String error;
    Errors(String error){
        this.error = error;
    }

    public String getError() {
        return CurrentLanguage.getCurrentLanguage().getString(getNameError());
    }

    public String getNameError(){
        return error;
    }

    @Override
    public String toString() {
        return error;
    }
}
