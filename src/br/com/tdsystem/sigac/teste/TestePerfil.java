package br.com.tdsystem.sigac.teste;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.tdsystem.sigac.dao.PerfilDAO;
import br.com.tdsystem.sigac.modelo.Perfil;
import br.com.tdsystem.sigac.modelo.Status;

public class TestePerfil {

	@Test
	@Ignore
	public void testeSalvar(){
		Perfil perfil = new Perfil();
		PerfilDAO perfilDAO = new PerfilDAO();
		
			perfil.setCodigo(1l);
			perfil.setNome("Teste");
			perfil.setStatus(Status.ATIVO);
			perfilDAO.salvar(perfil);
}
	
	@Test
	@Ignore
	public void testaLista(){
		PerfilDAO perfilDAO = new PerfilDAO();
		List<Perfil> listaPerfis = perfilDAO.listaPerfil();
		
		for (Perfil perfil : listaPerfis) {
			System.out.println(" " + perfil.toString());
		}
	}
	
	@Test
	public void testaBuscaporCodigo(){
		PerfilDAO perfilDAO = new PerfilDAO();
		Perfil perfil = perfilDAO.pesquisaCodigo(1L);
		System.out.println(" " + perfil.toString());
	}
	
}