package org.reyantovich.yauheni.runner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.reyantovich.yauheni.pojo.Developer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeveloperRunner {

//    public static void main(String[] args) {
//        sessionFactory = new Configuration().configure().buildSessionFactory();
//
//        DeveloperRunner developerRunner = new DeveloperRunner();
//
//        System.out.println("Adding developer's records to the DB");
//        /*
//           Adding developer's records to the database (DB)
//         */
//        developerRunner.addDeveloper("Proselyte", "Developer", "Java Developer", 2);
//        developerRunner.addDeveloper("Some", "Developer", "C++ Developer", 2);
//        developerRunner.addDeveloper("Peter", "UI", "UI Developer", 4);
//
//        System.out.println("List of developers");
//        /*
//          List developers
//         */
//        List developers = developerRunner.listDevelopers();
//        for (Object developer : developers) {
//            System.out.println(developer);
//        }
//        System.out.println("===================================");
//        System.out.println("Removing Some Developer and updating Proselyte");
//        /*
//          Update and Remove developers
//         */
//        developerRunner.updateDeveloper(16, 3);
//        developerRunner.removeDeveloper(17);
//
//        System.out.println("Final list of developers");
//        /*
//          List developers
//         */
//        developers = developerRunner.listDevelopers();
//        for (Object developer : developers) {
//            System.out.println(developer);
//        }
//        System.out.println("===================================");
//
//    }

    public void addDeveloper(String firstName, String lastName, String specialty, int experience) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Developer developer = new Developer(firstName, lastName, specialty, experience);
        session.save(developer);
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

//    private List listDevelopers() {
//        Session session = sessionFactory.openSession();
//        Transaction transaction = null;
//
//        transaction = session.beginTransaction();
//        List developers = session.createQuery("FROM Developer").list();
//
//        transaction.commit();
//        session.close();
//        return developers;
//    }

//    private void updateDeveloper(int developerId, int experience) {
//        Session session = sessionFactory.openSession();
//        Transaction transaction = null;
//
//        transaction = session.beginTransaction();
//        Developer developer = (Developer) session.get(Developer.class, developerId);
//        developer.setExperience(experience);
//        session.update(developer);
//        transaction.commit();
//        session.close();
//    }

//    private void removeDeveloper(int developerId) {
//        Session session = sessionFactory.openSession();
//        Transaction transaction = null;
//
//        transaction = session.beginTransaction();
//        Developer developer = (Developer) session.get(Developer.class, developerId);
//        session.delete(developer);
//        transaction.commit();
//        session.close();
//    }

    public Developer getDeveloper(int developerId){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Developer developer = (Developer) session.get(Developer.class, developerId);
        transaction.commit();
        session.close();
        sessionFactory.close();
        return developer;
    }

}
