package br.com.tdsystem.sigac.teste;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.tdsystem.sigac.dao.AlunoDAO;
import br.com.tdsystem.sigac.dao.AtividadeDAO;
import br.com.tdsystem.sigac.dao.AtividadeRealizadaDAO;
import br.com.tdsystem.sigac.modelo.Aluno;
import br.com.tdsystem.sigac.modelo.Atividade;
import br.com.tdsystem.sigac.modelo.AtividadeRealizada;
import br.com.tdsystem.sigac.util.FormataData;

public class AtividadeRealizadaTeste {
	
	@Test
	@Ignore
	public void salvarTeste(){
		try{			
			AlunoDAO alunoDAO = new AlunoDAO();
			Aluno aluno = alunoDAO.pesquisaCodigo(1l);
			
			AtividadeDAO atividadeDAO = new AtividadeDAO();
			Atividade atividade = atividadeDAO.pesquisaCodigo(2l);
			
			AtividadeRealizadaDAO atividadeRealizadaDAO = new AtividadeRealizadaDAO();
			AtividadeRealizada atividadeRealizada = new AtividadeRealizada();
			atividadeRealizada.setHorasRestantes(98);
			atividadeRealizada.setAluno(aluno);
			atividadeRealizada.setAtividade(atividade);
			atividadeRealizada.setDataRealizacao(FormataData.formataData(new Date()));
			atividadeRealizada.setEnderecoComprovante("D:/comprovantes/"+aluno.getNome()+
														"/"+atividadeRealizada.getDataRealizacao()+"");
			atividadeRealizadaDAO.salvar(atividadeRealizada);
			
		}catch(Exception e){
			System.out.println("Erro: " + e.getMessage());
			
		}	
	}
	
	@Test
	@Ignore
	public void editarTeste(){
		try {
			AtividadeRealizadaDAO atividadeRealizadaDAO = new AtividadeRealizadaDAO();
			AtividadeRealizada atividadeRealizada = atividadeRealizadaDAO.pesquisaCodigo(2l);
			AlunoDAO alunoDAO = new AlunoDAO();
			Aluno aluno = alunoDAO.pesquisaCodigo(6l);
			AtividadeDAO atividadeDAO = new AtividadeDAO();
			Atividade atividade = atividadeDAO.pesquisaCodigo(2l);
			
			atividadeRealizada.setDataRealizacao("Editado");
			atividadeRealizada.setEnderecoComprovante("Editado");
			atividadeRealizada.setHorasRestantes(60);
			atividadeRealizada.setAluno(aluno);
			atividadeRealizada.setAtividade(atividade);
			atividadeRealizadaDAO.editar(atividadeRealizada);
		} catch (RuntimeException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
	}
	
	@Test
	@Ignore
	public void excluirTeste(){
		AtividadeRealizadaDAO atividadeRealizadaDAO = new AtividadeRealizadaDAO();
		AtividadeRealizada atividadeRealizada = atividadeRealizadaDAO.pesquisaCodigo(1l);
		atividadeRealizadaDAO.excluir(atividadeRealizada);
	}
	
	@Test
	@Ignore
	public void listarAtividadesRealizadasTeste(){
		AtividadeRealizadaDAO  atividadeRealizadaDAO = new AtividadeRealizadaDAO();
		List<AtividadeRealizada> listaAtividades = atividadeRealizadaDAO.listarAtividadesRealizadas();
		
		for (AtividadeRealizada atividadeRealizada : listaAtividades) {
			System.out.println(" " + atividadeRealizada);
		}
	}

}
