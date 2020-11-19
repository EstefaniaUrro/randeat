package backend.modelo;

public class Tarjeta {
    private int idTarjeta;
    private String numero;

    public Tarjeta() {

    }

    public Tarjeta(int idTarjeta, String numero) {
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
