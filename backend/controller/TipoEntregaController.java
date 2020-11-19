package backend.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import backend.DBConn;
import backend.modelo.TipoEntrega;

public class TipoEntregaController {
    private static final String TABLE = "tipo_entrega";
    private static final String KEY = "id_tipo_entrega";
    private static final String NOMBRE = "nombre";

    private static final String SELECT_ALL = String.format(
        "SELECT * FROM %s", TABLE
    );

    public static List<TipoEntrega> getAll() {
        List<TipoEntrega> listaTipoEntrega = new ArrayList<>();

        try (
            Connection conn = DBConn.getConn();
            Statement statement = conn.createStatement();
        ) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);

            while (resultSet.next()) {
                listaTipoEntrega.add(new TipoEntrega(
                    resultSet.getInt(KEY),
                    resultSet.getString(NOMBRE)
                ));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return listaTipoEntrega;
    }
}
