package com.codesplai.randeat.controller;

import java.util.List;
import java.util.Optional;

import com.codesplai.randeat.DBConn;

public class RestauranteBebidaController {
    private static final String TABLE = "restaurante_bebida";
    private static final String ID_RESTAURANTE = "restaurante_id_restaurante";
    private static final String ID_BEBIDA = "bebida_id_bebida";

    private static final String SELECT_BY_ID_RESTAURANTE = String.format(
        "SELECT %s FROM %s WHERE %s = ?", ID_BEBIDA, TABLE, ID_RESTAURANTE
    );

    private static final String INSERT = String.format(
        "INSERT INTO %s (%s, %s) VALUES (?, ?)",
        TABLE, ID_RESTAURANTE, ID_BEBIDA
    );

    private static final String DELETE = String.format(
        "DELETE FROM %s WHERE %s = ? AND %s = ?",
        TABLE, ID_RESTAURANTE, ID_BEBIDA
    );

    public static List<Integer> getByIdRestaurante(int idRestaurante) {
        return DBConn.executeQueryGetIntIds(
            SELECT_BY_ID_RESTAURANTE,
            new Object[][] {
                {1, idRestaurante}
            },
            ID_BEBIDA
        );
    }

    public static Optional<Integer> add(int idRestaurante, int idBebida) {
        return DBConn.executeInsert(
            INSERT,
            new Object[][] {
                {1, idRestaurante},
                {2, idBebida}
            }
        );
    }

    public static boolean remove(int idRestaurante, int idBebida) {
        return DBConn.executeUpdateOrDelete(
            DELETE,
            new Object[][] {
                {1, idRestaurante},
                {2, idBebida}
            }
        );
    }
}