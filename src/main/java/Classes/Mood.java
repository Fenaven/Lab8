package Classes;

/**
 * The enum Mood.
 */
public enum Mood {
    /**
     * Sadness mood.
     */
    SADNESS(1),
    /**
     * Sorrow mood.
     */
    SORROW(2),
    /**
     * Longing mood.
     */
    LONGING(3);
    private final int description;
    Mood(int description) {
        this.description = description;
    }

    /**
     * Gets mood.
     *
     * @param description the description
     * @return the mood
     */
    public static Mood getMood(int description) {
        if (description == 1) {
            return Mood.SADNESS;
        }
        if (description == 2) {
            return Mood.SORROW;
        }
        if (description == 3) {
            return Mood.LONGING;
        }
        return null;
    }
}
