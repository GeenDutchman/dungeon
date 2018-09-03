package dungeon.doctor.creatures;

import dungeon.doctor.Inventory.Inventory;
import dungeon.doctor.Inventory.InventoryObject;
import dungeon.doctor.Inventory.Shield;
import dungeon.doctor.Inventory.Weapon;
import dungeon.doctor.dice.Dice;

public class Creature {


    public class CreatureException extends Exception {
        public CreatureException() {
        }

        public CreatureException(String s) {
            super(s);
        }
    }

    private Inventory inventory;
    private String name;
    private String description;
    private int currentHealth;
    private int maxHealth;
    private int level;

    private int strength;
    private int dexterity;
    private int baseAC = 10; //default value

    private boolean hostile = true;

    public Creature(String name, String description, int maxHealth, int level, int strength, int dexterity, int baseAC) {
        this.inventory = new Inventory();
        this.name = name;
        this.description = description;
        this.currentHealth = maxHealth;
        this.maxHealth = maxHealth;
        this.level = level;
        this.strength = strength;
        this.dexterity = dexterity;
        this.baseAC = baseAC;
    }

    public Creature(String name, String description, int currentHealth, int maxHealth, int level, int strength, int dexterity, int baseAC) {
        this.inventory = new Inventory();
        this.name = name;
        this.description = description;
        this.currentHealth = currentHealth;
        this.maxHealth = maxHealth;
        this.level = level;
        this.strength = strength;
        this.dexterity = dexterity;
        this.baseAC = baseAC;
    }

    public void heal(int amount) throws CreatureException {
        this.currentHealth = this.currentHealth + amount;
        if (this.currentHealth > this.maxHealth) {
            this.currentHealth = this.maxHealth;
            throw new CreatureException("Overhealing!"); //TODO: figure out mechanism
        }
    }

    /**
     * Damages the creature by amount.
     *
     * @param amount of damage to the creature.
     * @return true if still alive, false if not.
     */
    public boolean damage(int amount) {
        this.currentHealth = this.currentHealth - amount;
        if (this.currentHealth < 0) {
            return false;
        }
        return true;
    }

    public int getToHit(boolean mainHand) throws CreatureException {
        Dice dice = Dice.getInstance();
        int bonus = 0;

        Weapon weapon = this.getInventory().getMainHand();
        if (mainHand) {
            weapon = this.getInventory().getMainHand();
        } else {
            InventoryObject possWeapon = this.getInventory().getOffHand();
            if (possWeapon instanceof Weapon) {
                weapon = (Weapon) this.getInventory().getOffHand();
            } else {
                throw new CreatureException("You cannot attack with a " + possWeapon.getName() + "!");
            }
        }
        if (weapon.isNeedsDexNotStr()) {
            bonus = this.dexterity;
        } else {
            bonus = this.strength;
        }
        return dice.roll(1, 20) + bonus;
    }

    public int getAttackDamage(boolean mainHand) throws CreatureException {
        int result = 0;

        if (mainHand) {
            Weapon theWeapon = this.getInventory().getMainHand();

            int bonus = 0;
            if (theWeapon.isNeedsDexNotStr()) {
                bonus = this.dexterity;
            } else {
                bonus = this.strength;
            }
            result += theWeapon.swing() + bonus;
        } else {
            InventoryObject possWeapon = this.getInventory().getOffHand();
            if (this.getInventory().getOffHand() instanceof Weapon) {
                result += ((Weapon) this.getInventory().getOffHand()).swing();
            } else {
                throw new CreatureException("You cannot attack with a " + possWeapon.getName() + "!");
            }
        }

        return result;
    }

    public int getAC() {
        int result;
        result = baseAC + this.getInventory().getArmor().getArmorClassBonus() + this.getDexterity();
        if (this.getInventory().getOffHand() instanceof Shield) {
            result += ((Shield) this.getInventory().getOffHand()).getArmorClassBonus();
        }
        return result;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String getName() {
        return name;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public boolean isAlive() {
        if (currentHealth > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.name).append('\n');
        sb.append(this.description);
        return sb.toString();
    }
}
