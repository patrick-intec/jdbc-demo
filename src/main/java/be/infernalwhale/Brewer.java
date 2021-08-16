package be.infernalwhale;

public class Brewer {
    private int id;
    private String name;
    private String address;
    private String zipCode;
    private String city;
    private int turnover;

    public int getId() {
        return id;
    }

    public Brewer setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Brewer setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Brewer setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Brewer setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Brewer setCity(String city) {
        this.city = city;
        return this;
    }

    public int getTurnover() {
        return turnover;
    }

    public Brewer setTurnover(int turnover) {
        this.turnover = turnover;
        return this;
    }

    @Override
    public String toString() {
        return "Brewer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                ", turnover=" + turnover +
                '}';
    }
}
