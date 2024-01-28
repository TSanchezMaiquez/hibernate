package com.sanchez.test.JPQL;

import com.sanchez.jardineriahibernate.HibernateUtil;
import com.sanchez.jardineriahibernate.model.Office;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JPQLOfficeTest {
    @Test
    void findAllOffices() {
        createData();

        Session session = HibernateUtil.getSessionFactory().openSession();

        var jpql = "select o from Office o";
        var query = session.createQuery(jpql, Office.class);

        query.list().forEach(System.out::println);

        session.close();
    }
    @Test
    void findOfficesByCity() {
        createData(); // Assuming createData() method exists to populate some data

        Session session = HibernateUtil.getSessionFactory().openSession();

        // Assuming your Office entity is named "Office" and city is a field
        var jpql = "select o from Office o where o.city = :city";
        var query = session.createQuery(jpql, Office.class);
        query.setParameter("city", "Madrid"); // Replace with the actual city you want to search

        query.list().forEach(System.out::println);

        session.close();
    }
    @Test
    void findOfficesByCountryIn() {
        createData();

        Session session = HibernateUtil.getSessionFactory().openSession();

        var jpql = "select o from Office o where o.country in :countries";
        var query = session.createQuery(jpql, Office.class);
        query.setParameter("countries", List.of("España", "Francia")); // Replace with the actual countries you want to search

        query.list().forEach(System.out::println);

        session.close();
    }
    @Test
    void updateOffice() {
        createData(); // Assuming createData() method exists to populate some data

        var session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Assuming your Office entity is named "Office" and you want to update the phoneNumber
        var jpql = "update Office o set o.phoneNumber = :newPhoneNumber where o.id = :id";
        var query = session.createQuery(jpql)
                .setParameter("newPhoneNumber", "22222222") // Replace with the new phone number value
                .setParameter("id", "111") // Replace with the actual Office ID you want to update
                .executeUpdate();

        session.getTransaction().commit();
        System.out.println(query);

        session.close();
    }

    void createData(){

        Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        //String officeCod, String city, String country, String postCode, String phoneNumber, String address1
        var office1 = new Office("123", "Madrid", "España", "30134", "12312312", "asfsdf");
        //String officeCod, String city, String country, String region, String postCode, String phoneNumber, String address1, String address2
        var office2 = new Office("1234", "Sevilla", "Italia", "Sur", "12312", "12123123", "alegria", "esperanza");
        var office3 = new Office("567", "Barcelona", "Francia", "Cataluña", "56789", "56756756", "calle Barcelona", "piso 5");
        var office4 = new Office("890", "Valencia", "España", "Este", "89012", "89089089", "avenida Valencia", "piso 8");
        var office5 = new Office("111", "Bilbao", "Francia", "Norte", "11122", "11111111", "calle Bilbao", "piso 3");
        var office6 = new Office("777", "Malaga", "Portugal", "Andalucia", "77777", "77777777", "calle Malaga", "piso 7");

        session.persist(office1);
        session.persist(office2);
        session.persist(office3);
        session.persist(office4);
        session.persist(office5);
        session.persist(office6);

        session.getTransaction().commit();

        session.close();



    }
}
