package dungeon.doctor.player;

import java.util.ArrayList;

import dungeon.doctor.objects.SuperObject;

public class Inventory {

    public class InventoryException extends Exception {
        public InventoryException() {
        }

        public InventoryException(String s) {
            super(s);
        }

        public InventoryException(String s, Throwable throwable) {
            super(s, throwable);
        }
    }

    private SuperObject headgear;
    private SuperObject armor;
    private SuperObject mainHand;
    private SuperObject offHand;

    private ArrayList<SuperObject> backpack;

    private int MAX_BACKPACK = 50;

    public Inventory() {
        this.backpack = new ArrayList<>();
    }

    public Inventory(ArrayList<SuperObject> backpack) {
        this.backpack = backpack;
    }

    public void equipItem(int inventoryIndex) throws InventoryException {
        //TODO check the type of item and call proper equip function
    }

    public void equipHead(int inventoryIndex) throws InventoryException {
        //TODO: check the item at inventoryIndex to see if it can go on the head

        this.headgear = this.get(inventoryIndex);
    }

    public void unequipHead() {
        this.headgear = null;
    }

    public final SuperObject getHead() {
        return this.headgear;
    }

    public void equipArmor(int inventoryIndex) throws InventoryException {
        //TODO: check if it is armor

        this.armor = this.get(inventoryIndex);
    }

    public void unequipArmor() {
        this.armor = null;
    }

    public final SuperObject getArmor() {
        return this.armor;
    }

    public void equipMainHand(int inventoryIndex) throws InventoryException {
        //TODO: check if goes here

        this.mainHand = this.get(inventoryIndex);
    }

    public void unequipMainHand() {
        this.mainHand = null;
    }

    public final SuperObject getMainHand() {
        return this.mainHand;
    }

    public void equipOffHand(int inventoryIndex) throws InventoryException {
        //TODO: check if goes here

        this.offHand = this.get(inventoryIndex);
    }

    public void unequipOffHand() {
        this.offHand = null;
    }

    public final SuperObject getOffHand() {
        return this.offHand;
    }

    private SuperObject get(int inventoryIndex) throws InventoryException {
        if (inventoryIndex < 0 || inventoryIndex > backpack.size()) {
            throw new InventoryException("Invalid Inventory Index!");
        }
        return this.backpack.get(inventoryIndex);
    }

    public SuperObject retrieve(int inventoryIndex) throws InventoryException {
        if (inventoryIndex < 0 || inventoryIndex > backpack.size()) {
            throw new InventoryException("Invalid Inventory Index!");
        }

        SuperObject toReturn = this.backpack.get(inventoryIndex);
        this.backpack.remove(inventoryIndex);
        return toReturn;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        int i = 0;
        sb.append("Inventory:\n");
        for (SuperObject so : this.backpack) {
            sb.append(i).append(" : ").append(so.toString());
            if (so == headgear || so == armor || so == mainHand || so == offHand) {
                sb.append(" [equipped]");
            }
        }
        return sb.toString();
    }
}
