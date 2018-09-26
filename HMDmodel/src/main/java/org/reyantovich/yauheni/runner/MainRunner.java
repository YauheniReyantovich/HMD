package org.reyantovich.yauheni.runner;

import org.hibernate.Session;
import org.reyantovich.yauheni.attributesIds.CategoryAttributes;
import org.reyantovich.yauheni.attributesIds.IngredientHolderAttributes;
import org.reyantovich.yauheni.attributesIds.PizzaAttributes;
import org.reyantovich.yauheni.dao.*;
import org.reyantovich.yauheni.hmdbase.HmdAttributes;
import org.reyantovich.yauheni.hmdbase.HmdObjectType;
import org.reyantovich.yauheni.hmdbase.HmdObjects;
import org.reyantovich.yauheni.hmdbase.HmdValues;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.UUID;

public class MainRunner {

    public static void main(String[] args){

//        addObjectType("IngredientHolder");
//        addAttributeByObjectTypeId(PizzaAttributes.PIZZA, "totalCost");
//        addValue(UUID.fromString("D91D2D80-1082-4973-9F13-C3F54F9159D2"), CategoryAttributes.PRIORITY_UUID, "4");



//        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
//        ctx.register(ObjectDao.class);
//        ctx.register(SessionHolder.class);
//        ctx.register(CategoryDaoImpl.class);
//        ctx.refresh();
//
//        CategoryDaoImpl categoryDaoImpl = ctx.getBean(CategoryDaoImpl.class);
//        Collection<Category> objects = categoryDaoImpl.getAllCategories();
//
//        for(Category object: objects){
//            System.out.println(object);
//        }
    }

    static private void addObjectType(String objectType){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ObjectTypeDao.class);
        ctx.register(SessionHolder.class);
        ctx.refresh();

        ObjectTypeDao objectTypeDao = ctx.getBean(ObjectTypeDao.class);
        objectTypeDao.addObjectType(objectType);
    }

    static private void addAttributeByObjectTypeId(String objectTypeId, String name){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AttributeDao.class);
        ctx.register(ObjectTypeDao.class);
        ctx.register(SessionHolder.class);
        ctx.refresh();

        ObjectTypeDao otDao = ctx.getBean(ObjectTypeDao.class);
        UUID objectTypeUUID = UUID.fromString(objectTypeId);
        HmdObjectType objectType = otDao.getObjectType(objectTypeUUID);

        AttributeDao bean = ctx.getBean(AttributeDao.class);
        bean.addAttribute(objectType, name);
    }

    static private void addValue(UUID objectId, UUID attributeId, String value){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(SessionHolder.class);
        ctx.refresh();

        SessionHolder sessionHolder = ctx.getBean(SessionHolder.class);
        sessionHolder.init();
        Session session = sessionHolder.getSession();
        HmdObjects object = session.get(HmdObjects.class, objectId);
        HmdAttributes attribute = session.get(HmdAttributes.class, attributeId);

        HmdValues hmdValue = new HmdValues(object, attribute, value);
        sessionHolder.saveAndCommit(hmdValue);
        session.close();
    }
}
