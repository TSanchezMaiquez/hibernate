package com.example;

import com.example.model.SmartPhone;
import org.junit.jupiter.api.Test;

public class NamingTest {
    @Test
    void name(){
        insertData();
        var session = HibernateUtil.getSessionFactory().openSession();

        var phone1 = session.find(SmartPhone.class, 1L);

        System.out.println(phone1);
        session.close();

    }
    void insertData(){
        var session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        var phone1 = new SmartPhone("phone1", "12312312");
        var phone2 = new SmartPhone("phone2", "11111111");
        var phone3 = new SmartPhone("phone3", "22222222");

        session.persist(phone1);
        session.persist(phone2);
        session.persist(phone3);

        session.getTransaction().commit();
        session.close();

    }
}
