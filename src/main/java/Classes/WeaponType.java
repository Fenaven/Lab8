package Classes;

import org.jetbrains.annotations.Nullable;

/**
 * The enum Weapon type.
 */
public enum WeaponType {
    /**
     * Pistol weapon type.
     */
    PISTOL(1, "pistol"),
    /**
     * Knife weapon type.
     */
    KNIFE(2, "knife"),
    /**
     * Machine gun weapon type.
     */
    MACHINE_GUN(3, "machine_gun");
    private final int description;

    private final String name;
    WeaponType(int description, String name) {
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

    public static WeaponType getWeaponType(String s) {
        WeaponType[] weaponTypesValues = WeaponType.values();
        for (WeaponType weaponType : weaponTypesValues) {
            if (s.equals(weaponType.getName()) || s.equals(String.valueOf(weaponType.getDescription()))) {
                return weaponType;
            }
        }
        return null;
    }

}
