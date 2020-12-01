package com.codesplai.randeat.modelo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class Paquete {
    @JsonSerialize
    private int idPaquete;
    @JsonSerialize
    private String nombre;

    public Paquete() {

    }

    public Paquete(int idPaquete, String nombre) {
        this.idPaquete = idPaquete;
        this.nombre = nombre;
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
}
