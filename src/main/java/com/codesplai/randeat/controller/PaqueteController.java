package com.codesplai.randeat.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.codesplai.randeat.DBConn;
import com.codesplai.randeat.FromResultSet;
import com.codesplai.randeat.modelo.Paquete;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@RequestMapping("/paquete")
public class PaqueteController implements FromResultSet<Paquete> {
    static final String TABLE = "paquete";
    static final String ID_PAQUETE = "id_paquete";
    static final String NOMBRE = "nombre";
    static final String DESCRIPCION = "descripcion";

    private static final String SELECT_ALL = String.format(
        "SELECT * FROM %s ORDER BY %s ASC", TABLE, ID_PAQUETE
    );

    private static final String SELECT_BY_ID_PAQUETE = String.format(
        "SELECT * FROM %s WHERE %s = ?", TABLE, ID_PAQUETE
    );

    @GetMapping("/getAll")
    public static List<Paquete> getAll() {
        return DBConn.executeQueryIntoList(
            SELECT_ALL,
            new PaqueteController()
        );
    }

    @GetMapping("/getById/{idPaquete}")
    public static Optional<Paquete> getById(
        @PathVariable int idPaquete
    ) {
        return DBConn.executeQueryWithParamsSingleValue(
            SELECT_BY_ID_PAQUETE,
            new Object[][] {
                {1, idPaquete}
            },
            new PaqueteController()
        );
    }

    @Override
    public Paquete fromResultSet(ResultSet resultSet) throws SQLException {
        return new Paquete(
            resultSet.getInt(ID_PAQUETE),
            resultSet.getString(NOMBRE),
            resultSet.getString(DESCRIPCION)
        );
    }
}