package org.dao;

import org.dao.models.Owner;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class OwnerDao {
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
    public Owner findOwnerById(Integer id){
        Session session = openSession();
        Transaction transaction = startTransaction(session);
        Owner owner = session.get(Owner.class, id);
        commitTransaction(transaction);
        closeSession(session);
        return owner;
    }
    public Owner save(Owner owner){
        Session session = openSession();
        Transaction transaction = startTransaction(session);
        session.persist(owner);
        commitTransaction(transaction);
        closeSession(session);
        return owner;
    }
    public void update(){
        Session session = openSession();
        Transaction transaction = startTransaction(session);
        session.flush();
        commitTransaction(transaction);
        closeSession(session);
    }
    public void delete(Owner owner){
        Session session = openSession();
        Transaction transaction = startTransaction(session);
        session.remove(owner);
        commitTransaction(transaction);
        closeSession(session);
    }
}
