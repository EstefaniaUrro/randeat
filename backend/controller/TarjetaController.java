package backend.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import backend.DBConn;
import backend.FromResultSet;
import backend.modelo.Tarjeta;

public class TarjetaController implements FromResultSet<Tarjeta> {
    private static final String TABLE = "tarjeta";
    private static final String ID_TARJETA = "id_tarjeta";
    private static final String NUMERO = "numero";

    private static final String SELECT_BY_ID_TARJETA = String.format(
        "SELECT * FROM %s WHERE %s = ?", TABLE, ID_TARJETA
    );

    private static final String SELECT_IN_ID_TARJETA = String.format(
        "SELECT * FROM %s WHERE %s IN (?)", TABLE, ID_TARJETA
    );

    private static final String INSERT_TARJETA = String.format(
        "INSERT INTO %s (%s) VALUES (?)",
        TABLE, NUMERO
    );

    private static final String DELETE_TARJETA = String.format(
        "DELETE FROM %s WHERE %s = ?",
        TABLE, ID_TARJETA
    );

    public static Optional<Tarjeta> getById(int idTarjeta) {
        return DBConn.executeQueryWithParamsSingleValue(
            SELECT_BY_ID_TARJETA,
            new Object[][] {
                {1, idTarjeta}
            },
            new TarjetaController()
        );
    }

    public static List<Tarjeta> getMultById(List<Integer> idsTarjeta) {
        return DBConn.executeQueryWithParamsIntoList(
            SELECT_IN_ID_TARJETA,
            new Object[][] {
                {1, DBConn.joinIntsInClause(idsTarjeta)}
            },
            new TarjetaController()
        );
    }

    public static Optional<Integer> addTarjeta(Tarjeta tarjeta) {
        return DBConn.executeInsert(
            INSERT_TARJETA,
            new Object[][] {
                {1, tarjeta.getNumero()}
            }
        );
    }

    public static boolean removeTarjeta(int idTarjeta) {
        return DBConn.executeUpdateOrDelete(
            DELETE_TARJETA,
            new Object[][] {
                {1, idTarjeta}
            }
        );
    }

    @Override
    public Tarjeta fromResultSet(ResultSet resultSet) throws SQLException {
        return new Tarjeta(
            resultSet.getInt(ID_TARJETA),
            resultSet.getString(NUMERO)
        );
    }
}