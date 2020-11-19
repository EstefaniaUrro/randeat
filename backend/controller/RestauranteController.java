package backend.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import backend.DBConn;
import backend.modelo.Restaurante;

public class RestauranteController {
    private static final String TABLE = "restaurante";
    private static final String KEY = "id_restaurante";
    private static final String IBAN = "iban";
    private static final String USUARIO_ID_USUARIO = "usuario_id_usuario";

    private static final String SELECT_ALL = String.format(
        "SELECT * FROM %s", TABLE
    );

    public static List<Restaurante> getAll() {
        List<Restaurante> listaRestaurante = new ArrayList<>();

        try (
            Connection conn = DBConn.getConn();
            Statement statement = conn.createStatement();
        ) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);

            while (resultSet.next()) {
                listaRestaurante.add(new Restaurante(
                    resultSet.getInt(KEY),
                    resultSet.getString(IBAN),
                    resultSet.getInt(USUARIO_ID_USUARIO)
                ));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return listaRestaurante;
    }

    // TODO
    // public static List<RestaurantePaquete> getPaquetes(int idRestaurante) {

    // }
}