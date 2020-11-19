package backend.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import backend.DBConn;
import backend.modelo.Tarjeta;

public class TarjetaController {
    private static final String TABLE = "usuario";
    private static final String KEY = "id_tarjeta";
    private static final String NUMERO = "numero";

    private static final String SELECT_ALL = String.format(
        "SELECT * FROM %s", TABLE
    );

    public static List<Tarjeta> getAll() {
        List<Tarjeta> listaTarjeta = new ArrayList<>();

        try (
            Connection conn = DBConn.getConn();
            Statement statement = conn.createStatement();
        ) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);

            while (resultSet.next()) {
                listaTarjeta.add(new Tarjeta(
                    resultSet.getInt(KEY),
                    resultSet.getString(NUMERO)
                ));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return listaTarjeta;
    }
}
