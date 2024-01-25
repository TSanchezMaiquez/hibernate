package com.example;

import com.example.model.Author;
import com.example.model.Book;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaPredicate;
import org.hibernate.query.criteria.JpaRoot;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class CriteriaTest {


    @Test
    void findAll(){

        insertData();
        var session = HibernateUtil.getSessionFactory().openSession();

        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        JpaCriteriaQuery<Author> criteriaQuery = builder.createQuery(Author.class);

        //Las dos siguientes lineas llevan SELECT, FROM y clase AUTHOR = select * from Author
        Root<Author> root = criteriaQuery.from(Author.class);
        criteriaQuery.select(root);

        var authors = session.createQuery(criteriaQuery).list();

        authors.forEach(System.out::println);
    }
    @Test
    void findById(){
        insertData();
        var session = HibernateUtil.getSessionFactory().openSession();

        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        JpaCriteriaQuery<Author> criteriaQuery = builder.createQuery(Author.class);

        //Las dos siguientes lineas llevan SELECT, FROM y clase AUTHOR = select * from Author
        //Root<Author> root = criteriaQuery.from(Author.class);
        //criteriaQuery.select(root);

        //como "criteriaQuery.from(Author.class)" se repite a lo largo de la consulta lo guardamos en
        //una variable
        JpaRoot<Author> rootAuthor = criteriaQuery.from(Author.class);

        criteriaQuery.select(rootAuthor)
                .where(builder.equal(rootAuthor.get("id"), 1L));

        Author author = session.createQuery(criteriaQuery).getSingleResult();

        System.out.println(author);;
    }
    @Test
    void findByEmailLike(){
        insertData();
        Session session = HibernateUtil.getSessionFactory().openSession();

        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        JpaCriteriaQuery<Author> criteriaQuery = builder.createQuery(Author.class);

        JpaRoot<Author> rootAuthor = criteriaQuery.from(Author.class);

        criteriaQuery.select(rootAuthor)
                .where(builder
                        .like(rootAuthor.get("email"), "%.com"));

        var authors = session.createQuery(criteriaQuery).getResultList();

        authors.forEach(System.out::println);
    }
    @Test
    void findByPriceGreaterThan(){
        insertData();
        var session = HibernateUtil.getSessionFactory().openSession();

        var builder = session.getCriteriaBuilder();
        var criteriaQuery = builder.createQuery(Book.class);

        var root = criteriaQuery.from(Book.class);

        criteriaQuery.select(root)
                .where(builder
                        .greaterThanOrEqualTo(root.get("price"), "29.99"));

        var result = session.createQuery(criteriaQuery).getResultList();

        result.forEach(System.out::println);
    }
    @Test
    void finBetween(){

        insertData();
        Session session = HibernateUtil.getSessionFactory().openSession();

        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        JpaCriteriaQuery<Author> criteria = builder.createQuery(Author.class);
        JpaRoot<Author> authors = criteria.from(Author.class);

        criteria.select(authors)
                .where(builder
                        .between(
                                authors.get("birthdate"),
                                LocalDate.of(1989, 1, 1),
                                LocalDate.of(1995, 12, 1)
                        )
                );

        var authorsFromDB = session.createQuery(criteria).getResultList();

        authorsFromDB.forEach(System.out::println);
    }
    @Test
    void findByMultipleWhere() {

        insertData();
        Session session = HibernateUtil.getSessionFactory().openSession();

        var builder = session.getCriteriaBuilder();
        var criteria = builder.createQuery(Book.class);
        var root = criteria.from(Book.class);
        //FITROS DE BUSQUEDA PARA EL AND
        //Esto devuelve una variable del tipo predicate. En la consulta "AND" no acepta valores base
        JpaPredicate priceGte30 = builder.greaterThanOrEqualTo(root.get("price"), 29.99);
        var numPagesLte400 = builder.lessThanOrEqualTo(root.get("numPages"), 400);

        criteria.select(root)
                .where(builder  //AND es equivalente a una consulta con multiples WHERE
                        .and(priceGte30, numPagesLte400));

        var result = session.createQuery(criteria).getResultList();

        result.forEach(System.out::println);

    }
    @Test
    void multiSelect() {

        insertData();
        Session session = HibernateUtil.getSessionFactory().openSession();

        var builder = session.getCriteriaBuilder();
        /*Aqui cambiamos a Object en vez de la clase de la que queremos extraer la informacion
        * Esto es así porque los datos que vamos a recuperar de la base de datos no son de tipo libro
        * En el ejemplo actual recuperamos precio, que es de tipo LOng, y titulo, que es un String
        * Si el tipo fuera Book no podria recoger esos datos, en cambio la clase Object si, porque es el padre de todas las clases*/
        var criteria = builder.createQuery(Object[].class);
        var root = criteria.from(Book.class);
        //Esta consulta solamente va a traer las columnas que le solicitemos en el select
        criteria.multiselect(root.get("title"),
                             root.get("price"));

        //Lista de listas, en cada posicion de la lista guarda los campos solicitados en la uiltiselect
        List<Object[]> results = session.createQuery(criteria).getResultList();

        for(Object[] result : results) {
            System.out.println("Title: " + result[0] + " Price: " + result[1]);
        }

    }

    void insertData(){
        var session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        var author1 = new Author("author1", "a1@company1.com", LocalDate.of(1990, 1, 1));
        var author2 = new Author("author2", "a2@company1.es", LocalDate.of(1995, 1, 1));
        var author3 = new Author("author3", "a3@company2.com", LocalDate.of(1996, 1, 1));
        var author4 = new Author("author4", "a4@company2.com", LocalDate.of(1998, 1, 1));

        session.persist(author1);
        session.persist(author2);
        session.persist(author3);
        session.persist(author4);

        var book1 = new Book("book1", author1, 19.99, 450, true);
        var book2 = new Book("book2", author1, 29.99, 380, true);
        var book3 = new Book("book3", author1, 39.99, 350, true);
        session.persist(book1);
        session.persist(book2);
        session.persist(book3);

        session.getTransaction().commit();
    }
}
