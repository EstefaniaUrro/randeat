package backend.test;

import backend.controller.TipoCocinaController;
import backend.controller.UsuarioController;
import backend.modelo.TipoCocina;
import backend.modelo.Usuario;

public class Test {
    private static void selectAllTipoCocina() {
        for (TipoCocina tipoCocina : TipoCocinaController.getAll()) {
            System.out.println(tipoCocina.getIdTipoCocina() + ": " + tipoCocina.getNombre());
        }
    }

    private static void selectAllUsuario() {
        for (Usuario usuario : UsuarioController.getAll()) {
            System.out.println(
                usuario.getIdUsuario() + ": "
                + usuario.getCorreoElectronico() + ", "
                + usuario.getContrasena() + ", "
                + usuario.getNombre() + ", "
                + usuario.getTelefono() + ", "
                + usuario.getPoblacion() + ", "
                + usuario.getDireccion()
            );
        }
    }

    public static void main(String[] args) {
        System.out.println("Tipos cocina:");
        Test.selectAllTipoCocina();

        System.out.println("Usuarios:");
        Test.selectAllUsuario();
    }
}
