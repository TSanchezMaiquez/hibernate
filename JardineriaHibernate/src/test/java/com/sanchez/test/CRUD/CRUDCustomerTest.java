package com.sanchez.test.CRUD;

import com.sanchez.jardineriahibernate.HibernateUtil;
import com.sanchez.jardineriahibernate.model.Customer;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class CRUDCustomerTest {

    @Test
    void persist() {

        Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        //String name, String surname1, String extenxion, String email, String officeCod
        var customer1 = new Customer("NombreCliente1", "Contacto1", "ApellidoContacto1",
                "123456789", "123456789", "Direccion1", "Direccion2", "Ciudad1", "Region1",
                "Pais1", "12345", 101, new BigDecimal("10000.00"));

        // Segundo constructor
        var customer2 = new Customer("NombreCliente2", "987654321", "987654321",
                "OtraDireccion", "OtraCiudad");
        session.persist(customer1);
        session.persist(customer2);

        session.getTransaction().commit();

        session.close();

        System.out.println(customer1);
        System.out.println(customer2);

    }
    @Test
    void retrieve () {

        createDate();
        Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        var cust1 = session.find(Customer.class, 1L);
        var cust2 = session.find(Customer.class, 2L);

        session.close();

        System.out.println(cust1);
        System.out.println(cust2);

    }
    @Test
    void update () {

        createDate();
        Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        var cust1 = session.find(Customer.class, 1L);
        var cust2 = session.find(Customer.class, 2L);

        cust1.setPhoneNumber("11111111");
        cust2.setPhoneNumber("11111111");

        session.merge(cust1);
        session.merge(cust2);

        session.flush();
        session.clear();

        cust1 = session.find(Customer.class, 1L);
        cust2 = session.find(Customer.class, 2L);

        session.getTransaction().commit();
        session.close();

        System.out.println(cust1);
        System.out.println(cust2);
    }
    @Test
    void remove () {

        createDate();
        Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        var cust1 = session.find(Customer.class, 1L);
        session.remove(cust1);

        session.getTransaction().commit();


        System.out.println(session.contains(cust1));
        System.out.println(cust1);
        session.close();



    }
    void createDate(){

        Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        //String name, String surname1, String extenxion, String email, String officeCod
        var customer1 = new Customer("NombreCliente1", "Contacto1", "ApellidoContacto1",
                "123456789", "123456789", "Direccion1", "Direccion2", "Ciudad1", "Region1",
                "Pais1", "12345", 101, new BigDecimal("10000.00"));

        // Segundo constructor
        var customer2 = new Customer("NombreCliente2", "987654321", "987654321",
                "OtraDireccion", "OtraCiudad");
        session.persist(customer1);
        session.persist(customer2);

        session.getTransaction().commit();

        session.close();
    }


}
