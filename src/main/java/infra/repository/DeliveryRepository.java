package infra.repository;

import Domain.entities.Delivery;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryRepository {
    private DatabaseConnection connection;

    public DeliveryRepository(){
        this.connection = DatabaseConnection.getInstance();
    }

    public List<Delivery> findAll() throws SQLException {
        List<Delivery> deliveries = new ArrayList<>();
        PreparedStatement preparedStatement = this.connection.getConnection().prepareStatement("SELECT * FROM delivery");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int live = resultSet.getInt("Live");
            if (live == 1) {
                Delivery delivery = new Delivery();
                delivery.setId(resultSet.getInt("delivery_id"));
                delivery.setOrder_id(resultSet.getInt("order_id"));
                delivery.setDelivery_date(resultSet.getString("delivery_date"));
                delivery.setDelivery_address(resultSet.getString("delivery_address"));
                delivery.setDelivery_status(resultSet.getString("delivery_status"));
                delivery.setDelivery_received_date(resultSet.getString("delivery_received_date"));
                delivery.setReceived_by(resultSet.getString("received_by"));
                deliveries.add(delivery);
            }
        }
        return deliveries;
    }
    public List<Delivery> findAllFinal() throws SQLException {
        List<Delivery> deliveries = new ArrayList<>();
        PreparedStatement preparedStatement = this.connection.getConnection().prepareStatement("SELECT * FROM delivery");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int live = resultSet.getInt("Live");
            if (live == 0) {
                Delivery delivery = new Delivery();
                delivery.setId(resultSet.getInt("delivery_id"));
                delivery.setOrder_id(resultSet.getInt("order_id"));
                delivery.setDelivery_date(resultSet.getString("delivery_date"));
                delivery.setDelivery_address(resultSet.getString("delivery_address"));
                delivery.setDelivery_status(resultSet.getString("delivery_status"));
                delivery.setDelivery_received_date(resultSet.getString("delivery_received_date"));
                delivery.setReceived_by(resultSet.getString("received_by"));
                deliveries.add(delivery);
            }
        }
        return deliveries;
    }


    public Delivery findById(int id) throws SQLException {
        Delivery delivery = null;
        PreparedStatement preparedStatement = this.connection.getConnection().prepareStatement("SELECT * FROM delivery WHERE delivery_id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            int live = resultSet.getInt("Live");
            if (live == 1) {
                delivery = new Delivery();
                delivery.setId(resultSet.getInt("delivery_id"));
                delivery.setOrder_id(resultSet.getInt("order_id"));
                delivery.setDelivery_date(resultSet.getString("delivery_date"));
                delivery.setDelivery_address(resultSet.getString("delivery_address"));
                delivery.setDelivery_status(resultSet.getString("delivery_status"));
                delivery.setDelivery_received_date(resultSet.getString("delivery_received_date"));
                delivery.setReceived_by(resultSet.getString("received_by"));
            }
        }
        return delivery;
    }

    public boolean insert(Delivery delivery) throws SQLException {
        boolean inserted;
        PreparedStatement preparedStatement = this.connection.getConnection().prepareStatement("INSERT INTO delivery " +
                "(order_id, delivery_date, delivery_address, delivery_status, delivery_received_date, received_by) " +
                "VALUES (?,?,?,?,?,?)");
        preparedStatement.setInt(1, delivery.getOrder_id());
        preparedStatement.setString(2, delivery.getDelivery_date());
        preparedStatement.setString(3, delivery.getDelivery_address());
        preparedStatement.setString(4, delivery.getDelivery_status());
        preparedStatement.setString(5, delivery.getDelivery_received_date());
        preparedStatement.setString(6, delivery.getReceived_by());
        inserted = preparedStatement.execute();
        return inserted;
    }

    public boolean update(Delivery delivery) throws SQLException {
        boolean updated;
        PreparedStatement preparedStatement = this.connection.getConnection().prepareStatement("UPDATE delivery SET " +
                "order_id = ?, delivery_date = ?, delivery_address = ?, delivery_status = ?, delivery_received_date = ?, received_by = ? " +
                "WHERE delivery_id = ?");
        preparedStatement.setInt(1, delivery.getOrder_id());
        preparedStatement.setString(2, delivery.getDelivery_date());
        preparedStatement.setString(3, delivery.getDelivery_address());
        preparedStatement.setString(4, delivery.getDelivery_status());
        preparedStatement.setString(5, delivery.getDelivery_received_date());
        preparedStatement.setString(6, delivery.getReceived_by());
        preparedStatement.setInt(7, delivery.getId());
        updated = preparedStatement.execute();
        return updated;
    }

    public boolean delete() throws SQLException {

        PreparedStatement preparedStatement = this.connection.getConnection().prepareStatement(
                "UPDATE delivery SET Live = ? WHERE delivery_status = ?");
        preparedStatement.setInt(1, 0);
        preparedStatement.setString(2, "DELIVERED");
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected > 0;
    }
}

