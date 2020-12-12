package com.codesplai.randeat.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.codesplai.randeat.DBConn;
import com.codesplai.randeat.FromResultSet;
import com.codesplai.randeat.controller.wrapper.UsuarioRestauranteWrapper;
import com.codesplai.randeat.modelo.Restaurante;
import com.codesplai.randeat.modelo.Usuario;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/restaurante")
public class RestauranteController implements FromResultSet<Restaurante> {
    static final String TABLE = "restaurante";
    static final String ID_RESTAURANTE = "id_restaurante";
    static final String ID_USUARIO = "usuario_id_usuario";
    static final String CIF = "cif";
    static final String IBAN = "iban";
    static final String NOMBRE_RESTAURANTE = "nombre_restaurante";
    static final String NOMBRE_PROPIETARIO = "nombre_propietario";
    static final String ID_CODIGO_POSTAL = "codigo_postal_id_codigo_postal";
    static final String ACTIVO = "activo";

    private static final String SELECT_ACTIVOS = String.format(
        "SELECT * FROM %s WHERE %s = 1", TABLE, ACTIVO
    );

    private static final String SELECT_BY_ID_RESTAURANTE = String.format(
        "SELECT * FROM %s WHERE %s = ?", TABLE, ID_RESTAURANTE
    );

    private static final String SELECT_ACTIVOS_BY_ID_CODIGO_POSTAL = String.format(
        "SELECT * FROM %s WHERE %s = 1 AND %s = ?",
        TABLE, ACTIVO, ID_CODIGO_POSTAL
    );

    // Restaurantes activos (1) en un código postal concreto (2), con un tipo entrega (3) y un tipo cocina (4) determinados, .
    private static final String SELECT_FILTER = String.format(
        "SELECT DISTINCT r.* FROM %s r"
        + " INNER JOIN %s rtc ON rtc.%s = r.%s"
        + " INNER JOIN %s rte ON rte.%s = r.%s"
        + " WHERE r.%s = 1" // (1)
        + " AND r.%s = ?" // (2)
        + " AND rte.%s = ?" // (3)
        + " AND rtc.%s = ?", // (4)
        TABLE,

        RestauranteTipoCocinaController.TABLE,
        RestauranteTipoCocinaController.ID_RESTAURANTE,
        ID_RESTAURANTE,

        RestauranteTipoEntregaController.TABLE,
        RestauranteTipoEntregaController.ID_RESTAURANTE,
        ID_RESTAURANTE,

        ACTIVO,

        ID_CODIGO_POSTAL,

        RestauranteTipoEntregaController.ID_TIPO_ENTREGA,
        
        RestauranteTipoCocinaController.ID_TIPO_COCINA
    );

    private static final String SELECT_BY_ID_USUARIO = String.format(
        "SELECT * FROM %s WHERE %s = ?", TABLE, ID_USUARIO
    );

    private static final String INSERT = String.format(
        "INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?, 0)",
        TABLE, ID_USUARIO, CIF, IBAN, NOMBRE_RESTAURANTE, NOMBRE_PROPIETARIO, ID_CODIGO_POSTAL, ACTIVO
    );

    private static final String UPDATE = String.format(
        "UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ? WHERE %s = ?",
        TABLE, CIF, IBAN, NOMBRE_RESTAURANTE, NOMBRE_PROPIETARIO, ID_CODIGO_POSTAL, ACTIVO, ID_RESTAURANTE
    );

    @GetMapping("/getActivos")
    public static List<Restaurante> getActivos() {
        return DBConn.executeQueryIntoList(
            SELECT_ACTIVOS,
            new RestauranteController()
        );
    }

    @GetMapping("/getById/{idRestaurante}")
    public static Optional<Restaurante> getById(
        @PathVariable int idRestaurante
    ) {
        return DBConn.executeQueryWithParamsSingleValue(
            SELECT_BY_ID_RESTAURANTE,
            new Object[][] {
                {1, idRestaurante}
            },
            new RestauranteController()
        );
    }

