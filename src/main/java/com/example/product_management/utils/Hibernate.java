package com.example.product_management.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import com.example.product_management.model.Product;

@Component
public class Hibernate {
    @Bean
    @Primary
    public SessionFactory sessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(Product.class);
            return configuration.buildSessionFactory();
        } catch (Exception e) {
            throw new RuntimeException("Error creando la SessionFactory: " + e.getMessage(), e);
        }
    }
}
