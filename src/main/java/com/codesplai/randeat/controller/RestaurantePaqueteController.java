package com.codesplai.randeat.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.codesplai.randeat.DBConn;
import com.codesplai.randeat.FromResultSet;
import com.codesplai.randeat.modelo.RestaurantePaquete;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/restaurantePaquete")
public class RestaurantePaqueteController implements FromResultSet<RestaurantePaquete> {
    private static final String TABLE = "restaurante_paquete";
    private static final String ID_RESTAURANTE = "restaurante_id_restaurante";
    private static final String ID_PAQUETE = "paquete_id_paquete";
    private static final String COSTE = "coste";

    private static final String SELECT_BY_ID_RESTAURANTE = String.format(
        "SELECT rp.*, p.%s FROM %s r"
        + " INNER JOIN %s rp ON rp.%s = r.%s"
        + " INNER JOIN %s p ON p.%s = rp.%s"
        + " WHERE r.%s = ?"
        + " ORDER BY %s ASC",
        PaqueteController.NOMBRE,
        RestauranteController.TABLE,

        TABLE,
        ID_RESTAURANTE,
        RestauranteController.ID_RESTAURANTE,

        PaqueteController.TABLE,
        PaqueteController.ID_PAQUETE,
        ID_PAQUETE,

        RestauranteController.ID_RESTAURANTE,

        ID_PAQUETE
    );

    private static final String INSERT = String.format(
        "INSERT INTO %s (%s, %s, %s) VALUES (?, ?, ?)",
        TABLE,

        ID_RESTAURANTE,
        ID_PAQUETE,
        COSTE
    );

    private static final String DELETE_BY_ID_RESTAURANTE = String.format(
        "DELETE FROM %s WHERE %s = ?", TABLE, ID_RESTAURANTE
    );

    @GetMapping("/getByIdRestaurante/{idRestaurante}")
    public static List<RestaurantePaquete> getByIdRestaurante(
        @PathVariable int idRestaurante
    ) {
        return DBConn.executeQueryWithParamsIntoList(
            SELECT_BY_ID_RESTAURANTE,
            new Object[][] {
                {1, idRestaurante}
            },
            new RestaurantePaqueteController()
        );
    }

    @PostMapping("/add")
    public static void add(
        @RequestParam int idRestaurante,
        @RequestParam int idPaquete,
        @RequestParam double coste
    ) {
        DBConn.executeInsert(
            INSERT,
            new Object[][] {
                {1, idRestaurante},
                {2, idPaquete},
                {3, coste},
            }
        );
    }

    public static boolean removeByIdRestaurante(int idRestaurante) {
        return DBConn.executeUpdateOrDelete(
            DELETE_BY_ID_RESTAURANTE,
            new Object[][] {
                {1, idRestaurante}
            }
        );
    }

    @Override
    public RestaurantePaquete fromResultSet(ResultSet resultSet) throws SQLException {
        return new RestaurantePaquete(
            resultSet.getInt(ID_RESTAURANTE),
            resultSet.getInt(ID_PAQUETE),
            resultSet.getDouble(COSTE),
            resultSet.getString(PaqueteController.NOMBRE)
        );
    }
}