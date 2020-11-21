package backend.modelo;

public class TipoEntrega {
    private int idTipoEntrega;
    private String nombre;

    public TipoEntrega() {

    }

    public TipoEntrega(int idTipoEntrega, String nombre) {
        this.idTipoEntrega = idTipoEntrega;
        this.nombre = nombre;
    }

    public int getIdTipoEntrega() {
        return this.idTipoEntrega;
    }

    public void setIdTipoEntrega(int idTipoEntrega) {
        this.idTipoEntrega = idTipoEntrega;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}