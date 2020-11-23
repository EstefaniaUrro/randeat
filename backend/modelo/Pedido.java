package backend.modelo;

import java.time.LocalDateTime;
import java.util.Optional;

public class Pedido {
    private int idPedido;
    private int clienteIdCliente;
    private int restauranteIdRestaurante;
    private int tipoCocinaIdTipoCocina;
    private int tipoEntregaIdTipoEntrega;
    private Optional<String> direccionEnvio;
    private LocalDateTime fecha;
    private boolean aceptado;
    private String comentario;

    public Pedido() {

    }

    public Pedido(
        int idPedido,
        int clienteIdCliente,
        int restauranteIdRestaurante,
        int tipoCocinaIdTipoCocina,
        int tipoEntregaIdTipoEntrega,
        Optional<String> direccionEnvio,
        LocalDateTime fecha,
        boolean aceptado,
        String comentario
    ) {
        this.idPedido = idPedido;
        this.clienteIdCliente = clienteIdCliente;
        this.restauranteIdRestaurante = restauranteIdRestaurante;
        this.tipoCocinaIdTipoCocina = tipoCocinaIdTipoCocina;
        this.tipoEntregaIdTipoEntrega = tipoEntregaIdTipoEntrega;
        // TODO Comprovar que esté presente sólo cuando el tipo de entrega sea envío
        this.direccionEnvio = direccionEnvio;
        this.fecha = fecha;
        this.aceptado = aceptado;
        this.comentario = comentario;
    }

    public int getIdPedido() {
        return this.idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getClienteIdCliente() {
        return this.clienteIdCliente;
    }

    public void setClienteIdCliente(int clienteIdCliente) {
        this.clienteIdCliente = clienteIdCliente;
    }

    public int getRestauranteIdRestaurante() {
        return this.restauranteIdRestaurante;
    }

    public void setRestauranteIdRestaurante(int restauranteIdRestaurante) {
        this.restauranteIdRestaurante = restauranteIdRestaurante;
    }

    public int getTipoCocinaIdTipoCocina() {
        return this.tipoCocinaIdTipoCocina;
    }

    public void setTipoCocinaIdTipoCocina(int tipoCocinaIdTipoCocina) {
        this.tipoCocinaIdTipoCocina = tipoCocinaIdTipoCocina;
    }

    public int getTipoEntregaIdTipoEntrega() {
        return this.tipoEntregaIdTipoEntrega;
    }

    public void setTipoEntregaIdTipoEntrega(int tipoEntregaIdTipoEntrega) {
        this.tipoEntregaIdTipoEntrega = tipoEntregaIdTipoEntrega;
    }

    public Optional<String> getDireccionEnvio() {
        return this.direccionEnvio;
    }

    public void setDireccionEnvio(Optional<String> direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public LocalDateTime getFecha() {
        return this.fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public boolean isAceptado() {
        return this.aceptado;
    }

    public void setAceptado(boolean aceptado) {
        this.aceptado = aceptado;
    }

    public String getComentario() {
        return this.comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return
            this.idPedido + ": "
            + "\n\tCliente: " + this.clienteIdCliente + ", "
            + "\n\tRestaurante: " + this.restauranteIdRestaurante + ", "
            + "\n\tTipo cocina: " + this.tipoCocinaIdTipoCocina + ", "
            + "\n\tTipo entrega: " + this.tipoEntregaIdTipoEntrega + ", "
            + "\n\tDirección envío: " + this.direccionEnvio + ", "
            + "\n\tFecha: " + this.fecha + ", "
            + "\n\tAceptado: " + this.aceptado + ", "
            + "\n\tComentario: " + this.comentario
        ;
    }
}
