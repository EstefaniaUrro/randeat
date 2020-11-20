package backend.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import backend.DBConn;
import backend.modelo.RestaurantePaquete;


public class RestaurantePaqueteController {
    private static final String TABLE = "restaurante_paquete";
    private static final String RESTAURANTE_ID_RESTAURANTE = "restaurante_id_restaurante";
    private static final String PAQUETE_ID_PAQUETE = "paquete_id_paquete";
    private static final String COSTE = "coste";

    private static final String SELECT_ALL = String.format(
        "SELECT * FROM %s", TABLE
    );

    public static List<RestaurantePaquete> getAll() {
        List<RestaurantePaquete> listaRestaurantePaquete = new ArrayList<>();

        try (
            Connection conn = DBConn.getConn();
            Statement statement = conn.createStatement();
        ) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);

            while (resultSet.next()) {
                listaRestaurantePaquete.add(new RestaurantePaquete(
                    resultSet.getInt(RESTAURANTE_ID_RESTAURANTE),
                    resultSet.getInt(PAQUETE_ID_PAQUETE),
                    resultSet.getDouble(COSTE)
                ));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return listaRestaurantePaquete;
    }
}