package com.sanchez.test.JPQL;

import com.sanchez.jardineriahibernate.HibernateUtil;
import com.sanchez.jardineriahibernate.model.CustomerOrder;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class JPQLCustomerOrder {

    @Test
    void findByAll(){
        createData();
        Session session =  HibernateUtil.getSessionFactory().openSession();

        var jpql = "select a from CustomerOrder a ";
        var query = session.createQuery(jpql, CustomerOrder.class);

        query.list().forEach(System.out::println);
    }

    @Test
    void findById(){
        createData();
        Session session =  HibernateUtil.getSessionFactory().openSession();

        var jpql = "select a from CustomerOrder a where a.orderCode = :orderCode";
        var query = session.createQuery(jpql, CustomerOrder.class);
        query.setParameter("orderCode", 1L);

        System.out.println(query.getSingleResult());
    }

    @Test
    void findBetweenDate(){
        createData();
        Session session =  HibernateUtil.getSessionFactory().openSession();
        var jpql = "select a from CustomerOrder a where a.expectedOrderDate between :start and :end";
        var query = session.createQuery(jpql, CustomerOrder.class);
        query.setParameter("start", LocalDate.of(1999, 1, 1));
        query.setParameter("end", LocalDate.of(2022, 1, 8));

        query.list().forEach(System.out::println);
    }

    @Test
    void findIdIn(){
        createData();
        Session session =  HibernateUtil.getSessionFactory().openSession();
        var jpql = "select a from CustomerOrder a where a.orderCode in :ids";
        var query = session.createQuery(jpql, CustomerOrder.class);
        query.setParameter("ids", List.of(1, 2, 5));

        query.list().forEach(System.out::println);
    }
    @Test
    void update(){
        createData();
        var session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        var jpql = "update CustomerOrder a set a.deliveryOrderDate = :date where a.orderCode = :id";
        var query = session.createMutationQuery(jpql)
                        .setParameter("date", new Date(2099, 9, 9))
                        .setParameter("id", 1)
                        .executeUpdate();
        session.getTransaction().commit();
        System.out.println(query);

    }
    void createData(){
        Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        CustomerOrder customerOrder1 = new CustomerOrder(LocalDate.of(1999,1,1), LocalDate.of(2000, 2, 3), LocalDate.of(2024, 3, 4), "En proceso", "Comentarios de la orden 1", 101);
        CustomerOrder customerOrder2 = new CustomerOrder(LocalDate.of(1900, 1, 2), LocalDate.of(1999, 2, 3), "En proceso", 102);
        CustomerOrder customerOrder4 = new CustomerOrder(LocalDate.of(2023, 8, 10), LocalDate.of(2023, 9, 18), LocalDate.of(2023, 10, 25), "Completado", "Comentarios de la orden 4", 104);
        CustomerOrder customerOrder5 = new CustomerOrder(LocalDate.of(2021, 12, 1), LocalDate.of(2022, 1, 5), "En proceso", 105);
        CustomerOrder customerOrder3 = new CustomerOrder(LocalDate.of(2023, 10, 10), LocalDate.of(2023, 9, 18), LocalDate.of(2023, 10, 25), "Completado", "Comentarios de la orden 4", 104);

        session.persist(customerOrder1);
        session.persist(customerOrder2);
        session.persist(customerOrder3);
        session.persist(customerOrder4);
        session.persist(customerOrder5);

        session.getTransaction().commit();

        session.close();


    }
}
