package backend.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import backend.DBConn;
import backend.FromResultSet;
import backend.modelo.TipoCocina;

public class TipoCocinaController implements FromResultSet<TipoCocina> {
    private static final String TABLE = "tipo_cocina";
    private static final String KEY = "id_tipo_cocina";
    private static final String NOMBRE = "nombre";

    private static final String SELECT_ALL = String.format(
        "SELECT * FROM %s", TABLE
    );

    private static final String SELECT_BY_KEY = String.format(
        "SELECT * FROM %s WHERE %s = ?", TABLE, KEY
    );
    
    public static List<TipoCocina> getAll() {
        return DBConn.executeQueryIntoList(
            SELECT_ALL,
            new TipoCocinaController()
        );
    }

    public static Optional<TipoCocina> getById(int idTipoCocina) {
        return DBConn.executeQueryWithParamsSingleValue(
            SELECT_BY_KEY,
            new Object[][] {
                {1, idTipoCocina}
            },
            new TipoCocinaController()
        );
    }

    @Override
    public TipoCocina fromResultSet(ResultSet resultSet) throws SQLException {
        return new TipoCocina(
            resultSet.getInt(KEY),
            resultSet.getString(NOMBRE)
        );
    }
}