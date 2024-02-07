package com.example;

import com.example.model.Employee;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateHints;
import org.hibernate.stat.CacheRegionStatistics;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class CachingTest {

    @Test
    void caching_1level() {
        /**/
        insertData();

        var session = HibernateUtil.getSessionFactory().openSession();

        var employee = session.find(Employee.class, 1L);
        System.out.println(employee);

        // utiliza la caché de 1 nivel
        // Como ya tiene los datos guardados en la cache de session no se lanza consulta a la base de datos
        employee = session.find(Employee.class, 1L);
        System.out.println(employee);
    }

    /*
    Añadir en la entidad Employee:
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @Cacheable
     */
    @Test
    void caching_2level_WithAnnotations() {
        insertData();
        var session = HibernateUtil.getSessionFactory().openSession();
        String region = "com.example.model.Employee";

        // extrae de base de datos
        var employee = session.find(Employee.class, 1L);
        System.out.println(employee);
        /*Esto muestra que se ha creado una region asignada a la clase Employee, que es la que lleva las etiquetas
        * de cache*/
        Arrays.stream(session.getSessionFactory().getStatistics().getSecondLevelCacheRegionNames())
                .forEach(System.out::println);
        session.close();

        //Session cerrada y abrimos otra nueva

        session = HibernateUtil.getSessionFactory().openSession();

        //extrae de la cache de segundo nivel
        employee = session.find(Employee.class, 1L);

        printStats(session, region);
        /*con la región creada llamamos al metodo printStats, este cuenta las veces que se ha utilizado la cache
        * Devuelve lo siguiente:
        * Hit count: 1
          Miss count: 1
          Hit ratio: 0.5
          * El miss se produce en la primera consulta a la base de datos, que no encuentra el objeto en cache y debe
          * conectar a la base de datos, en esta segunda busqueda no se conecta porque ya esta guardado en cache de
          * segundo nivel, aunque sean session diferentes, y eso da de resultado un hit*/
        System.out.println(employee);
        session.close();

    }

    @Test
    void caching_2level_WithQueryHint() {
        /*Otra manera de activar la cache de lvl 2 es poner los metodos setCacheable() y setCacheRegion() en
        * la creacion de la query de session*/

        insertData();
        var session = HibernateUtil.getSessionFactory().openSession();
        String jpql = "select e from Employee e where e.id = :id";
        String region = "com.example.model.Employee";
        Long id = 1L;

        // 1. extrae de base de datos
        var employee = session.createQuery(jpql, Employee.class)
                .setParameter("id", id)
                .setCacheable(true)
                .setCacheRegion(region)
//                .setHint(HibernateHints.HINT_CACHEABLE, true)   //lo mismo que los dos metodos de arriba
//                .setHint(HibernateHints.HINT_CACHE_REGION, model)
                .getSingleResult();

        System.out.println(employee);
        Arrays.stream(session.getSessionFactory().getStatistics().getSecondLevelCacheRegionNames())
                .forEach(System.out::println);
        session.close();




        // 2. utiliza caché de 2 level
        session = HibernateUtil.getSessionFactory().openSession();
        employee = session.createQuery(jpql, Employee.class)
                .setParameter("id", id)
                .setCacheable(true)
                .setCacheRegion(region)
                .getSingleResult();
        System.out.println(employee);
        //REFRESCA LA CACHE. Si has modificado algo en base de datos puedes defrescar la cache para que se actualice
        // session.getSessionFactory().getCache().evictQueryRegion(model);
        session.close();


        // 3. utiliza caché de 2 level
        session = HibernateUtil.getSessionFactory().openSession();
        employee = session.createQuery(jpql, Employee.class)
                .setParameter("id", id)
                .setCacheable(true)
                .setCacheRegion(region)
                .getSingleResult();
        printStats(session, region);
        System.out.println(employee);
        session.close();


    }

    private void printStats(Session session, String region){
        // System.out.println(session.getSessionFactory().getStatistics().getSecondLevelCacheHitCount());

        Statistics statistics = session.getSessionFactory().getStatistics();
        CacheRegionStatistics secondLevelCacheStatistics =
                statistics.getDomainDataRegionStatistics(region);
        long hitCount = secondLevelCacheStatistics.getHitCount();
        long missCount = secondLevelCacheStatistics.getMissCount();
        double hitRatio = (double) hitCount / (hitCount + missCount);
        System.out.println("Hit count: " + hitCount);
        System.out.println("Miss count: " + missCount);
        System.out.println("Hit ratio: " + hitRatio);

    }


    void insertData(){

        var session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        var emp1 = new Employee("111", "emp1");
        var emp2 = new Employee("222", "emp2");
        var emp3 = new Employee("333", "emp3");

        session.persist(emp1);
        session.persist(emp2);
        session.persist(emp3);

        session.getTransaction().commit();

    }
}
