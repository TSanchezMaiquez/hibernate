package com.sanchez.test.JPQL;

import com.sanchez.jardineriahibernate.HibernateUtil;
import com.sanchez.jardineriahibernate.model.PaymentMethod;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Date;

public class JPQLPaymentMethodTest {
    @Test
    void findAll() {
        createData();
        Session session = HibernateUtil.getSessionFactory().openSession();
        var jpql = "select p from PaymentMethod p";
        var query = session.createQuery(jpql, PaymentMethod.class);

        query.list().forEach(System.out::println);

        session.close();
    }

    @Test
    void findPaymentMethodsByAccountNumber() {
        createData();
        Session session = HibernateUtil.getSessionFactory().openSession();
        var jpql = "select p from PaymentMethod p where p.transactionCode = :accountNumber";
        var query = session.createQuery(jpql, PaymentMethod.class);
        query.setParameter("accountNumber", 67890); // Replace with the actual account number you want to search

        query.list().forEach(System.out::println);

        session.close();
    }

    @Test
    void findSomethingByAmountGreaterThan() {
        createData();
        Session session = HibernateUtil.getSessionFactory().openSession();
        var jpql = "select p from PaymentMethod p where p.totalAmount > :amount";
        var query = session.createQuery(jpql, PaymentMethod.class);
        query.setParameter("amount", new BigDecimal("100.00")); // Replace with the actual amount you want to search

        query.list().forEach(System.out::println);

        session.close();
    }

    void createData (){

        Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();


        PaymentMethod payment1 = new PaymentMethod(1, 12345, new Date(2000, 2, 1), new BigDecimal("100.50"));
        PaymentMethod payment2 = new PaymentMethod(2, 67890, new Date(2001, 2, 3), new BigDecimal("75.25"));
        PaymentMethod payment3 = new PaymentMethod(3, 13579, new Date(2002 - 1900, 4 - 1, 15), new BigDecimal("150.75"));
        PaymentMethod payment4 = new PaymentMethod(4, 24680, new Date(2003 - 1900, 6 - 1, 7), new BigDecimal("50.00"));
        PaymentMethod payment5 = new PaymentMethod(5, 98765, new Date(2004 - 1900, 8 - 1, 22), new BigDecimal("120.25"));


        session.persist(payment1);
        session.persist(payment2);
        session.persist(payment3);
        session.persist(payment4);
        session.persist(payment5);

        session.getTransaction().commit();

        session.close();


    }
}
