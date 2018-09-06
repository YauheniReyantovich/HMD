package org.reyantovich.yauheni.runner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

@Component
public class SessionHolder {

    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    public SessionHolder init(){
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
        this.session = sessionFactory.openSession();
        this.transaction = session.beginTransaction();
        return this;
    }

    public void commit(){
        this.transaction.commit();
    }

    public void save(Object obj){this.session.save(obj);}

    public void saveAndCommit(Object obj){
        this.session.save(obj);
        this.transaction.commit();
    }

    public void close(){
        this.session.close();
        this.sessionFactory.close();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
