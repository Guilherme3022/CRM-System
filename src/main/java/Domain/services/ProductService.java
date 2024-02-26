package Domain.services;

import Domain.entities.Product;
import infra.repository.ProductRepository;
import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() throws SQLException {
        return productRepository.findAll();
    }
    public List<Product> getAllProductsDeleted() throws SQLException {
        return productRepository.findAlldeleted();
    }

    public Product getProductById(int id) throws SQLException {
        return productRepository.findById(id);
    }

    public boolean registerProduct(Product product) throws SQLException {
        product.setLive(1);
        if (productRepository.findByName(product.getName()) != null) {
            throw new IllegalArgumentException("A product with the same name already exists.");
        }
        return productRepository.insert(product);
    }

    public boolean updateProduct(Product product) throws SQLException {
        return productRepository.update(product);
    }

    public boolean deleteProduct(int id) throws SQLException {
        return productRepository.delete(id);
    }
}
