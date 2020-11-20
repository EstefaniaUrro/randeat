package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
     * Ejecuta una query SQL sin parámetros.
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
     * Ejecuta una query SQL con parámetros.
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

    public static String joinIntsInClause(int[] ints) {
        if (ints.length == 0) {
            return "";
        }

        StringBuilder content = new StringBuilder("");
        for (int i : ints) {
            content.append(i).append(", ");
        }
        return content.substring(
            0,
            content.length()-2
        );
    }

    public static String joinStringsInClause(String[] strings) {
        if (strings.length == 0) {
            return "";
        }
        
        StringBuilder content = new StringBuilder("");
        for (String s : strings) {
            content.append(s).append(", ");
        }
        return content.substring(
            0,
            content.length()-2
        );
    }

    public static <T, E extends FromResultSet<T>> List<T> executeQueryIntoList(
        String sql,
        E e
    ) {
        List<T> list = new ArrayList<>();

        try {
            ResultSet resultSet = DBConn.executeQuery(sql);
            
            while (resultSet.next()) {
                list.add(e.fromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return list;
    }

    public static <T, E extends FromResultSet<T>> List<T> executeQueryWithParamsIntoList(
        String sql,
        Object[][] params,
        E e
    ) {
        List<T> list = new ArrayList<>();

        try {
            ResultSet resultSet = DBConn.executeQueryWithParams(sql, params);
            
            while (resultSet.next()) {
                list.add(e.fromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return list;
    }

    public static <T, E extends FromResultSet<T>> Optional<T> executeQueryWithParamsSingleValue(
        String sql,
        Object[][] params,
        E e
    ) {
        Optional<T> value = Optional.empty();

        try {
            ResultSet resultSet = DBConn.executeQueryWithParams(sql, params);
            
            if (resultSet.next()) {
                value = Optional.of(e.fromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return value;
    }
}