package com.sanchez.test.CRUD;

import com.sanchez.jardineriahibernate.HibernateUtil;
import com.sanchez.jardineriahibernate.model.Employee;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

public class CRUDEmployeeTest {

    @Test
    void persist() {

        Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        //String name, String surname1, String extenxion, String email, String officeCod
        var Employee1 = new Employee("employee1", "ape", "1234", "anasf", "1");
        //String name, String surname1, String surname2, String extenxion, String email, String officeCod, Employee managerCode, String position
        var Employee2 = new Employee("employee2", "ap1", "sur2", "234", "asdfasdf", "asdf", 1, "123");

        session.persist(Employee1);
        session.persist(Employee2);

        session.getTransaction().commit();

        session.close();

        System.out.println(Employee1);
        System.out.println(Employee2);

    }
    @Test
    void retrieve (){
        createData();
        Session session =  HibernateUtil.getSessionFactory().openSession();

        var emp1 = session.find(Employee.class, 1L);
        var emp2 = session.find(Employee.class, 2L);

        session.close();
        System.out.println(emp1);
        System.out.println(emp2);
    }
    @Test
    void update () {

        createData();
        Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        var emp1 = session.find(Employee.class, 1L);
        var emp2 = session.find(Employee.class, 2L);

        emp1.setSurname1("AAAAAA");
        emp2.setSurname1("AAAAAA");

        session.merge(emp1);
        session.merge(emp2);

        session.getTransaction().commit();

        session.refresh(emp1);

        session.close();

        emp2.setSurname1("EEEEE");

        System.out.println(emp1);
        System.out.println(emp2);
    }

    @Test
    void delete () {

        createData();
        Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        var emp1 = session.find(Employee.class, 1L);
        session.remove(emp1);

        session.getTransaction().commit();

        session.close();

    }
    void createData () {

        Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        //String name, String surname1, String extenxion, String email, String officeCod
        var Employee1 = new Employee("employee1", "ape", "1234", "anasf", "1");
        //String name, String surname1, String surname2, String extenxion, String email, String officeCod, Employee managerCode, String position
        var Employee2 = new Employee("employee2", "ap1", "sur2", "234", "asdfasdf", "asdf", 1, "123");

        session.persist(Employee1);
        session.persist(Employee2);

        session.getTransaction().commit();

        session.close();
    }
}
