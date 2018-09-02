package dungeon.doctor.objects;

public abstract class SuperObject {
    private String description;
    private final static String DEFAULT_DESCRIPTION = "No description given";

    public String look() {
        if (description != null) {
            return description;
        }
        return DEFAULT_DESCRIPTION;
    }

}
