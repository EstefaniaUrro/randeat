package backend.test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import backend.controller.BebidaController;
import backend.controller.ClienteController;
import backend.controller.ClienteTarjetaController;
import backend.controller.CodigoPostalController;
import backend.controller.PedidoController;
import backend.controller.RestauranteBebidaController;
import backend.controller.RestauranteController;
import backend.controller.RestauranteTipoCocinaController;
import backend.controller.RestauranteTipoEntregaController;
import backend.controller.TarjetaController;
import backend.controller.TipoCocinaController;
import backend.controller.TipoEntregaController;
import backend.controller.UsuarioController;
import backend.modelo.Bebida;
import backend.modelo.Cliente;
import backend.modelo.CodigoPostal;
import backend.modelo.Pedido;
import backend.modelo.Restaurante;
import backend.modelo.Tarjeta;
import backend.modelo.TipoCocina;
import backend.modelo.TipoEntrega;
import backend.modelo.Usuario;

public class Test {
    private static void listTipoCocina() {
        for (TipoCocina tipoCocina : TipoCocinaController.getAll()) {
            System.out.println(
                tipoCocina.getIdTipoCocina() + ": "
                + tipoCocina.getNombre()
            );
        }
    }

    private static void listTipoEntrega() {
        for (TipoEntrega tipoEntrega : TipoEntregaController.getAll()) {
            System.out.println(
                tipoEntrega.getIdTipoEntrega() + ": "
                + tipoEntrega.getNombre()
            );
        }
    }

    private static void listUsuario() {
        for (Usuario usuario : UsuarioController.getAll()) {
            System.out.println(
                usuario.getIdUsuario() + ": "
                + usuario.getCorreoElectronico() + ", "
                + usuario.getContrasena() + ", "
                + usuario.getTelefono() + ", "
                + usuario.getPoblacion() + ", "
                + usuario.getDireccion()
            );
        }
    }

    private static void listCliente() {
        for (Cliente cliente : ClienteController.getAll()) {
            System.out.println(
                cliente.getIdCliente() + ": "
                + cliente.getUsuarioIdUsuario() + ", "
                + cliente.getNombreCompleto()
            );
        }
    }

    private static void listRestaurante() {
        for (Restaurante restaurante : RestauranteController.getActivos()) {
            System.out.println(restaurante);

            System.out.println(String.format(
                "El código postal %d es el '%s'",
                restaurante.getCodigoPostalIdCodigoPostal(),
                CodigoPostalController.getById(restaurante.getCodigoPostalIdCodigoPostal()).get().getNumero()
            ));
        }
    }

    private static void listTarjetasCliente() {
        for (Cliente cliente : ClienteController.getAll()) {
            System.out.println("Tarjetas de " + cliente.getNombreCompleto() + ":");
            List<Integer> idsTarjetaCliente = ClienteTarjetaController
                .getByIdCliente(cliente.getIdCliente())
            ;

            List<Tarjeta> tarjetas = TarjetaController.getMultById(
                idsTarjetaCliente
            );

            for (Tarjeta tarjeta : tarjetas) {
                System.out.println(tarjeta);
            }
        }
    }

    private static void listRestauranteCodigosPostales(
        List<Integer> idsCodigoPostal
    ) {
        System.out.println("Restaurantes en códigos postales " + idsCodigoPostal + ":");

        List<Restaurante> restaurantes = RestauranteController
            .getActivosByIdCodigoPostal(idsCodigoPostal)
        ;

        for (Restaurante restaurante : restaurantes) {
            System.out.println(restaurante);
        }
    }

    private static void listRestauranteIdsTipoCocina(int idTipoCocina) {
        List<Integer> idsRestauranteTipoCocina = RestauranteTipoCocinaController
            .getIdsRestauranteByIdTipoCocina(idTipoCocina)
        ;

        TipoCocina tipoCocina = TipoCocinaController
            .getById(idTipoCocina)
            .get()
        ;

        System.out.println(String.format(
            "Restaurantes con tipo_cocina %d (%s)",
            idTipoCocina,
            tipoCocina.getNombre()
        ));
        for (int idRestaurante : idsRestauranteTipoCocina) {
            Restaurante restaurante = RestauranteController
                .getById(idRestaurante)
                .get()
            ;
            System.out.println(restaurante);
        }
    }

