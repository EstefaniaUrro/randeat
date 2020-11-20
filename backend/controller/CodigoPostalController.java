package backend.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import backend.DBConn;
import backend.FromResultSet;
import backend.modelo.CodigoPostal;

public class CodigoPostalController implements FromResultSet<CodigoPostal> {
    private static final String TABLE = "codigo_postal";
    private static final String KEY = "id_codigo_postal";
    private static final String NUMERO = "numero";

    private static final String SELECT_ALL = String.format(
        "SELECT * FROM %s", TABLE
    );

    private static final String SELECT_BY_KEY = String.format(
        "SELECT * FROM %s WHERE id_codigo_postal = ?", TABLE
    );

    public static List<CodigoPostal> getAll() {
        return DBConn.executeQueryIntoList(
            SELECT_ALL,
            new CodigoPostalController()
        );
    }

    public static Optional<CodigoPostal> getById(int idCodigoPostal) {
        return DBConn.executeQueryWithParamsSingleValue(
            SELECT_BY_KEY,
            new Object[][] {
                {1, idCodigoPostal}
            },
            new CodigoPostalController()
        );
    }

    @Override
    public CodigoPostal fromResultSet(ResultSet resultSet) throws SQLException {
        return new CodigoPostal(
            resultSet.getInt(KEY),
            resultSet.getString(NUMERO)
        );
    }
}