package backend.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import backend.DBConn;
import backend.FromResultSet;
import backend.modelo.Bebida;


public class BebidaController implements FromResultSet<Bebida> {
    private static final String TABLE = "bebida";
    public static final String ID_BEBIDA = "id_bebida";
    public static final String NOMBRE = "nombre";

    private static final String SELECT_ALL = String.format(
        "SELECT * FROM %s", TABLE
    );

    private static final String SELECT_BY_ID_BEBIDA = String.format(
        "SELECT * FROM %s WHERE %s = ?", TABLE, ID_BEBIDA
    );

    public static List<Bebida> getAll() {
        return DBConn.executeQueryIntoList(SELECT_ALL, new BebidaController());
    }

    public static Optional<Bebida> getById(int idBebida) {
        return DBConn.executeQueryWithParamsSingleValue(
            SELECT_BY_ID_BEBIDA,
            new Object[][] {
                {1, idBebida}
            },
            new BebidaController()
        );
    }

    @Override
    public Bebida fromResultSet(ResultSet resultSet) throws SQLException {
        return new Bebida(
            resultSet.getInt(ID_BEBIDA),
            resultSet.getString(NOMBRE)
        );
    }
    public static void save(Bebida bebida) {
        String sql;
        if (bebida.getIdBebida()>0) {
        sql = String.format("UPDATE %s set id_bebida=?, nombre=?",
        TABLE, ID_BEBIDA, bebida.getIdBebida());
        } else {
        sql = String.format("INSERT INTO %s  id_bebida, nombre) VALUES (?,?)",
        TABLE);
        }
        try (Connection conn = DBConn.getConn();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        Statement stmt = conn.createStatement()) {
        // (4)
        pstmt.setInt(1, bebida.getIdBebida());
        pstmt.setString(2, bebida.getNombre());
        pstmt.executeUpdate();
        if (bebida.getIdBebida()==0) {
        ResultSet rs = stmt.executeQuery("select last_insert_id()"); // (6)
        if (rs.next()) {
        bebida.setIdBebida(rs.getInt(1));
        }
        }
        } catch (Exception e) {
        System.out.println(e.getMessage());
        }
        }
}