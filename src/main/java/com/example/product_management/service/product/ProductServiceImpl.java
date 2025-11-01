package com.example.product_management.service.product;

import com.example.product_management.exceptions.GeneralServiceException;
import com.example.product_management.exceptions.NoDataFoundException;
import com.example.product_management.exceptions.ValidateServiceException;
import com.example.product_management.model.Product;
import com.example.product_management.repository.ProductRepository;
import com.example.product_management.validator.ProductoValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Clase de servicios con la lógica de negocio, se implementan los metodos de interfaz
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        try {
            return productRepository.findAll();
        } catch (NoDataFoundException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> showByName(String name) {
        try {
            List<Product> products = productRepository.findByNameContaining(name);

            if (products.isEmpty()) {
                throw new NoDataFoundException("No existen productos con ese nombre");
            }

            return products;
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public Product create(Product product) {
        try {
            ProductoValidator.validate(product);
            return productRepository.save(product);
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public Product edit(Product product) {
        try {
            ProductoValidator.validate(product);

            Product productUpdate = productRepository.findById(product.getId()).orElseThrow(() -> new NoDataFoundException("No existe producto con ese id"));
            productUpdate.setName(product.getName());
            productUpdate.setPrice(product.getPrice());
            productUpdate.setStock(product.getStock());

            return productRepository.save(productUpdate);
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public Product softEdit(Product product) {
        try {
            Product productUpdate = productRepository.findById(product.getId()).orElseThrow();

            if(product.getName() != null && !product.getName().isEmpty()){
                productUpdate.setName(product.getName());
            }

            if(product.getPrice() != null){
                productUpdate.setPrice(product.getPrice());
            }

            if(product.getStock() != null){
                productUpdate.setStock(product.getStock());
            }

            return productRepository.save(productUpdate);
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public Product hardDelete(int id) {
        try {
            Product product = productRepository.findById(id).orElseThrow(() -> new NoDataFoundException("No existe producto con ese id"));
            productRepository.delete(product);
            return product;
        } catch (NoDataFoundException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }
}
