package backend.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import backend.DBConn;

public class RestauranteTipoCocinaController {
    private static final String TABLE = "restaurante_tipo_cocina";
    private static final String ID_RESTAURANTE = "restaurante_id_restaurante";
    private static final String ID_TIPO_COCINA = "tipo_cocina_id_tipo_cocina";

    private static final String SELECT_BY_TIPO_COCINA = String.format(
        "SELECT * FROM %s WHERE %s = ?",
        TABLE,
        ID_TIPO_COCINA
    );

    private static final String SELECT_BY_ID_RESTAURANTE = String.format(
        "SELECT * FROM %s WHERE %s = ?",
        TABLE,
        ID_RESTAURANTE
    );

    public static List<Integer> getIdsRestauranteConTipoCocina(
        int idTipoCocina
    ) {
        List<Integer> idsRestaurante = new ArrayList<>();

        try {
            ResultSet resultSet = DBConn.executeQueryWithParams(
                SELECT_BY_TIPO_COCINA,
                new Object[][] {
                    {1, idTipoCocina}
                }
            );

            while (resultSet.next()) {
                idsRestaurante.add(resultSet.getInt(ID_RESTAURANTE));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idsRestaurante;
    }

    public static List<Integer> getIdsTipoCocinaDeRestaurante(int idRestaurante) {
        List<Integer> idsTipoCocina = new ArrayList<>();

        try {
            ResultSet resultSet = DBConn.executeQueryWithParams(
                SELECT_BY_ID_RESTAURANTE,
                new Object[][] {
                    {1, idRestaurante}
                }
            );

            while (resultSet.next()) {
                idsTipoCocina.add(resultSet.getInt(ID_TIPO_COCINA));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idsTipoCocina;
    }
}