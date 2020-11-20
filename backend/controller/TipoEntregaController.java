package backend.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import backend.DBConn;
import backend.FromResultSet;
import backend.modelo.TipoEntrega;

public class TipoEntregaController implements FromResultSet<TipoEntrega> {
    private static final String TABLE = "tipo_entrega";
    private static final String KEY = "id_tipo_entrega";
    private static final String NOMBRE = "nombre";

    private static final String SELECT_ALL = String.format(
        "SELECT * FROM %s", TABLE
    );

    private static final String SELECT_BY_ID = String.format(
        "SELECT * FROM %s WHERE %s = ?", TABLE, KEY
    );

    public static List<TipoEntrega> getAll() {
        return DBConn.executeQueryIntoList(
            SELECT_ALL,
            new TipoEntregaController()
        );
    }

    public static Optional<TipoEntrega> getById(int idTipoEntrega) {
        return DBConn.executeQueryWithParamsSingleValue(
            SELECT_BY_ID,
            new Object[][] {
                {1, idTipoEntrega}
            },
            new TipoEntregaController()
        );
    }

    @Override
    public TipoEntrega fromResultSet(ResultSet resultSet) throws SQLException {
        return new TipoEntrega(
            resultSet.getInt(KEY),
            resultSet.getString(NOMBRE)
        );
    }
}