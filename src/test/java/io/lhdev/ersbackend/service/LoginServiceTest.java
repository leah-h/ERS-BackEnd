package io.lhdev.ersbackend.service;

import io.lhdev.ersbackend.DAO.UserDAO;
import io.lhdev.ersbackend.DTO.LoginDTO;
import io.lhdev.ersbackend.exception.BadParameterException;
import io.lhdev.ersbackend.exception.DatabaseException;
import io.lhdev.ersbackend.exception.LoginException;
import io.lhdev.ersbackend.model.User;
import io.lhdev.ersbackend.util.SessionUtility;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockedStatic;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class LoginServiceTest  {

    private static UserDAO mockUserDAO;
    private static SessionFactory mockSessionFactory;

    private LoginService loginService;

    @BeforeClass
    public static void setUp() throws SQLException, DatabaseException {
        mockUserDAO = mock(UserDAO.class);
        mockSessionFactory = mock(SessionFactory.class);

        User returnedUser = new User("username","password", "firstName", "lastName", "test@gmail.com", 1);

        when(mockUserDAO.getUserByUsernameAndPassword(eq(new LoginDTO("username", "password"))))
                .thenReturn(returnedUser);
    }

    @Before
    public void beforeTest() {
        loginService = new LoginService(mockUserDAO);
    }

    @Test
    public void test_login_happy() throws BadParameterException, LoginException, SQLException, DatabaseException {
        try (MockedStatic<SessionUtility> mockedSessionUtil = mockStatic(SessionUtility.class)) {

            mockedSessionUtil.when(SessionUtility::getSessionFactory)
                    .thenReturn(mockSessionFactory);

            User actual = loginService.login(new LoginDTO("username", "password"));
            User expected = new User(0,"username", "password", "firstName", "lastName", "test@gmail.com", 1);

            assertEquals(expected, actual);

        }
    }

    @Test
    public void test_login_blankUsername_blankPassword() {
        try (MockedStatic<SessionUtility> mockedSessionUtil = mockStatic(SessionUtility.class)) {
            mockedSessionUtil.when(SessionUtility::getSessionFactory)
                    .thenReturn(mockSessionFactory);

            try {
                loginService.login(new LoginDTO("", ""));
                fail("BadParameterException was not thrown");
            } catch (BadParameterException | LoginException | SQLException | DatabaseException e) {
                assertEquals(e.getMessage(), "Cannot have a blank name and password");
            }
        }
    }

}