package com.codesplai.randeat.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.codesplai.randeat.DBConn;
import com.codesplai.randeat.FromResultSet;
import com.codesplai.randeat.modelo.Restaurante;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
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

    private static final String INSERT = String.format(
        "INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?, 0)",
        TABLE, ID_USUARIO, CIF, IBAN, NOMBRE_RESTAURANTE, NOMBRE_PROPIETARIO, ID_CODIGO_POSTAL, ACTIVO
    );

    private static final String UPDATE = String.format(
        "UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ? WHERE %s = ?",
        TABLE, CIF, IBAN, NOMBRE_RESTAURANTE, NOMBRE_PROPIETARIO, ID_CODIGO_POSTAL, ACTIVO, ID_RESTAURANTE
    );

    // Restaurantes activos (1) en un código postal concreto (2), con un tipo entrega (3) y un tipo cocina (4) determinados, .
    private static final String SELECT_FILTER = String.format(
        "SELECT r.* FROM %s r"
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
    @GetMapping("/getInFilter/{idCodigoPostal}/{idTipoEntrega}/{idTipoCocina}")
    public static List<Restaurante> getInFilter(
        @PathVariable int idCodigoPostal,
        @PathVariable int idTipoEntrega,
        @PathVariable int idTipoCocina
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

    public static Optional<Integer> save(Restaurante restaurante) {
        if (restaurante.getIdRestaurante() == 0) {
            return RestauranteController.add(restaurante);
        } else {
            return RestauranteController.update(restaurante);
        }
    }

    public static Optional<Integer> add(Restaurante restaurante) {
        return DBConn.executeInsert(
            INSERT,
            new Object[][] {
                {1, restaurante.getUsuarioIdUsuario()},
                {2, restaurante.getCif()},
                {3, restaurante.getIban()},
                {4, restaurante.getNombreRestaurante()},
                {5, restaurante.getNombrePropietario()},
                {6, restaurante.getCodigoPostalIdCodigoPostal()}
            }
        );
    }

    public static Optional<Integer> update(Restaurante restaurante) {
        DBConn.executeUpdateOrDelete(
            UPDATE,
            new Object[][] {
                {1, restaurante.getCif()},
                {2, restaurante.getIban()},
                {3, restaurante.getNombreRestaurante()},
                {4, restaurante.getNombrePropietario()},
                {5, restaurante.getCodigoPostalIdCodigoPostal()},
                {6, restaurante.isActivo()},
                {7, restaurante.getIdRestaurante()}
            }
        );

        return Optional.of(restaurante.getIdRestaurante());
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