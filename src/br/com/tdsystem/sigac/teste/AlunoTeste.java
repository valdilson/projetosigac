package br.com.tdsystem.sigac.teste;

import java.security.NoSuchAlgorithmException;

import org.junit.Ignore;
import org.junit.Test;

import br.com.tdsystem.sigac.dao.AlunoDAO;
import br.com.tdsystem.sigac.dao.CursoDAO;
import br.com.tdsystem.sigac.dao.PeriodoDAO;
import br.com.tdsystem.sigac.dao.TurmaDAO;
import br.com.tdsystem.sigac.dao.TurnoDAO;
import br.com.tdsystem.sigac.dao.UnidadeDAO;
import br.com.tdsystem.sigac.modelo.Aluno;
import br.com.tdsystem.sigac.modelo.Curso;
import br.com.tdsystem.sigac.modelo.Periodo;
import br.com.tdsystem.sigac.modelo.Turma;
import br.com.tdsystem.sigac.modelo.Turno;
import br.com.tdsystem.sigac.modelo.Unidade;
import br.com.tdsystem.sigac.util.CriptografaSenhaMD5;

public class AlunoTeste {

	@Test
	//@Ignore
	public void salvarAlunoTeste() throws NoSuchAlgorithmException{
		
		AlunoDAO alunoDAO = new AlunoDAO();
		CursoDAO cursoDAO = new CursoDAO();
		UnidadeDAO unidadeDAO = new UnidadeDAO();
		TurmaDAO turmaDAO = new TurmaDAO();
		TurnoDAO turnoDAO = new TurnoDAO();
		PeriodoDAO periodoDAO = new PeriodoDAO();
		
		Aluno aluno = new Aluno();
		
		Curso curso = cursoDAO.pesquisaCodigo(1l);
		Unidade unidade = unidadeDAO.pesquisaCodigo(1l);
		Turma turma = turmaDAO.pesquisaCodigo(1l);
		Turno turno = turnoDAO.pesquisaCodigo(1l);
		Periodo periodo = periodoDAO.pesquisaCodigo(1l);
		
		aluno.setNome("Thiago");
		aluno.setRa("6273287272");
		aluno.setEmail("thiago@gmail.com");
		aluno.setHorasExigidas(100);
		aluno.setPassword("123");
		
		aluno.setPassword(CriptografaSenhaMD5.converteSenhaMD5
				(aluno.getPassword()));
		
		aluno.setPeriodo(periodo);
		aluno.setCurso(curso);
		aluno.setTurma(turma);
		aluno.setTurno(turno);
		aluno.setUnidade(unidade);
		aluno.setUsername("thiago");
		alunoDAO.salvar(aluno);
	}
	
	@Test
	@Ignore
	public void pesquisaCodigoTeste(){
		AlunoDAO alunoDAO = new AlunoDAO();
		Aluno aluno = alunoDAO.pesquisaCodigo(1l);
		System.out.println(" " + aluno.toString());
	}
}
