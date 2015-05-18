package br.com.tdsystem.sigac.teste;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.tdsystem.sigac.dao.CursoDAO;
import br.com.tdsystem.sigac.modelo.Status;
import br.com.tdsystem.sigac.modelo.Curso;

public class TesteCurso {

	@Test
	@Ignore
	public void testeSalvar() {
		
		Curso curso = new Curso();
		CursoDAO cursoDAO = new CursoDAO();
		
		curso.setNome("Curso1");
		curso.setStatus(Status.ATIVO);		
		cursoDAO.salvar(curso);
		
	}
	
	@Test
	//@Ignore
	 public void testeListar(){
		 
		 CursoDAO cursoDAO = new CursoDAO();
		 List<Curso> listaCurso = cursoDAO.listaCurso();		 
		 for (Curso curso : listaCurso) {
			
			 System.out.println(curso.toString());
		}
		 
	 }
}
