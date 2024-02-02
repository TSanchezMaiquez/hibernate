package com.example.model;

import jakarta.persistence.*;

import java.util.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String nss;

    /*Si queremos crear listas de elementos que no pertenecen a clases creadas por nosotros(STRING, INT,
    DOUBLE, BOOLEAN....) debemos usar la etiqueta ElementCollection*/
    @ElementCollection
    @CollectionTable(name = "Employee_pho")  //Nombre de la nueva tabla creada
    private List<String> phones = new ArrayList<>(); //INICIALIZAR para evitar errores

    @ElementCollection
    private Set<String> postalCodes = new HashSet<>();
    @ElementCollection
    private List<Double> salaries = new ArrayList<>();

    /*Cuando tienes una propiedad que es un Map, a diferencia que las anteriores te crea una tercera
    * columna, que es la key del map, y esta nueva tabla tendra una primary key compuesta: una de ellas es
    * la id del empleado y la otra es la key del map*/
    @OneToMany
    @JoinTable(name = "employee_cards",
                joinColumns =  @JoinColumn(name = "employee_id"),
                inverseJoinColumns = @JoinColumn(name = "card_id"))
    @MapKeyColumn(name = "k_number") // nombre de la columna key, que forma parte de la primary key compuesta
    private Map<String, CreditCard> cards = new HashMap<>();


    public Employee() {
    }

    public Employee(String name, String nss, List<String> phones, List<Double> salaries) {
        this.name = name;
        this.nss = nss;
        this.phones = new ArrayList<>(phones);
        this.salaries = new ArrayList<>(salaries);
    }

    public Employee(String name, String nss, List<String> phones) {

        this.name = name;
        this.nss = nss;
        /*Si en la creacion del objeto nos pasan los telefonos en una lista.of y la igualamos a la propiedad
        esta lista será inmutable y no nos permitiria modificarla por lo que podría crear problemas.
         Para evitarlo creamos una nueva lista a partir de la lista recibida y es esa la que recibirá la
         propiedad de la entity*/
        this.phones = new ArrayList<>(phones);
    }

    public Employee(String name, String nss, List<String> phones, Set<String> postalCodes, List<Double> salaries) {
        this.name = name;
        this.nss = nss;
        this.phones = new ArrayList<>(phones);
        this.salaries = new ArrayList<>(salaries);
        this.postalCodes = new HashSet<>(postalCodes);
    }

    public Employee(String name, String nss, Map<String, CreditCard> cards) {
        this.name = name;
        this.nss = nss;
        this.cards = new HashMap<>(cards);
    }

    public Long getId() {
        return id;
    }

    public Map<String, CreditCard> getCards() {
        return cards;
    }

    public void setCards(Map<String, CreditCard> cards) {
        this.cards = cards;
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

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public List<Double> getSalaries() {
        return salaries;
    }

    public void setSalaries(List<Double> salaries) {
        this.salaries = salaries;
    }

    public Set<String> getPostalCodes() {
        return postalCodes;
    }

    public void setPostalCodes(Set<String> postalCodes) {
        this.postalCodes = postalCodes;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nss='" + nss + '\'' +
                ", phones=" + phones +
                '}';
    }
}
