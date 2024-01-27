package com.example;

import com.example.model.example2.Company;
import com.example.model.example2.CompanyPK;
import org.junit.jupiter.api.Test;

public class EmbeddedIdTest {

    @Test
    void embeddedIdTest(){
        insertData();
        var session = HibernateUtil.getSessionFactory().openSession();

        var cPD1 = new CompanyPK("cif1", "brand1");
        var c1 = session.find(Company.class, cPD1);

        System.out.println(c1);
        System.out.println(c1.getId());
    }
    void insertData(){

        var session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        var cPD1 = new CompanyPK("cif1", "brand1");
        var cPD2 = new CompanyPK("cif1", "brand2");

        var c1 = new Company(cPD1, "madrdid", 5);
        var c2 = new Company(cPD2, "murcia", 15);

        session.persist(c1);
        session.persist(c2);

        session.getTransaction().commit();
        session.close();
    }
}
