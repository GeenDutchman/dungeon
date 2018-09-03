package dungeon.doctor.Inventory;

public abstract class InventoryObject {
    private String description;
    private String name;
    private final static String DEFAULT_DESCRIPTION = "No description given";

    private boolean immortal = false;
    private int endurance;

    public InventoryObject(String name, String description, int endurance) {
        this.description = description;
        this.name = name;
        this.endurance = endurance;
    }

    public InventoryObject(String name, String description, int endurance, boolean immortal) {
        this.description = description;
        this.name = name;
        this.endurance = endurance;
        this.immortal = immortal;
    }

    public String look() {
        if (description != null) {
            StringBuilder sb = new StringBuilder(name);
            sb.append('\n').append(description);
            return sb.toString();
        }
        return DEFAULT_DESCRIPTION;
    }

    @Override
    public String toString() {
        return look();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean checkEndurance() {
        return (this.endurance > 0);
    }

    protected void decrementEndurance() {
        if (!immortal) {
            --this.endurance;
        }
    }

    protected void setImmortal(boolean immortal) {
        this.immortal = immortal;
    }
}
