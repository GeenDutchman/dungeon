package dungeon.doctor.Inventory;


import java.util.TreeMap;

import dungeon.doctor.dice.Dice;

public class HealPotion extends InventoryObject {

    public enum HealType {
        LIGHT, MEDIUM, HIGH, SEVERE, DEADLY, EXTREME
    }

    private HealType type;

    private static TreeMap<String, Integer> variableStats;

    public HealPotion(String typeString, String name, String description, int endurance) {
        super(name, description, endurance);
        try {
            this.type = HealType.valueOf(typeString.toUpperCase());
        } catch (IllegalArgumentException e) {
            //TODO: log it
            this.type = HealType.LIGHT;
        }
    }

    public HealPotion(HealType type, String name, String description, int endurance) {
        super(name, description, endurance);
        this.type = type;
    }

    public int drink() {
        if (!checkEndurance()) {
            return 0;
        }
        int result = 0;
        Dice dice = Dice.getInstance();
        switch (this.type) {
            case LIGHT:
                result = dice.roll(1, 4);
                break;
            case MEDIUM:
                result = dice.roll(1, 6);
                break;
            case HIGH:
                result = dice.roll(2, 6);
                break;
            case SEVERE:
                result = dice.roll(2, 8);
                break;
            case DEADLY:
                result = dice.roll(2, 10);
                break;
            case EXTREME:
                result = dice.roll(3, 12);
                break;
            default:
                result = dice.roll(1, 4);
                break;
        }
        while (this.checkEndurance()) {
            this.decrementEndurance();
        }
        this.setDescription("This potion of healing is empty. It will not help you.");
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        if (this.checkEndurance()) {
            sb.append("It is categorized as ");
            sb.append(this.type.toString());
        }
        sb.append(".");
        return sb.toString();
    }
}
