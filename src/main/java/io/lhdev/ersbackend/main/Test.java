package io.lhdev.ersbackend.main;

import io.lhdev.ersbackend.model.Reimbursement;
import io.lhdev.ersbackend.model.User;
import io.lhdev.ersbackend.util.SessionUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class Test {

    public static void main(String[] args) {

       Session session = SessionUtility.getSessionFactory().openSession();

//       Transaction tx = session.beginTransaction();
//       // Create new user
//        User user = new User(1001, "username", "password", "Zoe", "KName",
//                "manager@lhdev.io", 1); // user is currently transient
//
//       // now user is persistent
//        session.persist(user);

//        Transaction tx = session.beginTransaction();
//
//        Reimbursement reimbursement = new Reimbursement(46, "fuel",
//                1001, 3);
//
//        session.persist(reimbursement);
//
//        tx.commit();
//
//        session.close();

//        Transaction tx = session.beginTransaction();
        List<Reimbursement> reimbursements = session.createQuery("FROM Reimbursement")
                .getResultList();
        System.out.println(reimbursements);

//        tx.commit();
//
//        session.close();
    }
}
