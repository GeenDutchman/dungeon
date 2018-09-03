package dungeon.doctor.Inventory;


public class StaticInventoryGenerator {

    public StaticInventoryGenerator() {
    }

    private static Weapon fistInstance;

    public static Weapon getFistInstance() {
        if (fistInstance == null) {
            fistInstance = new Weapon("Fist", "A clenched fist", 0, 1, 4, false, true);
        }
        return fistInstance;
    }

    private static Armor bodyInstance;

    public static Armor getBodyInstance() {
        if (bodyInstance == null) {
            bodyInstance = new Armor(0, "Tunic", "Just a tunic, no AC.", 1000);
            bodyInstance.setImmortal(true);
        }
        return bodyInstance;
    }


}
