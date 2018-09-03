package dungeon.doctor.Inventory;

import java.util.ArrayList;

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

    //private InventoryObject headgear;
    private Armor armor;
    private Weapon mainHand;
    private InventoryObject offHand;

    private ArrayList<InventoryObject> backpack;

    private int MAX_BACKPACK = 50;

    private Money myMoney;

    public Inventory() {
        this.backpack = new ArrayList<>();
        this.myMoney = new Money();
        this.armor = StaticInventoryGenerator.getBodyInstance();
        this.mainHand = StaticInventoryGenerator.getFistInstance();
        this.offHand = StaticInventoryGenerator.getFistInstance();
    }

    public Inventory(ArrayList<InventoryObject> backpack) {
        this.backpack = backpack;
        this.myMoney = new Money();
        this.armor = StaticInventoryGenerator.getBodyInstance();
        this.mainHand = StaticInventoryGenerator.getFistInstance();
        this.offHand = StaticInventoryGenerator.getFistInstance();
    }

    public void equipItem(int inventoryIndex) throws InventoryException {
        //TODO check the type of item and call proper equip function
    }

/*    public void equipHead(int inventoryIndex) throws InventoryException {
        //TODO: check the item at inventoryIndex to see if it can go on the head

        this.headgear = this.get(inventoryIndex);
    }

    public void unequipHead() {
        this.headgear = null;
    }

    public final InventoryObject getHead() {
        return this.headgear;
    }*/

    public void equipArmor(int inventoryIndex) throws InventoryException {
        InventoryObject toEquip = this.get(inventoryIndex);
        if (!toEquip.getClass().equals(Armor.class)) {
            throw new InventoryException("That is not Armor!");
        }
        this.armor = (Armor) toEquip;
    }

    public void unequipArmor() {
        this.armor = StaticInventoryGenerator.getBodyInstance();
    }

    public final InventoryObject getArmor() {
        return this.armor;
    }

    public void equipMainHand(int inventoryIndex) throws InventoryException {
        InventoryObject toEquip = this.get(inventoryIndex);
        if (!toEquip.getClass().equals(Weapon.class)) {
            throw new InventoryException("That is not a weapon!");
        }
        this.mainHand = (Weapon) toEquip;
    }

    public void unequipMainHand() {
        this.mainHand = StaticInventoryGenerator.getFistInstance();
    }

    public final Weapon getMainHand() {
        return this.mainHand;
    }

    public void equipOffHand(int inventoryIndex) throws InventoryException {
        //TODO: check if goes here

        this.offHand = this.get(inventoryIndex);
    }

    public void unequipOffHand() {
        this.offHand = StaticInventoryGenerator.getFistInstance();
    }

    public final InventoryObject getOffHand() {
        return this.offHand;
    }

    private InventoryObject get(int inventoryIndex) throws InventoryException {
        if (inventoryIndex < 0 || inventoryIndex > backpack.size()) {
            throw new InventoryException("Invalid Inventory Index!");
        }
        return this.backpack.get(inventoryIndex);
    }

    public InventoryObject retrieve(int inventoryIndex) throws InventoryException {
        if (inventoryIndex < 0 || inventoryIndex > backpack.size()) {
            throw new InventoryException("Invalid Inventory Index!");
        }

        InventoryObject toReturn = this.backpack.get(inventoryIndex);
        this.backpack.remove(inventoryIndex);
        return toReturn;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        int i = 0;
        sb.append("Inventory:\n");
        for (InventoryObject so : this.backpack) {
            sb.append(i).append(" : ").append(so.toString());
            if (/*so == headgear ||*/ so == armor || so == mainHand || so == offHand) {
                sb.append(" [equipped]");
            }
            sb.append('\n');
        }
        sb.append(this.myMoney.toString());
        return sb.toString();
    }
}
