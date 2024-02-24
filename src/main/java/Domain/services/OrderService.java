package Domain.services;

import Domain.entities.*;
import infra.repository.ClientRepository;
import infra.repository.DeliveryRepository;
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

    public boolean updateOrderToStatusAwaitingPayment(Order order) throws SQLException {
        order.setOrder_status("AWAITING_PAYMENT");
        return orderRepository.update(order);
    }

    public boolean updateOrderToStatusPaid(Order order) throws SQLException {
        order.setOrder_status("PAID");
        return orderRepository.update(order);
    }
    public boolean updateOrderPreparingDelivery(Order order) throws SQLException {
        order.setOrder_status("PREPARING_DELIVERY");
        updateDeliveryPreparing(order);
        return orderRepository.update(order);
    }
    public boolean updateDeliveryPreparing(Order order) throws SQLException {
        if (order != null && order.getOrder_status().equals("PREPARING_DELIVERY")) {
            if (orderRepository.update(order)) {
                ClientRepository clientRepository = new ClientRepository();
                Client client = clientRepository.findById(order.getClient_id());

                if (client != null) {
                    String deliveryAddress = client.getAddress();
                    Delivery newDelivery = new Delivery();
                    newDelivery.setOrder_id(order.getId());
                    newDelivery.setDelivery_status("PENDING");
                    newDelivery.setDelivery_date(currentDateAsString());
                    newDelivery.setDelivery_address(deliveryAddress);
                    DeliveryRepository deliveryRepository = new DeliveryRepository();
                    return deliveryRepository.insert(newDelivery);
                }
            }
        }
        return false;
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
