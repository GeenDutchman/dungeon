package dungeon.doctor.Inventory;

public class Armor extends InventoryObject {
    int armorClassBonus;

    public Armor(int armorClassBonus, String name, String description, int endurance) {
        super(name, description, endurance);
        this.armorClassBonus = armorClassBonus;
    }

    public int getArmorClassBonus() {
        return armorClassBonus;
    }

    public void takeHit() {
        this.decrementEndurance();
        if (!checkEndurance()) {
            this.armorClassBonus = 0;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("This adds ").append(this.armorClassBonus).append(" to your AC score when worn.");
        if (!checkEndurance()) {
            sb.append("This has taken too many hits, and is useless.");
        }
        //sb.append('\n');
        return sb.toString();
    }
}
