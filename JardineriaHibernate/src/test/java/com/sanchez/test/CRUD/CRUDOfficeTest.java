package com.sanchez.test.CRUD;

import com.sanchez.jardineriahibernate.HibernateUtil;
import com.sanchez.jardineriahibernate.model.Office;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

public class CRUDOfficeTest {

    @Test
    void persist() {

        Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        //String officeCod, String city, String country, String postCode, String phoneNumber, String address1
        var office1 = new Office("123", "Madrid", "España", "30134", "12312312", "asfsdf");
        //String officeCod, String city, String country, String region, String postCode, String phoneNumber, String address1, String address2
        var office2 = new Office("1234", "Sevilla", "España", "Sur", "12312", "12123123", "alegria", "esperanza");

        session.persist(office1);
        session.persist(office2);

        session.getTransaction().commit();

        session.close();

        System.out.println(office1);
        System.out.println(office2);

    }

    @Test
    void retrieve () {

        createData();
        Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        var office1 = session.find(Office.class, "123");
        var office2 = session.find(Office.class, "1234");

        session.close();

        System.out.println(office1);
        System.out.println(office2);

    }

    @Test
    void update() {

        createData();
        Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        var office1 = session.find(Office.class, "123");
        var office2 = session.find(Office.class, "1234");

        office1.setAddress1("AAAAAAA");
        office2.setAddress1("AAAAAAA");

        session.merge(office1);
        session.merge(office2);

        session.refresh(office2);
        session.getTransaction().commit();
        session.refresh(office1);

        session.close();

        System.out.println(office1);
        System.out.println(office2);
    }

    @Test
    void remove (){
        createData();
        Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        var office1 = session.find(Office.class, "123");
        session.remove(office1);
        session.getTransaction().commit();

        session.close();

    }
    void createData(){

        Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        //String officeCod, String city, String country, String postCode, String phoneNumber, String address1
        var office1 = new Office("123", "Madrid", "España", "30134", "12312312", "asfsdf");
        //String officeCod, String city, String country, String region, String postCode, String phoneNumber, String address1, String address2
        var office2 = new Office("1234", "Sevilla", "España", "Sur", "12312", "12123123", "alegria", "esperanza");
        var office3 = new Office("567", "Barcelona", "Francia", "Cataluña", "56789", "56756756", "calle Barcelona", "piso 5");
        var office4 = new Office("890", "Valencia", "Francia", "Este", "89012", "89089089", "avenida Valencia", "piso 8");
        var office5 = new Office("111", "Bilbao", "España", "Norte", "11122", "11111111", "calle Bilbao", "piso 3");
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
