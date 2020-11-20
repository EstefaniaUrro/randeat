package backend.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import backend.DBConn;

public class RestauranteBebidaController {
    private static final String TABLE = "restaurante_bebida";
    private static final String ID_RESTAURANTE = "restaurante_id_restaurante";
    private static final String ID_BEBIDA = "bebida_id_bebida";

    private static final String SELECT_BY_ID_RESTAURANTE = String.format(
        "SELECT %s FROM %s WHERE %s = ?", ID_BEBIDA, TABLE, ID_RESTAURANTE
    );

    public static List<Integer> getByIdRestaurante(int idRestaurante) {
        List<Integer> idsBebida = new ArrayList<>();

        try {
            ResultSet resultSet = DBConn.executeQueryWithParams(
                SELECT_BY_ID_RESTAURANTE,
                new Object[][] {
                    {1, idRestaurante}
                }
            );

            while (resultSet.next()) {
                idsBebida.add(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idsBebida;
    }
}