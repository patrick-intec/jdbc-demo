package be.infernalwhale;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("", "", "");
    }
}
