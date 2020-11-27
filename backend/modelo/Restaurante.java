package backend.modelo;

public class Restaurante {
    private int idRestaurante;
    private int usuarioIdUsuario;
    private String cif;
    private String iban;
    private String nombreRestaurante;
    private String nombrePropietario;
    private int codigoPostalIdCodigoPostal;
    private boolean activo;

    public Restaurante() {

    }

    public Restaurante(
        int idRestaurante,
        int usuarioIdUsuario,
        String cif,
        String iban,
        String nombreRestaurante,
        String nombrePropietario,
        int codigoPostalIdCodigoPostal,
        boolean activo
    ) {
        this.idRestaurante = idRestaurante;
        this.usuarioIdUsuario = usuarioIdUsuario;
        this.cif = cif;
        this.iban = iban;
        this.nombreRestaurante = nombreRestaurante;
        this.nombrePropietario = nombrePropietario;
        this.codigoPostalIdCodigoPostal = codigoPostalIdCodigoPostal;
        this.activo = activo;
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

    public int getCodigoPostalIdCodigoPostal() {
        return this.codigoPostalIdCodigoPostal;
    }

    public void setCodigoPostalIdCodigoPostal(int codigoPostalIdCodigoPostal) {
        this.codigoPostalIdCodigoPostal = codigoPostalIdCodigoPostal;
    }

    public boolean isActivo() {
        return this.activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    @Override
    public String toString() {
        return
            this.idRestaurante + ": "
            + "\n\tID usuario: " + this.usuarioIdUsuario + ", "
            + "\n\tCIF: " + this.cif + ", "
            + "\n\tIBAN: " + this.iban + ", "
            + "\n\tNombre restaurante: " + this.nombreRestaurante + ", "
            + "\n\tNombre propietario: " + this.nombrePropietario + ", "
            + "\n\tID código postal: " + this.codigoPostalIdCodigoPostal + ", "
            + "\n\tActivo: " + this.activo
        ;
    }
}