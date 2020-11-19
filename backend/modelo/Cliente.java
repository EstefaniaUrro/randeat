package backend.modelo;

public class Cliente {
    private int idCliente;
    private int usuarioIdUsuario;
    private String nombreCompleto;

    public Cliente() {

    }

    public Cliente(int idCliente, int usuarioIdUsuario, String nombreCompleto) {
        this.idCliente = idCliente;
        this.usuarioIdUsuario = usuarioIdUsuario;
        this.nombreCompleto = nombreCompleto;
    }

    public int getIdCliente() {
        return this.idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getUsuarioIdUsuario() {
        return this.usuarioIdUsuario;
    }

    public void setUsuarioIdUsuario(int usuarioIdUsuario) {
        this.usuarioIdUsuario = usuarioIdUsuario;
    }

    public String getNombreCompleto() {
        return this.nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
}