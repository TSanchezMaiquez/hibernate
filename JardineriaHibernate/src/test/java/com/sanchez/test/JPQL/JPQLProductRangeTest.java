package com.sanchez.test.JPQL;

import com.sanchez.jardineriahibernate.HibernateUtil;
import com.sanchez.jardineriahibernate.model.CustomerOrder;
import com.sanchez.jardineriahibernate.model.Product;
import com.sanchez.jardineriahibernate.model.ProductRange;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class JPQLProductRangeTest {

    @Test
    void findByAll(){
        createData();
        Session session =  HibernateUtil.getSessionFactory().openSession();

        var jpql = "select a from ProductRange a ";
        var query = session.createQuery(jpql, ProductRange.class);

        query.list().forEach(System.out::println);
    }


    void createData() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Product product1 = new Product("1", "Producto1", "Rango1", "10x20", "Proveedor1", "Descripción1", 100, new BigDecimal("25.99"), new BigDecimal("20.50"));
        Product product2 = new Product("2", "Producto2", "Rango2", 50, new BigDecimal("19.99"));
        Product product3 = new Product("3", "Producto3", "Rango3", "15x25", "Proveedor2", "Descripción3", 75, new BigDecimal("30.50"), new BigDecimal("25.99"));
        Product product4 = new Product("4", "Producto4", "Rango1", "12x18", "Proveedor3", "Descripción4", 120, new BigDecimal("15.99"), new BigDecimal("12.50"));
        Product product5 = new Product("5", "Producto5", "Rango2", "8x16", "Proveedor1", "Descripción5", 90, new BigDecimal("22.50"), new BigDecimal("18.99"));

        session.persist(product1);
        session.persist(product2);
        session.persist(product3);
        session.persist(product4);
        session.persist(product5);

        session.getTransaction().commit();
        session.close();
    }
}
