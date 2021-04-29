package io.lhdev.ersbackend.main;

import io.lhdev.ersbackend.model.User;
import io.lhdev.ersbackend.util.SessionUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Test {

    public static void main(String[] args) {

       Session session = SessionUtility.getSessionFactory().openSession();

       Transaction tx = session.beginTransaction();
       // Create new user
        User user = new User("username", "password", "leah", "manager",
                "manager@lhdev.io", 1); // user is currently transient

        //now user is persistent
        session.save(user);

        tx.commit();

    }
}
