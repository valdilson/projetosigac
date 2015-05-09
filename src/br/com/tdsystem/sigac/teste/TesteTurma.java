package br.com.tdsystem.sigac.teste;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.tdsystem.sigac.dao.TurmaDAO;
import br.com.tdsystem.sigac.modelo.Status;
import br.com.tdsystem.sigac.modelo.Turma;

public class TesteTurma {

	@Test
	@Ignore
	public void testeSalvar() {
		
		Turma turma = new Turma();
		TurmaDAO turmaDAO = new TurmaDAO();
		
		turma.setNome("Turma1");
		turma.setStatus(Status.ATIVO);		
		turmaDAO.salvar(turma);
		
	}
	
	@Test
	 public void testeListar(){
		 
		 TurmaDAO turmaDAO = new TurmaDAO();
		 List<Turma> listaTurma = turmaDAO.listaTurma();		 
		 for (Turma turma : listaTurma) {
			
			 System.out.println(turma.toString());
		}
		 
	 }
}
