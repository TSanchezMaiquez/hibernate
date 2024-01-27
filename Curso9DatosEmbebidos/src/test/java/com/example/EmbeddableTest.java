package com.example;

import com.example.model.example1.Address;
import com.example.model.example1.Customer;
import com.example.model.example1.Employee;
import org.junit.jupiter.api.Test;

import java.util.Currency;

public class EmbeddableTest {

    @Test
    void embeddedTest(){
        insertData();
        var session = HibernateUtil.getSessionFactory().openSession();

        var c1 = session.find(Customer.class, 1L);
        var e1 = session.find(Employee.class, 1L);
        System.out.println(c1);
        System.out.println(e1);
    }
    void insertData(){

        var session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        var address1 = new Address("s1", "c1", "co1");
        var address2 = new Address("s2", "c2", "co2");

        var customer1 = new Customer("c1", 18, address1);
        var customer2 = new Customer("c2", 18, address2);

        var employee1 = new Employee("em1", 20, address1);
        var employee2 = new Employee("em2", 20, address2);

        session.persist(customer1);
        session.persist(customer2);
        session.persist(employee1);
        session.persist(employee2);

        session.getTransaction().commit();
        session.close();
    }
}
