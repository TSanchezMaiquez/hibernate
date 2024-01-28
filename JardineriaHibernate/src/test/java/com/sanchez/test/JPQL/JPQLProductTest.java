package com.sanchez.test.JPQL;

import com.sanchez.jardineriahibernate.HibernateUtil;
import com.sanchez.jardineriahibernate.model.Product;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class JPQLProductTest {

    @Test
    void findAll() {
        createData();
        Session session = HibernateUtil.getSessionFactory().openSession();
        var jpql = "select p from Product p";
        var query = session.createQuery(jpql, Product.class);

        query.list().forEach(System.out::println);

        session.close();
    }
    @Test
    void findBySomething() {
        createData();
        Session session = HibernateUtil.getSessionFactory().openSession();
        var jpql = "select p from Product p where p.rangeId = :category";
        var query = session.createQuery(jpql, Product.class);
        query.setParameter("category", "Rango1"); // Reemplaza con la categoría que desees buscar

        query.list().forEach(System.out::println);

        session.close();
    }
    @Test
    void findProductsInStockLowerThan() {
        createData();
        Session session = HibernateUtil.getSessionFactory().openSession();
        var jpql = "select p from Product p where p.stock < :stock";
        var query = session.createQuery(jpql, Product.class);
        query.setParameter("stock", 100); // Reemplaza con la cantidad de stock que desees buscar

        query.list().forEach(System.out::println);

        session.close();
    }
    @Test
    void findIn(){
        createData();
        Session session = HibernateUtil.getSessionFactory().openSession();
        var jpql = "select p from Product p where p.productCode in :codes";
        var query = session.createQuery(jpql, Product.class);
        query.setParameter("codes", List.of("1", "2", "5"));

        query.list().forEach(System.out::println);

        session.close();

    }

    @Test
    void update(){
        createData();
        var session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        var jpql = "update Product a set a.stock = :stock where a.productCode = :id";
        var query = session.createMutationQuery(jpql)
                .setParameter("stock", 88)
                .setParameter("id", "1")
                .executeUpdate();
        session.getTransaction().commit();
        System.out.println(query);

    }
    @Test
    void between(){
        createData();
        Session session = HibernateUtil.getSessionFactory().openSession();
        var jpql = "select p from Product p where p.stock between :minStock and :maxStock";
        var query = session.createQuery(jpql, Product.class);
        query.setParameter("minStock", 0);
        query.setParameter("maxStock", 100);

        query.list().forEach(System.out::println);
    }
    @Test
    void equal(){
        createData();
        Session session = HibernateUtil.getSessionFactory().openSession();

        String jpql = "SELECT p FROM Product p WHERE p.name = :productName";
        var query = session.createQuery(jpql, Product.class);
        query.setParameter("productName", "Producto1");

        List<Product> products = query.getResultList();
        products.forEach(System.out::println);

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
