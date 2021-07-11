package ru.geekbrains.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//import ru.geekbrains.SpringBootInitialLoad;
import ru.geekbrains.model.Product;
import ru.geekbrains.model.User;

import javax.persistence.EntityManagerFactory;

@Component
public class UserRepository {

    public static Logger logger = LoggerFactory.getLogger(UserRepository.class);

    private static SessionFactory sessionFactory;

    @Autowired
    public UserRepository(EntityManagerFactory factory) {
        if (factory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }

    public static void create(String name) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            User user = new User(name);
            session.saveOrUpdate(user);
            session.getTransaction().commit();
        }
    }

    public static void read(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            logger.info(user.toString());
            session.getTransaction().commit();
        }
    }

    public static void update(Long id, String name) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.evict(user);
            user.setName(name);
            session.update(user);
            session.getTransaction().commit();
        }
    }

    public static void delete(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        }
    }

    public static void findProductByUser(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            logger.info(user.toString());
            for (Product p : user.getProductList()) {
                logger.info(p.getTitle());
                logger.info(String.valueOf(p.getPrice()));
            }
            session.getTransaction().commit();
        }
    }
}
