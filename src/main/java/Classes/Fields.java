package Classes;

import validators.Errors;

/**
 * Enum containing all fields of the HumanBeing class with 3 fields.
 * * 1 - name
 * * 2 - comment
 * * 3 - queue in order (order)
 */
public enum Fields {

    /**
     * Name fields.
     */
    NAME("имя", "не может быть null", 1),
    /**
     * Coordinates fields.
     */
    COORDINATES("координаты", "введите через запятую coordinate.x,coordinate.y (поля float и Integer соответственно, " +
            "поля не могут быть null, поле coordinate.x не должно превышать значение " + Fields.MAXIMUM_X + ")", 2),
    /**
     * Impactspeed fields.
     */
    IMPACTSPEED("скорость удара", "может быть null, не должно превышать значение " + Fields.MAXIMUM_IMPACT_SPEED, 3),
    /**
     * Realhero fields.
     */
    REALHERO("настоящий герой", "по умолчанию false, при вводе 1 или true - поле становится true", 4),
    /**
     * Hastoothpick fields.
     */
    HASTOOTHPICK("ковыряется в зубах", "по умолчанию false, при вводе 1 или true - поле становится true", 5),
    /**
     * Weapontype fields.
     */
    WEAPONTYPE("тип оружия", "может быть "  + ", может быть null", 6),
    /**
     * Mood fields.
     */
    MOOD("муд", "может быть " + ", может быть null", 7),
    /**
     * Carcool fields.
     */
    CARCOOL("наличие крутой машины", "по умолчанию false, при вводе 1 или true - поле становится true", 8);


    /**
     * The constant MAXIMUM_X.
     */
    public final static float MAXIMUM_X = -809f;
    /**
     * The constant MAXIMUM_IMPACT_SPEED.
     */
    public final static int MAXIMUM_IMPACT_SPEED = 496;
    /**
     * The Name.
     */
    final String name, /**
     * Comments fields.
     */
    comments;
    /**
     * The Order.
     */
    final int order;

    Fields(String name, String comments, int order) {
        this.name = name;
        this.comments = comments;
        this.order = order;
    }

    /**
     * Method that returns by field order, Field @param order the order
     *
     * @param order the order in fields
     * @return the fields
     */
    public static Fields getForOrder(int order){
        for(Fields field : Fields.values()){
            if(field.order == order) return  field;
        }
        System.out.println(Errors.NOTHASFIELD);
        return null;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        return "Введите значение поля " + name + " : " + comments;
    }
}
