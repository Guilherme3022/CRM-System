package Domain.services;

import Domain.entities.*;
import infra.repository.*;
import java.sql.SQLException;
import java.util.List;


public class DeliveryService {
    private final DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public List<Delivery> getAllDeliveries() throws SQLException {
        return deliveryRepository.findAll();
    }

    public Delivery getDeliveryById(int id) throws SQLException {
        return deliveryRepository.findById(id);
    }

    public boolean registerDelivery(Delivery delivery) throws SQLException {
        if (delivery.getDelivery_date() != null && delivery.getDelivery_received_date() != null && delivery.getReceived_by() == null) {
            throw new IllegalArgumentException("If delivery date is provided, received by name must be provided as well.");
        }

        return deliveryRepository.insert(delivery);
    }



    public boolean updateDelivery(Delivery delivery) throws SQLException {
        OrderRepository orderRepository = new OrderRepository();
        Order order = orderRepository.findById(delivery.getOrder_id());
        if (order != null) {
            if (delivery.getDelivery_status().equals("Entregue")) {
                order.setOrder_status("Finalizado");
                orderRepository.update(order);
            }
            return true;
        }
        return false;
    }


    public boolean deleteDelivery(int id) throws SQLException {
        return deliveryRepository.delete(id);
    }
}
