package backend.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import backend.DBConn;
import backend.modelo.CodigoPostal;

public class CodigoPostalController {
    private static final String TABLE = "codigo_postal";
    private static final String KEY = "id_codigo_postal";
    private static final String NUMERO = "numero";

    private static final String SELECT_ALL = String.format(
        "SELECT * FROM %s", TABLE
    );

    private static final String SELECT_NUMERO_BY_KEY = String.format(
        "SELECT %s FROM %s WHERE id_codigo_postal = ?", NUMERO, TABLE
    );

    public static List<CodigoPostal> getAll() {
        List<CodigoPostal> listCodigoPostal = new ArrayList<>();

        try (
            Connection conn = DBConn.getConn();
            PreparedStatement ps = conn.prepareStatement(
                SELECT_ALL
            );
        ) {

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listCodigoPostal;
    }

    public static String getNumeroById(int idCodigoPostal) {
        try (
            Connection conn = DBConn.getConn();
            PreparedStatement ps = conn.prepareStatement(
                SELECT_NUMERO_BY_KEY
            );
        ) {
            ps.setInt(1, idCodigoPostal);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(NUMERO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // TODO Nunca tendría que pasar, porque los campos codigo_postal_id_codigo_postal tienen que ser FOREIGN KEYs válidas.
        return "UNDEFINED";
    }
}
