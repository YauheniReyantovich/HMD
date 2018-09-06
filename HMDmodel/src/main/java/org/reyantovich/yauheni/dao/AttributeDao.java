package org.reyantovich.yauheni.runner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.reyantovich.yauheni.pojo.HmdAttributes;
import org.reyantovich.yauheni.pojo.HmdObjectType;
import org.springframework.stereotype.Component;

@Component
public class AttributeDao {

    public void addAttribute(HmdObjectType objectType, String name){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        HmdAttributes attribute = new HmdAttributes(objectType, name);
        session.save(attribute);
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

}
