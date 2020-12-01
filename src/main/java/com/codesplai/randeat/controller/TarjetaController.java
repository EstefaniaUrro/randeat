package com.codesplai.randeat.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.codesplai.randeat.DBConn;
import com.codesplai.randeat.FromResultSet;
import com.codesplai.randeat.modelo.Tarjeta;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tarjeta")
public class TarjetaController implements FromResultSet<Tarjeta> {
    private static final String TABLE = "tarjeta";
    private static final String ID_TARJETA = "id_tarjeta";
    private static final String NUMERO = "numero";

    private static final String SELECT_BY_ID_TARJETA = String.format(
        "SELECT * FROM %s WHERE %s = ?", TABLE, ID_TARJETA
    );

    private static final String INSERT_TARJETA = String.format(
        "INSERT INTO %s (%s) VALUES (?)",
        TABLE, NUMERO
    );

    private static final String DELETE_TARJETA = String.format(
        "DELETE FROM %s WHERE %s = ?",
        TABLE, ID_TARJETA
    );

    private static final String SELECT_BY_ID_CLIENTE = String.format(
        "SELECT t.* FROM %s t"
        + " INNER JOIN %s ct ON ct.%s = t.%s"
        + " WHERE ct.%s = ?",
        TABLE,

        ClienteTarjetaController.TABLE,
        ClienteTarjetaController.ID_TARJETA,
        ID_TARJETA,

        ClienteTarjetaController.ID_CLIENTE
    );

    @GetMapping("/byId/{idTarjeta:[\\d]}")
    @ResponseBody
    public static Optional<Tarjeta> getById(@PathVariable int idTarjeta) {
        return DBConn.executeQueryWithParamsSingleValue(
            SELECT_BY_ID_TARJETA,
            new Object[][] {
                {1, idTarjeta}
            },
            new TarjetaController()
        );
    }

    @GetMapping("/byIdCliente/{idCliente:[\\d]}")
    @ResponseBody
    public static List<Tarjeta> byIdCliente(@PathVariable int idCliente) {
        return DBConn.executeQueryWithParamsIntoList(
            SELECT_BY_ID_CLIENTE,
            new Object[][] {
                {1, idCliente}
            },
            new TarjetaController()
        );
    }

    public static Optional<Integer> addTarjeta(Tarjeta tarjeta) {
        return DBConn.executeInsert(
            INSERT_TARJETA,
            new Object[][] {
                {1, tarjeta.getNumero()}
            }
        );
    }

    public static boolean removeTarjeta(int idTarjeta) {
        return DBConn.executeUpdateOrDelete(
            DELETE_TARJETA,
            new Object[][] {
                {1, idTarjeta}
            }
        );
    }

    @Override
    public Tarjeta fromResultSet(ResultSet resultSet) throws SQLException {
        return new Tarjeta(
            resultSet.getInt(ID_TARJETA),
            resultSet.getString(NUMERO)
        );
    }
}