    @GetMapping("/getActivosByIdCodigoPostal/{idCodigoPostal}")
    public static List<Restaurante> getActivosByIdCodigoPostal(
        @PathVariable int idCodigoPostal
    ) {
        return DBConn.executeQueryWithParamsIntoList(
            SELECT_ACTIVOS_BY_ID_CODIGO_POSTAL,
            new Object[][] {
                {1, idCodigoPostal}
            },
            new RestauranteController()
        );
    }

    /**
     * Devuelve una lista con los restaurantes que se encuentran en un código postal concreto y que ofrecen un tipo de entrega y un tipo de cocina determinados
     * @param idCodigoPostal
     * @param idTipoEntrega
     * @param idTipoCocina
     * @return
     */
    @GetMapping("/getInFilter")
    public static List<Restaurante> getInFilter(
        @RequestParam int idCodigoPostal,
        @RequestParam int idTipoEntrega,
        @RequestParam int idTipoCocina
    ) {
        return DBConn.executeQueryWithParamsIntoList(
            SELECT_FILTER,
            new Object[][] {
                {1, idCodigoPostal},
                {2, idTipoEntrega},
                {3, idTipoCocina}
            },
            new RestauranteController()
        );
    }

    @GetMapping("/getByIdUsuario/{idUsuario}")
    public static Optional<Restaurante> getByIdUsuario(
        @PathVariable int idUsuario
    ) {
        return DBConn.executeQueryWithParamsSingleValue(
            SELECT_BY_ID_USUARIO,
            new Object[][] {
                {1, idUsuario}
            },
            new RestauranteController()
        );
    }

    @GetMapping("/add/{jsonString}")
    public static Optional<Integer> add(
        @PathVariable String jsonString
    ) throws ParseException {
        Map<String, Object> form = new JSONParser(jsonString).parseObject();

        // Doy de alta el Usuario. Si todo ha ido bien, tendré el idUsuario que
        // necesito para dar de alta el Restaurante.
        Usuario usuario = new Usuario(
            0,
            (String) form.get("correoElectronico"),
            (String) form.get("contrasena"),
            (String) form.get("telefono"),
            (String) form.get("poblacion"),
            (String) form.get("direccion")
        );

        int idUsuario = UsuarioController
            .add(usuario)
            .get()
        ;

        return DBConn.executeInsert(
            INSERT,
            new Object[][] {
                {1, idUsuario},
                {2, (String) form.get("cif")},
                {3, (String) form.get("iban")},
                {4, (String) form.get("nombreRestaurante")},
                {5, (String) form.get("nombrePropietario")},
                {6, (Integer) form.get("idCodigoPostal")}
            }
        );
    }

    @PutMapping("/update")
    public static boolean update(
        @RequestBody UsuarioRestauranteWrapper usuarioRestaurante
    ) {
        UsuarioController.update(usuarioRestaurante.usuario);
        
        DBConn.executeUpdateOrDelete(
            UPDATE,
            new Object[][] {
                {1, usuarioRestaurante.restaurante.getCif()},
                {2, usuarioRestaurante.restaurante.getIban()},
                {3, usuarioRestaurante.restaurante.getNombreRestaurante()},
                {4, usuarioRestaurante.restaurante.getNombrePropietario()},
                {5, usuarioRestaurante.restaurante.getCodigoPostalIdCodigoPostal()},
                {6, usuarioRestaurante.restaurante.isActivo()},
                {7, usuarioRestaurante.restaurante.getIdRestaurante()}
            }
        );

        return true;
    }

    @Override
    public Restaurante fromResultSet(ResultSet resultSet) throws SQLException {
        return new Restaurante(
            resultSet.getInt(ID_RESTAURANTE),
            resultSet.getInt(ID_USUARIO),
            resultSet.getString(CIF),
            resultSet.getString(IBAN),
            resultSet.getString(NOMBRE_RESTAURANTE),
            resultSet.getString(NOMBRE_PROPIETARIO),
            resultSet.getInt(ID_CODIGO_POSTAL),
            resultSet.getBoolean(ACTIVO)
        );
    }
}