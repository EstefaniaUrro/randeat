package backend.test;

import java.util.Arrays;
import java.util.List;

import backend.controller.BebidaController;
import backend.controller.ClienteController;
import backend.controller.CodigoPostalController;
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
        for (Restaurante restaurante : RestauranteController.getAll()) {
            System.out.println(restaurante);

            System.out.println(String.format(
                "El código postal %d es el '%s'",
                restaurante.getCodigoPostalIdCodigoPostal(),
                CodigoPostalController.getById(restaurante.getCodigoPostalIdCodigoPostal()).get().getNumero()
            ));
        }
    }

    // private static void listTarjetasCliente() {
    //     for (Cliente cliente : ClienteController.getAll()) {
    //         System.out.println(cliente.getNombreCompleto() + ":");
    //         List<Tarjeta> tarjetas = TarjetaController.getById(
    //             cliente.getIdCliente()
    //         );

    //         for (Tarjeta tarjeta : tarjetas) {
    //             System.out.println(
    //                 tarjeta.getIdTarjeta() + ": "
    //                 + tarjeta.getNumero()
    //             );
    //         }
    //     }
    // }

    private static void listRestauranteCodigosPostales(
        List<Integer> idsCodigoPostal
    ) {
        System.out.println("Restaurantes en códigos postales " + idsCodigoPostal + ":");

        List<Restaurante> restaurantes = RestauranteController
            .getByIdCodigoPostal(idsCodigoPostal)
        ;

        for (Restaurante restaurante : restaurantes) {
            System.out.println(restaurante);
        }
    }

    private static void listRestauranteIdsTipoCocina(int idTipoCocina) {
        List<Integer> idsRestauranteTipoCocina = RestauranteTipoCocinaController
            .getIdsRestauranteConTipoCocina(idTipoCocina)
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
            .getIdsTipoCocinaDeRestaurante(idRestaurante)
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

        // System.out.println("\nLas tarjetas de cada cliente:");
        // Test.listTarjetasCliente();

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
        Test.listRestauranteIdTipoEntrega(3);

        System.out.println();
        Test.listTiposEntregaIdRestaurante(1);
        Test.listTiposEntregaIdRestaurante(2);
        Test.listTiposEntregaIdRestaurante(3);

        System.out.println();
        Test.listBebidasFromRestaurante(1);
        Test.listBebidasFromRestaurante(2);
        Test.listBebidasFromRestaurante(3);
    }
}