    private static void listTipoCocinaIdRestaurante(int idRestaurante) {
        Restaurante restaurante = RestauranteController
            .getById(idRestaurante)
            .get()
        ;

        System.out.println(String.format(
            "Tipos de cocina del restaurante %d (%s)",
            idRestaurante,
            restaurante.getNombreRestaurante()
        ));

        List<Integer> idsTipoCocina = RestauranteTipoCocinaController
            .getIdsTipoCocinaByIdRestaurante(idRestaurante)
        ;

        for (int idTipoCocina : idsTipoCocina) {
            TipoCocina tipoCocina = TipoCocinaController
                .getById(idTipoCocina)
                .get()
            ;
            System.out.println(tipoCocina);
        }
    }

    private static void listRestauranteIdTipoEntrega(int idTipoEntrega) {
        String tipoEntregaNombre = TipoEntregaController
            .getById(idTipoEntrega)
            .get()
            .getNombre()
        ;
        System.out.println(String.format(
            "Restaurantes con tipo entrega %d (%s):",
            idTipoEntrega,
            tipoEntregaNombre
        ));

        List<Integer> idsRestaurante = RestauranteTipoEntregaController
            .getIdsRestauranteByIdTipoEntrega(idTipoEntrega)
        ;
        for (int idRestaurante : idsRestaurante) {
            Restaurante restaurante = RestauranteController
                .getById(idRestaurante)
                .get()
            ;

            System.out.println("\t- " + restaurante);
        }
    }

    private static void listTiposEntregaIdRestaurante(int idRestaurante) {
        Restaurante restaurante = RestauranteController
            .getById(idRestaurante)
            .get()
        ;

        System.out.println(String.format(
            "Tipos de entrega del restaurante %d (%s):",
            idRestaurante,
            restaurante.getNombreRestaurante()
        ));

        List<Integer> idsTipoEntrega = RestauranteTipoEntregaController
            .getIdsTipoEntregaByIdRestaurante(idRestaurante)
        ;

        for (int idTipoEntrega : idsTipoEntrega) {
            String tipoEntregaNombre = TipoEntregaController
                .getById(idTipoEntrega)
                .get()
                .getNombre()
            ;
            System.out.println("\t- " + tipoEntregaNombre);
        }
    }

    private static void listBebidasFromRestaurante(int idRestaurante) {
        Restaurante restaurante = RestauranteController
            .getById(idRestaurante)
            .get()
        ;

        System.out.println(String.format(
            "El restaurante %d (%s) ofrece las siguientes bebidas:",
            idRestaurante,
            restaurante.getNombreRestaurante()
        ));

        List<Integer> idsBebida = RestauranteBebidaController
            .getByIdRestaurante(idRestaurante)
        ;
        for (int idBebida : idsBebida) {
            Bebida bebida = BebidaController
                .getById(idBebida)
                .get()
            ;
            System.out.println(bebida);
        }
    }

    private static void listPedidosCliente(int idCliente) {
        Cliente cliente = ClienteController
            .getById(idCliente)
            .get()
        ;

        System.out.println(String.format(
            "Pedidos del cliente %d (%s)",
            idCliente,
            cliente.getNombreCompleto()
        ));

        List<Pedido> pedidosCliente = PedidoController
            .getByIdCliente(idCliente)
        ;

        for (Pedido pedido : pedidosCliente) {
            System.out.println(pedido);
        }
    }

    private static void listPedidosRestaurante(int idRestaurante) {
        Restaurante restaurante = RestauranteController
            .getById(idRestaurante)
            .get()
        ;

        System.out.println(String.format(
            "Pedidos del restaurante %d (%s)",
            idRestaurante,
            restaurante.getNombreRestaurante()
        ));

        List<Pedido> pedidosRestaurante = PedidoController
            .getByIdRestaurante(idRestaurante)
        ;

        for (Pedido pedido : pedidosRestaurante) {
            System.out.println(pedido);
        }
    }

    private static void addBebidaToRestaurante(int idBebida, int idRestaurante) {
        Bebida bebida = BebidaController
            .getById(idBebida)
            .get()
        ;
        Restaurante restaurante = RestauranteController
            .getById(idRestaurante)
            .get()
        ;

        System.out.println(String.format(
            "Añadiendo bebiba %s al restaurante %s",
            bebida.getNombre(), restaurante.getNombreRestaurante()
        ));

        RestauranteBebidaController
            .add(idRestaurante, idBebida)
        ;
    }

