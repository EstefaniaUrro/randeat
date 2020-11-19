package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
    private static final String URL = "jdbc:mysql://localhost/pistaccio";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "BernaT-457";

    public static Connection getConn() throws SQLException {
        // DriverManager.registerDriver(new com.mysql.jdbc.Driver());

        return DriverManager.getConnection(
            DBConn.URL,
            DBConn.USERNAME,
            DBConn.PASSWORD
        );
    }
}
