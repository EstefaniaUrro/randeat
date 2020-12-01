package com.codesplai.randeat.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.codesplai.randeat.DBConn;
import com.codesplai.randeat.FromResultSet;
import com.codesplai.randeat.modelo.TipoEntrega;

public class TipoEntregaController implements FromResultSet<TipoEntrega> {
    private static final String TABLE = "tipo_entrega";
    private static final String ID_TIPO_ENTREGA = "id_tipo_entrega";
    private static final String NOMBRE = "nombre";

    private static final String SELECT_ALL = String.format(
        "SELECT * FROM %s", TABLE
    );

    private static final String SELECT_BY_ID_TIPO_ENTREGA = String.format(
        "SELECT * FROM %s WHERE %s = ?", TABLE, ID_TIPO_ENTREGA
    );

    public static List<TipoEntrega> getAll() {
        return DBConn.executeQueryIntoList(
            SELECT_ALL,
            new TipoEntregaController()
        );
    }

    public static Optional<TipoEntrega> getById(int idTipoEntrega) {
        return DBConn.executeQueryWithParamsSingleValue(
            SELECT_BY_ID_TIPO_ENTREGA,
            new Object[][] {
                {1, idTipoEntrega}
            },
            new TipoEntregaController()
        );
    }

    @Override
    public TipoEntrega fromResultSet(ResultSet resultSet) throws SQLException {
        return new TipoEntrega(
            resultSet.getInt(ID_TIPO_ENTREGA),
            resultSet.getString(NOMBRE)
        );
    }
}