package backend.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import backend.DBConn;
import backend.FromResultSet;
import backend.modelo.Paquete;


public class PaqueteController implements FromResultSet<Paquete> {
    private static final String TABLE = "paquete";
    private static final String ID_PAQUETE = "id_paquete";
    private static final String NOMBRE = "nombre";

    private static final String SELECT_ALL = String.format(
        "SELECT * FROM %s", TABLE
    );

    private static final String SELECT_BY_ID_PAQUETE = String.format(
        "SELECT * FROM %s WHERE %s = ?", TABLE, ID_PAQUETE
    );

    public static List<Paquete> getAll() {
        return DBConn.executeQueryIntoList(
            SELECT_ALL,
            new PaqueteController()
        );
    }

    public static Optional<Paquete> getById(int idPaquete) {
        return DBConn.executeQueryWithParamsSingleValue(
            SELECT_BY_ID_PAQUETE,
            new Object[][] {
                {1, idPaquete}
            },
            new PaqueteController()
        );
    }

    @Override
    public Paquete fromResultSet(ResultSet resultSet) throws SQLException {
        return new Paquete(
            resultSet.getInt(ID_PAQUETE),
            resultSet.getString(NOMBRE)
        );
    }
}