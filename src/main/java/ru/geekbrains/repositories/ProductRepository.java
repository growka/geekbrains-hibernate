package ru.geekbrains.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.geekbrains.model.Product;
import ru.geekbrains.model.User;

import javax.persistence.EntityManagerFactory;

@Component
public class ProductRepository {

    public static Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    private static SessionFactory sessionFactory;

    @Autowired
    public ProductRepository(EntityManagerFactory factory) {
        if (factory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }

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
            logger.info(product.toString());
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

    public static void findUserByProduct(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            logger.info(product.toString());
            for (User u : product.getUserList()) {
                logger.info(u.getName());
            }
            session.getTransaction().commit();
        }
    }
}