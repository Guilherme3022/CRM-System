package Domain.services;

import Domain.entities.OrderProduct;
import infra.repository.OrderProductRepository;
import java.sql.SQLException;
import java.util.List;

public class OrderProductService {
    private final OrderProductRepository orderProductRepository;

    public OrderProductService(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    public List<OrderProduct> getAllOrderProducts() throws SQLException {
        return orderProductRepository.findAll();
    }
    public List<OrderProduct> getAllOrderProductsInactive() throws SQLException {
        return orderProductRepository.findAllInactive();
    }

    public OrderProduct getOrderProductById(int id) throws SQLException {
        return orderProductRepository.findById(id);
    }

    public boolean registerOrderProduct(OrderProduct orderProduct) throws SQLException {
        orderProduct.setLive(1);
        return orderProductRepository.insert(orderProduct);
    }

    public boolean updateOrderProduct(OrderProduct orderProduct) throws SQLException {
        return orderProductRepository.update(orderProduct);
    }

    public boolean deleteOrderProduct(int id) throws SQLException {
        return orderProductRepository.delete(id);
    }
}
