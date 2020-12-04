package com.codesplai.randeat.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.codesplai.randeat.DBConn;
import com.codesplai.randeat.FromResultSet;
import com.codesplai.randeat.modelo.TipoCocina;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/tipoCocina")
public class TipoCocinaController implements FromResultSet<TipoCocina> {
    private static final String TABLE = "tipo_cocina";
    private static final String ID_TIPO_COCINA = "id_tipo_cocina";
    private static final String NOMBRE = "nombre";

    private static final String SELECT_ALL = String.format(
        "SELECT * FROM %s", TABLE
    );

    private static final String SELECT_BY_ID_TIPO_COCINA = String.format(
        "SELECT * FROM %s WHERE %s = ?", TABLE, ID_TIPO_COCINA
    );

    // Tipos de cocina de los restaurantes activos (1) en un código postal (2) y un tipo de entrega (3) determinados.
    private static final String SELECT_TIPO_COCINA_FILTER = String.format(
        "SELECT DISTINCT tc.* FROM %s r"
        + " INNER JOIN %s rte ON rte.%s = r.%s"
        + " INNER JOIN %s rtc ON rtc.%s = r.%s"
        + " INNER JOIN %s tc ON tc.%s = rtc.%s"
        + " WHERE r.%s = 1" // (1)
        + " AND r.%s = ?" // (2)
        + " AND rte.%s = ?", // (3)
        RestauranteController.TABLE,

        RestauranteTipoEntregaController.TABLE,
        RestauranteTipoEntregaController.ID_RESTAURANTE,
        RestauranteController.ID_RESTAURANTE,

        RestauranteTipoCocinaController.TABLE,
        RestauranteTipoCocinaController.ID_RESTAURANTE,
        RestauranteController.ID_RESTAURANTE,

        TABLE,
        ID_TIPO_COCINA,
        RestauranteTipoCocinaController.ID_TIPO_COCINA,

        RestauranteController.ACTIVO,
        RestauranteController.ID_CODIGO_POSTAL,
        RestauranteTipoEntregaController.ID_TIPO_ENTREGA
    );

    private static final String INSERT = String.format(
        "INSERT INTO %s (%s) VALUES (?)",TABLE, NOMBRE
    );

    private static final String UPDATE = String.format(
        "UPDATE %s SET %s=? WHERE %s=?", TABLE, NOMBRE, ID_TIPO_COCINA
    );
    
    @GetMapping("/getAll")
    public static List<TipoCocina> getAll() {
        return DBConn.executeQueryIntoList(
            SELECT_ALL,
            new TipoCocinaController()
        );
    }

    @GetMapping("/getById/{idTipoCocina}")
    public static Optional<TipoCocina> getById(
        @PathVariable int idTipoCocina
    ) {
        return DBConn.executeQueryWithParamsSingleValue(
            SELECT_BY_ID_TIPO_COCINA,
            new Object[][] {
                {1, idTipoCocina}
            },
            new TipoCocinaController()
        );
    }

    @GetMapping("/getInFilter")
    public static List<TipoCocina> getInFilter(
        @RequestParam int idCodigoPostal,
        @RequestParam int idTipoEntrega
    ) {
        return DBConn.executeQueryWithParamsIntoList(
            SELECT_TIPO_COCINA_FILTER,
            new Object[][] {
                {1, idCodigoPostal},
                {2, idTipoEntrega}
            },
            new TipoCocinaController()
        );
    }

    public static Optional<Integer> add(TipoCocina tipoCocina) {
        return DBConn.executeInsert(
            INSERT, 
            new Object[][]{
                {1, tipoCocina.getNombre()}
            }
        );
    }

    public static Optional<Integer> update(TipoCocina tipoCocina) {
        DBConn.executeUpdateOrDelete(
            UPDATE,
            new Object[][]{
                {1, tipoCocina.getNombre()},
                {2, tipoCocina.getIdTipoCocina()}
            }
        );

        return Optional.of(tipoCocina.getIdTipoCocina());
    }

    @Override
    public TipoCocina fromResultSet(ResultSet resultSet) throws SQLException {
        return new TipoCocina(
            resultSet.getInt(ID_TIPO_COCINA),
            resultSet.getString(NOMBRE)
        );
    }
}