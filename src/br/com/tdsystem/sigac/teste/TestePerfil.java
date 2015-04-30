package br.com.tdsystem.sigac.teste;

import org.junit.Ignore;
import org.junit.Test;

import br.com.tdsystem.sigac.dao.PerfilDAO;
import br.com.tdsystem.sigac.modelo.Perfil;

public class TestePerfil {

	@Test
	//@Ignore
	public void testeSalvar(){
		Perfil perfil = new Perfil();
		PerfilDAO perfilDAO = new PerfilDAO();
		
			perfil.setCodigo(1l);
			perfil.setNome("Teste");
			perfil.setStatus("Ativo");
			perfilDAO.salvar(perfil);
}
}