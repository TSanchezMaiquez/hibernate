package com.sanchez.test.CRUD;

import com.sanchez.jardineriahibernate.HibernateUtil;
import com.sanchez.jardineriahibernate.model.CustomerOrder;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class CRUDCustomerOrderTest
{
    @Test
    void persist() {

        Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        CustomerOrder customerOrder1 = new CustomerOrder(LocalDate.of(1999,1,1), LocalDate.of(2000, 2, 3), LocalDate.of(2024, 3, 4), "En proceso", "Comentarios de la orden 1", 101);

        // Segundo constructor
        CustomerOrder customerOrder2 = new CustomerOrder(LocalDate.of(1900, 1, 2), LocalDate.of(1999, 2, 3), "En proceso", 102);

        session.persist(customerOrder1);
        session.persist(customerOrder2);

        session.getTransaction().commit();

        session.close();

        System.out.println(customerOrder1);
        System.out.println(customerOrder2);

    }

    @Test
    void retrieve(){

        createDate();
        Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        CustomerOrder co1 = session.find(CustomerOrder.class, 1L);
        CustomerOrder co2 = session.find(CustomerOrder.class, 2L);


        session.close();
        System.out.println(co1);
        System.out.println(co2);
    }
@Test
void update(){

    createDate();
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();

    CustomerOrder co1 = session.find(CustomerOrder.class, 1L);
    CustomerOrder co2 = session.find(CustomerOrder.class, 2L);

    co1.setState("ListoCalisto");
    co2.setState("ListoCalisto");

    // Realizar flush para sincronizar con la base de datos
    session.flush();

    // Limpiar la caché de la sesión
    session.clear();

    co1 = session.find(CustomerOrder.class, 1L);
    co2 = session.find(CustomerOrder.class, 2L);

    session.getTransaction().commit();
    session.close();

    System.out.println(co1);
    System.out.println(co2);
}

@Test
void delete (){

    createDate();
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();

    session.remove(session.find(CustomerOrder.class, 1L));

    session.getTransaction().commit();
    session.close();

}
void createDate(){
    Session session =  HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();

    CustomerOrder customerOrder1 = new CustomerOrder(LocalDate.of(1999,1,1), LocalDate.of(2000, 2, 3), LocalDate.of(2024, 3, 4), "En proceso", "Comentarios de la orden 1", 101);
    CustomerOrder customerOrder2 = new CustomerOrder(LocalDate.of(1900, 1, 2), LocalDate.of(1999, 2, 3), "En proceso", 102);

    session.persist(customerOrder1);
    session.persist(customerOrder2);

    session.getTransaction().commit();

    session.close();


    }
}
