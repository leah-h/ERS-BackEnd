package io.lhdev.ersbackend.DAO;

import io.lhdev.ersbackend.DTO.LoginDTO;
import io.lhdev.ersbackend.exception.DatabaseException;
import io.lhdev.ersbackend.model.Reimbursement;
import io.lhdev.ersbackend.util.ConnectionUtil;
import io.lhdev.ersbackend.util.SessionUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDAO {

    private static final Logger logger = LoggerFactory.getLogger(ReimbursementDAO.class);

    private Connection connection;

    public ReimbursementDAO() {
        super();
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public List<Reimbursement> getAllReimbursements() {

        Session session = SessionUtility.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            String hql = "FROM Reimbursement";
            Query query = session.createQuery(hql);
            List results = query.list();

            tx.commit();
            return results;

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }finally {
           session.close();
        }

        return getAllReimbursements();
        }

    public List<Reimbursement> getReimbursementsByUserId(int userId) throws DatabaseException, SQLException {
        Session session = SessionUtility.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            logger.info("UserId value is: " + userId);

            String hql = "FROM Reimbursement WHERE author= ?";
            Query query = session.createQuery(hql);
            List results = query.list();

            tx.commit();
            return results;

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }

        return getReimbursementsByUserId(userId);
    }


    public Reimbursement addReimbursement(Reimbursement reimbursement) throws DatabaseException,
            SQLException {

        Session session = SessionUtility.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {

            Reimbursement newReim  = new Reimbursement();
            newReim = reimbursement;

            logger.info("new reimbursement value is: " + reimbursement);

            session.save(newReim);

            tx.commit();
            return newReim;

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }

        return addReimbursement(reimbursement);
    }




}







