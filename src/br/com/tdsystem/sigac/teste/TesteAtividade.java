package br.com.tdsystem.sigac.teste;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.tdsystem.sigac.dao.AtividadeDAO;
import br.com.tdsystem.sigac.modelo.Atividade;

public class TesteAtividade {

	@Test
	@Ignore
	public void testeSalvar() {
		Atividade atividade = new Atividade();
		AtividadeDAO atividadeDAO = new AtividadeDAO();

		atividade.setCodigo(1l);
		atividade.setNome("Palestra T.I");
		atividade.setHoras(2F);
		atividade.setDescricao("Palestra sobre Desenvolvimento");
		atividade.setStatus("Ativo");
		atividadeDAO.salvar(atividade);
	}
	
	@Test
	public void testeListarAtividade(){
		AtividadeDAO atividadeDAO = new AtividadeDAO();
		List<Atividade> listaAtividades = atividadeDAO.listaAtividade();
		
		for (Atividade atividade : listaAtividades) {
			System.out.println(" " + atividade.toString());
		}
	}
}
