package dungeon.doctor.creatures;

import dungeon.doctor.dice.Dice;

public class Player extends Creature {

    private int currentXP;
    private int levelThreshold;

    public class PlayerException extends CreatureException {
        public PlayerException() {
        }

        public PlayerException(String s) {
            super(s);
        }

        public PlayerException(Exception e) {
            super(e.getMessage(), e); //wrapping
        }
    }


    public Player(String name) {
        super(name, "This is you.", 10, 1, 1, 1);
        //TODO: find something realistic for str and dex
        this.currentXP = 0;
        this.levelThreshold = this.getLevel() * this.getLevel();
    }

    public boolean checkLevelUp() {
        if (this.currentXP >= levelThreshold) {
            return true;
        }
        return false;
    }

    public void levelUp(boolean increaseStrNotDex) {
        Dice dice = Dice.getInstance();
        this.setLevel(this.getLevel() + 1);
        if (!increaseStrNotDex) {
            this.setDexterity(this.getDexterity() + 1);
        } else {
            this.setStrength(this.getStrength() + 1);
        }
        this.currentXP = this.currentXP - this.levelThreshold;
        this.levelThreshold = this.getLevel() * this.getLevel();
    }

    public void addXP(int amount) {
        this.currentXP += amount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("You have ").append(this.getCurrentHealth()).append('/').append(this.getMaxHealth()).append(" health.\n");
        sb.append("You are level: ").append(this.getLevel()).append('\n');
        sb.append("With ").append(this.currentXP).append(" experience points.");
        return sb.toString();
    }

}
