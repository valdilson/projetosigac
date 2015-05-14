package br.com.tdsystem.sigac.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.tdsystem.sigac.modelo.Aluno;
import br.com.tdsystem.sigac.util.HibernateUtil;

public class AlunoDAO {
	

	public void salvar(Aluno aluno){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.save(aluno);
			transaction.commit();
			System.out.println("Gravou");
			
		} catch (Exception e) {
			transaction.rollback();
			e.getMessage();
		}finally{
			session.close();
		}
	}
	
	public void editar(Aluno aluno){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.update(aluno);
			transaction.commit();
			System.out.println("Salvou!");
		} catch (Exception e) {
			transaction.rollback();
			System.out.println("Nao salvou " + e.getMessage());
		}finally{
			session.close();
		}
	}
	
	public Aluno pesquisaCodigo(Long codigo){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Aluno aluno = null;
		try {
			Query hql = session.getNamedQuery("Aluno.codigo");
			hql.setLong("codigo", codigo);
			aluno = (Aluno) hql.uniqueResult();
			System.out.println("Retornou registro bunito!");
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}finally{
			session.close();
		}
		
		return aluno;
	}
	
	
}
