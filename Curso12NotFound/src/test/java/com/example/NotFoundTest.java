package com.example;

import com.example.model.Author;
import com.example.model.Book;
import org.junit.jupiter.api.Test;

public class NotFoundTest {
    @Test
    void notFoundTest (){
        createData();
        var session = HibernateUtil.getSessionFactory().openSession();
        var book1 = session.find(Book.class, 1L);
        System.out.println("____________________");
        System.out.println(book1.getAuthor());

    }

    void createData(){
        var session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        var a1 = new Author("a1");
        var a2 = new Author("a2");

        session.persist(a1);
        session.persist(a2);

        var b1 = new Book("b1", a1);
        var b2 = new Book("b2", a1);
        var b3 = new Book("b3", a1);

        session.persist(b1);
        session.persist(b2);
        session.persist(b3);

        //session.remove(a1);

        session.getTransaction().commit();
        session.close();
    }
}
