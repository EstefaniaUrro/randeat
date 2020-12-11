package com.codesplai.randeat.modelo;

public class Cliente {
    private int idCliente;
    private int usuarioIdUsuario;
    private String nombreCompleto;
    private int codigoPostalIdCodigoPostal;

    public Cliente(
        int idCliente,
        int usuarioIdUsuario,
        String nombreCompleto,
        int codigoPostalIdCodigoPostal
    ) {
        this.idCliente = idCliente;
        this.usuarioIdUsuario = usuarioIdUsuario;
        this.nombreCompleto = nombreCompleto;
        this.codigoPostalIdCodigoPostal = codigoPostalIdCodigoPostal;
    }

    public int getIdCliente() {
        return this.idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getUsuarioIdUsuario() {
        return this.usuarioIdUsuario;
    }

    public void setUsuarioIdUsuario(int usuarioIdUsuario) {
        this.usuarioIdUsuario = usuarioIdUsuario;
    }

    public String getNombreCompleto() {
        return this.nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getCodigoPostalIdCodigoPostal() {
        return this.codigoPostalIdCodigoPostal;
    }

    public void setCodigoPostalIdCodigoPostal(int codigoPostalIdCodigoPostal) {
        this.codigoPostalIdCodigoPostal = codigoPostalIdCodigoPostal;
    }
}