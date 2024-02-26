package Domain.entities;

public class Order {
    private int id;
    private int client_id;
    private String created_at;
    private double order_value;
    private String order_status;
    private int live;



    public Order(Client client, String created_at, double order_value, String order_status) {
        this.client_id = client.getId();
        this.created_at = created_at;
        this.order_value = order_value;
        this.order_status = order_status;
    }
    public Order(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public double getOrder_value() {
        return order_value;
    }

    public void setOrder_value(double order_value) {
        this.order_value = order_value;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }
    public int getLive() {
        return live;
    }

    public void setLive(int live) {
        this.live = live;
    }

    @Override
    public String toString() {
        return String.format("| %-4d | %-8d | %-14s | %-10.2f | %-12s |",
                        getId(), getClient_id(), getCreated_at(), getOrder_value(), getOrder_status());
    }
}
