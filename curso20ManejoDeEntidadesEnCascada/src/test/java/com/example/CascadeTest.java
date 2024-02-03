package com.example;

import com.example.model.Address;
import com.example.model.Author;
import com.example.model.Book;
import org.junit.jupiter.api.Test;

public class CascadeTest {
    //En la asociacion dentro de Author se le ha puesto la etiqueta cascade lo que permite guardar author y address
    //al mismo tiempo sin necesidad de persistir primero la direccion
    @Test
    void oneToOne(){

        var session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        var address1 = new Address("strees1", "city1", "country1");
        var address2 = new Address("strees2", "city2", "country2");

        var author1 = new Author("a1", 20, address1);
        var author2 = new Author("a2", 26, address2);

        session.persist(author1);
        session.persist(author2);

        session.getTransaction().commit();

        var a1 = session.find(Author.class, 1L);

        session.close();
        System.out.println(a1 + " "+ a1.getAddress());
    }
    @Test
    void oneToMany(){

        var session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();


        var author1 = new Author("a1", 20, null);
        session.persist(author1);

        var book1 = new Book("b1", author1);
        var book2 = new Book("b2", author1);
        var book3 = new Book("b3", author1);
        var book4 = new Book("b4", author1);

        session.persist(book1);
        session.persist(book2);
        session.persist(book3);
        session.persist(book4);

        session.getTransaction().commit();

        session.close();


        session = HibernateUtil.getSessionFactory().openSession();
        var a1 = session.find(Author.class, 1L);

        a1.getBooks().forEach(System.out::println);
        session.beginTransaction();
        session.remove(a1);
        session.getTransaction().commit();
        session.close();

    }
@Test
void manyToOne(){

    var session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();


    var author1 = new Author("a1", 20, null);
    //session.persist(author1);
    var book1 = new Book("b1", author1);
    var book2 = new Book("b2", author1);
    var book3 = new Book("b3", author1);
    var book4 = new Book("b4", author1);

    session.persist(book1);
    session.persist(book2);
    session.persist(book3);
    session.persist(book4);

    session.getTransaction().commit();

    session.close();

    session = HibernateUtil.getSessionFactory().openSession();

    var b4 = session.find(Book.class, 4L);
    System.out.println(b4);
    session.beginTransaction();
    session.remove(b4);
    session.getTransaction().commit();
    session.close();

}

@Test
void oneToManyRemoveAssociation(){
    var session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();


    var author1 = new Author("a1", 20, null);
    session.persist(author1);
    var book1 = new Book("b1", author1);
    var book2 = new Book("b2", author1);
    var book3 = new Book("b3", author1);
    var book4 = new Book("b4", author1);

    session.persist(book1);
    session.persist(book2);
    session.persist(book3);
    session.persist(book4);

    session.getTransaction().commit();
    session.close();

    session = HibernateUtil.getSessionFactory().openSession();

    var a1 = session.find(Author.class, 1L);

    session.beginTransaction();

    a1.getBooks().removeIf(book -> book.getTitle().equals("b1"));

    session.persist(a1);
    session.getTransaction().commit();
    session.close();
}

}
