package infra.repository;

import Domain.entities.OrderProduct;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderProductRepository {
    private DatabaseConnection connection;

    public OrderProductRepository(){
        this.connection = DatabaseConnection.getInstance();
    }

    public List<OrderProduct> findAll() throws SQLException {
        List<OrderProduct> orderProducts = new ArrayList<>();
        PreparedStatement preparedStatement = this.connection.getConnection().prepareStatement("SELECT * FROM order_product");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int live = resultSet.getInt("Live");
            if (live == 1) {
                OrderProduct orderProduct = new OrderProduct();
                orderProduct.setId(resultSet.getInt("id"));
                orderProduct.setOrder_id(resultSet.getInt("order_id"));
                orderProduct.setProduct_id(resultSet.getInt("product_id"));
                orderProduct.setQuantity(resultSet.getInt("quantity"));
                orderProducts.add(orderProduct);
            }
        }
        return orderProducts;
    }
    public List<OrderProduct> findAllInactive() throws SQLException {
        List<OrderProduct> orderProducts = new ArrayList<>();
        PreparedStatement preparedStatement = this.connection.getConnection().prepareStatement("SELECT * FROM order_product");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int live = resultSet.getInt("Live");
            if (live == 0) {
                OrderProduct orderProduct = new OrderProduct();
                orderProduct.setId(resultSet.getInt("id"));
                orderProduct.setOrder_id(resultSet.getInt("order_id"));
                orderProduct.setProduct_id(resultSet.getInt("product_id"));
                orderProduct.setQuantity(resultSet.getInt("quantity"));
                orderProducts.add(orderProduct);
            }
        }
        return orderProducts;
    }

    public OrderProduct findById(int id) throws SQLException {
        OrderProduct orderProduct = null;
        PreparedStatement preparedStatement = this.connection.getConnection().prepareStatement("SELECT * FROM order_product WHERE id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            int live = resultSet.getInt("Live");
            if (live == 1) {
                orderProduct = new OrderProduct();
                orderProduct.setId(resultSet.getInt("id"));
                orderProduct.setOrder_id(resultSet.getInt("order_id"));
                orderProduct.setProduct_id(resultSet.getInt("product_id"));
                orderProduct.setQuantity(resultSet.getInt("quantity"));
            }
        }
        return orderProduct;
    }

    public boolean insert(OrderProduct orderProduct) throws SQLException {
        boolean inserted;
        PreparedStatement preparedStatement = this.connection.getConnection().prepareStatement("INSERT INTO order_product " +
                "(order_id, product_id, quantity) VALUES (?,?,?)");
        preparedStatement.setInt(1, orderProduct.getOrder_id());
        preparedStatement.setInt(2, orderProduct.getProduct_id());
        preparedStatement.setInt(3, orderProduct.getQuantity());
        inserted = preparedStatement.execute();
        return inserted;
    }

    public boolean update(OrderProduct orderProduct) throws SQLException {
        boolean updated;
        PreparedStatement preparedStatement = this.connection.getConnection().prepareStatement("UPDATE order_product SET " +
                "order_id = ?, product_id = ?, quantity = ? WHERE id = ?");
        preparedStatement.setInt(1, orderProduct.getOrder_id());
        preparedStatement.setInt(2, orderProduct.getProduct_id());
        preparedStatement.setInt(3, orderProduct.getQuantity());
        preparedStatement.setInt(4, orderProduct.getId());
        updated = preparedStatement.execute();
        return updated;
    }
    public boolean delete(int id) throws SQLException {
        if (id <= 0) {
            return false;
        }
        PreparedStatement preparedStatement = this.connection.getConnection().prepareStatement(
                "UPDATE order_product SET Live = ? WHERE id = ?");
        preparedStatement.setInt(1, 0);
        preparedStatement.setInt(2, id);
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected > 0;
    }
}
