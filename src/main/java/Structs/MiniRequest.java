package Structs;

public class MiniRequest {

    private final String id;
    private final String type;
    private final String mode;
    private final String name;
    private final String info;

    public String getType() {
        return type;
    }

    public String getMode() {
        return mode;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public String getId() {
        return id;
    }

    public MiniRequest(String id, String type, String mode, String name, String info) {
        this.id = id;
        this.type = type;
        this.mode = mode;
        this.name = name;
        this.info = info;
    }
}
