package Domain.entities;

public class Delivery {
    private int id;
    private int order_id;
    private String delivery_date;
    private String delivery_address;
    private String delivery_status;
    private String delivery_notes;
    private String delivery_received_date;
    private String received_by;

    public Delivery(int id, Order order, String delivery_date, String delivery_address, String delivery_status,
                    String delivery_notes, String delivery_received_date, String received_by) {
        this.id = id;
        this.order_id = order.getId();
        this.delivery_date = delivery_date;
        this.delivery_address = delivery_address;
        this.delivery_status = delivery_status;
        this.delivery_received_date = delivery_received_date;
        this.received_by = received_by;
    }
    public Delivery(int orderId,String deliveryStatus, String deliveryDate,String receiverName){


    }
    public Delivery() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(String delivery_date) {
        this.delivery_date = delivery_date;
    }

    public String getDelivery_address() {
        return delivery_address;
    }

    public void setDelivery_address(String delivery_address) {
        this.delivery_address = delivery_address;
    }

    public String getDelivery_status() {
        return delivery_status;
    }

    public void setDelivery_status(String delivery_status) {
        this.delivery_status = delivery_status;
    }
    public String getDelivery_received_date() {
        return delivery_received_date;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public void setDelivery_received_date(String delivery_received_date) {
        this.delivery_received_date = delivery_received_date;
    }

    public String getReceived_by() {
        return received_by;
    }

    public void setReceived_by(String received_by) {
        this.received_by = received_by;
    }

    @Override
    public String toString() {
        return String.format("| %-4d | %-8d | %-14s | %-30s | %-15s | %-20s | %-30s |",
                        getId(), getOrder_id(), getDelivery_date(), getDelivery_address(),
                        getDelivery_status(), getDelivery_received_date(), getReceived_by());
    }
}
