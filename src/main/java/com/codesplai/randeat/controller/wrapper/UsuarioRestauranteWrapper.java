package com.codesplai.randeat.controller.wrapper;

import com.codesplai.randeat.modelo.Restaurante;
import com.codesplai.randeat.modelo.Usuario;

/**
 * Al registrar un cliente se necesita info de Usuario y de Cliente.
 * Esta clase lo agrupa todo para tener un único @RequestBody. 
 */
public class UsuarioRestauranteWrapper {
	public Usuario usuario;
	public Restaurante restaurante;
}