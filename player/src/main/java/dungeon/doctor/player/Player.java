package dungeon.doctor.player;

public class Player {
    private Inventory inventory;
    private String name;
    private int currentHealth;
    private int maxHealth;
    private int currentXP;
    private int level;

    private int strength;
    private int dexterity;

    public class PlayerException extends Exception {
        public PlayerException() {
        }

        public PlayerException(String s) {
            super(s);
        }
    }

    public Player(String name) {
        this.name = name;
        this.inventory = new Inventory();
        this.maxHealth = 10;
        this.currentHealth = this.maxHealth; //at full health when start
        this.currentXP = 0;
        this.level = 0;

        //TODO: find something realistic
        this.strength = 1;
        this.dexterity = 1;
    }

    public void heal(int amount) throws PlayerException {
        this.currentHealth = this.currentHealth + amount;
        if (this.currentHealth > this.maxHealth) {
            this.currentHealth = this.maxHealth;
            throw new PlayerException("Overhealing!"); //TODO: figure out mechanism
        }
    }

    /**
     * Damages the player by amount.
     *
     * @param amount of damage to the player.
     * @return true if still alive, false if not.
     */
    public boolean damage(int amount) {
        this.currentHealth = this.currentHealth - amount;
        if (this.currentHealth < 0) {
            return false;
        }
        return true;
    }
}
