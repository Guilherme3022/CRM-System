package infra.repository;

import Domain.entities.Client;
import java.util.*;
import java.sql.*;

public class ClientRepository {
    private DatabaseConnection connection;

    public ClientRepository() {
        this.connection = DatabaseConnection.getInstance();
    }

    public List<Client> findAll() throws SQLException {
        List<Client> clients = new ArrayList<>();
        PreparedStatement preparedStatement = this.connection.getConnection().prepareStatement("SELECT * FROM client");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int live = resultSet.getInt("Live");
            if (live == 1) {
                Client client = new Client();
                client.setId(resultSet.getInt("id"));
                client.setSsn(resultSet.getString("ssn"));
                client.setFullName(resultSet.getString("fullName"));
                client.setEmail(resultSet.getString("email"));
                client.setBirthDate(resultSet.getString("birthDate"));
                client.setAddress(resultSet.getString("address"));
                client.setPhoneNumber(resultSet.getString("phoneNumber"));
                clients.add(client);
            }
        }
        return clients;
    }


    public List<Client> findAllInactive() throws SQLException {
        List<Client> clients = new ArrayList<>();
        PreparedStatement preparedStatement = this.connection.getConnection().prepareStatement("SELECT * FROM client");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Client client = new Client();
            int live = resultSet.getInt("Live");
            if (live == 0) {
                client.setId(resultSet.getInt("id"));
                client.setSsn(resultSet.getString("ssn"));
                client.setFullName(resultSet.getString("fullName"));
                client.setEmail(resultSet.getString("email"));
                client.setBirthDate(resultSet.getString("birthDate"));
                client.setAddress(resultSet.getString("address"));
                client.setPhoneNumber(resultSet.getString("phoneNumber"));
                clients.add(client);
            }
        }
        return clients;
    }

    public Client findById(int id) throws SQLException {
        Client client = null;
        PreparedStatement preparedStatement = this.connection.getConnection().prepareStatement("SELECT * FROM client WHERE id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            client = new Client();
            int live = resultSet.getInt("Live");
            if (live == 1) {
                client.setId(resultSet.getInt("id"));
                client.setSsn(resultSet.getString("ssn"));
                client.setFullName(resultSet.getString("fullName"));
                client.setEmail(resultSet.getString("email"));
                client.setBirthDate(resultSet.getString("birthDate"));
                client.setAddress(resultSet.getString("address"));
                client.setPhoneNumber(resultSet.getString("phoneNumber"));
            } else {
                System.out.println("inactive individual");
            }
        }
        return client;
    }

    public Client findBySsn(String ssn) throws SQLException {
        Client client = null;
        PreparedStatement preparedStatement = this.connection.getConnection().prepareStatement("SELECT * FROM client WHERE ssn = ?");
        preparedStatement.setString(1, ssn);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            client = new Client();
            int live = resultSet.getInt("Live");
            if (live == 1) {
                client.setId(resultSet.getInt("id"));
                client.setSsn(resultSet.getString("ssn"));
                client.setFullName(resultSet.getString("fullName"));
                client.setEmail(resultSet.getString("email"));
                client.setBirthDate(resultSet.getString("birthDate"));
                client.setAddress(resultSet.getString("address"));
                client.setPhoneNumber(resultSet.getString("phoneNumber"));
            } else {
                System.out.println("inactive individual");
            }
        }
        return client;
    }

    public Client findByEmail(String email) throws SQLException {
        Client client = null;
        PreparedStatement preparedStatement = this.connection.getConnection().prepareStatement("SELECT * FROM client WHERE email = ?");
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            client = new Client();
            int live = resultSet.getInt("Live");
            if (live == 1) {
                client.setId(resultSet.getInt("id"));
                client.setSsn(resultSet.getString("ssn"));
                client.setFullName(resultSet.getString("fullName"));
                client.setEmail(resultSet.getString("email"));
                client.setBirthDate(resultSet.getString("birthDate"));
                client.setAddress(resultSet.getString("address"));
                client.setPhoneNumber(resultSet.getString("phoneNumber"));
            } else {
                System.out.println("inactive individual");
            }
        }
        return client;
    }

    public boolean insert(Client client) throws SQLException {
        boolean inserted;
        PreparedStatement preparedStatement = this.connection.getConnection().prepareStatement("INSERT INTO client " +
                "(ssn, fullName, email, birthDate, address, phoneNumber, live)" +
                "VALUES (?,?,?,?,?,?,?)");
        preparedStatement.setString(1, client.getSsn());
        preparedStatement.setString(2, client.getFullName());
        preparedStatement.setString(3, client.getEmail());
        preparedStatement.setString(4, client.getBirthDate());
        preparedStatement.setString(5, client.getAddress());
        preparedStatement.setString(6, client.getPhoneNumber());
        preparedStatement.setInt(7, client.getLive());
        inserted = preparedStatement.execute();
        return inserted;
    }

    public boolean update(Client client) throws SQLException {
        boolean updated = false;
        if (client == null || client.getId() <= 0) {
            return false;
        }
        PreparedStatement preparedStatement = this.connection.getConnection().prepareStatement("UPDATE client SET " +
                "ssn = ?, fullName = ?, email = ?, birthDate = ?, address = ?, phoneNumber = ? WHERE id = ?");
        preparedStatement.setString(1, client.getSsn());
        preparedStatement.setString(2, client.getFullName());
        preparedStatement.setString(3, client.getEmail());
        preparedStatement.setString(4, client.getBirthDate());
        preparedStatement.setString(5, client.getAddress());
        preparedStatement.setString(6, client.getPhoneNumber());
        preparedStatement.setInt(7, client.getId());
        updated = preparedStatement.execute();
        return updated;
    }

    public boolean deleteLogical(int id) throws SQLException {
        if (id <= 0) {
            return false;
        }
        PreparedStatement preparedStatement = this.connection.getConnection().prepareStatement(
                "UPDATE client SET Live = ? WHERE id = ?");
        preparedStatement.setInt(1, 0);
        preparedStatement.setInt(2, id);
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected > 0;
    }
    public boolean turnActive(int id) throws SQLException {
        if (id <= 0) {
            return false;
        }
        PreparedStatement preparedStatement = this.connection.getConnection().prepareStatement(
                "UPDATE client SET Live = ? WHERE id = ?");
        preparedStatement.setInt(1, 1);
        preparedStatement.setInt(2, id);
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected > 0;
    }

}