package com.example.model;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@NamedEntityGraph(
        name = "authorWithBooks",
        //Hay que indicarle el nombre que se le puso a la asociacion que queremos recuperar
        attributeNodes = @NamedAttributeNode(value = "books"))
@NamedEntityGraph(
        name = "authorWithBooksAndChapters",
        attributeNodes = @NamedAttributeNode(value = "books", subgraph = "author.boooks"),
        subgraphs = {
                @NamedSubgraph(
                        name = "author.boooks",
                        attributeNodes = @NamedAttributeNode(value = "chapters")
                )
        }
)

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @OneToMany(mappedBy = "author")
    private Set<Book> books = new HashSet<>();


    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}