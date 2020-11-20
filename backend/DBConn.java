package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    /**
     * Ejecutar una query SQL sin parámetros
     * @param sql La query SQL sin parámetros
     * @return
     * @throws SQLException
     */
    public static ResultSet executeQuery(String sql) throws SQLException {
        Connection conn = DBConn.getConn();

        Statement statement = conn.createStatement();
        return statement.executeQuery(sql);
    }

    /**
     * Ejecutar una query SQL con parámetros TODO
     * @param sql La query SQL con parámetros ("?")
     * @param params Array de arrays formados por la posición y el argumento que va en ésta
     * @return
     * @throws SQLException
     */
    public static ResultSet executeQueryWithParams(String sql, Object[][] params) throws SQLException {
        Connection conn = DBConn.getConn();

        PreparedStatement ps = conn.prepareStatement(sql);
        for (Object[] posParam : params) {
            ps.setObject((int)posParam[0], posParam[1]);
        }

        return ps.executeQuery();
    }
}
