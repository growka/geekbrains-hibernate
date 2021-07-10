package ru.geekbrains.crud;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.geekbrains.MainApp;

public class CrudApp {

    private static SessionFactory sessionFactory;

    public static void init() {
        MainApp.forcePrepareData();
        sessionFactory = new Configuration()
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();
    }

    public static void close() {sessionFactory.close();}

    public static void create() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Product product = new Product("peach", 100);
            session.saveOrUpdate(product);
            session.getTransaction().commit();
        }
    }

    public static void read() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, 3L);
            System.out.println(product);
            session.getTransaction().commit();
        }
    }

    public static void update() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, 1L);
            session.evict(product);
            product.setPrice(200);
            session.update(product);
            session.getTransaction().commit();
        }
    }

    public static void delete() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, 2L);
            session.delete(product);
            session.getTransaction().commit();
        }
    }

    public static void main(String[] args) {

        init();

        //create();
        //read();
        //update();
        delete();

        close();
    }

}
