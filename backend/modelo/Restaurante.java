package backend.modelo;

public class Restaurante {
    private int idRestaurante;
    private String iban;

    private int usuarioIdUsuario;

    public Restaurante() {

    }

    public Restaurante(int idRestaurante, String iban, int usuarioIdUsuario) {
        this.idRestaurante = idRestaurante;
        this.iban = iban;
        this.usuarioIdUsuario = usuarioIdUsuario;
    }

    public int getIdRestaurante() {
        return this.idRestaurante;
    }

    public void setIdRestaurante(int idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    public String getIban() {
        return this.iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public int getUsuarioIdUsuario() {
        return this.usuarioIdUsuario;
    }

    public void setUsuarioIdUsuario(int usuarioIdUsuario) {
        this.usuarioIdUsuario = usuarioIdUsuario;
    }
}
