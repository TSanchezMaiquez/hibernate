package com.sanchez.test.JPQL;

import com.sanchez.jardineriahibernate.HibernateUtil;
import com.sanchez.jardineriahibernate.model.Customer;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class JPQLCustomerTest {

    @Test
    void findAll(){
        createDate();
        Session session =  HibernateUtil.getSessionFactory().openSession();

        var jpql = "SELECT a from Customer a";
        var query = session.createQuery(jpql, Customer.class);

        query.list().forEach(System.out::println);

    }
    @Test
    void findByName(){
        createDate();
        Session session =  HibernateUtil.getSessionFactory().openSession();
        var jpql = "SELECT a from Customer a where a.customerName = :customerName ";
        var query = session.createQuery(jpql, Customer.class);
        //Nombre de la tabla
        query.setParameter("customerName", "NombreCliente1");

        var customer = query.getSingleResult();
        System.out.println(customer);
    }

    @Test
    void findBetweenCredits(){
        createDate();
        Session session =  HibernateUtil.getSessionFactory().openSession();
        var jpql = "SELECT a from Customer a where a.creditLimit BETWEEN :start AND :end";
        var query = session.createQuery(jpql, Customer.class);

        query.setParameter("start", 13000);
        query.setParameter("end", 15000);

        query.list().forEach(System.out::println);

    }
    @Test
    void findByFaxIn(){
        createDate();
        Session session =  HibernateUtil.getSessionFactory().openSession();
        var jpql = "SELECT a from Customer a where a.fax IN :faxes";
        var query = session.createQuery(jpql, Customer.class);

        query.setParameter("faxes", List.of("123456789", "987654321"));

        query.list().forEach(System.out::println);
    }

    @Test
    void count(){
        createDate();
        Session session =  HibernateUtil.getSessionFactory().openSession();
        var jpql = "select count (a.id) from Customer a";
        var query = session.createQuery(jpql, Customer.class);

        System.out.println(query.getSingleResult());
    }

    @Test
    void update(){
        createDate();
        Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        var jpql = "update Customer a set a.customerName = :name where a.customerCode = :id";

        var update = session.createMutationQuery(jpql)
                        .setParameter("name", "Yunno")
                        .setParameter("id", 1)
                        .executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println(update);
    }
    void createDate(){

        Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        //String name, String surname1, String extenxion, String email, String officeCod
        var customer1 = new Customer("NombreCliente1", "Contacto1", "ApellidoContacto1",
                "123456789", "123456789", "Direccion1", "Direccion2", "Ciudad1", "Region1",
                "Pais1", "12345", 101, new BigDecimal("10000.00"));


        var customer2 = new Customer("NombreCliente2", "987654321", "987694321",
                "OtraDireccion", "OtraCiudad");

        var customer3 = new Customer("NombreCliente3", "Contacto3", "ApellidoContacto3",
                "987654321", "987654321", "OtraDireccion3", "OtraDireccion4", "Ciudad3", "Region3",
                "Pais3", "54321", 103, new BigDecimal("15000.00"));


        var customer4 = new Customer("NombreCliente4", "456789012", "456779012",
                "Direccion4", "Ciudad4");

        var customer5 = new Customer("NombreCliente3", "Contacto3", "ApellidoContacto3",
                "987654321", "987654621", "OtraDireccion3", "OtraDireccion4", "Ciudad3", "Region3",
                "Pais3", "54321", 103, new BigDecimal("13000.00"));


        session.persist(customer1);
        session.persist(customer2);
        session.persist(customer3);
        session.persist(customer4);
        session.persist(customer5);


        session.getTransaction().commit();

        session.close();
    }
}
