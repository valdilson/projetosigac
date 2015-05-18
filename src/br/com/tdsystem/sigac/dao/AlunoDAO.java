package br.com.tdsystem.sigac.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import br.com.tdsystem.sigac.modelo.Aluno;
import br.com.tdsystem.sigac.util.Constante;
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
	
	public void excluir(Aluno aluno){
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
			System.out.println("Editou!");
		} catch (Exception e) {
			transaction.rollback();
			System.out.println("Nao Editou " + e.getMessage());
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
			System.out.println(aluno);
			
		} catch (RuntimeException e) {
			System.out.println("Erro: " + e.getMessage());
		}finally{
			session.close();
		}
		
		return aluno;
	}
	
	@SuppressWarnings("unchecked")
	public List<Aluno> listarAlunos(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Aluno> listaAlunos = null;
		
		try {
			Query hql = session.getNamedQuery(Constante.NamedQueries.ALUNO_RECUPERA_LISTA);
			listaAlunos = hql.list();
			
			for (Aluno aluno : listaAlunos) {
				System.out.println("" + aluno);
			}
			
		} catch (RuntimeException e) {
			System.out.println("Erro: " + e.getMessage());
		}finally{
			session.close();
		}
		
		return listaAlunos;
		
	}
	
	
}
