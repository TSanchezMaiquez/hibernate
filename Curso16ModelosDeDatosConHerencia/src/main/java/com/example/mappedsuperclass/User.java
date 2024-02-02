package com.example.mappedsuperclass;

import jakarta.persistence.*;

import java.util.Set;

@MappedSuperclass  // recordatorio de class abstract: no se puede instanciar
//NO TENDRA TABLA  NO PERMITE ASOCIACIONES
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String dni;

    public User() {
    }

    public User(String dni) {
        this.dni = dni;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", dni='" + dni + '\'' +
                '}';
    }
}
