package br.com.tdsystem.sigac.teste;

import org.junit.Ignore;
import org.junit.Test;
import br.com.tdsystem.sigac.dao.AtividadeDAO;
import br.com.tdsystem.sigac.modelo.Atividade;

public class TesteAtividade {
	
	@Test
	//@Ignore
	public void testeSalvar(){
		Atividade atividade = new Atividade();
		AtividadeDAO atividadeDAO = new AtividadeDAO();
		
			atividade.setCodigo(1l);
			atividade.setNome("Passeio Bicicleta");
			atividade.setStatus("Ativo");
			atividadeDAO.salvar(atividade);

}
}
