package com.example;

import com.example.model.Address;
import org.junit.jupiter.api.Test;

public class SchemaTest {
    @Test
    void name (){
        createData();
        var session = HibernateUtil.getSessionFactory().openSession();
        var address1 = session.find(Address.class, 1L);
        System.out.println(address1);

        session.close();
        HibernateUtil.shutdown();
    }

    void createData(){
        var session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        var street = new Address("street1", "city1", "country1");
        session.persist(street);

        session.getTransaction().commit();
        session.close();


    }
}
