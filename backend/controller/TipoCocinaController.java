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
    private static final String ID_TIPO_COCINA = "id_tipo_cocina";
    private static final String NOMBRE = "nombre";

    private static final String SELECT_ALL = String.format(
        "SELECT * FROM %s", TABLE
    );

    private static final String SELECT_BY_ID_TIPO_COCINA = String.format(
        "SELECT * FROM %s WHERE %s = ?", TABLE, ID_TIPO_COCINA
    );
    
    public static List<TipoCocina> getAll() {
        return DBConn.executeQueryIntoList(
            SELECT_ALL,
            new TipoCocinaController()
        );
    }

    public static Optional<TipoCocina> getById(int idTipoCocina) {
        return DBConn.executeQueryWithParamsSingleValue(
            SELECT_BY_ID_TIPO_COCINA,
            new Object[][] {
                {1, idTipoCocina}
            },
            new TipoCocinaController()
        );
    }

    @Override
    public TipoCocina fromResultSet(ResultSet resultSet) throws SQLException {
        return new TipoCocina(
            resultSet.getInt(ID_TIPO_COCINA),
            resultSet.getString(NOMBRE)
        );
    }
}