package Domain.services;

import Domain.entities.Order;
import infra.repository.OrderRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class OrderService {
    private final OrderRepository orderRepository;
    private static int orderIdCounter = 1;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() throws SQLException {
        return orderRepository.findAll();
    }

    public Order getOrderById(int id) throws SQLException {
        return orderRepository.findById(id);
    }
    public boolean createOrder(Order order) throws SQLException {
        order.setCreated_at(currentDateAsString());
        order.setOrder_status("CREATED");
        return orderRepository.insert(order);
    }

    public boolean updateOrder(Order order) throws SQLException {
        return orderRepository.update(order);
    }

    public boolean deleteOrder(int id) throws SQLException {
        return orderRepository.delete(id);
    }
    private String currentDateAsString() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);

        return formattedDate;
    }

}
