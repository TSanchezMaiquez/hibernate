package com.sanchez.test.JPQL;

import com.sanchez.jardineriahibernate.HibernateUtil;
import com.sanchez.jardineriahibernate.model.Office;
import com.sanchez.jardineriahibernate.model.OrderDetail;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class JPQLOrderDetailTest {


    @Test
    void findAll() {
        createData();
        Session session = HibernateUtil.getSessionFactory().openSession();
        var jpql = "select a from OrderDetail a";
        var query = session.createQuery(jpql, OrderDetail.class);

        query.list().forEach(System.out::println);
        session.close();
    }
    @Test
    void findby(){
        createData();
        Session session = HibernateUtil.getSessionFactory().openSession();
        var jpql = "select a from OrderDetail a where a.id = :id";
        var query = session.createQuery(jpql, OrderDetail.class);
        query.setParameter("id", 1L);
        query.list().forEach(System.out::println);
        session.close();
    }
   @Test
   void finBySomethingIn(){
       createData();
       Session session = HibernateUtil.getSessionFactory().openSession();
       var jpql = "select a from OrderDetail a where a.unitPrice in :prices";
       var query = session.createQuery(jpql, OrderDetail.class);
       query.setParameter("prices", List.of(new BigDecimal("10.00"), new BigDecimal("5.99")));

       query.list().forEach(System.out::println);
       session.close();
   }
    @Test
    void update(){
        createData();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        var jpql = "update OrderDetail o set o.lineNumber = :number where o.id = :id";
        var query = session.createQuery(jpql)
                .setParameter("number", 8)
                .setParameter("id", 1)
                .executeUpdate();

        session.getTransaction().commit();

        session.close();
    }
    void createData (){

        Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        OrderDetail orderDetail1 = new OrderDetail("Producto1", 5, new BigDecimal("10.00"), 1);
        OrderDetail orderDetail2 = new OrderDetail("Producto2", 3, new BigDecimal("15.50"), 2);
        OrderDetail orderDetail3 = new OrderDetail("Producto3", 2, new BigDecimal("20.75"), 3);
        OrderDetail orderDetail4 = new OrderDetail("Producto4", 1, new BigDecimal("5.99"), 4);
        OrderDetail orderDetail5 = new OrderDetail("Producto5", 4, new BigDecimal("8.25"), 5);


        session.persist(orderDetail1);
        session.persist(orderDetail2);
        session.persist(orderDetail3);
        session.persist(orderDetail4);
        session.persist(orderDetail5);

        session.getTransaction().commit();

        session.close();
    }
}
