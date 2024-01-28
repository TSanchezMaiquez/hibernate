package com.sanchez.test.CRUD;

import com.sanchez.jardineriahibernate.HibernateUtil;
import com.sanchez.jardineriahibernate.model.Product;
import com.sanchez.jardineriahibernate.model.ProductRange;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class CRUDProductTest {

    @Test
    void update() {

        createData();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        var productToUpdate = session.find(Product.class, "1");

        productToUpdate.setDescription("Nueva descripción");
        productToUpdate.setName("AAAAA");

        session.merge(productToUpdate);

        session.getTransaction().commit();
        session.close();
    }

    @Test
    void retrieve() {
        createData();
        Session session = HibernateUtil.getSessionFactory().openSession();

        var retrievedProduct = session.find(Product.class, "1");

        System.out.println(retrievedProduct);

        session.close();
    }

    @Test
    void delete() {
        createData();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        var productToDelete = session.find(Product.class, "1");

        session.remove(productToDelete);

        session.getTransaction().commit();
        session.close();
    }



    void createData() {

        Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();


        Product product1 = new Product("1", "Producto1", "Rango1", "10x20", "Proveedor1", "Descripción1", 100, new BigDecimal("25.99"), new BigDecimal("20.50"));
        Product product2 = new Product("2","Producto2", "Rango2", 50, new BigDecimal("19.99"));
        Product product3 = new Product("3", "Producto3", "Rango3", "15x30", "Proveedor2", "Descripción2", 75, new BigDecimal("35.50"), new BigDecimal("15.00"));
        Product product4 = new Product("4", "Producto4", "Rango1", "20x40", "Proveedor3", "Descripción3", 120, new BigDecimal("18.75"), new BigDecimal("10.00"));
        Product product5 = new Product("5", "Producto5", "Rango2", "25x50", "Proveedor4", "Descripción4", 90, new BigDecimal("22.50"), new BigDecimal("12.50"));


        session.persist(product1);
        session.persist(product2);
        session.persist(product3);
        session.persist(product4);
        session.persist(product5);

        session.getTransaction().commit();

        session.close();

    }
}
