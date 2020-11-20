package backend.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import backend.DBConn;
import backend.modelo.Paquete;


public class PaqueteController {
    private static final String TABLE = "usuario";
    private static final String KEY = "id_paquete";
    private static final String NOMBRE = "nombre";

    private static final String SELECT_ALL = String.format(
        "SELECT * FROM %s", TABLE
    );

    public static List<Paquete> getAll() {
        List<Paquete> listaPaquete = new ArrayList<>();

        try (
            Connection conn = DBConn.getConn();
            Statement statement = conn.createStatement();
        ) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);

            while (resultSet.next()) {
                listaPaquete.add(new Paquete(
                    resultSet.getInt(KEY),
                    resultSet.getString(NOMBRE)
                ));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return listaPaquete;
    }
}