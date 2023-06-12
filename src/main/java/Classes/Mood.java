package Classes;

/**
 * The enum Mood.
 */
public enum Mood {
    /**
     * Sadness mood.
     */
    SADNESS(1, "sadness"),
    /**
     * Sorrow mood.
     */
    SORROW(2, "sorrow"),
    /**
     * Longing mood.
     */
    LONGING(3, "longing");
    private final int description;

    private final String name;
    Mood(int description, String name) {
        this.description = description;
        this.name = name;
    }

    public int getDescription() {
        return description;
    }

    public String getName() {
        return name;
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

    public static Mood getMood(String s) throws  IllegalArgumentException{
        Mood[] moodValues = Mood.values();
        for (Mood mood : moodValues) {
            if (s.equals(mood.getName()) || s.equals(String.valueOf(mood.getDescription()))) {
                return mood;
            }
        }
        return null;
    }

}