    private static void insertPedido() {
        Pedido pedido = new Pedido(3, 1, 1, 1, 2, Optional.empty(), LocalDateTime.now(), "asd");

        PedidoController.add(pedido);
    }

    public static void main(String[] args) {
        System.out.println("Tipos cocina:");
        Test.listTipoCocina();

        System.out.println("\nTipos entrega:");
        Test.listTipoEntrega();

        System.out.println("\nTodos los usuarios:");
        Test.listUsuario();

        System.out.println("\nAhora la info de los restaurantes:");
        Test.listRestaurante();

        System.out.println("\nY ahora la de los clientes:");
        Test.listCliente();

        System.out.println("\nLas tarjetas de cada cliente:");
        Test.listTarjetasCliente();

        System.out.println();
        Test.listRestauranteCodigosPostales(Arrays.asList(1));
        Test.listRestauranteCodigosPostales(Arrays.asList(1, 2));
        Test.listRestauranteCodigosPostales(Arrays.asList());

        System.out.println();
        Test.listRestauranteIdsTipoCocina(1);

        System.out.println();
        Test.listTipoCocinaIdRestaurante(1);
        Test.listTipoCocinaIdRestaurante(2);
        Test.listTipoCocinaIdRestaurante(3);

        System.out.println();
        Test.listRestauranteIdTipoEntrega(1);
        Test.listRestauranteIdTipoEntrega(2);

        System.out.println();
        Test.listTiposEntregaIdRestaurante(1);
        Test.listTiposEntregaIdRestaurante(2);
        Test.listTiposEntregaIdRestaurante(3);

        System.out.println();
        Test.listBebidasFromRestaurante(1);
        Test.listBebidasFromRestaurante(2);
        Test.listBebidasFromRestaurante(3);

        // Pedidos
        System.out.println("\nPEDIDOS\n");

        Test.listPedidosCliente(1);
        System.out.println();
        Test.listPedidosRestaurante(1);

        System.out.println("\n### UPDATES");
        
        System.out.println();
        Test.addBebidaToRestaurante(4, 1);
        Test.listBebidasFromRestaurante(1);

        Test.insertPedido();

        Test.ponerRestauranteActivo(1);
        Test.ponerRestauranteActivo(2);
        Test.ponerRestauranteActivo(3);

        Test.listTipoCocinaFilter(1, 2);

        Test.listRestauranteFilter(1, 2, 1);
    }

    private static final void listTipoCocinaFilter(int idCodigoPostal, int idTipoEntrega) {
        CodigoPostal codigoPostal = CodigoPostalController
            .getById(idCodigoPostal)
            .get()
        ;

        TipoEntrega tipoEntrega = TipoEntregaController
            .getById(idTipoEntrega)
            .get()
        ;

        System.out.println(String.format(
            "Lista de tipos de cocina en el código postal %s y con tipo entrega %s",
            codigoPostal.getNumero(), tipoEntrega.getNombre()
        ));

        List<TipoCocina> listTipoCocina = TipoCocinaController
            .getInFilter(idCodigoPostal, idTipoEntrega)
        ;

        for (TipoCocina tipoCocina : listTipoCocina) {
            System.out.println(tipoCocina);
        }
    }

    private static final void listRestauranteFilter(int idCodigoPostal, int idTipoEntrega, int idTipoCocina) {
        CodigoPostal codigoPostal = CodigoPostalController
            .getById(idCodigoPostal)
            .get()
        ;

        TipoEntrega tipoEntrega = TipoEntregaController
            .getById(idTipoEntrega)
            .get()
        ;

        TipoCocina tipoCocina = TipoCocinaController
            .getById(idTipoCocina)
            .get()
        ;

        System.out.println(String.format(
            "Lista de restaurantes en el código postal %s, con tipo entrega %s y tipo cocina %s",
            codigoPostal.getNumero(),
            tipoEntrega.getNombre(),
            tipoCocina.getNombre()
        ));

        List<Restaurante> listRestaurante = RestauranteController
            .getInFilter(idCodigoPostal, idTipoEntrega, idTipoCocina)
        ;

        for (Restaurante restaurante : listRestaurante) {
            System.out.println(restaurante);
        }
    }

    private static final void ponerRestauranteActivo(int idRestaurante) {
        Restaurante restaurante = RestauranteController
            .getById(idRestaurante)
            .get()
        ;

        restaurante.setActivo(true);

        RestauranteController.save(restaurante);
    }
}