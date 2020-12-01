package com.codesplai.randeat.modelo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class CodigoPostal {
    @JsonSerialize
    private int idCodigoPostal;
    @JsonSerialize
    private String numero;

    public CodigoPostal() {

    }

    public CodigoPostal(int idCodigoPostal, String numero) {
        this.idCodigoPostal = idCodigoPostal;
        this.numero = numero;
    }

    public int getIdCodigoPostal() {
        return this.idCodigoPostal;
    }

    public void setIdCodigoPostal(int idCodigoPostal) {
        this.idCodigoPostal = idCodigoPostal;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }   
}
