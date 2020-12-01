package com.codesplai.randeat.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.codesplai.randeat.DBConn;
import com.codesplai.randeat.FromResultSet;
import com.codesplai.randeat.modelo.Paquete;


public class PaqueteController implements FromResultSet<Paquete> {
    static final String TABLE = "paquete";
    static final String ID_PAQUETE = "id_paquete";
    static final String NOMBRE = "nombre";

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