package backend.modelo;

public class Cliente {
    private int idCliente;
    private int usuarioIdUsuario;

    public Cliente() {

    }

    public Cliente(int idCliente, int usuarioIdUsuario) {
        this.idCliente = idCliente;
        this.usuarioIdUsuario = usuarioIdUsuario;
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
}
