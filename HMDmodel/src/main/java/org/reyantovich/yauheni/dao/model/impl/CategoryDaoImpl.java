package org.reyantovich.yauheni.dao.model.impl;

import org.hibernate.Session;
import org.reyantovich.yauheni.attributesIds.CategoryAttributes;
import org.reyantovich.yauheni.dao.ValueDao;
import org.reyantovich.yauheni.dao.model.CategoryDao;
import org.reyantovich.yauheni.dao.ObjectDao;
import org.reyantovich.yauheni.hmdbase.HmdObjectType;
import org.reyantovich.yauheni.hmdbase.HmdObjects;
import org.reyantovich.yauheni.hmdbase.HmdValues;
import org.reyantovich.yauheni.model.pojo.Category;
import org.reyantovich.yauheni.runner.SessionHolder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CategoryDaoImpl implements CategoryDao {

    private SessionHolder sessionHolder;

    private ObjectDao objectDao;

    private ValueDao valueDao;

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
                    UUID attributeId = value.getValuesId().getAttribute().getAttrId();
                    if(CategoryAttributes.RUS_NAME_UUID.equals(attributeId)){
                        category.setRusName(value.getValue());
                    }
                    if(CategoryAttributes.ENG_NAME_UUID.equals(attributeId)){
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

            Map<UUID, String> values = new HashMap<>();
            values.put(CategoryAttributes.ENG_NAME_UUID, category.getEngName());
            values.put(CategoryAttributes.RUS_NAME_UUID, category.getRusName());
            valueDao.addValues(object, values);

            sessionHolder.commit();
            sessionHolder.close();
        }
    }

    public CategoryDaoImpl(SessionHolder sessionHolder, ObjectDao objectDao, ValueDao valueDao){
        this.sessionHolder = sessionHolder;
        this.objectDao = objectDao;
        this.valueDao = valueDao;
    }
}
