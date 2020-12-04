package com.codesplai.randeat.controller.wrapper;

import com.codesplai.randeat.modelo.Cliente;
import com.codesplai.randeat.modelo.Usuario;

/**
 * Al registrar un restaurante se necesita info de Usuario y de Restaurante.
 * Esta clase lo agrupa todo para tener un único @RequestBody. 
 */
public class UsuarioClienteWrapper {
	public Usuario usuario;
	public Cliente cliente;
}