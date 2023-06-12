package Classes;


/**
 * The type Coordinates.
 */
public class Coordinates {
    private double x;
    private double y;

    /**
     * Sets x.
     *
     * @param x the x
     */
    public void setX(double x) {

            if (x > 357.0) {
                throw new IllegalArgumentException("Максимальное значение x: 357");
            } else {
                this.x = x;
            }
        }


    /**
     * Sets y.
     *
     * @param y the y
     */
    public void setY(double y) {
            this.y = y;

    }

    @Override
    public String toString() {
        return "Координаты: x = " + x + ", y = " + y;
    }

    /**
     * Instantiates a new Coordinates.
     *
     * @param x the x
     * @param y the y
     */
    public Coordinates(double x, double y) {
        setX(x);
        setY(y);
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return y;
    }
}
