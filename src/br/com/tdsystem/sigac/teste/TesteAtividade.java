package br.com.tdsystem.sigac.teste;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.tdsystem.sigac.dao.AtividadeDAO;
import br.com.tdsystem.sigac.modelo.Atividade;
import br.com.tdsystem.sigac.modelo.Status;

public class TesteAtividade {

	@Test
	//@Ignore
	public void testeSalvar() {
		Atividade atividade = new Atividade();
		AtividadeDAO atividadeDAO = new AtividadeDAO();

		atividade.setCodigo(1l);
		atividade.setNome("Palestra T.I");
		atividade.setHoras(2);
		atividade.setDescricao("Palestra sobre Desenvolvimento");
		atividade.setStatus(Status.ATIVO);
		atividadeDAO.salvar(atividade);
		
		atividade = new Atividade();
		atividade.setNome("Passeio Ciclístico");
		atividade.setHoras(5);
		atividade.setDescricao("Passeio lagoa da Pampulha");
		atividade.setStatus(Status.ATIVO);
		atividadeDAO.salvar(atividade);
	}
	
	@Test
	@Ignore
	public void testeListarAtividade(){
		AtividadeDAO atividadeDAO = new AtividadeDAO();
		List<Atividade> listaAtividades = atividadeDAO.listaAtividade();
		
		for (Atividade atividade : listaAtividades) {
			System.out.println(" " + atividade.toString());
		}
	}
}
