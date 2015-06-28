package br.com.tdsystem.sigac.teste;

import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import org.junit.Test;

import br.com.tdsystem.sigac.dao.AlunoDAO;
import br.com.tdsystem.sigac.dao.AtividadeDAO;
import br.com.tdsystem.sigac.dao.CoordenadorDAO;
import br.com.tdsystem.sigac.dao.CursoDAO;
import br.com.tdsystem.sigac.dao.TurmaDAO;
import br.com.tdsystem.sigac.dao.TurnoDAO;
import br.com.tdsystem.sigac.dao.UnidadeDAO;
import br.com.tdsystem.sigac.modelo.Aluno;
import br.com.tdsystem.sigac.modelo.Atividade;
import br.com.tdsystem.sigac.modelo.Coordenador;
import br.com.tdsystem.sigac.modelo.Curso;
import br.com.tdsystem.sigac.modelo.Periodo;
import br.com.tdsystem.sigac.modelo.Status;
import br.com.tdsystem.sigac.modelo.StatusAprovacao;
import br.com.tdsystem.sigac.modelo.Turma;
import br.com.tdsystem.sigac.modelo.Turno;
import br.com.tdsystem.sigac.modelo.Unidade;
import br.com.tdsystem.sigac.modelo.negocio.CriptografaSenhaMD5;

public class PopulaBanco {

	@Test
	//@Ignore
	public void testeSalvar() throws NoSuchAlgorithmException{
		
		//Insere Turno
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
			
			//Insere Turno
			Turno turno = new Turno();
			TurnoDAO turnoDAO = new TurnoDAO();
			
			turno.setNome("Manha");
			turno.setStatus(Status.ATIVO);		
			turnoDAO.salvar(turno);
			
			turno = new Turno();
			turno.setNome("Tarde");
			turno.setStatus(Status.ATIVO);		
			turnoDAO.salvar(turno);
			
			turno = new Turno();
			turno.setNome("Noite");
			turno.setStatus(Status.ATIVO);		
			turnoDAO.salvar(turno);
			
			//Insere Turma
			Turma turma = new Turma();
			TurmaDAO turmaDAO = new TurmaDAO();
			
			turma.setNome("101");
			turma.setStatus(Status.ATIVO);		
			turmaDAO.salvar(turma);
			
			turma = new Turma();
			turma.setNome("102");
			turma.setStatus(Status.ATIVO);		
			turmaDAO.salvar(turma);
			
			turma = new Turma();
			turma.setNome("103");
			turma.setStatus(Status.ATIVO);		
			turmaDAO.salvar(turma);
			
			turma = new Turma();
			turma.setNome("104");
			turma.setStatus(Status.ATIVO);		
			turmaDAO.salvar(turma);
			
			turma = new Turma();
			turma.setNome("105");
			turma.setStatus(Status.ATIVO);		
			turmaDAO.salvar(turma);
			
			//Insere Curso
			Curso curso = new Curso();
			CursoDAO cursoDAO = new CursoDAO();
			
			curso.setNome("Sistemas de Informação");
			curso.setHorasExigidas(100);
			curso.setQtdPeriodos(Periodo.OITAVO);
			curso.setStatus(Status.ATIVO);		
			cursoDAO.salvar(curso);
			
			curso = new Curso();
			curso.setNome("Técnologia em Análise e Desenvolvimento de Sistemas");
			curso.setHorasExigidas(100);
			curso.setQtdPeriodos(Periodo.QUINTO);
			curso.setStatus(Status.ATIVO);		
			cursoDAO.salvar(curso);
			
			curso = new Curso();
			curso.setNome("Direito");
			curso.setHorasExigidas(100);
			curso.setQtdPeriodos(Periodo.DECIMO);
			curso.setStatus(Status.ATIVO);		
			cursoDAO.salvar(curso);
			
			//Insere Atividade
			Atividade atividade = new Atividade();
			AtividadeDAO atividadeDAO = new AtividadeDAO();

			Calendar c = Calendar.getInstance();
			c.set(Calendar.DAY_OF_MONTH, 9);
			c.set(Calendar.MONTH, 5);
			c.set(Calendar.YEAR, 2015);
			
			atividade.setCodigo(1l);
			atividade.setNome("Palestra T.I");
			atividade.setHoras(2);
			atividade.setDescricao("Palestra sobre Desenvolvimento");
			atividade.setStatus(Status.ATIVO);
			atividade.setDataEvento(c.getTime());
			atividadeDAO.salvar(atividade);
			
			c = Calendar.getInstance();
			c.set(Calendar.DAY_OF_MONTH, 26);
			c.set(Calendar.MONTH, 5);
			c.set(Calendar.YEAR, 2015);
			
			atividade = new Atividade();
			atividade.setNome("Passeio Ciclístico");
			atividade.setHoras(5);
			atividade.setDescricao("Passeio lagoa da Pampulha");
			atividade.setStatus(Status.ATIVO);
			atividadeDAO.salvar(atividade);
			
			//Insere Coordenador
			Coordenador coordenador = new Coordenador();
			CoordenadorDAO coordenadorDAO = new CoordenadorDAO();
			unidadeDAO = new UnidadeDAO();
			unidade = unidadeDAO.pesquisaCodigo(1l);
			
			coordenador.setEmail("efreim@anhanguera.com");
			coordenador.setNome("Efreim Louzada");
			coordenador.setUnidade(unidade);
			coordenador.setPassword(CriptografaSenhaMD5.converteSenhaMD5("123"));
			coordenador.setRa("123");
			coordenadorDAO.salvar(coordenador);
			
			//Insere Aluno
			AlunoDAO alunoDAO = new AlunoDAO();
			cursoDAO = new CursoDAO();
			unidadeDAO = new UnidadeDAO();
			turmaDAO = new TurmaDAO();
			turnoDAO = new TurnoDAO();
			
			Aluno aluno = new Aluno();
			
			curso = cursoDAO.pesquisaCodigo(1l);
			unidade = unidadeDAO.pesquisaCodigo(1l);
			turma = turmaDAO.pesquisaCodigo(1l);
			turno = turnoDAO.pesquisaCodigo(1l);
			
			aluno.setNome("Thiago Oliveira");
			aluno.setRa("123");
			aluno.setEmail("thiago.krathos@gmail.com");
			aluno.setPassword("123");
			aluno.setStatus(Status.ATIVO);
			aluno.setStatusApovacao(StatusAprovacao.PENDENTE);
			
			aluno.setPassword(CriptografaSenhaMD5.converteSenhaMD5
					(aluno.getPassword()));
			
			aluno.setPeriodo(Periodo.PRIMEIRO);
			aluno.setCurso(curso);
			aluno.setTurma(turma);
			aluno.setTurno(turno);
			aluno.setUnidade(unidade);
			alunoDAO.salvar(aluno);
	}
}
