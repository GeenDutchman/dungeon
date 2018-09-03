package dungeon.doctor.Inventory;

public class Money {
/*
    public class MoneyException extends Exception{
        public MoneyException(String s) {
            super(s);
        }
    }*/

    private int gold;

    //TODO: add silver and copper

    public int getMoney() {
        return gold;
    }

    public void setMoney(int gold) {
        this.gold = gold;
    }

    /**
     * Decrements money by so much.
     *
     * @param other contained amount to lose.
     * @return false if cannot pay, true if can.
     */
    public boolean loseMoney(Money other) {
        if (other.gold > this.gold) {
            return false; //cannot lose it
        }
        this.gold = this.gold - other.gold;
        return true;
    }

    public void gainMoney(Money other) {
        this.gold += other.gold;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Money:\n");
        sb.append(gold).append(" gold\n");
        return sb.toString();
    }
}
