package backend.modelo;

public class Bebida {
    private int idBebida;
    private String nombre;

    public Bebida() {

    }

    public Bebida(int idBebida, String nombre) {
        this.idBebida = idBebida;
        this.nombre = nombre;
    }

    public int getIdBebida() {
        return this.idBebida;
    }

    public void setIdBebida(int idBebida) {
        this.idBebida = idBebida;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
