package com.example;

import com.example.dto.EmployeeCountry;
import com.example.dto.EmployeeEmail;
import com.example.model.Address;
import com.example.model.Employee;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Test;

public class DTOTest {
    /*Data transfer object: se usan solamente cuando queremos obtener informacion de la base de datos, si queremos
    * modificar o eliminar registros debemos hacerlo con consultas criterio o jpql*/

    @Test
    void dtoClassInJPQL() {
        insertData();
        var session = HibernateUtil.getSessionFactory().openSession();
        /*Creamos una clase record con los campos que nos interesa obtener de una entidad en concreto
        * clase record: EmployeeEmail, entity: Employee
        * creamos la query y le pasamos los parametros de empleado en el orden en el que los recibe la clase record*/
        String jpql = """
                select new com.example.dto.EmployeeEmail(e.id, e.email)
                from Employee e
                """;

        /*OJO: aqui no ponemos la entity, ponemos la clase record: EmployeEmail*/
        session.createQuery(jpql, EmployeeEmail.class)
                .list()
                .forEach(System.out::println);
    }

    @Test
    void dtoClassWithAssociationInJPQL() {
        insertData();
        var session = HibernateUtil.getSessionFactory().openSession();
                        //En este caso accedemos al pais, almacenado en la asociacion address
        String jpql = """ 
                select new com.example.dto.EmployeeCountry(e.id, e.address.country)
                from Employee e
                """;

        session.createQuery(jpql, EmployeeCountry.class)
                .list()
                .forEach(System.out::println);
    }

    @Test
    void tuplesJPQL() {

        /*Puedes traerte los campos que quieras de una entidad con el uso de tuplas*/
        insertData();
        var session = HibernateUtil.getSessionFactory().openSession();
        /*Aqui nos traemos id y email que perteneces a employee, y a traves de address nos traemos city*/
        String jpql = """
                select e.id as id, e.email as email, e.address.city as city
                from Employee e
                """;

        session.createQuery(jpql, Tuple.class)
                .list()
                .forEach(tuple -> System.out.println(tuple.get("id")
                                        + " " + tuple.get("email")
                                        + " "+ tuple.get("city")));
    }

    @Test
    void tuplesSQL() {
        insertData();
        var session = HibernateUtil.getSessionFactory().openSession();
        //OJO: aqui employee y address se han escrito en minuscula, eso se debe a que se han cogido los nombres que
        //se han puesto en base de datos, no los que tienen las entidades
        //No es lo mismo jpql que sql.
        // jpql:  session.createQuery
        //sql : session.createNativeQuery
        String sql = """
                select e.id as id, e.email as email, a.country as country
                from employee e JOIN address a on a.id = e.address_id
                """;

        session.createNativeQuery(sql, Tuple.class)
                .list()
                .forEach(tuple -> System.out.println(tuple.get("id") + " " + tuple.get("email") + " " + tuple.get("country")));
    }

    @Test
    void criteria() {
        insertData();
        var session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<EmployeeCountry> cq = cb.createQuery(EmployeeCountry.class);

        /*Las dos siguientes lineas sirven para hacer el inner join en consultas criteria*/
        Root<Employee> root = cq.from(Employee.class);  //se accede a campos de las entitys address y employee haciendo
        Join<Employee, Address> address = root.join("address"); // un join usando la propiedad address de employee

        cq.select(cb.construct(EmployeeCountry.class, root.get("id"), address.get("country")));

        session.createQuery(cq)
                .list()
                .forEach(System.out::println);
    }

    void insertData(){

        var session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        var address1 = new Address("street1", "11111", "city", "country1");
        var address2 = new Address("street1", "11111", "city", "country1");
        var address3 = new Address("street1", "11111", "city", "country1");

        var emp1 = new Employee("emp1", "emp1", "e1@email.com", address1);
        var emp2 = new Employee("emp2", "emp2", "e2@email.com", address2);
        var emp3 = new Employee("emp3", "emp3", "e3@email.com", address3);

        session.persist(address1);
        session.persist(address2);
        session.persist(address3);

        session.persist(emp1);
        session.persist(emp2);
        session.persist(emp3);

        session.getTransaction().commit();

    }
}
