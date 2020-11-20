package backend.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
    private static final String USUARIO_ID_USUARIO = "usuario_id_usuario";
    private static final String CIF = "cif";
    private static final String IBAN = "iban";
    private static final String NOMBRE_RESTAURANTE = "nombre_restaurante";
    private static final String NOMBRE_PROPIETARIO = "nombre_propietario";
    private static final String CODIGO_POSTAL_ID_CODIGO_POSTAL = "codigo_postal_id_codigo_postal";

    private static final String SELECT_ALL = String.format(
        "SELECT * FROM %s", TABLE
    );

    /**
     * Crea un Restaurante con los datos de un registro.
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private static Restaurante resultSetToRestaurante(ResultSet resultSet) throws SQLException {
        return new Restaurante(
            resultSet.getInt(KEY),
            resultSet.getInt(USUARIO_ID_USUARIO),
            resultSet.getString(CIF),
            resultSet.getString(IBAN),
            resultSet.getString(NOMBRE_RESTAURANTE),
            resultSet.getString(NOMBRE_PROPIETARIO),
            resultSet.getInt(CODIGO_POSTAL_ID_CODIGO_POSTAL)
        );
    }

    public static List<Restaurante> getAll() {
        List<Restaurante> listaRestaurante = new ArrayList<>();

        try (
            Connection conn = DBConn.getConn();
            Statement statement = conn.createStatement();
        ) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);

            while (resultSet.next()) {
                listaRestaurante.add(
                    RestauranteController.resultSetToRestaurante(resultSet)
                );
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return listaRestaurante;
    }

    public static List<Restaurante> getRestaurantesEnCodigosPostales(
        int[] idsCodigoPostal
    ) {
        List<Restaurante> listaRestaurante = new ArrayList<>();

        // Si no hay ningún id de código postal, lista vacía.
        if (idsCodigoPostal.length == 0) {
            return listaRestaurante;
        }

        try (
            Connection conn = DBConn.getConn();
            PreparedStatement ps = conn.prepareStatement(String.format(
                "SELECT * FROM %s WHERE codigo_postal_id_codigo_postal IN (?)", TABLE
            ));
        ) {
            // Metemos todos los códigos postales en una String, separados con comas, para usar el IN de SQL.
            StringBuilder idsCodigoPostalSql = new StringBuilder("");
            for (int idCodigoPostal : idsCodigoPostal) {
                idsCodigoPostalSql.append(idCodigoPostal).append(", ");
            }
            ps.setString(1, idsCodigoPostalSql.substring(
                0,
                idsCodigoPostalSql.length()-2
            ));

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                listaRestaurante.add(
                    RestauranteController.resultSetToRestaurante(resultSet)
                );
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