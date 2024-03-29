package org.dao;

import org.dao.models.Cat;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CatDao {

    public Session openSession(){
        return HibernateSessionFactoryUtil.getSessionFactory().openSession();
    }
    public Transaction startTransaction(Session session){
        return session.beginTransaction();
    }
    public void commitTransaction(Transaction transaction){
        transaction.commit();
    }
    public void closeSession(Session session){
        session.close();
    }

    public Cat findCatById(Integer id){
        Session session = openSession();
        Transaction transaction = startTransaction(session);
        Cat cat = session.get(Cat.class, id);
        commitTransaction(transaction);
        closeSession(session);
        return cat;
    }
    public void save(Cat cat){
        Session session = openSession();
        Transaction transaction = startTransaction(session);
        session.persist(cat);
        commitTransaction(transaction);
        closeSession(session);
    }
    public void update() {
        Session session = openSession();
        Transaction transaction = startTransaction(session);
        session.flush();
        transaction.commit();
        session.close();
    }

    public void delete(Cat cat){
        Session session = openSession();
        Transaction transaction = startTransaction(session);
        session.remove(cat);
        transaction.commit();
        session.close();
    }

}
