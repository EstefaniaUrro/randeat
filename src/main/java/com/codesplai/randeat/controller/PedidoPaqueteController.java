package com.codesplai.randeat.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.codesplai.randeat.DBConn;
import com.codesplai.randeat.FromResultSet;
import com.codesplai.randeat.modelo.PedidoPaquete;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("pedidoPaquete")
public class PedidoPaqueteController implements FromResultSet<PedidoPaquete> {
	private static final String TABLE = "pedido_paquete";
	private static final String ID_PEDIDO = "pedido_id_pedido";
	private static final String ID_PAQUETE = "paquete_id_paquete";
	private static final String CANTIDAD = "cantidad";

	private static final String SELECT_BY_ID_PEDIDO = String.format(
		"SELECT * FROM %s WHERE %s = ?",
		TABLE, ID_PEDIDO
	);

	private static final String INSERT_PEDIDO_PAQUETE = String.format(
		"INSERT INTO %s (%s, %s, %s) VALUES (?, ?, ?)",
		TABLE, ID_PEDIDO, ID_PAQUETE, CANTIDAD
	);

	@GetMapping("/getByIdPedido/{idPedido}")
	public static List<PedidoPaquete> getByIdPedido(
		@PathVariable int idPedido
	) {
		return DBConn.executeQueryWithParamsIntoList(
			SELECT_BY_ID_PEDIDO,
			new Object[][] {
				{1, idPedido}
			},
			new PedidoPaqueteController()
		);
	}

	public static boolean addPedidoPaquete(
		int idPedido,
		int idPaquete,
		int cantidad
	) {
		DBConn.executeInsert(
			INSERT_PEDIDO_PAQUETE,
			new Object[][] {
				{1, idPedido},
				{2, idPaquete},
				{3, cantidad}
			}
		);

		return true;
	}

	@Override
	public PedidoPaquete fromResultSet(ResultSet resultSet) throws SQLException {
		return new PedidoPaquete(
			resultSet.getInt(ID_PEDIDO),
			resultSet.getInt(ID_PAQUETE),
			resultSet.getInt(CANTIDAD)
		);
	}
}