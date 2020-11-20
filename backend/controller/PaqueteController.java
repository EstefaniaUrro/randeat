package backend.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import backend.DBConn;
import backend.FromResultSet;
import backend.modelo.Paquete;


public class PaqueteController implements FromResultSet<Paquete> {
    private static final String TABLE = "paquete";
    private static final String KEY = "id_paquete";
    private static final String NOMBRE = "nombre";

    private static final String SELECT_ALL = String.format(
        "SELECT * FROM %s", TABLE
    );

    public static List<Paquete> getAll() {
        return DBConn.executeQueryIntoList(
            SELECT_ALL,
            new PaqueteController()
        );
    }

    @Override
    public Paquete fromResultSet(ResultSet resultSet) throws SQLException {
        return new Paquete(
            resultSet.getInt(KEY),
            resultSet.getString(NOMBRE)
        );
    }
}