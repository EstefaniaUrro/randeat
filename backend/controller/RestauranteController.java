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
    private static final String ID_RESTAURANTE = "id_restaurante";
    private static final String ID_USUARIO = "usuario_id_usuario";
    private static final String CIF = "cif";
    private static final String IBAN = "iban";
    private static final String NOMBRE_RESTAURANTE = "nombre_restaurante";
    private static final String NOMBRE_PROPIETARIO = "nombre_propietario";
    private static final String ID_CODIGO_POSTAL = "codigo_postal_id_codigo_postal";
    private static final String ACTIVO = "activo";

    private static final String SELECT_ACTIVOS = String.format(
        "SELECT * FROM %s WHERE %s = 1", TABLE, ACTIVO
    );

    private static final String SELECT_BY_ID_RESTAURANTE = String.format(
        "SELECT * FROM %s WHERE %s = ?", TABLE, ID_RESTAURANTE
    );

    private static final String SELECT_ACTIVOS_BY_ID_CODIGO_POSTAL = String.format(
        "SELECT * FROM %s WHERE %s = 1 AND %s IN (?)",
        TABLE, ACTIVO, ID_CODIGO_POSTAL
    );

    private static final String INSERT = String.format(
        "INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?, 0)",
        TABLE, ID_USUARIO, CIF, IBAN, NOMBRE_RESTAURANTE, NOMBRE_PROPIETARIO, ID_CODIGO_POSTAL, ACTIVO
    );

    public static List<Restaurante> getActivos() {
        return DBConn.executeQueryIntoList(
            SELECT_ACTIVOS,
            new RestauranteController()
        );
    }

    public static Optional<Restaurante> getById(int idRestaurante) {
        return DBConn.executeQueryWithParamsSingleValue(
            SELECT_BY_ID_RESTAURANTE,
            new Object[][] {
                {1, idRestaurante}
            },
            new RestauranteController()
        );
    }

    public static List<Restaurante> getActivosByIdCodigoPostal(
        List<Integer> idsCodigoPostal
    ) {
        return DBConn.executeQueryWithParamsIntoList(
            SELECT_ACTIVOS_BY_ID_CODIGO_POSTAL,
            new Object[][] {
                {1, DBConn.joinIntsInClause(idsCodigoPostal)}
            },
            new RestauranteController()
        );
    }

    public static Optional<Integer> add(Restaurante restaurante) {
        return DBConn.executeInsert(
            INSERT,
            new Object[][] {
                {1, restaurante.getUsuarioIdUsuario()},
                {2, restaurante.getCif()},
                {3, restaurante.getIban()},
                {4, restaurante.getNombreRestaurante()},
                {5, restaurante.getNombrePropietario()},
                {6, restaurante.getCodigoPostalIdCodigoPostal()}
            }
        );
    }

    @Override
    public Restaurante fromResultSet(ResultSet resultSet) throws SQLException {
        return new Restaurante(
            resultSet.getInt(ID_RESTAURANTE),
            resultSet.getInt(ID_USUARIO),
            resultSet.getString(CIF),
            resultSet.getString(IBAN),
            resultSet.getString(NOMBRE_RESTAURANTE),
            resultSet.getString(NOMBRE_PROPIETARIO),
            resultSet.getInt(ID_CODIGO_POSTAL),
            resultSet.getBoolean(ACTIVO)
        );
    }
}