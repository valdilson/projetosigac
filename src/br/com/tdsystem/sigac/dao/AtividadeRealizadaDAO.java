package br.com.tdsystem.sigac.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.tdsystem.sigac.modelo.Aluno;
import br.com.tdsystem.sigac.modelo.AtividadeRealizada;
import br.com.tdsystem.sigac.modelo.Coordenador;
import br.com.tdsystem.sigac.modelo.IPessoa;
import br.com.tdsystem.sigac.util.Constante;
import br.com.tdsystem.sigac.util.HibernateUtil;

public class AtividadeRealizadaDAO {

	

	public void salvar(AtividadeRealizada atividadeRealizada) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(atividadeRealizada);
			transaction.commit();
			System.out.println("Gravou!");
		} catch (HibernateException e) {
			System.out.println("Erro :" + e.getMessage());
			transaction.rollback();
		} finally {
			session.close();
		}
	}

	public void editar(AtividadeRealizada atividadeRealizada) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.update(atividadeRealizada);
			transaction.commit();
			System.out.println("Editou!");
		} catch (RuntimeException e) {
			System.out.println("Erro :" + e.getMessage());
			transaction.rollback();
		} finally {
			session.close();
		}
	}

	public void excluir(AtividadeRealizada atividadeRealizada) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.delete(atividadeRealizada);
			transaction.commit();
			System.out.println("Excluiu!");
		} catch (RuntimeException e) {
			System.out.println("Erro :" + e.getMessage());
			transaction.rollback();
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<AtividadeRealizada> listarAtividadesRealizadas(IPessoa usuario){
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<AtividadeRealizada> atividade = null;
		try {
			Query hql = null;
			if (usuario instanceof Aluno) {
				hql = session.getNamedQuery(Constante.NamedQueries.ATIVIVIDADE_REALIZADA_LISTA_INDIVIDUAL);
				hql.setLong("codigo_aluno", usuario.getCodigo());
			} else if (usuario instanceof Coordenador) {	
				hql = session.getNamedQuery(Constante.NamedQueries.ATIVIVIDADE_REALIZADA_LISTA);
			} else {
				throw new IllegalArgumentException();
			}
			atividade = hql.list();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			session.close();
		}
		return atividade;
	}
	
	
	
	public AtividadeRealizada pesquisaCodigo(Long codigo){
		Session session = HibernateUtil.getSessionFactory().openSession();
		AtividadeRealizada atividadeRealizada = null;
		try {
			
			Query hql = session.getNamedQuery(Constante.NamedQueries.ATIVIVIDADE_REALIZADA_CODIGO);
			hql.setLong("codigo", codigo);
			
			atividadeRealizada = (AtividadeRealizada) hql.uniqueResult();
			System.out.println(atividadeRealizada);
			
		} catch (RuntimeException e) {
			System.out.println("Erro: " + e.getMessage());
		}finally{
			session.close();
		}
		
		return atividadeRealizada;
	}
}
