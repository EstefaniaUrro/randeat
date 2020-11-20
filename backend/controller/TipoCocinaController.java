package backend.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import backend.DBConn;
import backend.modelo.TipoCocina;

public class TipoCocinaController {
    private static final String TABLE = "tipo_cocina";
    private static final String KEY = "id_tipo_cocina";
    private static final String NOMBRE = "nombre";

    private static final String SELECT_ALL = String.format(
        "SELECT * FROM %s", TABLE
    );

    private static final String SELECT_BY_KEY = String.format(
        "SELECT * FROM %s WHERE %s = ?", TABLE, KEY
    );

    private static TipoCocina fromResultSet(ResultSet resultSet) throws SQLException {
        return new TipoCocina(
            resultSet.getInt(KEY),
            resultSet.getString(NOMBRE)
        );
    }
    
    public static List<TipoCocina> getAll() {
        List<TipoCocina> listaTipoCocina = new ArrayList<>();

        try (
            Connection conn = DBConn.getConn();
            Statement statement = conn.createStatement();
        ) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);

            while (resultSet.next()) {
                listaTipoCocina.add(
                    TipoCocinaController.fromResultSet(resultSet)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaTipoCocina;
    }

    public static Optional<TipoCocina> getById(int idTipoCocina) {
        Optional<TipoCocina> tipoCocina = Optional.empty();

        try {
            ResultSet resultSet = DBConn.executeQueryWithParams(
                SELECT_BY_KEY, new Object[][] {
                    {1, idTipoCocina}
                }
            );

            if (resultSet.next()) {
                tipoCocina = Optional.of(
                    TipoCocinaController.fromResultSet(resultSet)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tipoCocina;
    }
}