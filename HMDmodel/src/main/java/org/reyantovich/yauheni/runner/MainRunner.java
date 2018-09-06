package org.reyantovich.yauheni;

import org.reyantovich.yauheni.runner.ObjectTypeDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainRunner {

    public static void main(String[] args){

        ApplicationContext context = new AnnotationConfigApplicationContext(ObjectTypeDao.class);

        ObjectTypeDao ot = context.getBean(ObjectTypeDao.class);
        ot.asd();



    }
}
