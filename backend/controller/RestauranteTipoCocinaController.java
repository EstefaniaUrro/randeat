package backend.controller;

import java.util.List;
import java.util.Optional;

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

    private static final String INSERT_RESTAURANTE_TIPO_COCINA = String.format(
        "INSERT INTO %s (%s, %s) VALUES (?, ?)",
        TABLE, ID_RESTAURANTE, ID_TIPO_COCINA
    );

    private static final String DELETE_RESTAURANTE_TIPO_COCINA = String.format(
        "DELETE FROM %s WHERE %s = ? AND %s = ?",
        TABLE, ID_RESTAURANTE, ID_TIPO_COCINA
    );

    public static List<Integer> getIdsRestauranteByIdTipoCocina(
        int idTipoCocina
    ) {
        return DBConn.executeQueryGetIntIds(
            SELECT_BY_TIPO_COCINA,
            new Object[][] {
                {1, idTipoCocina}
            },
            ID_RESTAURANTE
        );
    }

    public static List<Integer> getIdsTipoCocinaByIdRestaurante(
        int idRestaurante
    ) {
        return DBConn.executeQueryGetIntIds(
            SELECT_BY_ID_RESTAURANTE,
            new Object[][] {
                {1, idRestaurante}
            },
            ID_TIPO_COCINA
        );
    }

    public static Optional<Integer> addRestauranteTipoCocina(int idRestaurante, int idTipoCocina) {
        return DBConn.executeInsert(
            INSERT_RESTAURANTE_TIPO_COCINA,
            new Object[][] {
                {1, idRestaurante},
                {2, idTipoCocina}
            }
        );
    }

    public static boolean removeRestauranteTipoCocina(int idRestaurante, int idTipoCocina) {
        return DBConn.executeUpdateOrDelete(
            DELETE_RESTAURANTE_TIPO_COCINA,
            new Object[][] {
                {1, idRestaurante},
                {2, idTipoCocina}
            }
        );
    }
}