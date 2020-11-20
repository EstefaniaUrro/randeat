package backend.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import backend.DBConn;
import backend.modelo.TipoEntrega;

public class TipoEntregaController {
    private static final String TABLE = "tipo_entrega";
    private static final String KEY = "id_tipo_entrega";
    private static final String NOMBRE = "nombre";

    private static final String SELECT_ALL = String.format(
        "SELECT * FROM %s", TABLE
    );

    private static final String SELECT_BY_ID = String.format(
        "SELECT * FROM %s WHERE %s = ?", TABLE, KEY
    );

    private static TipoEntrega fromResultSet(
        ResultSet resultSet
    ) throws SQLException {
        return new TipoEntrega(
            resultSet.getInt(KEY),
            resultSet.getString(NOMBRE)
        );
    }

    public static List<TipoEntrega> getAll() {
        List<TipoEntrega> listaTipoEntrega = new ArrayList<>();

        try (
            Connection conn = DBConn.getConn();
            Statement statement = conn.createStatement();
        ) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);

            while (resultSet.next()) {
                listaTipoEntrega.add(
                    TipoEntregaController.fromResultSet(resultSet)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaTipoEntrega;
    }

    public static Optional<TipoEntrega> getById(int idTipoEntrega) {
        Optional<TipoEntrega> tipoEntrega = Optional.empty();

        try {
            ResultSet resultSet = DBConn.executeQueryWithParams(
                SELECT_BY_ID,
                new Object[][] {
                    {1, idTipoEntrega}
                }
            );

            if (resultSet.next()) {
                tipoEntrega = Optional.of(
                    TipoEntregaController.fromResultSet(resultSet)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tipoEntrega;
    } 
}