package Classes;

/**
 * The enum Weapon type.
 */
public enum WeaponType {
    /**
     * Pistol weapon type.
     */
    PISTOL(1),
    /**
     * Knife weapon type.
     */
    KNIFE(2),
    /**
     * Machine gun weapon type.
     */
    MACHINE_GUN(3);
    private final int description;
    WeaponType(int description) {
        this.description = description;
    }

    /**
     * Gets weapon type.
     *
     * @param description the description
     * @return the weapon type
     */
    public static WeaponType getWeaponType(int description) {
        if (description == 1) {
            return WeaponType.PISTOL;
        }
        if (description == 2) {
            return WeaponType.KNIFE;
        }
        if (description == 3) {
            return WeaponType.MACHINE_GUN;
        }
        return null;
    }
}
