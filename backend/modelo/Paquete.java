package backend.modelo;

public class Paquete {
    private int idPaquete;
    private String nombre;

    public Paquete() {

    }

    public Paquete(int idPaquete, String nombre) {
        this.idPaquete = idPaquete;
        this.nombre = nombre;
    }

    public int getIdPaquete() {
        return this.idPaquete;
    }

    public void setIdBebida(int idPaquete) {
        this.idPaquete = idPaquete;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
