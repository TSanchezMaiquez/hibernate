package com.sanchez.test.CRUD;

import com.sanchez.jardineriahibernate.HibernateUtil;
import com.sanchez.jardineriahibernate.model.OrderDetail;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class CRUDOrderDetailTest {

    @Test
    void persist() {

        Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        OrderDetail orderDetail1 = new OrderDetail("Producto1", 5, new BigDecimal("10.00"), 1);

        // Segundo objeto
        OrderDetail orderDetail2 = new OrderDetail("Producto2", 3, new BigDecimal("15.50"), 2);


        session.persist(orderDetail1);
        session.persist(orderDetail2);

        session.getTransaction().commit();

        session.close();

        System.out.println(orderDetail1);
        System.out.println(orderDetail2);

    }
    @Test
    void retrieve (){
        createData();
        Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        var od1 = session.find(OrderDetail.class, 1L);

        session.close();

        System.out.println(od1);

    }

    @Test
    void update () {
        createData();
        Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        var od1 = session.find(OrderDetail.class, 1L);
        od1.setAmount(7);

        session.merge(od1);

        session.getTransaction().commit();

        session.close();

        System.out.println(od1);
    }
    @Test
    void delete (){
        createData();
        Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        var od1 = session.find(OrderDetail.class, 1L);

        session.remove(od1);

        session.getTransaction().commit();

        session.close();

        System.out.println(od1);
    }
    void createData (){

        Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        OrderDetail orderDetail1 = new OrderDetail("Producto1", 5, new BigDecimal("10.00"), 1);

        // Segundo objeto
        OrderDetail orderDetail2 = new OrderDetail("Producto2", 3, new BigDecimal("15.50"), 2);


        session.persist(orderDetail1);
        session.persist(orderDetail2);

        session.getTransaction().commit();

        session.close();

        System.out.println(orderDetail1);
        System.out.println(orderDetail2);
    }
}
