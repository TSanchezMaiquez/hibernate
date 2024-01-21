package com.example;

import com.example.model.Address;
import com.example.model.Author;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

/*
* 1.Transient -> creado pero no reconocido por el objeto session
* 2. Managed -> existe en la cache del objeto session, por lo que sabe que existe y podemos
*               modificarlo o eliminarlo, pero no dejara volver a crearlo
* 3. Detached -> existe en base de datos pero session no sabe que existe porque no ha trabajado con el.
*               Si intentamos volver a crear el mismo objeto Hibernate lanzara excepcion porque comprobara
*               las ids de los objetos. Si queremos que session lo reconozca podemos traerlo de base de
*               datos con find() o merge()
* 4. Removed*/
public class LifeCycleTest {

    @Test
    void tramsientState() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Address address = new Address("street 1", "city1", "country1");
        /*Cuando se recupera o guarda un objeto en BD queda registrado dentro de la variable session.
        * Esto permite saber si el objeto es nuevo o uno con el que se ha trabajado anteriormente.
        * En este caso el metodo contains, que es el que permite obtner esa informacion devolvera false
        * puesto que el objeto es completamente nuevo y no se ha trabajado con el anteriormente*/
        System.out.print(session.contains(address));

        /*Cuando un objeto no es managed (la variable session no lo reconoce) puede dar problemas si se
        * asocia con otra entidad. Esto se debe a que session no conoce el objeto address(no se ha
        * persistido) */

        var author1 = new Author("author1", "autor1@gmail.com",
                LocalDate.of(1990,1,1));
        author1.setAddress(address);

        session.beginTransaction();
        session.persist(author1);
        session.getTransaction().commit();


    }

    @Test
    void managed() {
        /*Aqui tenemos dos objetos session. Con uno guardo el objeto en BD y utilizo el metodo contains con ambos
        * objetos session para saber si conocian ese objeto previamente. El que ha persistido el objeto
        * devuelve true, puesto que ya ha trabajado con ese objeto y lo reconoce y el otro, que no sabe nada
        * de ese objeto devuelve false */
        Session session = HibernateUtil.getSessionFactory().openSession();
        Session session2 = HibernateUtil.getSessionFactory().openSession();
        Address address = new Address("street 1", "city1", "country1");
        session.beginTransaction();
        session.persist(address);
        session.getTransaction().commit();
        System.out.println(session.contains(address));
        System.out.println(session2.contains(address));

    }


    @Test
    void detached() {
        /*Hay que cambiar la conf de hibernate a UPDATE para comprender este test*/
        Session session = HibernateUtil.getSessionFactory().openSession();
        Address address = new Address("street 1", "city2", "country1");
        address.setId(1L);
        session.beginTransaction();
        /*Cuando queremos actualizar un objeto que es persistente en la base de datos no podemos usar
        * persist cuando queremos ACTUALIZAR o MODIFICAR un objeto, ya que persist crea un objeto nuevo
        * y al tener la misma ID da error, por lo que hay que usar MERGE*/
        //session.persist(address);
        session.merge(address);
        session.getTransaction().commit();
        /*BORRAR UN OBJETO DE LA CACHE DE SESSION: session.detach(address)*/
    }

    @Test
    void remove(){
        /*Este objeto session no tiene informacion del objeto address, ya que se ha creado un nuevo
        * session y no tiene nada que ver con los session de los otros test.
        * Al no saber si el objeto existe deberia dar error como es el caso de los test anteriores
        * pero este funciona perfectamente. Para una eliminacion no necesita saber si el objeto
        * existe o no existe en la base de datos, simplemente lanza el comando y si existe lo elimina y
        * si no existe no pasa nada*/
        Session session = HibernateUtil.getSessionFactory().openSession();
        Address address = new Address("street 1", "city2", "country1");
        address.setId(1L);
        session.beginTransaction();
        session.remove(address);
        session.getTransaction().commit();
        System.out.println(session.contains(address));
    }
    @Test
    void differentSessions(){
        //UPDATE

        /*Al cerrar la session que encuentra el objeto y volver a crear una nueva session, la
        * segunda sesion ya no tiene la informacion del objeto address por lo que contains devuelve
        * false en esa segunda session, en cambio en la primera session, como es la que realiza la
        * busqueda al llamar al metodo contains este devuelve true, ya que lo reconoce*/
        Session session = HibernateUtil.getSessionFactory().openSession();
        var address = session.find(Address.class, 1L);
        System.out.println(session.contains(address));

        session.close();

        session = HibernateUtil.getSessionFactory().openSession();
        System.out.println(session.contains(address));


    }

    @Test
    void getReference(){
        //UPDATE

        /*getReference crea un proxy (funciona como un puntero hacia la base de datos). No se trae la informacion
        * del objeto solo guarda una referencia. Solo se creara una consulta select cuando se
        * quiera acceder a la informacion.
        * Si comento las lineas de println y ejecuto el test no aparece ningun select*/
        Session session = HibernateUtil.getSessionFactory().openSession();
        var address = session.getReference(Address.class, 1L);
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAA"+address);
        System.out.println("______________");
        System.out.println(address.getCountry());



    }

}
