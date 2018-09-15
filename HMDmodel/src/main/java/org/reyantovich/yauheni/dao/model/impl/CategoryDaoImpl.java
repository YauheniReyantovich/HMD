package org.reyantovich.yauheni.dao.model.impl;

import org.hibernate.Session;
import org.reyantovich.yauheni.attributesIds.CategoryAttributes;
import org.reyantovich.yauheni.dao.model.CategoryDao;
import org.reyantovich.yauheni.dao.ObjectDao;
import org.reyantovich.yauheni.hmdbase.HmdAttributes;
import org.reyantovich.yauheni.hmdbase.HmdObjectType;
import org.reyantovich.yauheni.hmdbase.HmdObjects;
import org.reyantovich.yauheni.hmdbase.HmdValues;
import org.reyantovich.yauheni.model.pojo.Category;
import org.reyantovich.yauheni.runner.SessionHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryDaoImpl implements CategoryDao {

    private SessionHolder sessionHolder;

    private ObjectDao objectDao;

    @Override
    public List<Category> getAllCategories() {
        try {
            sessionHolder.init();
            List<HmdObjects> objects = objectDao.getAllObjectsOfObjectType(CategoryAttributes.CATEGORY_UUID);
            List<Category> categories = new ArrayList<>();

            Category category;
            for(HmdObjects object: objects){
                category = new Category();
                for(HmdValues value: object.getValues()){
                    if(value.getValuesId().getAttribute().getAttrId().equals(CategoryAttributes.RUS_NAME_UUID)){
                        category.setRusName(value.getValue());
                    }
                    if(value.getValuesId().getAttribute().getAttrId().equals(CategoryAttributes.ENG_NAME_UUID)){
                        category.setEngName(value.getValue());
                    }
                }
                categories.add(category);
            }

            return categories;
        } catch (Exception e) {
            sessionHolder.getSession().getTransaction().rollback();
            e.printStackTrace();
        } finally {
            sessionHolder.close();
        }
        return null;
    }

    @Override
    public void addCategory(Category category) {
        if(category != null) {
            sessionHolder = sessionHolder.init();
            Session session = sessionHolder.getSession();
            HmdObjectType categoryObjectType = session.get(HmdObjectType.class, CategoryAttributes.CATEGORY_UUID);
            HmdObjects object = new HmdObjects(categoryObjectType);
            sessionHolder.save(object);

            if(category.getEngName() != null) {
                sessionHolder.save(
                        new HmdValues(object, session.get(HmdAttributes.class, CategoryAttributes.ENG_NAME_UUID), category.getEngName())
                );
            }
            if(category.getRusName() != null) {
                sessionHolder.save(
                        new HmdValues(object, session.get(HmdAttributes.class, CategoryAttributes.RUS_NAME_UUID), category.getRusName())
                );
            }

            sessionHolder.commit();
            sessionHolder.close();
        }
    }

    public CategoryDaoImpl(SessionHolder sessionHolder, ObjectDao objectDao){
        this.sessionHolder = sessionHolder;
        this.objectDao = objectDao;
    }
}
