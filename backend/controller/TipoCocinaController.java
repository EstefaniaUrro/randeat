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

    private static final String INSERT = String.format(
        "INSERT INTO %s (%s) VALUES (?)",TABLE, NOMBRE
    );

    private static final String UPDATE = String.format(
        "UPDATE %s SET %s=? WHERE %s=?", TABLE, NOMBRE, ID_TIPO_COCINA
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

    public static Optional<Integer> add(TipoCocina tipoCocina) {
        return DBConn.executeInsert(
            INSERT, 
            new Object[][]{
                {1, tipoCocina.getNombre()}
            }
        );
    }

    public static Optional<Integer> update(TipoCocina tipoCocina) {
        DBConn.executeUpdateOrDelete(
            UPDATE,
            new Object[][]{
                {1, tipoCocina.getNombre()},
                {2, tipoCocina.getIdTipoCocina()}
            }
        );

        return Optional.of(tipoCocina.getIdTipoCocina());
    }

    @Override
    public TipoCocina fromResultSet(ResultSet resultSet) throws SQLException {
        return new TipoCocina(
            resultSet.getInt(ID_TIPO_COCINA),
            resultSet.getString(NOMBRE)
        );
    }
}