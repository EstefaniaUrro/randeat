package backend.controller;

import java.util.List;

import backend.DBConn;

public class ClienteTarjetaController {
    private static final String TABLE = "cliente_tarjeta";
    private static final String ID_CLIENTE = "cliente_id_cliente";
    private static final String ID_TARJETA = "tarjeta_id_tarjeta";

    private static final String SELECT_BY_ID_CLIENTE = String.format(
        "SELECT * FROM %s WHERE %s = ?", TABLE, ID_CLIENTE
    );

    private static final String INSERT_CLIENTE_TARJETA = String.format(
        "INSERT INTO %s (%s, %s) VALUES (?, ?)",
        TABLE, ID_CLIENTE, ID_TARJETA

    );

    private static final String DELETE_CLIENTE_TARJETA = String.format(
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

    public static void addTarjeta(int idCliente, int idTarjeta) {
        DBConn.executeInsert(
            INSERT_CLIENTE_TARJETA,
            new Object[][] {
                {1, idCliente},
                {2, idTarjeta}
            }
        );
    }

    public static void removeTarjeta(int idCliente, int idTarjeta) {
        DBConn.executeUpdateOrDelete(
            DELETE_CLIENTE_TARJETA,
            new Object[][] {
                {1, idCliente},
                {2, idTarjeta}
            }
        );
    }
}