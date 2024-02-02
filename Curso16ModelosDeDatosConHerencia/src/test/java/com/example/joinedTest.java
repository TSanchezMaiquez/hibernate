package com.example;

import com.example.joined.Computer;
import com.example.joined.DeviceOwner;
import com.example.joined.Phone;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

public class joinedTest {

    @Test
    void joinedTest(){

        createData();
        var session = HibernateUtil.getSessionFactory().openSession();

        var comps = session.createQuery("select c from Computer c", Computer.class).list();
        comps.forEach(System.out::println);

        var phones = session.createQuery("select c from Phone c", Phone.class).list();
        phones.forEach(System.out::println);

        session.find(DeviceOwner.class, 1L).getDevices().forEach(System.out::println);
    }

    void createData(){

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        var owner = new DeviceOwner("mike");
        session.persist(owner);

        var comp1 = new Computer(null, "asus", owner, "i7");
        var comp2 = new Computer(null, "msi", owner, "i9");
        var phone1 = new Phone(null, "Onep", owner, "654987987");
        var phone2 = new Phone(null, "Sams", owner, "654987987");

        session.persist(comp1);
        session.persist(comp2);
        session.persist(phone1);
        session.persist(phone2);

        session.getTransaction().commit();
        session.close();
    }
}
