package infra.repository;

import Domain.entities.Order;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private DatabaseConnection connection;

    public OrderRepository(){
        this.connection = DatabaseConnection.getInstance();
    }

    public List<Order> findAll() throws SQLException {
        List<Order> order = new ArrayList<>();
        PreparedStatement preparedStatement = this.connection.getConnection().prepareStatement("SELECT * FROM `order`");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Order orders = new Order();
            orders.setId(resultSet.getInt("id"));
            orders.setClient_id(resultSet.getInt("client_id"));
            orders.setCreated_at(resultSet.getString("created_at"));
            orders.setOrder_value(resultSet.getDouble("order_value"));
            orders.setOrder_status(resultSet.getString("order_status"));
            order.add(orders);
        }
        return order;
    }

    public Order findById(int id) throws SQLException {
        Order order = null;
        PreparedStatement preparedStatement = this.connection.getConnection().prepareStatement("SELECT * FROM `order` WHERE id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            order = new Order();
            order.setId(resultSet.getInt("id"));
            order.setClient_id(resultSet.getInt("client_id"));
            order.setCreated_at(resultSet.getString("created_at"));
            order.setOrder_value(resultSet.getDouble("order_value"));
            order.setOrder_status(resultSet.getString("order_status"));
        }
        return order;
    }

    public boolean insert(Order order) throws SQLException {
        boolean inserted;
        PreparedStatement preparedStatement = this.connection.getConnection().prepareStatement("INSERT INTO `order` " +
                "(client_id, created_at, order_value, order_status) VALUES (?,?,?,?)");
        preparedStatement.setInt(1, order.getClient_id());
        preparedStatement.setString(2, order.getCreated_at());
        preparedStatement.setDouble(3,  order.getOrder_value());
        preparedStatement.setString(4, order.getOrder_status());
        inserted = preparedStatement.execute();
        return inserted;
    }

    public boolean update(Order order) throws SQLException {
        boolean updated;
        double orderValue = calculateOrderValue(order.getId());
        PreparedStatement preparedStatement = this.connection.getConnection().prepareStatement("UPDATE `order` SET " +
                "client_id = ?, created_at = ?, order_value = ?, order_status = ? WHERE id = ?");
        preparedStatement.setInt(1, order.getClient_id());
        preparedStatement.setString(2, order.getCreated_at());
        preparedStatement.setDouble(3, orderValue);
        preparedStatement.setString(4, order.getOrder_status());
        preparedStatement.setInt(5, order.getId());
        updated = preparedStatement.executeUpdate() > 0;
        return updated;
    }


    public boolean delete(int id) throws SQLException {
        boolean isDeleted;
        PreparedStatement preparedStatement = this.connection.getConnection().prepareStatement("DELETE FROM `order` WHERE id = ?");
        preparedStatement.setInt(1, id);
        isDeleted = preparedStatement.execute();
        return isDeleted;
    }
    public double calculateOrderValue(int orderId) throws SQLException {
        double totalOrderValue = 0.0;
        PreparedStatement preparedStatement = this.connection.getConnection().prepareStatement("SELECT * FROM order_product WHERE order_id = ?");
        preparedStatement.setInt(1, orderId);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int productId = resultSet.getInt("product_id");
            int quantity = resultSet.getInt("quantity");

            PreparedStatement productStatement = this.connection.getConnection().prepareStatement("SELECT price FROM product WHERE id = ?");
            productStatement.setInt(1, productId);
            ResultSet productResultSet = productStatement.executeQuery();

            if (productResultSet.next()) {
                double productPrice = productResultSet.getDouble("price");
                totalOrderValue += (productPrice * quantity);
            }
        }

        return totalOrderValue;
    }
}
