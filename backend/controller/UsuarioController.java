package backend.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import backend.DBConn;
import backend.modelo.Usuario;

public class UsuarioController {
    private static final String TABLE = "usuario";
    private static final String KEY = "id_usuario";
    private static final String CORREO_ELECTRONICO = "correo_electronico";
    private static final String CONTRASENA = "contrasena";
    private static final String NOMBRE = "nombre";
    private static final String TELEFONO = "telefono";
    private static final String POBLACION = "poblacion";
    private static final String DIRECCION = "direccion";

    private static final String SELECT_ALL = String.format(
        "SELECT * FROM %s", TABLE
    );

    public static List<Usuario> getAll() {
        List<Usuario> listaUsuario = new ArrayList<>();

        try (
            Connection conn = DBConn.getConn();
            Statement statement = conn.createStatement();
        ) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);

            while (resultSet.next()) {
                listaUsuario.add(new Usuario(
                    resultSet.getInt(KEY),
                    resultSet.getString(CORREO_ELECTRONICO),
                    resultSet.getString(CONTRASENA),
                    resultSet.getString(NOMBRE),
                    resultSet.getString(TELEFONO),
                    resultSet.getString(POBLACION),
                    resultSet.getString(DIRECCION)
                ));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return listaUsuario;
    }
}
