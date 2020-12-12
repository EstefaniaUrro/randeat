package com.codesplai.randeat.modelo;

public class TipoEntrega {
    private int idTipoEntrega;
    private String nombre;
    private String descripcion;

    public TipoEntrega(int idTipoEntrega, String nombre, String descripcion) {
        this.idTipoEntrega = idTipoEntrega;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getIdTipoEntrega() {
        return this.idTipoEntrega;
    }

    public void setIdTipoEntrega(int idTipoEntrega) {
        this.idTipoEntrega = idTipoEntrega;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}