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
		
			unidade.setCodigo(1l);
			unidade.setBairro("Nova vista");
			unidade.setCep("31060450");
			unidade.setCidade("BH");
			unidade.setEstado("MG");
			unidade.setNome("Teste");
			unidade.setTelefone("3196937955");
			unidadeDAO.salvar(unidade);
	}
	
		
}
