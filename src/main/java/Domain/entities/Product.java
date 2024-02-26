package Domain.entities;

public class Product {
    private int id;
    private String name;
    private double price;
    private int live;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public Product(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getLive() {
        return live;
    }

    public void setLive(int live) {
        this.live = live;
    }

    @Override
    public String toString() {
        return  String.format("| %-4d | %-30s | %-10.2f |",
                        getId(), getName(), getPrice());
    }

}
