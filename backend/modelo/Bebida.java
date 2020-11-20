package backend.modelo;

public class Bebida {
    private int idTarjeta;
    private String numero;

    public Bebida() {

    }

    public Bebida(int idBebida, String nombre) {
        this.idTarjeta = idTarjeta;
        this.numero = numero;
    }

    public int getIdTarjeta() {
        return this.idTarjeta;
    }

    public void setIdTarjeta(int idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
