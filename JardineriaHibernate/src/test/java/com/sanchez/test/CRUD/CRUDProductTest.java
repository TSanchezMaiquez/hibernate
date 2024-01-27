package com.sanchez.test.CRUD;

import com.sanchez.jardineriahibernate.HibernateUtil;
import com.sanchez.jardineriahibernate.model.Product;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class CRUDProductTest {

    @Test
    void persist() {

        Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();


        Product product1 = new Product("1", "Producto1", "Rango1", "10x20", "Proveedor1", "Descripción1", 100, new BigDecimal("25.99"), new BigDecimal("20.50"));

        Product product2 = new Product("2","Producto2", "Rango2", 50, new BigDecimal("19.99"));


        session.persist(product1);
        session.persist(product2);

        session.getTransaction().commit();

        session.close();

        System.out.println(product1);
        System.out.println(product2);

    }
}
