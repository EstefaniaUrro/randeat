package backend.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import backend.DBConn;

public class RestauranteTipoEntregaController {
    private static final String TABLE = "restaurante_tipo_entrega";
    private static final String ID_RESTAURANTE = "restaurante_id_restaurante";
    private static final String ID_TIPO_ENTREGA =
        "tipo_entrega_id_tipo_entrega"
    ;

    private static final String SELECT_BY_ID_RESTAURANTE = String.format(
        "SELECT %s FROM %s WHERE %s = ?", ID_TIPO_ENTREGA, TABLE, ID_RESTAURANTE
    );

    private static final String SELECT_BY_IDS_TIPO_ENTREGA = String.format(
        "SELECT %s FROM %s WHERE %s IN (?)",
        ID_RESTAURANTE,
        TABLE,
        ID_TIPO_ENTREGA
    );

    public static List<Integer> getIdsTipoEntregaByIdRestaurante(
        int idRestaurante
    ) {
        List<Integer> idsTipoEntrega = new ArrayList<>();

        try {
            ResultSet resultSet = DBConn.executeQueryWithParams(
                SELECT_BY_ID_RESTAURANTE,
                new Object[][] {
                    {1, idRestaurante}
                }
            );

            while (resultSet.next()) {
                idsTipoEntrega.add(resultSet.getInt(ID_TIPO_ENTREGA));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idsTipoEntrega;
    }

    public static List<Integer> getIdsRestauranteByIdTipoEntrega(
        int idTipoEntrega
    ) {
        List<Integer> idsRestaurante = new ArrayList<>();

        try {
            ResultSet resultSet = DBConn.executeQueryWithParams(
                SELECT_BY_IDS_TIPO_ENTREGA,
                new Object[][] {
                    {1, idTipoEntrega}
                }
            );

            while (resultSet.next()) {
                idsRestaurante.add(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idsRestaurante;
    }
}
