package com.sanchez.test.CRUD;

import com.sanchez.jardineriahibernate.HibernateUtil;
import com.sanchez.jardineriahibernate.model.Product;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class CRUDProductRangeTest {

    @Test
    void persist() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Product product1 = new Product("1", "Producto1", "Rango1", "10x20", "Proveedor1", "Descripción1", 100, new BigDecimal("25.99"), new BigDecimal("20.50"));

        Product product2 = new Product("2", "Producto2", "Rango2", 50, new BigDecimal("19.99"));

        session.persist(product1);
        session.persist(product2);

        session.getTransaction().commit();

        session.close();

        System.out.println(product1);
        System.out.println(product2);

    }

    @Test
    void update() {

        createData();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Recuperar un producto por su identificador
        Product productToUpdate = session.find(Product.class, "1");

        // Actualizar propiedades del producto
        productToUpdate.setDescription("Nueva descripción");
        productToUpdate.setSalesPrice(new BigDecimal("29.99"));

        // Guardar los cambios
        session.merge(productToUpdate);

        session.getTransaction().commit();
        session.close();
    }

    @Test
    void retrieve() {
        createData();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Recuperar un producto por su identificador
        Product retrievedProduct = session.find(Product.class, "1");

        System.out.println(retrievedProduct);

        session.getTransaction().commit();
        session.close();
    }

    @Test
    void delete() {
        createData();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Recuperar un producto por su identificador
        Product productToDelete = session.find(Product.class, "1");

        // Eliminar el producto
        session.remove(productToDelete);

        session.getTransaction().commit();
        session.close();
    }

    void createData() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Product product1 = new Product("1", "Producto1", "Rango1", "10x20", "Proveedor1", "Descripción1", 100, new BigDecimal("25.99"), new BigDecimal("20.50"));

        Product product2 = new Product("2", "Producto2", "Rango2", 50, new BigDecimal("19.99"));

        session.persist(product1);
        session.persist(product2);

        session.getTransaction().commit();
        session.close();
    }
}