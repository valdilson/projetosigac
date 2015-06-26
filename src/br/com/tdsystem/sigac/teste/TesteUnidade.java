package br.com.tdsystem.sigac.teste;

import org.junit.Ignore;
import org.junit.Test;

import br.com.tdsystem.sigac.dao.UnidadeDAO;
import br.com.tdsystem.sigac.modelo.Unidade;

public class TesteUnidade {

	@Test
	//@Ignore
	public void testeSalvar(){
		Unidade unidade = new Unidade();
		UnidadeDAO unidadeDAO = new UnidadeDAO();
		
			unidade.setBairro("Lourdes");
			unidade.setEndereco("Rua Guajajaras 1000");
			unidade.setCep("31060450");
			unidade.setCidade("BH");
			unidade.setEstado("MG");
			unidade.setNome("PITAGORAS BH III");
			unidade.setTelefone("3196937955");
			unidade.setEmail("bh3@anhanguera.com");
			unidadeDAO.salvar(unidade);
			
			unidade = new Unidade();
			unidade.setBairro("Centro");
			unidade.setEndereco("Av.dos Andradas. 1000");
			unidade.setCep("31060-450");
			unidade.setCidade("BH");
			unidade.setEstado("MG");
			unidade.setNome("PITAGORAS BH I");
			unidade.setTelefone("3196937955");
			unidade.setEmail("bh1@anhanguera.com");
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
