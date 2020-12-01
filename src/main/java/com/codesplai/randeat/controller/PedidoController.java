package com.codesplai.randeat.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import com.codesplai.randeat.DBConn;
import com.codesplai.randeat.FromResultSet;
import com.codesplai.randeat.modelo.Pedido;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedido")
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
    private static final String FECHA_DATE = "fecha_date";
    private static final String FECHA_TIME = "fecha_time";
    private static final String COMENTARIO = "comentario";
    private static final String DIRECCION_ENVIO = "direccion_envio";

    private static final String SELECT_BY_ID_PEDIDO = String.format(
        "SELECT * FROM %s WHERE %s = ?", TABLE, ID_PEDIDO
    );

    private static final String SELECT_BY_ID_RESTAURANTE = String.format(
        "SELECT * FROM %s WHERE %s = ?", TABLE, ID_RESTAURANTE
    );

    private static final String SELECT_BY_ID_CLIENTE = String.format(
        "SELECT * FROM %s WHERE %s = ?", TABLE, ID_CLIENTE
    );

    private static final String INSERT = String.format(
        "INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
        TABLE,
        ID_CLIENTE, ID_RESTAURANTE, ID_TIPO_COCINA, ID_TIPO_ENTREGA, DIRECCION_ENVIO, FECHA_DATE, FECHA_TIME, COMENTARIO
    );

    @GetMapping("/getById/{idPedido}")
    public static Optional<Pedido> getById(
        @PathVariable int idPedido
    ) {
        return DBConn.executeQueryWithParamsSingleValue(
            SELECT_BY_ID_PEDIDO,
            new Object[][] {
                {1, idPedido}
            },
            new PedidoController()
        );
    }
    
    @GetMapping("/getByIdRestaurante/{idRestaurante}")
    public static List<Pedido> getByIdRestaurante(
        @PathVariable int idRestaurante
    ) {
        return DBConn.executeQueryWithParamsIntoList(
            SELECT_BY_ID_RESTAURANTE,
            new Object[][] {
                {1, idRestaurante}
            },
            new PedidoController()
        );
    }

    @GetMapping("/getByIdCliente/{idCliente}")
    public static List<Pedido> getByIdCliente(
        @PathVariable int idCliente
    ) {
        return DBConn.executeQueryWithParamsIntoList(
            SELECT_BY_ID_CLIENTE,
            new Object[][] {
                {1, idCliente}
            },
            new PedidoController()
        );
    }

    public static Optional<Integer> add(Pedido pedido) {
        Optional<String> direccionEnvio = pedido.getDireccionEnvio();
        String direccionEnvioQuizaNull = direccionEnvio.isEmpty() ?
            null
            :
            direccionEnvio.get()
        ;

        return DBConn.executeInsert(
            INSERT,
            new Object[][] {
                {1, pedido.getClienteIdCliente()},
                {2, pedido.getRestauranteIdRestaurante()},
                {3, pedido.getTipoCocinaIdTipoCocina()},
                {4, pedido.getTipoEntregaIdTipoEntrega()},
                {5, direccionEnvioQuizaNull},
                {6, pedido.getFecha().toLocalDate()},
                {7, pedido.getFecha().toLocalTime()},
                {8, pedido.getComentario()}
            }
        );
    }

    @Override
    public Pedido fromResultSet(ResultSet resultSet) throws SQLException {
        String direccionEnvioQuizaNull = resultSet.getString(DIRECCION_ENVIO);
        Optional<String> direccionEnvio = direccionEnvioQuizaNull == null ?
            Optional.empty()
            :
            Optional.of(direccionEnvioQuizaNull)
        ;

        // Time y date están en columnas distintas. Las obtengo por separado y luego las uso para crear el LocalDateTime que almacenaré en Pedido.
        LocalTime time = resultSet.getTime(FECHA_TIME).toLocalTime();
        LocalDate date = resultSet.getDate(FECHA_DATE).toLocalDate();

        return new Pedido(
            resultSet.getInt(ID_PEDIDO),
            resultSet.getInt(ID_CLIENTE),
            resultSet.getInt(ID_RESTAURANTE),
            resultSet.getInt(ID_TIPO_COCINA),
            resultSet.getInt(ID_TIPO_ENTREGA),
            direccionEnvio,
            LocalDateTime.of(date, time),
            resultSet.getString(COMENTARIO)
        );
    }
}