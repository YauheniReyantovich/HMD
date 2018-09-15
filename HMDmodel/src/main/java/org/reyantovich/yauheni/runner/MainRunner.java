package org.reyantovich.yauheni.runner;

import org.reyantovich.yauheni.attributesIds.CategoryAttributes;
import org.reyantovich.yauheni.dao.*;
import org.reyantovich.yauheni.hmdbase.HmdObjectType;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.UUID;

public class MainRunner {

    public static void main(String[] args){

//        addObjectType("Ingredient");
        addAttributeByObjectTypeId(CategoryAttributes.CATEGORY, "EngName");



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
}
