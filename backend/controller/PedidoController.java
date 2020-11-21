package backend.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import backend.DBConn;
import backend.FromResultSet;
import backend.modelo.Pedido;

public class PedidoController implements FromResultSet<Pedido> {
    private static final String TABLE = "pedido";
    private static final String ID_PEDIDO = "id_pedido";
    private static final String ID_CLIENTE = "cliente_id_cliente";
    private static final String ID_RESTAURANTE =
        "restaurante_id_restaurante"
    ;
    private static final String ID_TIPO_COCINA =
        "tipo_cocina_id_tipo_cocina"
    ;
    private static final String ID_TIPO_ENTREGA =
        "tipo_entrega_id_tipo_entrega"
    ;
    private static final String ACEPTADO = "aceptado";
    private static final String COMENTARIO = "comentario";

    private static final String SELECT_BY_ID_PEDIDO = String.format(
        "SELECT * FROM %s WHERE %s = ?", TABLE, ID_PEDIDO
    );

    private static final String SELECT_BY_ID_RESTAURANTE = String.format(
        "SELECT * FROM %s WHERE %s = ?", TABLE, ID_RESTAURANTE
    );

    private static final String SELECT_BY_ID_CLIENTE = String.format(
        "SELECT * FROM %s WHERE %s = ?", TABLE, ID_CLIENTE
    );

    public static Optional<Pedido> getById(int idPedido) {
        return DBConn.executeQueryWithParamsSingleValue(
            SELECT_BY_ID_PEDIDO,
            new Object[][] {
                {1, idPedido}
            },
            new PedidoController()
        );
    }
    
    public static List<Pedido> getByIdRestaurante(
        int idRestaurante
    ) {
        return DBConn.executeQueryWithParamsIntoList(
            SELECT_BY_ID_RESTAURANTE,
            new Object[][] {
                {1, idRestaurante}
            },
            new PedidoController()
        );
    }

    public static List<Pedido> getByIdCliente(
        int idCliente
    ) {
        return DBConn.executeQueryWithParamsIntoList(
            SELECT_BY_ID_CLIENTE,
            new Object[][] {
                {1, idCliente}
            },
            new PedidoController()
        );
    }

    @Override
    public Pedido fromResultSet(ResultSet resultSet) throws SQLException {
        return new Pedido(
            resultSet.getInt(ID_PEDIDO),
            resultSet.getInt(ID_CLIENTE),
            resultSet.getInt(ID_RESTAURANTE),
            resultSet.getInt(ID_TIPO_COCINA),
            resultSet.getInt(ID_TIPO_ENTREGA),
            resultSet.getBoolean(ACEPTADO),
            resultSet.getString(COMENTARIO)
        );
    }
}