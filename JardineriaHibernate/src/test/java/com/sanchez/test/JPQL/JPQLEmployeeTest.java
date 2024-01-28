package com.sanchez.test.JPQL;

import com.sanchez.jardineriahibernate.HibernateUtil;
import com.sanchez.jardineriahibernate.model.Employee;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JPQLEmployeeTest {

    @Test
    void findAllEmployees() {
        createData();

        Session session = HibernateUtil.getSessionFactory().openSession();

        // Assuming your Employee entity is named "Employee"
        var jpql = "select e from Employee e";
        var query = session.createQuery(jpql, Employee.class);

        query.list().forEach(System.out::println);

        session.close();
    }
    @Test
    void findByEmail() {
        createData(); // Assuming createData() method exists to populate some data

        Session session = HibernateUtil.getSessionFactory().openSession();

        // Assuming your Employee entity is named "Employee" and the email is a field named "email"
        var jpql = "select e from Employee e where e.email = :email";
        var query = session.createQuery(jpql, Employee.class);
        query.setParameter("email", "asdfasdf"); // Replace with the actual email you want to search

        System.out.println(query.getSingleResult());

        session.close();
    }
    @Test
    void findEmployeesByPositionIn() {
        createData(); // Assuming createData() method exists to populate some data

        Session session = HibernateUtil.getSessionFactory().openSession();

        // Assuming your Employee entity is named "Employee" and position is a field
        var jpql = "select e from Employee e where e.position in :positions";
        var query = session.createQuery(jpql, Employee.class);
        query.setParameter("positions", List.of("123", "456", "789"));

        query.list().forEach(System.out::println);

        session.close();
    }
    @Test
    void findByManagerCodeBetween() {
        createData(); // Assuming createData() method exists to populate some data

        Session session = HibernateUtil.getSessionFactory().openSession();

        // Assuming your Employee entity is named "Employee" and managerCode is a numerical field
        var jpql = "select e from Employee e where e.managerCode between :start and :end";
        var query = session.createQuery(jpql, Employee.class);
        query.setParameter("start", 1); // Replace with the starting managerCode value
        query.setParameter("end", 3);   // Replace with the ending managerCode value

        query.list().forEach(System.out::println);

        session.close();
    }

    @Test
    void updateEmployeePosition() {
        createData(); // Assuming createData() method exists to populate some data

        var session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Assuming your Employee entity is named "Employee" and position is a field
        var jpql = "update Employee e set e.position = :newPosition where e.id = :id";
        var query = session.createQuery(jpql)
                .setParameter("newPosition", "Senior Developer") // Replace with the new position value
                .setParameter("id", 1) // Replace with the actual Employee ID you want to update
                .executeUpdate();

        session.getTransaction().commit();
        session.close();
        System.out.println(query);
    }
    void createData () {

        Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        //String name, String surname1, String extenxion, String email, String officeCod
        var employee1 = new Employee("employee1", "ape", "1234", "anasf", "4");
        //String name, String surname1, String surname2, String extenxion, String email, String officeCod, Employee managerCode, String position
        var employee2 = new Employee("employee2", "ap1", "sur2", "234", "asdfasdf", "asdf", 1, "123");
        var employee3 = new Employee("employee3", "ap2", "sur3", "345", "qwerqwer", "qwer", 2, "456");
        var employee4 = new Employee("employee4", "ap3", "sur4", "456", "zxcvzxcv", "zxcv", 6, "789");
        var employee5 = new Employee("employee5", "ap4", "sur5", "567", "uiopuiop", "uiop", 3, "987");
        var employee6 = new Employee("employee6", "ap5", "sur6", "678", "tyuityui", "tyui", 5, "654");

        session.persist(employee1);
        session.persist(employee2);
        session.persist(employee3);
        session.persist(employee4);
        session.persist(employee5);
        session.persist(employee6);

        session.getTransaction().commit();

        session.close();
    }
}
