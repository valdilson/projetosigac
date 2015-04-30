package br.com.tdsystem.sigac.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import br.com.tdsystem.sigac.modelo.Perfil;
import br.com.tdsystem.sigac.util.HibernateUtil;

public class PerfilDAO {

public void salvar(Perfil perfil){
		
		//Abre a fabrica de Sessao e cria uma sessao
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			transaction = secao.beginTransaction();
			secao.save(perfil);
			
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

	public void exluir(Perfil perfil){
		
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			transaction = secao.beginTransaction();
			secao.delete(perfil);
			
			transaction.commit();
			
		} catch (RuntimeException e) {
			if (transaction != null){
				transaction.rollback();
			}throw e;
		}finally{
			secao.close();
		}
	}//Fim Excluir
	
	public void editar(Perfil perfil){
		
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			transaction = secao.beginTransaction();
			secao.update(perfil);
			
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
	public List<Perfil> listaPerfil(){
		
		Session secao = HibernateUtil.getSessionFactory().openSession();
		List<Perfil> listaPerfis = null;
		try {
			
			Query hql = secao.getNamedQuery("Perfil.lista");
			listaPerfis = hql.list();
			
		} catch (RuntimeException e) {
			throw e;
		}finally{
			secao.close();
		}
		return listaPerfis;
		
	}//Fim Pesquisa Lista

	public Perfil pesquisaCodigo(Long codigo){
		
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Perfil perfil = null;
		try{
			Query hql = secao.getNamedQuery("Perfil.codido");
			hql.setLong("codigo", codigo);
			perfil = (Perfil) hql.uniqueResult();
			
		}catch(RuntimeException e){
			throw e;
		}finally{
			secao.close();
		}
		
		return perfil;
	}//FimPesquisaPorCodigo

}
