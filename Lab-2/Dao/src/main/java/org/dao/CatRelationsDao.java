package org.dao;

import org.dao.models.CatRelations;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CatRelationsDao {
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
    public void save(CatRelations catRelations){
        Session session = openSession();
        Transaction transaction = startTransaction(session);
        session.persist(catRelations);
        commitTransaction(transaction);
        closeSession(session);
    }

    public void update(){
        Session session = openSession();
        Transaction transaction = startTransaction(session);
        session.flush();
        commitTransaction(transaction);
        closeSession(session);
    }
}
