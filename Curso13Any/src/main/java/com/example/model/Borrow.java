package com.example.model;

import jakarta.persistence.*;
import org.hibernate.annotations.*;

@Entity
public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String concept;
    /*Permite guardar objetos de cualquier tipo(POLIMORFISMO), de cualquier tabla. En este ejemplo se alguilan
    * casas y vehiculos, y debe registrarse en la tabla de alquiler, para lo cual utilizamos la
    * anotacion any*/
    @Any
    //discriminator trabaja sobre la columna item_type.Se puede cambiar el tipo a STRING y
    //en vez de mostrar V-H, que puede dar lugar a confusion mostrar Vehicle, House
 /*   @AnyDiscriminator(DiscriminatorType.CHAR)
    @AnyDiscriminatorValue(discriminator = "V", entity = Vehicle.class)
    @AnyDiscriminatorValue(discriminator = "H", entity = House.class)*/
    @AnyDiscriminator(DiscriminatorType.STRING)
    @AnyDiscriminatorValue(discriminator = "Vehicle", entity = Vehicle.class)
    @AnyDiscriminatorValue(discriminator = "House", entity = House.class)
    //El tipo de la columna
    @AnyKeyJavaClass(Long.class)
    //No pueden faltar las 2 siguiente lineas. Es el nombre de la entity origen(vehicle o house)
    @Column(name = "item_type")
    //Es la id que tiene el objeto en su entity origen(su id de vehicle o house)
    @JoinColumn(name = "item_id")
    //Muestra error porque no tiene en cuenta las anotaciones previas, pero funciona perfectamente
    private Item item;

    public Borrow() {
    }

    public Borrow(String concept, Item item) {
        this.concept = concept;
        this.item = item;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "id=" + id +
                ", concept='" + concept + '\'' +
                ", item=" + item +
                '}';
    }
}