package com.codesplai.randeat.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.codesplai.randeat.DBConn;
import com.codesplai.randeat.FromResultSet;
import com.codesplai.randeat.modelo.Usuario;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/usuario")
public class UsuarioController implements FromResultSet<Usuario> {
    private static final String TABLE = "usuario";
    private static final String ID_USUARIO = "id_usuario";
    private static final String CORREO_ELECTRONICO = "correo_electronico";
    private static final String CONTRASENA = "contrasena";
    private static final String TELEFONO = "telefono";
    private static final String POBLACION = "poblacion";
    private static final String DIRECCION = "direccion";

    private static final String SELECT_BY_ID_USUARIO = String.format(
        "SELECT * FROM %s WHERE %s = ?", TABLE, ID_USUARIO
    );

    private static final String SELECT_BY_CREDENTIALS = String.format(
        "SELECT * FROM %s"
        + " WHERE %s = ?"
        + " AND %s = ?",
        TABLE, CORREO_ELECTRONICO, CONTRASENA
    );

    private static final String INSERT_USUARIO = String.format(
        "INSERT INTO %s (%s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?)",
        TABLE, CORREO_ELECTRONICO, CONTRASENA, TELEFONO, POBLACION, DIRECCION
    );

    private static final String UPDATE_USUARIO = String.format(
        "UPDATE %s SET %s=?, %s=?, %s=?, %s=?, %s=? WHERE %s=?",
        TABLE, CORREO_ELECTRONICO, CONTRASENA, TELEFONO, POBLACION, DIRECCION, ID_USUARIO
    );

    @GetMapping("/getById/{idUsuario}")
    public static Optional<Usuario> getById(@PathVariable int idUsuario) {
        return DBConn.executeQueryWithParamsSingleValue(
            SELECT_BY_ID_USUARIO,
            new Object[][] {
                {1, idUsuario}
            },
            new UsuarioController()
        );
    }

    @GetMapping("/getByCredentials")
    public static Optional<Usuario> getByCredentials(
        @RequestParam String email,
        @RequestParam String password
    ) {
        return DBConn.executeQueryWithParamsSingleValue(
            SELECT_BY_CREDENTIALS,
            new Object[][] {
                {1, email},
                {2, password}
            },
            new UsuarioController()
        );
    }

    @PostMapping("/add")
    public static Optional<Integer> add(
        @RequestBody Usuario usuario
    ) {
        return DBConn.executeInsert(
            INSERT_USUARIO,
            new Object[][] {
                {1, usuario.getCorreoElectronico()},
                {2, usuario.getContrasena()},
                {3, usuario.getTelefono()},
                {4, usuario.getPoblacion()},
                {5, usuario.getDireccion()}
            }
        );
    }

    public static boolean update(Usuario usuario) {
        return DBConn.executeUpdateOrDelete(
            UPDATE_USUARIO,
             new Object[][] {
                 {1, usuario.getCorreoElectronico()},
                 {2, usuario.getContrasena()},
                 {3, usuario.getTelefono()},
                 {4, usuario.getPoblacion()},
                 {5, usuario.getDireccion()}
             }
        );     
    }

    @Override
    public Usuario fromResultSet(ResultSet resultSet) throws SQLException {
        return new Usuario(
            resultSet.getInt(ID_USUARIO),
            resultSet.getString(CORREO_ELECTRONICO),
            resultSet.getString(CONTRASENA),
            resultSet.getString(TELEFONO),
            resultSet.getString(POBLACION),
            resultSet.getString(DIRECCION)
        );
    }
}