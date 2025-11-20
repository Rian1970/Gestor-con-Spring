package com.example.product_management.service.product;

import com.example.product_management.exceptions.NoDataFoundException;
import com.example.product_management.model.Product;
import com.example.product_management.repository.ProductRepository;
import com.example.product_management.validator.ProductoValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // -----------------------
    //     TEST FIND ALL
    // -----------------------
    @Test
    void testFindAll_ReturnsProducts() {
        List<Product> products = Arrays.asList(new Product(), new Product());

        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.findAll();

        assertEquals(2, result.size());
        verify(productRepository, times(1)).findAll();
    }

    // -----------------------
    //  TEST showByName
    // -----------------------
    @Test
    void testShowByName_ReturnsProducts() {
        String name = "Laptop";
        List<Product> products = List.of(new Product());

        when(productRepository.findByNameContaining(name)).thenReturn(products);

        List<Product> result = productService.showByName(name);

        assertFalse(result.isEmpty());
    }

    @Test
    void testShowByName_NoResults_ThrowsException() {
        when(productRepository.findByNameContaining("test")).thenReturn(Collections.emptyList());

        assertThrows(NoDataFoundException.class, () ->
                productService.showByName("test")
        );
    }

    // -----------------------
    //        CREATE
    // -----------------------
    @Test
    void testCreate_ProductSavedSuccessfully() {
        Product product = new Product();
        product.setName("Phone");
        product.setPrice(100.0);
        product.setStock(200);

        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.create(product);

        assertNotNull(result);
        verify(productRepository, times(1)).save(product);
    }

    // -----------------------
    //         EDIT
    // -----------------------
    @Test
    void testEdit_SuccessfulUpdate() {
        Product oldProduct = new Product();
        oldProduct.setId(1);
        oldProduct.setName("Old");
        oldProduct.setPrice(50.0);
        oldProduct.setStock(50);

        Product newProduct = new Product();
        newProduct.setId(1);
        newProduct.setName("New");
        newProduct.setPrice(100.0);
        newProduct.setStock(200);

        when(productRepository.findById(1)).thenReturn(Optional.of(oldProduct));
        when(productRepository.save(oldProduct)).thenReturn(oldProduct);

        Product result = productService.edit(newProduct);

        assertEquals("New", result.getName());
        assertEquals(100.0, result.getPrice());
    }

    // -----------------------
    //     HARD DELETE
    // -----------------------
    @Test
    void testHardDelete_ProductDeleted() {
        Product product = new Product();
        product.setId(1);

        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        Product result = productService.hardDelete(1);

        verify(productRepository, times(1)).delete(product);
        assertEquals(product, result);
    }

    @Test
    void testHardDelete_NotFound_ThrowsException() {
        when(productRepository.findById(10)).thenReturn(Optional.empty());

        assertThrows(NoDataFoundException.class, () ->
                productService.hardDelete(10)
        );
    }
}
