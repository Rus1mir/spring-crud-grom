package com.dao;

import com.exception.NotFoundException;
import com.model.Item;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ItemDAO {

    private SessionFactory sessionFactory;

    public Item save(Item item) {

        Transaction tr = null;
        try (Session session = getSessionFactory().openSession()) {

            tr = session.beginTransaction();

            session.persist(item);

            tr.commit();
            return item;

        } catch (HibernateException e) {
            if (tr != null)
                tr.rollback();
            throw e;
        }
    }

    public Item getById(long id) {

        try (Session session = getSessionFactory().openSession()) {

            Item item = session.get(Item.class, id);

            if (item == null)
                throw new NotFoundException("Item id: " + id + " was not found");

            return item;

        } catch (HibernateException e) {
            throw e;
        }
    }

    public Item update(Item item) {

        Transaction tr = null;
        try (Session session = getSessionFactory().openSession()) {

            tr = session.beginTransaction();

            session.update(item);

            tr.commit();
            return item;

        } catch (HibernateException e) {
            if (tr != null)
                tr.rollback();
            throw e;
        }
    }

    public void delete(long id) {

        Transaction tr = null;
        try (Session session = getSessionFactory().openSession()) {

            tr = session.beginTransaction();

            session.delete(session.load(Item.class, id));

            tr.commit();

        } catch (HibernateException e) {
            if (tr != null)
                tr.rollback();
            throw e;
        }
    }

    private SessionFactory getSessionFactory() {

        if (sessionFactory == null)
            sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }
}
