package backend.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import backend.DBConn;
import backend.FromResultSet;
import backend.modelo.Cliente;

public class ClienteController implements FromResultSet<Cliente> {
    private static final String TABLE = "cliente";
    private static final String ID_CLIENTE = "id_cliente";
    private static final String USUARIO_ID_USUARIO = "usuario_id_usuario";
    private static final String NOMBRE_COMPLETO = "nombre_completo";

    private static final String SELECT_ALL = String.format(
        "SELECT * FROM %s", TABLE
    );

    private static final String SELECT_BY_ID_CLIENTE = String.format(
        "SELECT * FROM %s WHERE %s = ?", TABLE, ID_CLIENTE
    );

    public static List<Cliente> getAll() {
        return DBConn.executeQueryIntoList(SELECT_ALL, new ClienteController());
    }

    public static Optional<Cliente> getById(int idCliente) {
        return DBConn.executeQueryWithParamsSingleValue(
            SELECT_BY_ID_CLIENTE,
            new Object[][] {
                {1, idCliente}
            },
            new ClienteController()
        );
    }

    @Override
    public Cliente fromResultSet(ResultSet resultSet) throws SQLException {
        return new Cliente(
            resultSet.getInt(ID_CLIENTE),
            resultSet.getInt(USUARIO_ID_USUARIO),
            resultSet.getString(NOMBRE_COMPLETO)
        );
    }
}