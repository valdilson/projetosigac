package br.com.tdsystem.sigac.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import br.com.tdsystem.sigac.modelo.Turno;
import br.com.tdsystem.sigac.util.HibernateUtil;

public class TurnoDAO {

public void salvar(Turno turno){
		
		//Abre a fabrica de Sessao e cria uma sessao
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			transaction = secao.beginTransaction();
			secao.save(turno);
			
			transaction.commit();
			System.out.println("Gravou");
		} catch (RuntimeException e) {
			System.out.println("Errou" +e.getMessage());
			e.getMessage();
			if (transaction != null){
				transaction.rollback();
				e.getMessage();
			}throw e;
		}finally{
			secao.close();
		}
	}//Fim salvar

	public void excluir(Turno turno){
		
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			transaction = secao.beginTransaction();
			secao.delete(turno);
			
			transaction.commit();
			
		} catch (RuntimeException e) {
			if (transaction != null){
				transaction.rollback();
			}throw e;
		}finally{
			secao.close();
		}
	}//Fim Excluir
	
	public void editar(Turno turno){
		
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			transaction = secao.beginTransaction();
			secao.update(turno);
			
			transaction.commit();
			
		} catch (RuntimeException e) {
			if (transaction != null){
				transaction.rollback();
			}throw e;
		}finally{
			secao.close();
		}
	}//Fim Editar

	@SuppressWarnings("unchecked")
	public List<Turno> listaTurno(){
		
		Session secao = HibernateUtil.getSessionFactory().openSession();
		List<Turno> listaTurnos = null;
		try {
			
			Query hql = secao.getNamedQuery("Turno.lista");
			listaTurnos = hql.list();
			
		} catch (RuntimeException e) {
			throw e;
		}finally{
			secao.close();
		}
		return listaTurnos;
		
	}//Fim Pesquisa Lista

	public Turno pesquisaCodigo(Long codigo){
		
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Turno turno = null;
		try{
			Query hql = secao.getNamedQuery("Turno.codigo");
			hql.setLong("codigo", codigo);
			turno = (Turno) hql.uniqueResult();
			
		}catch(RuntimeException e){
			throw e;
		}finally{
			secao.close();
		}
		
		return turno;
	}//FimPesquisaPorCodigo

}