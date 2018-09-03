package dungeon.doctor.Inventory;

import java.util.Random;

import dungeon.doctor.dice.Dice;

public class Weapon extends InventoryObject {
    private Random r = new Random();
    private int numDice;
    private int dieType;

    boolean needsDexNotStr = false;

    public Weapon(String name, String description, int endurance, int numDice, int dieType, boolean needsDexNotStr) {
        super(name, description, endurance);
        this.numDice = numDice;
        this.dieType = dieType;

        this.needsDexNotStr = needsDexNotStr;
    }

    public Weapon(String name, String description, int endurance, int numDice, int dieType, boolean needsDexNotStr, boolean immortal) {
        super(name, description, endurance, immortal);
        this.numDice = numDice;
        this.dieType = dieType;

        this.needsDexNotStr = needsDexNotStr;
    }

    public int swing() {
        return this.swing(false);
    }

    public int swing(boolean critical) {
        Dice dice = Dice.getInstance();
        int result = dice.roll(this.numDice, this.dieType);
        this.decrementEndurance();
        if (critical) {
            result += dice.roll(this.numDice, this.dieType);
        }
        if (!this.checkEndurance()) {
            this.numDice = 0;
        }
        return result;
    }

    public boolean isNeedsDexNotStr() {
        return needsDexNotStr;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("On a hit, this ").append(this.getName()).append(" deals ");
        sb.append(this.numDice).append('d').append(this.dieType);
        if (!this.checkEndurance()) {
            sb.append(" This weapon has taken too many hits, it is useless.");
        }
        //sb.append('\n');
        return sb.toString();
    }
}
