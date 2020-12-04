package com.codesplai.randeat.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.codesplai.randeat.DBConn;
import com.codesplai.randeat.FromResultSet;
import com.codesplai.randeat.modelo.CodigoPostal;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/codigoPostal")
public class CodigoPostalController implements FromResultSet<CodigoPostal> {
    private static final String TABLE = "codigo_postal";
    private static final String ID_CODIGO_POSTAL = "id_codigo_postal";
    private static final String NUMERO = "numero";

    private static final String SELECT_ALL = String.format(
        "SELECT * FROM %s", TABLE
    );

    private static final String SELECT_BY_ID_CODIGO_POSTAL = String.format(
        "SELECT * FROM %s WHERE %s = ?", TABLE, ID_CODIGO_POSTAL
    );

    @GetMapping("/getAll")
    public static List<CodigoPostal> getAll() {
        return DBConn.executeQueryIntoList(
            SELECT_ALL,
            new CodigoPostalController()
        );
    }

    @GetMapping("/getById/{idCodigoPostal}")
    public static Optional<CodigoPostal> getById(
        @PathVariable int idCodigoPostal
    ) {
        return DBConn.executeQueryWithParamsSingleValue(
            SELECT_BY_ID_CODIGO_POSTAL,
            new Object[][] {
                {1, idCodigoPostal}
            },
            new CodigoPostalController()
        );
    }

    @Override
    public CodigoPostal fromResultSet(ResultSet resultSet) throws SQLException {
        return new CodigoPostal(
            resultSet.getInt(ID_CODIGO_POSTAL),
            resultSet.getString(NUMERO)
        );
    }
}