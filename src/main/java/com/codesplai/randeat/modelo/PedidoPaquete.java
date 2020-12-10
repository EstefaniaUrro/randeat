package com.codesplai.randeat.modelo;

public class PedidoPaquete {
	private int idPedido;
	private int idPaquete;
	private int cantidad;

	public PedidoPaquete(int idPedido, int idPaquete, int cantidad) {
		this.idPedido = idPedido;
		this.idPaquete = idPaquete;
		this.cantidad = cantidad;
	}

	public int getIdPedido() {
		return this.idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public int getIdPaquete() {
		return this.idPaquete;
	}

	public void setIdPaquete(int idPaquete) {
		this.idPaquete = idPaquete;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	
}
