package backend.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import backend.DBConn;
import backend.modelo.Pedido;

public class PedidoController {
    private static final String TABLE = "usuario";
    private static final String KEY = "id_pedido";
    private static final String CLIENTE_ID_CLIENTE =
        "cliente_id_cliente"
    ;
    private static final String RESTAURANTE_ID_RESTAURANTE =
        "restaurante_id_restaurante"
    ;
    private static final String TIPO_COCINA_ID_TIPO_COCINA =
        "tipo_cocina_id_tipo_cocina"
    ;
    private static final String TIPO_ENTREGA_ID_TIPO_ENTREGA =
        "tipo_entrega_id_tipo_entrega"
    ;
    private static final String ACEPTADO = "aceptado";
    private static final String COMENTARIO = "comentario";

    private static final String SELECT_ALL_WHERE_RESTAURANTE = String.format(
        "SELECT * FROM %s WHERE restaurante_id_restaurante = ?", TABLE
    );

    private static final String SELECT_ALL_WHERE_CLIENTE = String.format(
        "SELECT * FROM %s WHERE cliente_id_cliente = ?", TABLE
    );

    public static List<Pedido> getAllFromRestaurant(
        int restauranteIdRestaurante
    ) {
        return PedidoController.get(
            restauranteIdRestaurante,
            SELECT_ALL_WHERE_RESTAURANTE
        );
    }

    public static List<Pedido> getAllFromCliente(
        int cliente_id_cliente
    ) {
        return PedidoController.get(
            cliente_id_cliente,
            SELECT_ALL_WHERE_CLIENTE
        );
    }

    private static List<Pedido> get(
        int foreign_key,
        String sql
    ) {
        List<Pedido> listaPedido = new ArrayList<>();

        try (
            Connection conn = DBConn.getConn();
            PreparedStatement statement = conn.prepareStatement(
                SELECT_ALL_WHERE_CLIENTE
            );
        ) {
            statement.setInt(1, foreign_key);
            ResultSet resultSet = statement.executeQuery(
                sql
            );

            while (resultSet.next()) {
                listaPedido.add(new Pedido(
                    resultSet.getInt(KEY),
                    resultSet.getInt(CLIENTE_ID_CLIENTE),
                    resultSet.getInt(RESTAURANTE_ID_RESTAURANTE),
                    resultSet.getInt(TIPO_COCINA_ID_TIPO_COCINA),
                    resultSet.getInt(TIPO_ENTREGA_ID_TIPO_ENTREGA),
                    resultSet.getBoolean(ACEPTADO),
                    resultSet.getString(COMENTARIO)
                ));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return listaPedido;
    }
}
