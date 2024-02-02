package com.example;

import com.example.mappedsuperclass.Customer;
import com.example.mappedsuperclass.Employee;
import com.example.mappedsuperclass.User;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class MappedSuperClassTest {
    @Test
    void mappedSuperClass(){
        createData();
        var session = HibernateUtil.getSessionFactory().openSession();

        var emps = session.createQuery("select a from Employee a", Employee.class)
                .list();
        emps.forEach(System.out::println);

        var customers = session.createQuery("select a from Customer a", Customer.class)
                .list();
        customers.forEach(System.out::println);

        var customers2 = session.createQuery("select a from Customer a", User.class)
                .list();
        customers2.forEach(System.out::println);
    }


    private void createData() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        // mapped super class
        var employee1 = new Employee("7777", 1999.9, LocalDate.now());
        var employee2 = new Employee("7777", 1999.9, LocalDate.now());
        var customer1 = new Customer("777", "email", "street", "666");
        var customer2 = new Customer("777", "email", "street", "666");

        session.persist(employee1);
        session.persist(employee2);
        session.persist(customer1);
        session.persist(customer2);

        session.getTransaction().commit();
        session.close();
    }
}
