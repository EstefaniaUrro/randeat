package backend.modelo;

public class RestaurantePaquete {
    private int restauranteIdRestaurante;
    private int paqueteIdPaquete;
    private double coste;

    public RestaurantePaquete() {

    }

    public RestaurantePaquete(int restauranteIdRestaurante, int paqueteIdPaquete, double coste) {
        this.restauranteIdRestaurante = restauranteIdRestaurante;
        this.paqueteIdPaquete = paqueteIdPaquete;
        this.coste = coste;
    }

    public int getRestauranteIdRestaurante() {
        return this.restauranteIdRestaurante;
    }

    public void setRestauranteIdRestaurante(int RestauranteIdRestaurante) {
        this.restauranteIdRestaurante = RestauranteIdRestaurante;
    }

    public int getPaqueteIdPaquete() {
        return this.paqueteIdPaquete;
    }

    public void setPaqueteIdPaquete(int PaqueteIdPaquete) {
        this.paqueteIdPaquete = PaqueteIdPaquete;
    }

    public double getCoste() {
        return this.coste;
    }

    public void setCoste(double coste) {
        this.coste = coste;
    }
}