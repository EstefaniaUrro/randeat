package backend.modelo;

public class TipoCocina {
    private int idTipoCocina;
    private String nombre;

    public TipoCocina() {

    }

    public TipoCocina(int idTipoCocina, String nombre) {
        this.idTipoCocina = idTipoCocina;
        this.nombre = nombre;
    }
    
    public int getIdTipoCocina() {
        return this.idTipoCocina;
    }

    public void setIdTipoCocina(int idTipoCocina) {
        this.idTipoCocina = idTipoCocina;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return
            "TipoCocina " + this.idTipoCocina + ": "
            + "\n\tNombre: " + this.nombre
        ;
    }
}