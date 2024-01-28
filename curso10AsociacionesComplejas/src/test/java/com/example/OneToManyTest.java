package com.example;

import com.example.model.onetomany.Company;
import com.example.model.onetomany.Employee;
import org.junit.jupiter.api.Test;

public class OneToManyTest {

    @Test
    void oneToManyTest(){

        insertData();
        var session = HibernateUtil.getSessionFactory().openSession();

        var com1 = session.find(Company.class, 1L);
        com1.getEmployees().forEach(System.out::println);


    }

    void insertData(){
        var session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        var company1 = new Company("V34523452345");
        var company2 = new Company("V345234545345");

        /*var emp1 = new Employee("emp1", company1);
        var emp2 = new Employee("emp2", company1);
        var emp3 = new Employee("emp3", company1);*/
        var emp1 = new Employee("emp1");
        var emp2 = new Employee("emp2");
        var emp3 = new Employee("emp3");

        company1.getEmployees().add(emp1);
        company1.getEmployees().add(emp2);
        company1.getEmployees().add(emp3);

        session.persist(company1);
        session.persist(company2);

        session.persist(emp1);
        session.persist(emp2);
        session.persist(emp3);

        session.getTransaction().commit();
        session.close();
    }
}
