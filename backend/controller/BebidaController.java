package backend.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import backend.DBConn;
import backend.FromResultSet;
import backend.modelo.Bebida;


public class BebidaController implements FromResultSet<Bebida> {
    private static final String TABLE = "bebida";
    public static final String ID_BEBIDA = "id_bebida";
    public static final String NOMBRE = "nombre";

    private static final String SELECT_ALL = String.format(
        "SELECT * FROM %s", TABLE
    );

    private static final String SELECT_BY_ID_BEBIDA = String.format(
        "SELECT * FROM %s WHERE %s = ?", TABLE, ID_BEBIDA
    );

    public static List<Bebida> getAll() {
        return DBConn.executeQueryIntoList(SELECT_ALL, new BebidaController());
    }

    public static Optional<Bebida> getById(int idBebida) {
        return DBConn.executeQueryWithParamsSingleValue(
            SELECT_BY_ID_BEBIDA,
            new Object[][] {
                {1, idBebida}
            },
            new BebidaController()
        );
    }

    @Override
    public Bebida fromResultSet(ResultSet resultSet) throws SQLException {
        return new Bebida(
            resultSet.getInt(ID_BEBIDA),
            resultSet.getString(NOMBRE)
        );
    }
}