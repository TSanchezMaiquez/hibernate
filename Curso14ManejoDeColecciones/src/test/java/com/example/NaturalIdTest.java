package com.example;

import com.example.model.Customer;
import com.example.model.Employee;
import org.junit.jupiter.api.Test;

public class NaturalIdTest {
    /*La clase Customer tiene dos naturalId, por esa razon la consulta debe incluir esas dos columnas
    * para poder encontrar el objeto*/

    @Test
    void employeeTest(){
        createData();
        var session = HibernateUtil.getSessionFactory().openSession();

        var emp1 = session.find(Employee.class, 1L);
        var emp2 = session.byNaturalId(Employee.class)
                .using("dni", "222A")
                .load();

        //Cuando hay una unica clave natural
        var emp3 = session.bySimpleNaturalId(Employee.class). load("333A");
        System.out.println(emp1);
        System.out.println(emp2);
        System.out.println(emp3);

    }

    @Test
    void CustomerTest(){
        createData();
        var session = HibernateUtil.getSessionFactory().openSession();

        var cust1 =session.byNaturalId(Customer.class)
                .using("email", "1")
                .using("dni", "111AA")
                .load();

        System.out.println(cust1);


    }
    @Test
    void multiple(){
        createData();
        var session = HibernateUtil.getSessionFactory().openSession();

        session.byMultipleIds(Employee.class)
                .multiLoad(1L, 3L)
                .forEach(System.out::println);

        //Aun no soportado por hibernate 6.1
       /* session.byMultipleNaturalId(Employee.class)
                .multiLoad("111AA", "222AA")
                .forEach(System.out::println);*/
    }
    void createData (){

        var session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        var emp1 = new Employee("111A", "toñi", 3000.0);
        var emp2 = new Employee("222A", "toñi", 3000.0);
        var emp3 = new Employee("333A", "toñi", 3000.0);

        var cust1 = new Customer("toñi", "111AA", "1");
        var cust2 = new Customer("toñi", "222AA", "2");
        var cust3 = new Customer("toñi", "333AA", "3");

        session.persist(emp1);
        session.persist(emp2);
        session.persist(emp3);

        session.persist(cust1);
        session.persist(cust2);
        session.persist(cust3);

        session.getTransaction().commit();
        session.close();
    }
}
