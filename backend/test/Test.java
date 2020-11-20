package backend.test;

import java.util.List;

import backend.controller.ClienteController;
import backend.controller.CodigoPostalController;
import backend.controller.RestauranteController;
import backend.controller.TarjetaController;
import backend.controller.TipoCocinaController;
import backend.controller.TipoEntregaController;
import backend.controller.UsuarioController;
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
                CodigoPostalController.getNumeroById(restaurante.getCodigoPostalIdCodigoPostal())
            ));
        }
    }

    private static void listTarjetasCliente() {
        for (Cliente cliente : ClienteController.getAll()) {
            System.out.println(cliente.getNombreCompleto() + ":");
            List<Tarjeta> tarjetas = TarjetaController.getTarjetasCliente(
                cliente.getIdCliente()
            );

            for (Tarjeta tarjeta : tarjetas) {
                System.out.println(
                    tarjeta.getIdTarjeta() + ": "
                    + tarjeta.getNumero()
                );
            }
        }
    }

    private static void listRestauranteCodigosPostales(int[] idsCodigoPostal) {
        for (Restaurante restaurante : RestauranteController.getRestaurantesEnCodigosPostales(idsCodigoPostal)) {
            System.out.println(restaurante);
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

        System.out.println("\nLas tarjetas de cada cliente:");
        Test.listTarjetasCliente();

        System.out.println("\n Restaurantes en códigos postales (1):");
        Test.listRestauranteCodigosPostales(new int[] {1});
        System.out.println("\n Restaurantes en códigos postales (1, 2):");
        Test.listRestauranteCodigosPostales(new int[] {1, 2});
        System.out.println("\n Restaurantes en códigos postales ():");
        Test.listRestauranteCodigosPostales(new int[] {});
    }
}