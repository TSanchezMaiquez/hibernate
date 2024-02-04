package com.example;

import com.example.model.Address;
import com.example.model.Author;
import com.example.model.Book;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class FetchingManyTest {

    @Test
    void findAuthorById() {
        insertData();
        /*Este tipo de consultas son las llamas n+1, y que hay que evitar.
        * Aqui se hace una primera conexion a BD para traer al autor y otra conexion posterior para traer los libros
        *
        * Si estas seguro de que necesitas trabajar con alguna de las asociaciones entonces es mejor incluir un
        * fetch join en la consulta para que de una sola conexion a la base de datos te traiga todos los datos que
        * necesites*/
        var session = HibernateUtil.getSessionFactory().openSession();

        String jpql = """
                select a from Author a
                where a.id = :id
                """;

        var a1 = session.createQuery(jpql, Author.class)
                .setParameter("id", 1L)
                .getSingleResult();

        System.out.println("===============");
        a1.getBooks().forEach(System.out::println);

    }
    @Test
    void findAuthorByIdWithBooks() {
        insertData();

        var session = HibernateUtil.getSessionFactory().openSession();
        //join fetch se trae todos los libros desde el principio
        String jpql = """
                select a from Author a
                join fetch a.books
                where a.id = :id
                """;

        var a1 = session.createQuery(jpql, Author.class)
                .setParameter("id", 1L)
                .getSingleResult();


        a1.getBooks().forEach(System.out::println);

    }

    void insertData(){

        var session = HibernateUtil.getSessionFactory().openSession();


        var author1 = new Author("author1", "a1@email.com", LocalDate.of(1990, 1, 1));

        session.beginTransaction();

        session.persist(author1);


        var book1 = new Book("b1", 1.0, 100, true, author1);
        var book2 = new Book("b2", 1.0, 100, true, author1);
        var book3 = new Book("b3", 1.0, 100, true, author1);

        session.persist(book1);
        session.persist(book2);
        session.persist(book3);

        session.getTransaction().commit();

    }

}
