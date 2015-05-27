package br.com.tdsystem.sigac.teste;

import java.util.Date;

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

}
