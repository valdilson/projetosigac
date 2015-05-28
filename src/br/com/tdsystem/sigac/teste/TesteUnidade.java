package br.com.tdsystem.sigac.teste;

import org.junit.Ignore;
import org.junit.Test;

import br.com.tdsystem.sigac.dao.UnidadeDAO;
import br.com.tdsystem.sigac.modelo.Unidade;

public class TesteUnidade {

	@Test
	@Ignore
	public void testeSalvar(){
		Unidade unidade = new Unidade();
		UnidadeDAO unidadeDAO = new UnidadeDAO();
		
			unidade.setBairro("Nova vista");
			unidade.setEndereco("Rua Teste 123");
			unidade.setCep("31060450");
			unidade.setCidade("BH");
			unidade.setEstado("MG");
			unidade.setNome("Teste");
			unidade.setTelefone("3196937955");
			unidadeDAO.salvar(unidade);
			
			unidade = new Unidade();
			unidade.setBairro("Centro");
			unidade.setEndereco("Av.dos Andradas. 1000");
			unidade.setCep("31060-450");
			unidade.setCidade("BH");
			unidade.setEstado("MG");
			unidade.setNome("Unidade BHI");
			unidade.setTelefone("3196937955");
			unidadeDAO.salvar(unidade);
	}
	
	@Test
	@Ignore
	public void testarPesquisaCodigo(){
	
		UnidadeDAO unidadeDAO = new UnidadeDAO();
		Unidade unidade = unidadeDAO.pesquisaCodigo(1L);
		
		System.out.println(unidade.toString());
		
		
	}
	
		
}
