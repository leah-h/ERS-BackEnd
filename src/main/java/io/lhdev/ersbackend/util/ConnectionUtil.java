package io.lhdev.ersbackend.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.mariadb.jdbc.Driver;

public class ConnectionUtil {

    private ConnectionUtil(){}

    public static Connection getConnection() throws SQLException {

        Driver mariaDBDriver = new Driver();
        DriverManager.registerDriver(mariaDBDriver);

            String username = "root";
            String password = "leahh";
            String connectionString = "jdbc:mariadb://localhost/ers_backend";

        Connection connection = DriverManager.getConnection(connectionString, username, password);

        return connection;
    }
}
