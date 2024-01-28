package com.sanchez.test.CRUD;

import com.sanchez.jardineriahibernate.HibernateUtil;
import com.sanchez.jardineriahibernate.model.Product;
import com.sanchez.jardineriahibernate.model.ProductRange;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class CRUDProductRangeTest {

    @Test
    void persist() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        //String rangeCode, String html_description, String textDescription, String image
        var pr1 = new ProductRange("1", "htm1", "txt1", "img1");
        var pr2 = new ProductRange("2", "htm2", "txt2", "img2");

        session.persist(pr1);
        session.persist(pr2);

        session.getTransaction().commit();

        session.close();

        System.out.println(pr1);
        System.out.println(pr2);

    }

    @Test
    void update() {

        createData();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Recuperar un producto por su identificador
        var productToUpdate = session.find(ProductRange.class, "1");

        // Actualizar propiedades del producto
        productToUpdate.setTextDescription("Nueva descripción");
        productToUpdate.setImage("hty");

        // Guardar los cambios
        session.merge(productToUpdate);

        session.getTransaction().commit();
        session.close();
    }

    @Test
    void retrieve() {
        createData();
        Session session = HibernateUtil.getSessionFactory().openSession();

        var retrievedProduct = session.find(ProductRange.class, "1");

        System.out.println(retrievedProduct);

        session.close();
    }

    @Test
    void delete() {
        createData();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Recuperar un producto por su identificador
        var productToDelete = session.find(ProductRange.class, "1");

        // Eliminar el producto
        session.remove(productToDelete);

        session.getTransaction().commit();
        session.close();
    }

    void createData() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        //String rangeCode, String html_description, String textDescription, String image
        var pr1 = new ProductRange("1", "htm1", "txt1", "img1");
        var pr2 = new ProductRange("2", "htm2", "txt2", "img2");
        var pr3 = new ProductRange("3", "htm3", "txt3", "img3");
        var pr4 = new ProductRange("4", "htm4", "txt4", "img4");
        var pr5 = new ProductRange("5", "htm5", "txt5", "img5");
        var pr6 = new ProductRange("6", "htm6", "txt6", "img6");

        session.persist(pr1);
        session.persist(pr2);
        session.persist(pr3);
        session.persist(pr4);
        session.persist(pr5);
        session.persist(pr6);


        session.getTransaction().commit();

        session.close();
    }
}