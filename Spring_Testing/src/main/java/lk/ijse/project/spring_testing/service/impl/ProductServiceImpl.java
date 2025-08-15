package lk.ijse.project.spring_testing.service.impl;

import lk.ijse.project.spring_testing.entity.Product;
import lk.ijse.project.spring_testing.repo.ProductRepository;
import lk.ijse.project.spring_testing.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor/*constructor injection*/
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product not found" + id)
        );
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Product existProduct = productRepository.getProductById(product.getId());
        existProduct.setName(product.getName());
        existProduct.setPrice(product.getPrice());
        existProduct.setQuantity(product.getQuantity());
        return productRepository.save(existProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);

    }
}
