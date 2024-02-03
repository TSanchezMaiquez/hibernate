package com.example;

import com.example.model.Address;
import org.junit.jupiter.api.Test;

public class sessionTest {

    @Test
    void refreshTest(){
        var session = HibernateUtil.getSessionFactory().openSession();

        var address = new Address("stree1", "city1", "country1");

        session.beginTransaction();
        session.persist(address);
        session.getTransaction().commit();

        session.close();

        session = HibernateUtil.getSessionFactory().openSession();

        //Si haces cambios en un objeto esos se quedan guardados en el objeto session actual, aunque no se hayan
        //persistido en la base de datos. Si esos cambios, sin persistir, no te interesan y quieres recuperar los
        //datos originarles puedes hacer un refresh el cuál hará una busqueda select en BD
        address.setCity("ciudad cambiada");
        address.setCountry("otro pais");

        session.refresh(address);
        System.out.println(address);


    }
    @Test
    void getVsGetReference(){
        //get te trae el objeto completo al conectar con base de datos
        //getReference te trae la referencia y cuando le pides que te muestre el objeto entero o alguna de sus
        //propiedades vuelve a hacer otra consulta a la base de datos para traerse esa informacion

        createData();
        var session = HibernateUtil.getSessionFactory().openSession();

        var address = session.get(Address.class, 1L);
        System.out.println("----------------------");
        System.out.println(address.getCountry());

        var address1 = session.getReference(Address.class, 1L);
        System.out.println("----------------------");
        System.out.println(address1.getCountry());
    }
    @Test
    void load(){
        createData();
        var session = HibernateUtil.getSessionFactory().openSession();
        var address = new Address("prueba", "prueba", "country1");

        //devuelve false, ya que no ha trabajado con el objeto y no lo conoce
        System.out.println(session.contains(address));
        //Aunque address se ha creado con unos valores diferentes a los que hay en BD cuando uso el metodo
        //load lo que hace este es cargar en ese objeto los datos que hay guardados en BD por lo que lo sobreescribe.
        session.load(address, 1L);
        System.out.println(address);

    }
    @Test
    void evict(){
        createData();
        var session = HibernateUtil.getSessionFactory().openSession();

        var address = session.get(Address.class, 1L);
        System.out.println(session.contains(address));  //True

        //Hacen lo mismo.
        session.evict(address);  //Eliminas el objeto de la cache
        //session.detach(address);

        System.out.println(address);
        System.out.println(session.contains(address));  //False
    }

    @Test
    void isDirty(){
        //Dirty comprueba si hay cambios en el objeto que no se han guardado
        createData();
        var session = HibernateUtil.getSessionFactory().openSession();

        var address = session.get(Address.class, 1L);

        System.out.println(session.isDirty()); //False

        address.setCity("otro sitio");
        System.out.println(session.isDirty()); //True

    }

    @Test
    void byId(){
        createData();
        var session = HibernateUtil.getSessionFactory().openSession();

        /*loadOptional permite buscar un objeto por una id que no sabemos si existe,
        * El caso de que no exista no devuelve nada
        * Si existe devuelve el objeto*/
        session.byId(Address.class)
                .loadOptional(9999L)
                .ifPresent(System.out::println);

        session.byId(Address.class)
                .loadOptional(1L)
                .ifPresent(System.out::println);

    }
    void createData(){
        var session = HibernateUtil.getSessionFactory().openSession();

        var address = new Address("stree1", "city1", "country1");

        session.beginTransaction();
        session.persist(address);
        session.getTransaction().commit();

        session.close();
    }
}
