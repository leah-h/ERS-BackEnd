package io.lhdev.ersbackend.service;

import io.lhdev.ersbackend.DAO.ReimbursementDAO;
import io.lhdev.ersbackend.exception.DatabaseException;
import io.lhdev.ersbackend.model.Reimbursement;
import org.hibernate.dialect.Database;

import java.sql.SQLException;
import java.util.List;

public class ReimbursementService {

    private ReimbursementDAO reimbursementDAO;

    public ReimbursementService() {
        this.reimbursementDAO = new ReimbursementDAO();
    }

    // For passing in the mocked reimbursementDAO for unit testing
    public ReimbursementService(ReimbursementDAO reimbursementDAO) {
        this.reimbursementDAO = reimbursementDAO;
    }

    public List<Reimbursement> getAllReimbursements() throws DatabaseException, SQLException {
        return reimbursementDAO.getAllReimbursements();
    }
}
