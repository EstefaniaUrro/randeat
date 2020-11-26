package backend.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    private static final String CODIGO_POSTAL_ID_CODIGO_POSTAL = "codigo_postal_id_codigo_postal";

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
            resultSet.getString(NOMBRE_COMPLETO),
            resultSet.getInt(CODIGO_POSTAL_ID_CODIGO_POSTAL)
        );
    }
    public static void save(Cliente Cliente) {
        String sql;
        if (Cliente.getIdCliente()>0) {
        sql = String.format("UPDATE %s set id_cliente=?, usuario_id_usuario=?, nombre_completo=?, codigo_postal_id_codigo_postal=?",
        TABLE, ID_CLIENTE, Cliente.getIdCliente());
        } else {
        sql = String.format("INSERT INTO %s  id_cliente, usuario_id_usuario, nombre_completo, codigo_postal_id_codigo_postal) VALUES (?,?,?,?)",
        TABLE);
        }
        try (Connection conn = DBConn.getConn();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        Statement stmt = conn.createStatement()) {
        pstmt.setInt(1, Cliente.getIdCliente());
        pstmt.setInt(2, Cliente.getUsuarioIdUsuario());
        pstmt.setString(3, Cliente.getNombreCompleto());
        pstmt.setInt(4, Cliente.getCodigoPostalIdCodigoPostal());
        pstmt.executeUpdate();
        if (Cliente.getIdCliente()==0) {
        ResultSet rs = stmt.executeQuery("select last_insert_id()");
        if (rs.next()) {
        Cliente.setIdCliente(rs.getInt(1));
        }
        }
        } catch (Exception e) {
        System.out.println(e.getMessage());
        }
        }
}
