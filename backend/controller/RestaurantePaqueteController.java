package backend.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import backend.DBConn;
import backend.FromResultSet;
import backend.modelo.RestaurantePaquete;


public class RestaurantePaqueteController implements FromResultSet<RestaurantePaquete> {
    private static final String TABLE = "restaurante_paquete";
    private static final String ID_RESTAURANTE = "restaurante_id_restaurante";
    private static final String ID_PAQUETE = "paquete_id_paquete";
    private static final String COSTE = "coste";

    private static final String SELECT_BY_ID_RESTAURANTE = String.format(
        "SELECT rp.*, p.%s FROM %s r"
        + " INNER JOIN %s rp ON rp.%s = r.%s"
        + " INNER JOIN %s p ON p.%s = rp.%s"
        + " WHERE r.%s = ?",
        PaqueteController.NOMBRE,
        RestauranteController.TABLE,

        TABLE,
        ID_RESTAURANTE,
        RestauranteController.ID_RESTAURANTE,

        PaqueteController.TABLE,
        PaqueteController.ID_PAQUETE,
        ID_PAQUETE,

        RestauranteController.ID_RESTAURANTE
    );

    public static List<RestaurantePaquete> getByIdRestaurante(
        int idRestaurante
    ) {
        return DBConn.executeQueryWithParamsIntoList(
            SELECT_BY_ID_RESTAURANTE,
            new Object[][] {
                {1, idRestaurante}
            },
            new RestaurantePaqueteController()
        );
    }

    @Override
    public RestaurantePaquete fromResultSet(ResultSet resultSet) throws SQLException {
        return new RestaurantePaquete(
            resultSet.getInt(ID_RESTAURANTE),
            resultSet.getInt(ID_PAQUETE),
            resultSet.getDouble(COSTE),
            resultSet.getString(PaqueteController.NOMBRE)
        );
    }
}