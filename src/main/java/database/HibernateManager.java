package database;

import models.Attempt;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateManager {
    private final SessionFactory sessionFactory;

    public HibernateManager() {
//        File f = new File("D:\\itmo\\web\\lab3\\lab3\\src\\main\\resources\\hibernate.cfg.xml");
//
//        sessionFactory = new Configuration().configure(f).buildSessionFactory();

        //работает, когда hibernate.cfg.xml лежит в src\\main\\resources
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public List<Attempt> getAttempts() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Attempt", Attempt.class).list();
        }
    }

    public void addAttempt(Attempt attempt) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(attempt);
            transaction.commit();
        }
    }

    public void clearAttempts() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("delete from Attempt").executeUpdate();
            transaction.commit();
        }
    }
}
