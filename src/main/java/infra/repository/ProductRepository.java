package infra.repository;

import Domain.entities.Product;

import java.util.*;
import java.sql.*;

public class ProductRepository {
    private DatabaseConnection  connection;

    public ProductRepository(){
        this.connection = DatabaseConnection.getInstance();
    }
    public List<Product> findAll() throws SQLException {
        List<Product> products = new ArrayList<>();
        PreparedStatement preparedStatement = this.connection.getConnection().prepareStatement("SELECT * FROM product");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Product product = new Product();
            product.setId(resultSet.getInt("id"));
            product.setName(resultSet.getString("name"));
            product.setPrice(resultSet.getDouble("price"));
            products.add(product);
        }
        return products;
    }
    public Product findById(int id) throws SQLException{
        Product product = null;
        PreparedStatement preparedStatement = this.connection.getConnection().prepareStatement("SELECT * FROM product WHERE id = ?");
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            product = new Product();
            product.setId(resultSet.getInt("id"));
            product.setName(resultSet.getString("name"));
            product.setPrice(resultSet.getDouble("price"));
        }
        return product;
    }
    public Product findByName(String name) throws SQLException{
        Product product = null;
        PreparedStatement preparedStatement = this.connection.getConnection().prepareStatement("SELECT * FROM product WHERE name = ?");
        preparedStatement.setString(1,name);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            product = new Product();
            product.setId(resultSet.getInt("id"));
            product.setName(resultSet.getString("name"));
            product.setPrice(resultSet.getDouble("price"));
        }
        return product;
    }
    public boolean insert(Product product) throws SQLException {
        boolean inserted;
        PreparedStatement preparedStatement = this.connection.getConnection().prepareStatement("INSERT INTO product " +
                "(name, price)" +
                "VALUES (?,?)");
        preparedStatement.setString(1,product.getName());
        preparedStatement.setDouble(2,product.getPrice());
        inserted = preparedStatement.execute();
        return inserted;
    }
    public boolean update(Product product) throws SQLException {
        boolean updated = false;
        if (product == null ||product.getId() <= 0  ){
            return false;
        }
        PreparedStatement preparedStatement = this.connection.getConnection().prepareStatement("UPDATE product SET " +
                "name = ?,price = ?  WHERE id = ?");
        preparedStatement.setString(1,product.getName());
        preparedStatement.setDouble(2,product.getPrice());
        updated = preparedStatement.execute();
        return updated;
    }
    public boolean delete(int id) throws SQLException {
        boolean isDeleted = false;

        PreparedStatement preparedStatement = this.connection.getConnection().prepareStatement("DELETE FROM product WHERE id = ? ");
        preparedStatement.setInt(1, id);
        isDeleted = preparedStatement.execute();
        return isDeleted;
    }


}
