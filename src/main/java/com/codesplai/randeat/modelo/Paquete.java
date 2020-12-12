package com.codesplai.randeat.modelo;

public class Paquete {
    private int idPaquete;
    private String nombre;
    private String descripcion;

    public Paquete(int idPaquete, String nombre, String descripcion) {
        this.idPaquete = idPaquete;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getIdPaquete() {
        return this.idPaquete;
    }

    public void setIdBebida(int idPaquete) {
        this.idPaquete = idPaquete;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdPaquete(int idPaquete) {
        this.idPaquete = idPaquete;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
