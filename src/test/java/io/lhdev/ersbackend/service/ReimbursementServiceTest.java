package io.lhdev.ersbackend.service;
import io.lhdev.ersbackend.DAO.ReimbursementDAO;
import io.lhdev.ersbackend.exception.DatabaseException;
import io.lhdev.ersbackend.model.Reimbursement;
import io.lhdev.ersbackend.util.SessionUtility;

import org.hibernate.SessionFactory;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockedStatic;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ReimbursementServiceTest {

    // Fake repository dependency(mocked with Mockito)
    private static ReimbursementDAO mockReimbursementDAO;
    private static SessionFactory mockSessionFactory;

    // The system under test, our reimbursementService instance;
    private ReimbursementService reimbursementService;

    @BeforeClass
    public static void setUp() throws DatabaseException, SQLException {
        mockReimbursementDAO = mock(ReimbursementDAO.class);
        mockSessionFactory = mock(SessionFactory.class);

        List<Reimbursement> reimList = new ArrayList<>();

        Reimbursement testReim1 = new Reimbursement(300, new Date(100), "food", 0, 1);
        Reimbursement testReim2 = new Reimbursement(100, new Date(500), "lodging", 300, 2);
        reimList.add(testReim1);
        reimList.add(testReim2);

        List<Reimbursement> testList = new ArrayList<>();
        Reimbursement testReim4 = new Reimbursement(100, 300, "food", 300, 1, 2);
        testList.add(testReim4);

        when(mockReimbursementDAO.getAllReimbursements()).thenReturn(reimList);

        Reimbursement testReim3 = new Reimbursement(100, new Date(100), "food", 0, 1);
        when(mockReimbursementDAO.addReimbursement(testReim3)).thenReturn(testReim3);

       when(mockReimbursementDAO.getReimbursementsByUserId(300)).thenReturn(Collections.singletonList((testReim2)));

       when(mockReimbursementDAO.filterReimbursementsByStatusId(1)).thenReturn(testList);
       when(mockReimbursementDAO.approveReimbursementById(100, testReim4)).thenReturn((testReim4));

    }

    @Before
    public void beforeTest() {
        reimbursementService = new ReimbursementService((mockReimbursementDAO));
    }


    @Test
    public void test_getAllReimbursements_happy() throws SQLException, DatabaseException {

        try (MockedStatic<SessionUtility> mockedSessionUtil = mockStatic(SessionUtility.class)) {
            mockedSessionUtil.when(SessionUtility::getSessionFactory).thenReturn(mockSessionFactory);

            List<Reimbursement> expected = new ArrayList<>();

            Reimbursement testReim1 = new Reimbursement(300, new Date(100), "food", 0, 1);
            Reimbursement testReim2 = new Reimbursement(100, new Date(500), "lodging", 300, 2);

            expected.add(testReim1);
            expected.add(testReim2);

            List<Reimbursement> actual = reimbursementService.getAllReimbursements();

            assertEquals(expected, actual);
        }
    }

    @Test
    public void getReimbursementByUserId() {
        try (MockedStatic<SessionUtility> mockedSessionUtil = mockStatic(SessionUtility.class)) {
            mockedSessionUtil.when(SessionUtility::getSessionFactory).thenReturn(mockSessionFactory);

            List<Reimbursement> expectedList = new ArrayList<>();
            Reimbursement testReim = new Reimbursement(100, new Date(500), "lodging", 300, 2);
            expectedList.add(testReim);

            List<Reimbursement> actual = reimbursementService.getReimbursementByUserId(300);

            assertEquals(expectedList, actual);

        } catch (SQLException | DatabaseException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test_addReimbursement_happy() throws SQLException, DatabaseException{

        try (MockedStatic<SessionUtility> mockedSessionUtil = mockStatic(SessionUtility.class)) {
            mockedSessionUtil.when(SessionUtility::getSessionFactory).thenReturn(mockSessionFactory);

            reimbursementService.addReimbursement(new Reimbursement(100, new Date(100), "food", 0, 1));
        }
    }


    @Test
    public void itShouldFilterReimbursementsByStatusId() throws SQLException, DatabaseException {

        try (MockedStatic<SessionUtility> mockedSessionUtil = mockStatic(SessionUtility.class)) {
            mockedSessionUtil.when(SessionUtility::getSessionFactory).thenReturn(mockSessionFactory);

            List<Reimbursement> expected = new ArrayList<>();
            Reimbursement testReim4 = new Reimbursement(100, 300, "food", 300, 1, 1);
            expected.add(testReim4);

            List<Reimbursement> actual = reimbursementService.filterReimbursementsByStatusId(1);

            assertEquals(expected, actual);

        }
    }


    @Test
    public void itShouldApproveReimbursementById() {
            try (MockedStatic<SessionUtility> mockedSessionUtil = mockStatic(SessionUtility.class)) {
                mockedSessionUtil.when(SessionUtility::getSessionFactory).thenReturn(mockSessionFactory);

                List<Reimbursement> expected = new ArrayList<>();
                Reimbursement reim = new Reimbursement(100, 300.00, "food", 300,  1, 2);
                expected.add(reim);

                Reimbursement newReim = new Reimbursement(100, 300.00, "food", 300,  1, 2);

                Reimbursement actual = reimbursementService.approveReimbursementById(100, newReim);

                assertEquals(expected, actual);

        } catch (SQLException | DatabaseException e) {
                e.printStackTrace();
            }
    }

}