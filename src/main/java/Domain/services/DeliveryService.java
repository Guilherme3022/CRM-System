package Domain.services;

import Domain.entities.*;
import infra.repository.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;


public class DeliveryService {
    private final DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public List<Delivery> getAllDeliverie() throws SQLException {
        return deliveryRepository.findAll();
    }
    public List<Delivery> getAllDeliveriefinal() throws SQLException {
        return deliveryRepository.findAllFinal();
    }

    public Delivery getDeliveryById(int id) throws SQLException {
        return deliveryRepository.findById(id);
    }
    public boolean updateDeliveryFinal(Delivery delivery) throws SQLException {
        delivery.setDelivery_status("DELIVERED");
        OrderRepository orderRepository = new OrderRepository();
        Order order = orderRepository.findById(delivery.getOrder_id());
        if (order != null) {
            if (delivery.getDelivery_status().equals("DELIVERED")) {
                System.out.print("Enter who received the package: ");
                Scanner scanner = new Scanner(System.in);
                String received_by = scanner.nextLine();
                delivery.setReceived_by(received_by);
                delivery.setDelivery_received_date(currentDateAsString());
                deliveryRepository.update(delivery);
                order.setOrder_status("DELIVERED");
                orderRepository.update(order);
            }
            return true;
        }
        return false;
    }
    public boolean updateDeliveryInTransit(Delivery delivery) throws SQLException {
        delivery.setDelivery_status("IN_TRANSIT");
        deliveryRepository.update(delivery);
        OrderRepository orderRepository = new OrderRepository();
        Order order = orderRepository.findById(delivery.getOrder_id());
        if (order != null) {
            if (delivery.getDelivery_status().equals("IN_TRANSIT")) {
                order.setOrder_status("SHIPPED");
                orderRepository.update(order);
            }
            return true;
        }
        return false;
    }


    public boolean deleteDelivery() throws SQLException {
        return deliveryRepository.delete();
    }
    private String currentDateAsString() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);

        return formattedDate;
    }
}
