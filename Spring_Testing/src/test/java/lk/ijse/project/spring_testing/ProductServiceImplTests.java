package lk.ijse.project.spring_testing;

import lk.ijse.project.spring_testing.entity.Product;
import lk.ijse.project.spring_testing.repo.ProductRepository;
import lk.ijse.project.spring_testing.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTests {
    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    private Product product;

    @BeforeEach
    public void setUp() {
        product = Product.builder()
                .id(1L)
                .name("Test Product")
                .price(10.11)
                .quantity(12).build();
    }

    @Test
    void shouldSaveProduct() {
        //arrange
        when(productRepository.save(any(Product.class))).thenReturn(product);

        //action
        Product saveProduct = productService.createProduct(product);

        //assert
        Assertions.assertNotNull(saveProduct);
        Assertions.assertEquals(product, saveProduct);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void shouldUpdateProduct() {
        Product updateProducts = Product.builder()
                .id(1L)
                .name("Update Products")
                .price(10.11)
                .quantity(12).build();

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(updateProducts);

        //action
        Product result = productService.updateProduct(updateProducts);
        //assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(updateProducts, result);
        Assertions.assertEquals("Update Products", result.getName());
        Assertions.assertEquals(10.11, result.getPrice());
        Assertions.assertEquals(12, result.getQuantity());
        verify(productRepository, times(1)).findById(1L);
    }


}
