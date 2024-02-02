package com.example.tableperClass;

import jakarta.persistence.*;

@Entity /*En este caso tenemos una clase que no es abstracta de la que van a heredar motorcycle y car
Se le esta indicando a Hibernate que cada una de las entidades debe tener su propia tabla en base de
datos. Esta configuracion genera problemas con la estrategia de generacion de primary keys. Hasta ahora
siempre hemos usado IDENTITY pero en este caso esa indicacion da problemas.
TABLE PER CLASS -> obliga a la creacion de la primary key a ser de tipo AUTO, dejando que hibernate lo
gestione como crea conveniente. LO MISMO SE APLICA A LA PK DE OWNER*/
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    protected String manufacturer;

    @ManyToOne
    protected Owner owner;

    public Vehicle(Long id, String manufacturer, Owner owner) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.owner = owner;
    }

    public Vehicle() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", manufacturer='" + manufacturer + '\'' +
                '}';
    }
}