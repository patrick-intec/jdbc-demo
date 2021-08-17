package be.infernalwhale;

public class Beer {
    private int id;
    private String name;
    private int brewerId; // FK
    private Brewer brewer;
    private int categoryId; // FK
    private double price;
    private int stock;
    private int alcohol;
    private int version;

    public int getId() {
        return id;
    }

    public Beer setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Beer setName(String name) {
        this.name = name;
        return this;
    }

    public int getBrewerId() {
        return brewerId;
    }

    public Beer setBrewerId(int brewerId) {
        this.brewerId = brewerId;
        return this;
    }

    public Brewer getBrewer() {
        return brewer;
    }

    public Beer setBrewer(Brewer brewer) {
        this.brewer = brewer;
        return this;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public Beer setCategoryId(int categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Beer setPrice(double price) {
        this.price = price;
        return this;
    }

    public int getStock() {
        return stock;
    }

    public Beer setStock(int stock) {
        this.stock = stock;
        return this;
    }

    public int getAlcohol() {
        return alcohol;
    }

    public Beer setAlcohol(int alcohol) {
        this.alcohol = alcohol;
        return this;
    }

    public int getVersion() {
        return version;
    }

    public Beer setVersion(int version) {
        this.version = version;
        return this;
    }
}
