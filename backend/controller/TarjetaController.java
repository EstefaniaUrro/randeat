package backend.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import backend.DBConn;
import backend.FromResultSet;
import backend.modelo.Tarjeta;

public class TarjetaController implements FromResultSet<Tarjeta> {
    private static final String TABLE = "tarjeta";
    private static final String KEY = "id_tarjeta";
    private static final String NUMERO = "numero";

    private static final String SELECT_BY_KEY = String.format(
        "SELECT * FROM %s WHERE %s = ?", TABLE, KEY
    );

    public static List<Tarjeta> getById(int id_tarjeta) {
        return DBConn.executeQueryWithParamsIntoList(
            SELECT_BY_KEY,
            new Object[][] {
                {1, id_tarjeta}
            },
            new TarjetaController()
        );
    }

    @Override
    public Tarjeta fromResultSet(ResultSet resultSet) throws SQLException {
        return new Tarjeta(
            resultSet.getInt(KEY),
            resultSet.getString(NUMERO)
        );
    }
}