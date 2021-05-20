package io.lhdev.ersbackend.service;

import io.lhdev.ersbackend.DAO.ReimbursementDAO;
import io.lhdev.ersbackend.exception.BadParameterException;
import io.lhdev.ersbackend.exception.DatabaseException;
import io.lhdev.ersbackend.model.Reimbursement;
import io.lhdev.ersbackend.model.User;
import io.lhdev.ersbackend.util.ConnectionUtil;

import io.lhdev.ersbackend.util.SessionUtility;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import org.mockito.MockedStatic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.*;

class ReimbursementServiceTest {


    // Fake repository dependency(mocked with Mockito)
    private static ReimbursementDAO mockReimbursementDAO;
    private static SessionFactory mockSessionFactory;

    // The system under test, our reimbursementService instance;
    private ReimbursementService reimbursementService;

    @BeforeAll
    public static void setupAll() throws DatabaseException {
        mockReimbursementDAO = mock(ReimbursementDAO.class);
        mockSessionFactory = mock(SessionFactory.class);
//
//        User userTest = new User(1111, "test@lhdev.io", "password", 2);
//        List<Reimbursement> reimList = new ArrayList<>();
//
//        Reimbursement testReim1 = new Reimbursement(8000, 569.00, "room fees",
//                1002, 2);
//        Reimbursement testReim2 = new Reimbursement(8001, 350.00, "misc.",
//                1002, 4);
//
//        reimList.add(testReim1);
//        reimList.add(testReim2);
//
//        when(mockReimbursementDAO.getReimbursementsByUserId()
//                .thenReturn(reimList));
//
//
//        System.out.println("Should Print Before All Tests");
    }

    @BeforeEach
    public void setUp() throws DatabaseException {
        mockReimbursementDAO = mock(ReimbursementDAO.class);
//        mockConnection = mock(Connection.class);

        reimbursementService = new ReimbursementService(mockReimbursementDAO);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void getAllReimbursements() throws SQLException, DatabaseException {

//        try (MockedStatic<SessionUtility> mockedSessionUtil = mockStatic(SessionUtility.class) {
//            mockedSessionUtil.when(SessionUtility::getSessionFactory).thenReturn(mockSessionFactory);
//
//            Assertions.assertFalse(reimbursementService.getAllReimbursements().isEmpty());
//            Assertions.assertEquals(1, reimbursementService.getAllReimbursements().size());
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//

    }

    @Test
    void getReimbursementByUserId() {
    }

    @Test
    public void itShouldAddReimbursement() throws SQLException, DatabaseException {

        try {

            Reimbursement reimbursement = new Reimbursement();
            reimbursement = new Reimbursement(6269, 569.00, "room fees", 1002, 2);

            reimbursementService.addReimbursement(reimbursement);
            Assertions.assertFalse(mockReimbursementDAO.getAllReimbursements().isEmpty());
            Assertions.assertEquals(1, mockReimbursementDAO.getAllReimbursements().size());
            Assertions.assertTrue(reimbursementService.getAllReimbursements().stream()
                    .anyMatch(reim -> Objects.equals(reim.getId(), 6269) &&
                            Objects.equals(reim.getAmount(), 569.00) &&
                            Objects.equals(reim.getDescription(), ("room fees")) &&
                            Objects.equals(reim.getAuthor(), 1002) &&
                            Objects.equals(reim.getTypeId(), 2)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//
//    @Test
//    @DisplayName("It Should Not Create Reimbursement When Amount is Blank")
//    public void itShouldThrowRuntimeExceptionWhenAmountIsNull() {
//
//          Assertions.assertThrows(RuntimeException.class, () -> {
//              reimbursementService.addReimbursement(6269, null, "room fees", 1002, 2);
//          });
//    }
//
//    @Test
//    @DisplayName("It Should Not Create Reimbursement When Id is nonInteger")
//    public void itShouldThrowRuntimeExceptionWhenIdIsNonInteger() {
//
//        Assertions.assertThrows(RuntimeException.class, () -> {
//            reimbursementService.addReimbursement("abc", 300, "room fees", 1002, 2);
//        });
//    }

    @Test
    public void itShouldFilterReimbursementsByStatusId() {

    }

    @Test
    void approveReimbursementById() {
    }
}