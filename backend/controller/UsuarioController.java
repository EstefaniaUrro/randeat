package backend.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import backend.DBConn;
import backend.FromResultSet;
import backend.modelo.Usuario;

public class UsuarioController implements FromResultSet<Usuario> {
    private static final String TABLE = "usuario";
    private static final String ID_USUARIO = "id_usuario";
    private static final String CORREO_ELECTRONICO = "correo_electronico";
    private static final String CONTRASENA = "contrasena";
    private static final String TELEFONO = "telefono";
    private static final String POBLACION = "poblacion";
    private static final String DIRECCION = "direccion";

    private static final String SELECT_ALL = String.format(
        "SELECT * FROM %s", TABLE
    );

    private static final String SELECT_BY_ID_USUARIO = String.format(
        "SELECT * FROM %s WHERE %s = ?", TABLE, ID_USUARIO
    );

    private static final String INSERT_USUARIO = String.format(
        "INSERT INTO %s (%s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?)",
        TABLE, CORREO_ELECTRONICO, CONTRASENA, TELEFONO, POBLACION, DIRECCION
    );

    private static final String UPDATE_USUARIO = String.format(
        "UPDATE %s SET %s=?, %s=?, %s=?, %s=?, %s=? WHERE %s=?",
        TABLE, CORREO_ELECTRONICO, CONTRASENA, TELEFONO, POBLACION, DIRECCION, ID_USUARIO
    );

    public static List<Usuario> getAll() {
        return DBConn.executeQueryIntoList(SELECT_ALL, new UsuarioController());
    }

    public static Optional<Usuario> getById(int idUsuario) {
        return DBConn.executeQueryWithParamsSingleValue(
            SELECT_BY_ID_USUARIO,
            new Object[][] {
                {1, idUsuario}
            },
            new UsuarioController()
        );
    }

    public static Optional<Integer> add(Usuario usuario) {
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
//Preguntar Bernat Lunes
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