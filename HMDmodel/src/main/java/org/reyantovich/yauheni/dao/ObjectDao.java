package org.reyantovich.yauheni.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.reyantovich.yauheni.HqlService;
import org.reyantovich.yauheni.hmdbase.HmdObjectType;
import org.reyantovich.yauheni.hmdbase.HmdObjects;
import org.reyantovich.yauheni.runner.SessionHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

@Component
public class ObjectDao {

    private SessionHolder sessionHolder;

    public List<HmdObjects> getAllObjectsOfObjectType(UUID objectTypeId) {

        Session session = sessionHolder.getSession();
        HmdObjectType objectType = session.get(HmdObjectType.class, objectTypeId);
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<HmdObjects> cr = cb.createQuery(HmdObjects.class);
        Root<HmdObjects> root = cr.from(HmdObjects.class);

        cr.select(root).where(cb.equal(root.get("objectType"), objectType));

        Query<HmdObjects> query = session.createQuery(cr);

        return query.getResultList();
    }

    public HmdObjects getObjectByValue(String objectType, String attribute, String value){
        ResourceBundle resourceBundle = ResourceBundle.getBundle(HqlService.SQL_PROPERTIES);
        Query sqlQuery = sessionHolder.getSession().createQuery(resourceBundle.getString(HqlService.GET_OBJECT_ID_BY_VALUE));

        sqlQuery.setParameter("value", value);
        sqlQuery.setParameter("objOTId", objectType);
        sqlQuery.setParameter("attrId", attribute);

        return (HmdObjects) sqlQuery.getSingleResult();
    }

    @Autowired
    public ObjectDao(SessionHolder sessionHolder){this.sessionHolder = sessionHolder;}

}
