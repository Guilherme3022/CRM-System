package Domain.entities;

public class OrderProduct {
    private int id;
    private int order_id;
    private int product_id;
    private int quantity;

    public OrderProduct(Order order, Product product, int quantity) {
        this.order_id = order.getId();
        this.product_id = product.getId();
        this.quantity = quantity;
    }

    public OrderProduct() {

    }

    public int getId() {
        return id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("| %-4d | %-8d | %-10d | %-8d |",
                        getId(), getOrder_id(), getProduct_id(), getQuantity());
    }
}
