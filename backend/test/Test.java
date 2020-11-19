package backend.test;

import backend.controller.ClienteController;
import backend.controller.RestauranteController;
import backend.controller.TipoCocinaController;
import backend.controller.TipoEntregaController;
import backend.controller.UsuarioController;
import backend.modelo.Cliente;
import backend.modelo.Restaurante;
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
                + usuario.getCodigoPostal() + ", "
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
            System.out.println(
                restaurante.getIdRestaurante() + ": "
                + restaurante.getUsuarioIdUsuario() + ", "
                + restaurante.getCif() + ", "
                + restaurante.getIban() + ", "
                + restaurante.getNombreRestaurante() + ", "
                + restaurante.getNombrePropietario()
            );
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
    }
}