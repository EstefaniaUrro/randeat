package backend.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import backend.DBConn;
import backend.FromResultSet;
import backend.modelo.Restaurante;

public class RestauranteController implements FromResultSet<Restaurante> {
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

    private static final String SELECT_BY_ID = String.format(
        "SELECT * FROM %s WHERE %s = ?", TABLE, KEY
    );

    public static List<Restaurante> getAll() {
        return DBConn.executeQueryIntoList(
            SELECT_ALL,
            new RestauranteController()
        );
    }

    public static Optional<Restaurante> getById(int idRestaurante) {
        return DBConn.executeQueryWithParamsSingleValue(
            SELECT_BY_ID,
            new Object[][] {
                {1, idRestaurante}
            },
            new RestauranteController()
        );
    }

    public static List<Restaurante> getByIdCodigoPostal(
        List<Integer> idsCodigoPostal
    ) {
        return DBConn.executeQueryWithParamsIntoList(
            String.format(
                "SELECT * FROM %s WHERE codigo_postal_id_codigo_postal IN (?)", TABLE
            ),
            new Object[][] {
                {1, DBConn.joinIntsInClause(idsCodigoPostal)}
            },
            new RestauranteController()
        );
    }

    @Override
    public Restaurante fromResultSet(ResultSet resultSet) throws SQLException {
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
}