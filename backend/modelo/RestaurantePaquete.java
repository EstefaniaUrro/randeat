package backend.modelo;

public class RestaurantePaquete {
    private int RestauranteIdRestaurante;
    private int PaqueteIdPaquete;
    private double coste;

    public RestaurantePaquete() {

    }

    public RestaurantePaquete(int RestauranteIdRestaurante, int PaqueteIdPaquete, double coste) {
        this.RestauranteIdRestaurante = RestauranteIdRestaurante;
        this.PaqueteIdPaquete = PaqueteIdPaquete;
        this.coste = coste;
    }

    public int getRestauranteIdRestaurante() {
        return this.RestauranteIdRestaurante;
    }

    public void setRestauranteIdRestaurante(int RestauranteIdRestaurante) {
        this.RestauranteIdRestaurante = RestauranteIdRestaurante;
    }

    public int getPaqueteIdPaquete() {
        return this.PaqueteIdPaquete;
    }

    public void setPaqueteIdPaquete(int PaqueteIdPaquete) {
        this.PaqueteIdPaquete = PaqueteIdPaquete;
    }

    public double getCoste() {
        return this.coste;
    }

    public void setCoste(double coste) {
        this.coste = coste;
    }
}