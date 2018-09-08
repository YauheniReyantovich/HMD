package org.reyantovich.yauheni.runner;

import org.reyantovich.yauheni.dao.*;
import org.reyantovich.yauheni.dao.impl.UserDaoImpl;
import org.reyantovich.yauheni.hmdbase.HmdObjectType;
import org.reyantovich.yauheni.model.pojo.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.UUID;

public class MainRunner {

    public static void main(String[] args){

//        addObjectByObjectTypeId(UserAttributes.USER);
//        addUser(UserRoles.REGISTERED, "Pavel", "4321");

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AttributeDao.class);
        ctx.register(ObjectTypeDao.class);
        ctx.register(SessionHolder.class);
        ctx.register(ObjectDao.class);
        ctx.register(ValuesDao.class);
        ctx.register(UserDaoImpl.class);
        ctx.refresh();

        UserDao userDao = ctx.getBean(UserDaoImpl.class);
        User user = userDao.findByUsername("Darment");
        System.out.println(user);

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

    static private void addObjectByObjectTypeId(String objectTypeId){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ObjectDao.class);
        ctx.register(ObjectTypeDao.class);
        ctx.register(SessionHolder.class);
        ctx.refresh();

        ObjectTypeDao otDao = ctx.getBean(ObjectTypeDao.class);
        UUID objectTypeUUID = UUID.fromString(objectTypeId);
        HmdObjectType objectType = otDao.getObjectType(objectTypeUUID);

        ObjectDao bean = ctx.getBean(ObjectDao.class);
        bean.addObject(objectType);
    }
}
