package com.example.model;

import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
public class Book {
    /*NoFound nos ayuda a trabajar con tablas que por mala gestion??? no tienen foreign keys*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @ManyToOne  //EXCEPTION crea un FETCH EAGER, aunque pusieramos EASY
    //@NotFound(action = NotFoundAction.EXCEPTION) //Lanza una excepcion si la foreign key no contiene valor
    //@NotFound(action = NotFoundAction.IGNORE)  //Se evita que lance error en el caso de que la foreign key
    //                                               este vacia
    //Evita que se creen las foreign keys
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Author author;

    public Book() {
    }

    public Book(String title, Author author) {
        this.title = title;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
