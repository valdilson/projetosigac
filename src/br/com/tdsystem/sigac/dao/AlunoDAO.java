package br.com.tdsystem.sigac.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.tdsystem.sigac.modelo.Aluno;
import br.com.tdsystem.sigac.util.Constante;
import br.com.tdsystem.sigac.util.HibernateUtil;

public class AlunoDAO implements Serializable {

	private static final long serialVersionUID = -4280225683772058698L;

	public void salvar(Aluno aluno) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.save(aluno);
			transaction.commit();
			System.out.println("Gravou");

		} catch (Exception e) {
			System.out.println("ErroDAO: "+e.getCause());
			System.out.println("Gravou");
			transaction.rollback();
			e.getMessage();
		} finally {
			session.close();
		}
	}

	public void excluir(Aluno aluno) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.delete(aluno);
			transaction.commit();
			System.out.println("Excluiu");

		} catch (Exception e) {
			transaction.rollback();
			e.getMessage();
		} finally {
			session.close();
		}
	}

	public void editar(Aluno aluno) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			Aluno novo = new Aluno();
			session.load(novo, aluno.getCodigo());
			atualizarAluno(aluno, novo);
			session.update(novo);

			transaction.commit();
			System.out.println("Editou!");
		} catch (Exception e) {
			transaction.rollback();
			System.out.println("Nao Editou " + e.getMessage());
		} finally {
			session.close();
		}
	}
	
	public void editarComum(Aluno aluno) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.update(aluno);
			transaction.commit();
			System.out.println("Editou!");
		} catch (Exception e) {
			transaction.rollback();
			System.out.println("Nao Editou " + e.getMessage());
		} finally {
			session.close();
		}
	}

	private void atualizarAluno(Aluno aluno, Aluno novo) {
		novo.setNome(aluno.getNome());
		novo.setEmail(aluno.getEmail());
		novo.setUnidade(aluno.getUnidade());
		novo.setTurno(aluno.getTurno());
		novo.setTurma(aluno.getTurma());
		novo.setCurso(aluno.getCurso());
		novo.setPassword(aluno.getPassword());
		novo.setPeriodo(aluno.getPeriodo());
	}

	public Aluno pesquisaCodigo(Long codigo) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Aluno aluno = null;
		try {

			Query hql = session.getNamedQuery("Aluno.codigo");
			hql.setLong("codigo", codigo);

			aluno = (Aluno) hql.uniqueResult();
			System.out.println(aluno);

		} catch (RuntimeException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			session.close();
		}

		return aluno;
	}

	public Aluno pesquisaRA(String ra) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Aluno aluno = null;
		try {
			Query hql = session.getNamedQuery(Constante.NamedQueries.ALUNO_RECUPERA_RA);
			hql.setString("ra", ra);
			aluno = (Aluno) hql.uniqueResult();
		} catch (RuntimeException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			session.close();
		}
		return aluno;
	}

	@SuppressWarnings("unchecked")
	public List<Aluno> listarAlunos() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Aluno> listaAlunos = null;
		Query query = null;
		try {
			query = session.getNamedQuery(Constante.NamedQueries.ALUNO_RECUPERA_LISTA);
			listaAlunos = query.list();
		} catch (RuntimeException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			session.close();
		}

		return listaAlunos;
	}
}
