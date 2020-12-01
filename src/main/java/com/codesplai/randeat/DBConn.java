package com.codesplai.randeat;

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
    private static Optional<Connection> connection = Optional.empty();

    private DBConn() {

    }

    private static Connection getConn() throws SQLException {
        if (connection.isEmpty()) {
            // DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            DBConn.connection = Optional.of(DriverManager.getConnection(
                DBConnSettings.URL,
                DBConnSettings.USERNAME,
                DBConnSettings.PASSWORD
            ));
        }

        return DBConn.connection.get();
    }

    /**
     * Ejecuta una query SQL sin parámetros.
     * @param sql La query SQL sin parámetros
     * @return
     * @throws SQLException
     */
    private static ResultSet executeQuery(String sql) throws SQLException {
        Statement statement = DBConn.getConn().createStatement();
        return statement.executeQuery(sql);
    }

    private static void setPreparedStatementParams(
        PreparedStatement ps,
        Object[][] params
    ) throws SQLException {
        for (Object[] posParam : params) {
            ps.setObject((int)posParam[0], posParam[1]);
        }
    }

    /**
     * Ejecuta una query SQL con parámetros.
     * @param sql La query SQL con parámetros ("?")
     * @param params Array de arrays formados por la posición y el argumento que va en ésta
     * @return
     * @throws SQLException
     */
    private static ResultSet executeQueryWithParams(
        String sql,
        Object[][] params
    ) throws SQLException {
        PreparedStatement ps = DBConn.getConn().prepareStatement(sql);
        DBConn.setPreparedStatementParams(ps, params);

        // System.out.println("executing query with params: " + ps.toString());

        return ps.executeQuery();
    }

    public static String joinIntsInClause(List<Integer> ints) {
        if (ints.isEmpty()) {
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

    public static String joinStringsInClause(List<String> strings) {
        if (strings.isEmpty()) {
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

    /**
     * Ejecuta una query con parámetros para listar los IDs de los resultados, que estarán en la columna especificada.
     * @param sql
     * @param params
     * @param intCol la columna que contiene los ints a listar
     * @return
     */
    public static List<Integer> executeQueryGetIntIds(
        String sql,
        Object[][] params,
        String intCol
    ) {
        List<Integer> ids = new ArrayList<>();

        try {
            ResultSet resultSet = DBConn.executeQueryWithParams(sql, params);

            while (resultSet.next()) {
                ids.add(resultSet.getInt(intCol));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ids;
    }

    /**
     * 
     * @param sql
     * @param params
     * @return el ID autogenerado si lo hay y si todo ha ido bien
     */
    public static Optional<Integer> executeInsert(
        String sql,
        Object[][] params
    ) {
        Optional<Integer> autoIncId = Optional.empty();

        try {
            PreparedStatement ps = DBConn.getConn().prepareStatement(sql);

            DBConn.setPreparedStatementParams(ps, params);

            ps.executeUpdate();

            ResultSet resultSet = ps.executeQuery("SELECT LAST_INSERT_ID()");
            if (resultSet.next()) {
                autoIncId = Optional.of(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return autoIncId;
    }

    public static boolean executeUpdateOrDelete(
        String sql,
        Object[][] params
    ) {
        try {
            PreparedStatement ps = DBConn.getConn().prepareStatement(sql);
            DBConn.setPreparedStatementParams(ps, params);

            ps.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}