package Classes;


/**
 * The type Coordinates.
 */
public class Coordinates {
    private Long x;
    private Long y;

    /**
     * Sets x.
     *
     * @param x the x
     */
    public void setX(Long x) {
        if (x == null) {
            throw new IllegalArgumentException("Координата по x не может быть null");
        }
        else {
            if (x > 357) {
                throw new IllegalArgumentException("Максимальное значение x: 357");
            } else {
                this.x = x;
            }
        }
    }

    /**
     * Sets y.
     *
     * @param y the y
     */
    public void setY(Long y) {
        if (y == null) {
            throw new IllegalArgumentException("Координата по y не может быть null");
        }
        else {
            this.y = y;
        }
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
    public Coordinates(Long x, Long y) {
        setX(x);
        setY(y);
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public Long getX() {
        return x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public Long getY() {
        return y;
    }
}
