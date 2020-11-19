package backend.modelo;

public class Pedido {
    private int idPedido;
    private int clienteIdCliente;
    private int restauranteIdRestaurante;
    private int tipoCocinaIdTipoCocina;
    private int tipoEntregaIdTipoEntrega;
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
        boolean aceptado,
        String comentario
    ) {
        this.idPedido = idPedido;
        this.clienteIdCliente = clienteIdCliente;
        this.restauranteIdRestaurante = restauranteIdRestaurante;
        this.tipoCocinaIdTipoCocina = tipoCocinaIdTipoCocina;
        this.tipoEntregaIdTipoEntrega = tipoEntregaIdTipoEntrega;
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
}
