package io.lhdev.ersbackend.service;

import io.lhdev.ersbackend.DAO.UserDAO;
import io.lhdev.ersbackend.DTO.LoginDTO;
import io.lhdev.ersbackend.exception.BadParameterException;
import io.lhdev.ersbackend.exception.DatabaseException;
import io.lhdev.ersbackend.exception.LoginException;
import io.lhdev.ersbackend.model.User;

import java.sql.SQLException;


public class LoginService {

    private UserDAO userDAO;

    public LoginService() {
        this.userDAO = new UserDAO();
    }

    // This one is for Mockito
    public LoginService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User login(LoginDTO loginDTO) throws BadParameterException, LoginException, SQLException, DatabaseException {
        // check for blank name, password
        if(loginDTO.getUsername().trim().equals("") || loginDTO.getPassword().trim().equals("")) {
            throw new BadParameterException("Cannot have a blank name and/or password");
        }

        User user = userDAO.getUserByUsernameAndPassword(loginDTO);

        if (user == null) {
            throw new LoginException("User unable to login with the username and password entered.");
        }

        return user;
    }

}
