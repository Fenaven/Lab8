package Classes;

/**
 * The type Car.
 */
public class Car {
    private Boolean cool = null;

    /**
     * Sets cool.
     *
     * @param cool the cool
     */
    public void setCool(Boolean cool) {
        this.cool = cool;
    }
    @Override
    public String toString() {
        return String.valueOf(cool);
    }

    /**
     * Instantiates a new Car.
     *
     * @param cool the cool
     */
    public Car(Boolean cool) {
        setCool(cool);
    }

    /**
     * Gets cool.
     *
     * @return the cool
     */
    public Boolean getCool() {
        return cool;
    }
}
