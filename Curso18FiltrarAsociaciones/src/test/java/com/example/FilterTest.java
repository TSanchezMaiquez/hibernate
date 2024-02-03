package com.example;

import com.example.filter.Employee;
import org.junit.jupiter.api.Test;

public class FilterTest {

    @Test
    void name() {
        insertData();
        var session = HibernateUtil.getSessionFactory().openSession();
        /*Llamamos al filtro con el nombre que se le ha asignado en la entity origen y le pasamos los parametros*/
        session.enableFilter("ageFilter")
                .setParameter("minAge", 25)
                .setParameter("maxAge", 50);
        /*Como el filtro se asigna a un objeto session este desaparecera cuando se cierre dicha session
        * En esta consulta se aplica el filtro pero en la que hay debajo NO ya que se ha cerrado la sesion*/
        session.createQuery("from Employee", Employee.class)
                .getResultList().forEach(System.out::println);

        session.close();

        session = HibernateUtil.getSessionFactory().openSession();
        session.createQuery("from Employee", Employee.class)
                .getResultList().forEach(System.out::println);
    }

    void insertData(){

        var session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        var emp1 = new Employee("emp1", 18);
        var emp2 = new Employee("emp1", 23);
        var emp3 = new Employee("emp1", 30);
        var emp4 = new Employee("emp1", 40);
        var emp5 = new Employee("emp1", 60);

        session.persist(emp1);
        session.persist(emp2);
        session.persist(emp3);
        session.persist(emp4);
        session.persist(emp5);

        session.getTransaction().commit();

    }
}

