package backend.modelo;

public class RestaurantePaquete {
    private int restauranteIdRestaurante;
    private int paqueteIdPaquete;
    private double coste;

    // TODO Duplicado para poder listarlos más fácilmente.
    private String paqueteNombre;

    public RestaurantePaquete() {

    }

    public RestaurantePaquete(
        int restauranteIdRestaurante,
        int paqueteIdPaquete,
        double coste,
        String paqueteNombre
    ) {
        this.restauranteIdRestaurante = restauranteIdRestaurante;
        this.paqueteIdPaquete = paqueteIdPaquete;
        this.coste = coste;

        this.paqueteNombre = paqueteNombre;
    }

    public int getRestauranteIdRestaurante() {
        return this.restauranteIdRestaurante;
    }

    public void setRestauranteIdRestaurante(int restauranteIdRestaurante) {
        this.restauranteIdRestaurante = restauranteIdRestaurante;
    }

    public int getPaqueteIdPaquete() {
        return this.paqueteIdPaquete;
    }

    public void setPaqueteIdPaquete(int paqueteIdPaquete) {
        this.paqueteIdPaquete = paqueteIdPaquete;
    }

    public double getCoste() {
        return this.coste;
    }

    public void setCoste(double coste) {
        this.coste = coste;
    }

    public String getPaqueteNombre() {
        return this.paqueteNombre;
    }

    public void setPaqueteNombre(String paqueteNombre) {
        this.paqueteNombre = paqueteNombre;
    }

    @Override
    public String toString() {
        return "RestaurantePaquete:"
            + "\n\tID restaurante: " + this.restauranteIdRestaurante + ", "
            + "\n\tID paquete: " + this.paqueteIdPaquete + ", "
            + "\n\tNombre paquete: " + this.paqueteNombre + ", "
            + "\n\tCoste: " + this.coste
        ;
    }
}