package backend.controller;

import java.util.List;
import java.util.Optional;

import backend.DBConn;

public class RestauranteTipoEntregaController {
    static final String TABLE = "restaurante_tipo_entrega";
    static final String ID_RESTAURANTE = "restaurante_id_restaurante";
    static final String ID_TIPO_ENTREGA = "tipo_entrega_id_tipo_entrega";

    private static final String SELECT_BY_ID_RESTAURANTE = String.format(
        "SELECT %s FROM %s WHERE %s = ?", ID_TIPO_ENTREGA, TABLE, ID_RESTAURANTE
    );

    private static final String SELECT_BY_IDS_TIPO_ENTREGA = String.format(
        "SELECT %s FROM %s WHERE %s IN (?)",
        ID_RESTAURANTE,
        TABLE,
        ID_TIPO_ENTREGA
    );

    private static final String INSERT_RESTAURANTE_TIPO_COCINA = String.format(
        "INSERT INTO %s (%s, %s) VALUES (?, ?)",
        TABLE, ID_RESTAURANTE, ID_TIPO_ENTREGA
    );

    private static final String DELETE_RESTAURANTE_TIPO_ENTREGA = String.format(
        "DELETE FROM %s WHERE %s = ? AND %s = ?",
        TABLE, ID_RESTAURANTE, ID_TIPO_ENTREGA
    );

    public static List<Integer> getIdsTipoEntregaByIdRestaurante(
        int idRestaurante
    ) {
        return DBConn.executeQueryGetIntIds(
            SELECT_BY_ID_RESTAURANTE,
            new Object[][] {
                {1, idRestaurante}
            },
            ID_TIPO_ENTREGA
        );
    }

    public static List<Integer> getIdsRestauranteByIdTipoEntrega(
        int idTipoEntrega
    ) {
        return DBConn.executeQueryGetIntIds(
            SELECT_BY_IDS_TIPO_ENTREGA,
            new Object[][] {
                {1, idTipoEntrega}
            },
            ID_RESTAURANTE
        );
    }

    public static Optional<Integer> addRestauranteTipoEntrega(int idRestaurante, int idTipoEntrega) {
        return DBConn.executeInsert(
            INSERT_RESTAURANTE_TIPO_COCINA,
            new Object[][] {
                {1, idRestaurante},
                {2, idTipoEntrega}
            }
        );
    }

    public static boolean removeRestauranteTipoEntrega(int idRestaurante, int idTipoEntrega) {
        return DBConn.executeUpdateOrDelete(
            DELETE_RESTAURANTE_TIPO_ENTREGA,
            new Object[][] {
                {1, idRestaurante},
                {2, idTipoEntrega}
            }
        );
    }
}
