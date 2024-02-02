package com.example;

import com.example.model.CreditCard;
import com.example.model.Employee;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionsTest {

    @Test
    void basicTypes(){
        createData();
        var session = HibernateUtil.getSessionFactory().openSession();

        var emp1 = session.find(Employee.class, 1L);
        System.out.println(emp1.getPhones()); // igual de valido que emp1.getPhones().forEach(sout)
    }
    @Test
    void basicTypesSalaries(){
        createData();
        var session = HibernateUtil.getSessionFactory().openSession();

        var emp3 = session.find(Employee.class, 3L);

        System.out.println(emp3.getPhones());
        //reduce suma hasta que no queden valores. ifPresent confirma si hay valor y lo imprime
        emp3.getSalaries().stream().reduce((a, b) -> a+b).ifPresent(System.out::println);
    }
    @Test
    void basicPostalCodes(){
        createData();
        var session = HibernateUtil.getSessionFactory().openSession();

        var emp5 = session.find(Employee.class, 5L);

        System.out.println(emp5.getPhones());
        System.out.println(emp5.getSalaries());
        System.out.println(emp5.getPostalCodes());
    }

      @Test
      void entityType(){
          createData();
          var session = HibernateUtil.getSessionFactory().openSession();

          var emp6 = session.find(Employee.class, 6L);
          emp6.getCards().forEach((k, v) -> System.out.println("k: "+k+", v: "+ v));
      }
    void createData(){

        var session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        var emp1 = new Employee("emp1", "1111", List.of("1111", "2222", "3333"));
        var emp2 = new Employee("emp2", "111144", List.of("1111333", "2222444", "3333555"));

        session.persist(emp1);
        session.persist(emp2);


        var emp3 = new Employee("emp3", "1111", List.of("1111", "2222", "3333"), List.of(1220.0, 1500.0, 2000.0));
        var emp4 = new Employee("emp4", "111144", List.of("1111333", "2222444", "3333555"), List.of(3000.0, 3550.0, 4000.0));

        session.persist(emp3);
        session.persist(emp4);

        var emp5 = new Employee("emp3", "1111",
                List.of("1111", "2222", "3333"),
                Set.of("24000", "456778", "235345"),
                List.of(1220.0, 1500.0, 2000.0));
        session.persist(emp5);

        var cc1 = new CreditCard("111", "emp6");
        var cc2 = new CreditCard("11133", "emp6");
        var cc3 = new CreditCard("11144", "emp6");

        session.persist(cc1);
        session.persist(cc2);
        session.persist(cc3);

        var emp6 = new Employee("emp6", "2222",
                Map.of(
                   "111", cc1,
                        "222", cc2,
                        "333", cc3
                ));
        session.persist(emp6);

        session.getTransaction().commit();
        session.close();
    }
}
