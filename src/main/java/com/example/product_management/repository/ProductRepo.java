package com.example.product_management.repository;

import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.product_management.model.Product;

import java.util.List;

@Repository
public class ProductRepo {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Product> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Product", Product.class).list();
        }
    }

    public Product findByNombre(String nombre){
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Product WHERE nombre = :nombre", Product.class).setParameter("nombre", nombre).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
