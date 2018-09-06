package org.reyantovich.yauheni.runner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.reyantovich.yauheni.pojo.Developer;
import org.reyantovich.yauheni.pojo.HmdObjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.xml.crypto.dsig.spec.HMACParameterSpec;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

@Component
public class ObjectTypeDao {

    void addObjectType(String name){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        HmdObjectType objectType = new HmdObjectType(UUID.randomUUID(), name);
        session.save(objectType);
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    HmdObjectType getObjectType(){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        List ot = session.createQuery("FROM HmdObjectType ").list();
        transaction.commit();
        session.close();
        sessionFactory.close();
        return (HmdObjectType) ot.get(0);
    }
}
