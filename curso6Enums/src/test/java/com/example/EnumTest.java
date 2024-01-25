package com.example;

import com.example.model.Employee;
import com.example.model.EmployeeCategory;
import org.junit.jupiter.api.Test;

public class EnumTest {


    @Test
    void checkEnum(){

        insertData();
        var session = HibernateUtil.getSessionFactory().openSession();

        var emp1 = session.find(Employee.class, 1);
        System.out.println(emp1);

    }
    @Test
    void findByCategory(){

        insertData();
        var session = HibernateUtil.getSessionFactory().openSession();
        /*Otra manera de hacerlo seria sin pasar paramentros, en ":category pones "MANAGER" o "JUNIOR" y le quitas
        * el ".setParameter" a session*/
       String jpql = "select e from Employee e where e.category = :category";
       session.createQuery(jpql, Employee.class)
               .setParameter("category", EmployeeCategory.MANAGER)
               .getResultList()
               .forEach(System.out::println);

    }

    void insertData(){
        var session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();


        var emp1 = new Employee("11A", "emp1", 20, EmployeeCategory.JUNIOR);
        var emp2 = new Employee("22B", "emp2", 25, EmployeeCategory.SENIOR);
        var emp3 = new Employee("33C", "emp3", 30, EmployeeCategory.MANAGER);
        var emp4 = new Employee("44D", "emp4", 40, EmployeeCategory.C_LEVEL);

        session.persist(emp1);
        session.persist(emp2);
        session.persist(emp3);
        session.persist(emp4);

        session.getTransaction().commit();
    }
}
