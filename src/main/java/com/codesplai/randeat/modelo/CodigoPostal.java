package com.codesplai.randeat.modelo;

public class CodigoPostal {
    private int idCodigoPostal;
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
