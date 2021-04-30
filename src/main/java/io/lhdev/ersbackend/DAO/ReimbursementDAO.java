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
//
//    public Reimbursement addReimbursementByUser(LoginDTO loginDTO) throws DatabaseException, SQLException {


//        Session session = SessionUtility.getSessionFactory().openSession();
//
//            Transaction tx = session.beginTransaction();
//
//            Reimbursement reimbursement = new Reimbursement(100, "Food allowance, field visit",
//                    2, 1);
//
//            session.save(reimbursement);
//
//            tx.commit();
//
//            return reimbursement;

//    }

    public List<Reimbursement> getAllReimbursements() throws DatabaseException, SQLException {

        Session session = SessionUtility.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            List<Reimbursement> reimbursements = session.createQuery("FROM Reimbursement r")
                    .getResultList();

            tx.commit();
            return reimbursements;

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }finally {
            if(session != null) {session.close();}
        }

        return null;
        }
    }

//        List<Reimbursement> listOfReimbursements = new ArrayList<>();
//
//        try (Connection connection = ConnectionUtil.getConnection()) {
//            String sql = "SELECT * FROM reimbursements";
//
//            Statement stmt = connection.createStatement();
//
//            ResultSet rs = stmt.executeQuery(sql);
//
//            while (rs.next()) {
//                int reimId = rs.getInt("reimId");
//                int amount = rs.getInt("amount");
//                String description = rs.getString("description");
//                int author = rs.getInt("author");
//                int typeId = rs.getInt("typeId");
//
//                Reimbursement reimbursement = new Reimbursement(reimId, amount, description, author, typeId);
//                listOfReimbursements.add(reimbursement);
//            }
//        } catch (SQLException e) {
//            throw new DatabaseException("Unable to connect: " + e.getMessage());
//        }
//
//        return listOfReimbursements;
//    }


