package org.reyantovich.yauheni.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.reyantovich.yauheni.hmdbase.HmdAttributes;
import org.reyantovich.yauheni.hmdbase.HmdObjects;
import org.reyantovich.yauheni.hmdbase.HmdValues;
import org.reyantovich.yauheni.runner.SessionHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ValuesDao {

    private SessionHolder sessionHolder;

    public void addValues(HmdObjects object, Map<HmdAttributes, String> values){
//        sessionHolder = sessionHolder.init();
        for(Map.Entry<HmdAttributes, String> entry: values.entrySet()){
            HmdValues value = new HmdValues(object, entry.getKey(), entry.getValue());
            sessionHolder.save(value);
        }
//        sessionHolder.commit();
//        sessionHolder.close();
    }

    public void getAllAttrs(HmdObjects object){
        sessionHolder = sessionHolder.init();
        Session session = sessionHolder.getSession();
        List values = null;
        try {
//            CriteriaBuilder cb = session.getCriteriaBuilder();
//            CriteriaQuery<HmdValues> cq = cb.createQuery(HmdValues.class);
//            Root<HmdValues> root = cq.from(HmdValues.class);
//            cq.select(root);
//
//            Query<HmdValues> query = session.createQuery(cq);
//            values = query.getResultList();

            Criteria criteria = session.createCriteria(HmdValues.class, "val");
            criteria.add(Restrictions.eq("val.object", object));
            values = criteria.list();
            sessionHolder.commit();
        }
        catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            sessionHolder.close();
        }
        if(values != null) {
            for (Object value : values) {
                System.out.println(((HmdValues) value).getValue() + " - " + ((HmdValues) value).getAttribute().getName());
            }
        }
    }

    public void getByAttrs(HmdObjects object, HmdAttributes... attributes){

    }

    @Autowired
    public ValuesDao(SessionHolder sessionHolder){this.sessionHolder = sessionHolder;}

}