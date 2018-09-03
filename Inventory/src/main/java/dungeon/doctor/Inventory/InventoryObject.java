package dungeon.doctor.Inventory;

public abstract class InventoryObject implements Comparable {
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

    @Override
    public int compareTo(Object o) {
        if (o == null) {
            return 1;
        }
        if (this == o) {
            return 0;
        }
        if (!(o instanceof InventoryObject)) {
            return 1;
        }

        int result;

        InventoryObject other = (InventoryObject) o;

        result = this.getClass().toString().compareTo(other.getClass().toString()); //just compare by class name
        if (result != 0) {
            return result;
        }

        result = this.getName().compareTo(other.getName());
        if (result != 0) {
            return result;
        }

        if (this.endurance >= other.endurance) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public boolean equals(Object o) {
        int result = this.compareTo(o);
        if (result == 0) {
            return true;
        }
        return false;
    }

    /*    protected boolean getImmortal(){
        return this.immortal;
    }*/
}
