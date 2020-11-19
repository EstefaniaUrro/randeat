package backend.modelo;

public class Restaurante {
    private int idRestaurante;
    private int usuarioIdUsuario;
    private String cif;
    private String iban;
    private String nombreRestaurante;
    private String nombrePropietario;


    public Restaurante() {

    }

    public Restaurante(
        int idRestaurante,
        int usuarioIdUsuario,
        String cif,
        String iban,
        String nombreRestaurante,
        String nombrePropietario
    ) {
        this.idRestaurante = idRestaurante;
        this.usuarioIdUsuario = usuarioIdUsuario;
        this.cif = cif;
        this.iban = iban;
        this.nombreRestaurante = nombreRestaurante;
        this.nombrePropietario = nombrePropietario;
    }

    public int getIdRestaurante() {
        return this.idRestaurante;
    }

    public void setIdRestaurante(int idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    public int getUsuarioIdUsuario() {
        return this.usuarioIdUsuario;
    }

    public void setUsuarioIdUsuario(int usuarioIdUsuario) {
        this.usuarioIdUsuario = usuarioIdUsuario;
    }

    public String getCif() {
        return this.cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getIban() {
        return this.iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getNombreRestaurante() {
        return this.nombreRestaurante;
    }

    public void setNombreRestaurante(String nombreRestaurante) {
        this.nombreRestaurante = nombreRestaurante;
    }

    public String getNombrePropietario() {
        return this.nombrePropietario;
    }

    public void setNombrePropietario(String nombrePropietario) {
        this.nombrePropietario = nombrePropietario;
    }
}