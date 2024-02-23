package infra.repository;

import java.sql.*;

public class DatabaseConnection {
    private Connection connection;
    private static DatabaseConnection instance;

    public static  DatabaseConnection getInstance(){
        if (instance == null){
            instance = new DatabaseConnection();
        }
        return instance;
    }
    private DatabaseConnection(){
        try {
            this.createConnection();
        }catch (SQLException e){
            System.out.println("SQL Error: "+ e.getMessage());
        }
    }
    private void createConnection() throws SQLException{
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crm_system","root","@R19p11g12g26");
        this.connection.setAutoCommit(true);
    }
    public Connection getConnection(){
        return this.connection;
    }
    public void closeConnection(){
        try{
            this.connection.close();
        }catch (SQLException e){
            System.out.println("SQL Error: "+ e.getMessage());
        }
    }
}