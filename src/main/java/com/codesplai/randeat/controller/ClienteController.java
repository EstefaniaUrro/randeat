package com.codesplai.randeat.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.codesplai.randeat.DBConn;
import com.codesplai.randeat.FromResultSet;
import com.codesplai.randeat.controller.wrapper.UsuarioClienteWrapper;
import com.codesplai.randeat.modelo.Cliente;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/cliente")
public class ClienteController implements FromResultSet<Cliente> {
    private static final String TABLE = "cliente";
    private static final String ID_CLIENTE = "id_cliente";
    private static final String USUARIO_ID_USUARIO = "usuario_id_usuario";
    private static final String NOMBRE_COMPLETO = "nombre_completo";
    private static final String CODIGO_POSTAL_ID_CODIGO_POSTAL = "codigo_postal_id_codigo_postal";

    private static final String SELECT_BY_ID_CLIENTE = String.format(
        "SELECT * FROM %s WHERE %s = ?", TABLE, ID_CLIENTE
    );

    private static final String SELECT_BY_ID_USUARIO = String.format(
        "SELECT * FROM %s WHERE %s = ?", TABLE, USUARIO_ID_USUARIO
    );

    private static final String INSERT = String.format(
        "INSERT INTO %s (%s, %s, %s) VALUES (?,?,?)",
        TABLE, USUARIO_ID_USUARIO, NOMBRE_COMPLETO, CODIGO_POSTAL_ID_CODIGO_POSTAL
    );

    private static final String UPDATE = String.format(
        "UPDATE %s set %s=?, %s=?, %s=? WHERE %s=?",
        TABLE, USUARIO_ID_USUARIO,NOMBRE_COMPLETO, CODIGO_POSTAL_ID_CODIGO_POSTAL, ID_CLIENTE
    );

    @GetMapping("/getById/{idCliente}")
    public static Optional<Cliente> getById(
        @PathVariable int idCliente
    ) {
        return DBConn.executeQueryWithParamsSingleValue(
            SELECT_BY_ID_CLIENTE,
            new Object[][] {
                {1, idCliente}
            },
            new ClienteController()
        );
    }

    @GetMapping("/getByIdUsuario/{idUsuario}")
    public static Optional<Cliente> getByIdUsuario(
        @PathVariable int idUsuario
    ) {
        return DBConn.executeQueryWithParamsSingleValue(
            SELECT_BY_ID_USUARIO,
            new Object[][] {
                {1, idUsuario}
            },
            new ClienteController()
        );
    }

    @PostMapping("/add")
    public static Optional<Integer> add(
        @RequestBody UsuarioClienteWrapper usuarioCliente
    ) {
        // Doy de alta el Usuario. Si todo ha ido bien, tendré el idUsuario que
        // necesito para dar de alta el Cliente.
        int idUsuario = UsuarioController.add(
            usuarioCliente.usuario
        ).get();

        return DBConn.executeInsert(
            INSERT,
            new Object[][] {
                 {1, idUsuario},
                 {2, usuarioCliente.cliente.getNombreCompleto()},
                 {3, usuarioCliente.cliente.getCodigoPostalIdCodigoPostal()}
            }
        );
    }

    @PutMapping("/update")
    public static boolean update(
        @RequestBody UsuarioClienteWrapper usuarioCliente
    ) {
        UsuarioController.update(usuarioCliente.usuario);

        DBConn.executeUpdateOrDelete(
            UPDATE,
            new Object[][]{
                {1, usuarioCliente.cliente.getUsuarioIdUsuario()},
                {2, usuarioCliente.cliente.getNombreCompleto()},
                {3, usuarioCliente.cliente.getCodigoPostalIdCodigoPostal()},
                {4, usuarioCliente.cliente.getIdCliente()}
        });

        return true;
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
}
