package com.example;

import com.example.model.Employee;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

public class QueryTest {


    @Test
    void list_And_GetResultList() {
        insertData();
        var session = HibernateUtil.getSessionFactory().openSession();

        String jpql = "select e from Employee e";
        Query<Employee> query = session.createQuery(jpql, Employee.class);

        /*hacen exactamente lo mismo. getResultList() lo unico que hace es llamar a list(), de ahi que si lo
        * sustituyes por list() es lo mismo*/
        query.getResultList().forEach(System.out::println);
        System.out.println("======");
        query.list().forEach(System.out::println);
    }

    @Test
    void getResultStream() {
        insertData();
        var session = HibernateUtil.getSessionFactory().openSession();

        //Consulta 1
        String jpql = "select e from Employee e";
        Query<Employee> query = session.createQuery(jpql, Employee.class);
        var emails = query.getResultList().stream()
                .map(e -> e.getEmail())
                .map(email -> email.toUpperCase())
                .collect(Collectors.toList());
        System.out.println(emails);

        System.out.println("======");  //consulta 1 y consulta 2 hacen exactamente lo mismo
        //Consulta 2
        query.getResultStream()
                .map(Employee::getEmail)
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }

    @Test
    void getSingleResult() {
        insertData();
        var session = HibernateUtil.getSessionFactory().openSession();

        String jpql = "select e from Employee e where e.id = :id";
        Query<Employee> query = session.createQuery(jpql, Employee.class);

        /* Opción 1:
            En caso de que no haya un valor:
            NonUniqueResultException – if there is more than one matching result
            NoResultException – if there is no result to return
         */
        Employee emp = query.setParameter("id", 1L).getSingleResult();
        System.out.println(emp);
        System.out.println("=======");

        /* Opción 2:
            En caso de que no haya un valor:
            NonUniqueResultException – if there is more than one matching result
         */
        emp = query.setParameter("id", 1L).getSingleResultOrNull();
        System.out.println(emp);
        System.out.println("=======");
        /*
        Opción 3:
         */

        emp = query.setParameter("id", 1L).uniqueResult();
        System.out.println(emp);
        System.out.println("=======");


        /*
        Opción 4:
         *Programacion funcional. uniqueResultOptional permite acceder a metodos como map, inpresent, isnull...*/
        query.setParameter("id", 1L)
                .uniqueResultOptional()
                .map(Employee::getEmail)
                .map(String::toUpperCase)
                .ifPresent(System.out::println);
    }

    @Test
    void limit() {

        insertData();
        var session = HibernateUtil.getSessionFactory().openSession();

        String jpql = "select e from Employee e";
        Query<Employee> query = session.createQuery(jpql, Employee.class);
        query.setMaxResults(2);  //Te devuelve los 2 primeros resultados

        query.getResultList().forEach(System.out::println);

    }

    @Test
    void pagination() {

        insertData();
        var session = HibernateUtil.getSessionFactory().openSession();

        String jpql = "select e from Employee e";
        Query<Employee> query = session.createQuery(jpql, Employee.class);
        //empieza a partir del segundo registro y te devuelve 2
        // muestra los elementos con id 3 y 4
        query.setFirstResult(2);
        query.setMaxResults(2);

        query.getResultList().forEach(System.out::println);

    }


    @Test
    void dynamicPaginationLastPage() {

        insertData();
        var session = HibernateUtil.getSessionFactory().openSession();

        // numero de registros que quieres que devuelva por pagina
        //por pagina entendemos: hay 100 empleados, si queremos que nos los muestre de 20 en 20, se crearan un total
        //de 5 paginas: 20*5 = 100
        int pageSize = 20;

        // contamos cuantos empleados hay, en este caso son 100
        String jpql = "select count(e.id) from Employee e";
        Long numEmployees = session.createQuery(jpql, Long.class).uniqueResult();

        // en lastPageNum estamos guardando la ultima pagina, un 5
        int lastPageNum = (int) Math.ceil(numEmployees / (double) pageSize);

        // pagination
        String empJpql = "select e from Employee e";
        Query<Employee> query = session.createQuery(empJpql, Employee.class);
        //En posicion se guarda el 80
        //nos vamos a lastPageNum -1 = 4 y lo multiplicamos por pageSize = 80
        // le restamos 1 a lastPageNum porque en la primera pagina van los registros del 1 al 20
        //eso nos permite desplazarnos a la ultima pagina, que lleva los registros del 81 al 100
        int position = (lastPageNum - 1) * pageSize;
        //Nos colocamos en la posicion 80, que es el registro 81
        query.setFirstResult(position);
        //muestra un maximo de 20 resultados pos pagina
        query.setMaxResults(pageSize);

        System.out.println("lastPageNum: " + lastPageNum);
        System.out.println("position: " + position);
        System.out.println("pageSize: " + pageSize);
        query.getResultList().forEach(System.out::println);

    }

    void insertData(){

        var session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        for(int i = 0; i < 100; i++){
            var emp = new Employee(null, "e" + i + "@email.com", 1000.0);
            session.persist(emp);
        }

        session.getTransaction().commit();

    }
}
