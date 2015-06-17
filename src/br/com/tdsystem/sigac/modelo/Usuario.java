package br.com.tdsystem.sigac.modelo;

import java.io.Serializable;

public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private IPessoa usuario;
	private PerfilEnum perfil;
	
	public IPessoa getUsuario() {
		return usuario;
	}
	public void setUsuario(IPessoa usuario) {
		this.usuario = usuario;
	}
	public PerfilEnum getPerfil() {
		return perfil;
	}
	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil;
	}
	
	
}
