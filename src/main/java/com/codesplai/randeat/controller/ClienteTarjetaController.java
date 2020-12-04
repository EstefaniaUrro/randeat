package com.codesplai.randeat.controller;

import java.util.List;

import com.codesplai.randeat.DBConn;

public class ClienteTarjetaController {
    static final String TABLE = "cliente_tarjeta";
    static final String ID_CLIENTE = "cliente_id_cliente";
    static final String ID_TARJETA = "tarjeta_id_tarjeta";

    private static final String SELECT_BY_ID_CLIENTE = String.format(
        "SELECT * FROM %s WHERE %s = ?", TABLE, ID_CLIENTE
    );

    private static final String INSERT = String.format(
        "INSERT INTO %s (%s, %s) VALUES (?, ?)",
        TABLE, ID_CLIENTE, ID_TARJETA

    );

    private static final String DELETE = String.format(
        "DELETE FROM %s WHERE %s = ? AND %s = ?",
        TABLE, ID_CLIENTE, ID_TARJETA
    );

    /**
     * Devuele una lista con los IDs de las taretas de un cliente.
     * @param idCliente
     * @return
     */
    public static List<Integer> getByIdCliente(int idCliente) {
        return DBConn.executeQueryGetIntIds(
            SELECT_BY_ID_CLIENTE,
            new Object[][] {
                {1, idCliente}
            },
            ID_TARJETA
        );
    }

    public static void add(int idCliente, int idTarjeta) {
        DBConn.executeInsert(
            INSERT,
            new Object[][] {
                {1, idCliente},
                {2, idTarjeta}
            }
        );
    }

    public static boolean remove(int idCliente, int idTarjeta) {
        return DBConn.executeUpdateOrDelete(
            DELETE,
            new Object[][] {
                {1, idCliente},
                {2, idTarjeta}
            }
        );
    }
}