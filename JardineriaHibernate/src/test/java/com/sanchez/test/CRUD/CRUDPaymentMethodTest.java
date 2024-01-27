package com.sanchez.test.CRUD;

import com.sanchez.jardineriahibernate.HibernateUtil;
import com.sanchez.jardineriahibernate.model.PaymentMethod;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Date;

public class CRUDPaymentMethodTest {

    @Test
    void persist() {

        Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();


        PaymentMethod payment1 = new PaymentMethod(1, 12345, new Date(2000, 2, 1), new BigDecimal("100.50"));


        PaymentMethod payment2 = new PaymentMethod(2, 67890, new Date(2001, 2, 3), new BigDecimal("75.25"));


        session.persist(payment1);
        session.persist(payment2);

        session.getTransaction().commit();

        session.close();

        System.out.println(payment1);
        System.out.println(payment2);

    }
    @Test
    void retrieve (){
        createData();
        Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        var pay1 = session.find(PaymentMethod.class, 1L);

        session.close();

        System.out.println(pay1);
    }

    @Test
    void update(){
        createData();
        Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        var pay1 = session.find(PaymentMethod.class, 1L);
        pay1.setTransactionCode(1111);
        session.merge(pay1);
        session.getTransaction().commit();

        session.close();

        System.out.println(pay1);
    }

    @Test
    void delete (){
        createData();
        Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        var pay1 = session.find(PaymentMethod.class, 1L);
        session.remove(pay1);
        session.getTransaction().commit();

        session.close();

        System.out.println(pay1);
    }
    void createData (){

        Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();


        PaymentMethod payment1 = new PaymentMethod(1, 12345, new Date(2000, 2, 1), new BigDecimal("100.50"));


        PaymentMethod payment2 = new PaymentMethod(2, 67890, new Date(2001, 2, 3), new BigDecimal("75.25"));


        session.persist(payment1);
        session.persist(payment2);

        session.getTransaction().commit();

        session.close();


    }



}
