package io.lhdev.ersbackend.DAO;

import io.lhdev.ersbackend.DTO.LoginDTO;
import io.lhdev.ersbackend.exception.DatabaseException;
import io.lhdev.ersbackend.model.User;
import io.lhdev.ersbackend.util.ConnectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDAO {

    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

    private Connection connection;

    public UserDAO() {
        super();
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }


    public User getUserByUsernameAndPassword (LoginDTO loginDTO) throws DatabaseException, SQLException {

        User user = new User();

        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM users WHERE username=? AND password=?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, loginDTO.getUsername());
            pstmt.setString(2, loginDTO.getPassword());


            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                int roleId = rs.getInt("roleId");

                user = new User(id, username, password, firstName, lastName, email, roleId);

                if(loginDTO.getUsername().equals(user.getUsername()) && loginDTO.getPassword().equals(user.getPassword())){
                    return user;
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error occurred with the database: " + e.getMessage());
        }

        return null;
    }


}
