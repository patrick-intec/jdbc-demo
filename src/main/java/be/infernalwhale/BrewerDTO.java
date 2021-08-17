package be.infernalwhale;

public class BrewerDTO {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public BrewerDTO setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public BrewerDTO setName(String name) {
        this.name = name;
        return this;
    }
}
