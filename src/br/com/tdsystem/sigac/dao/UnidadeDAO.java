package br.com.tdsystem.sigac.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.tdsystem.sigac.modelo.Unidade;
import br.com.tdsystem.sigac.util.HibernateUtil;

public class UnidadeDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	public void salvar(Unidade unidade){
		
		//Abre a fabrica de Sessao e cria uma sessao
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			transaction = secao.beginTransaction();
			secao.save(unidade);
			
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

	public void exluir(Unidade unidade){
		
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			transaction = secao.beginTransaction();
			secao.delete(unidade);
			
			transaction.commit();
			
		} catch (RuntimeException e) {
			if (transaction != null){
				transaction.rollback();
			}throw e;
		}finally{
			secao.close();
		}
	}//Fim Excluir
	
	public void editar(Unidade unidade){
		
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			transaction = secao.beginTransaction();
			secao.update(unidade);
			
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
	public List<Unidade> listarUnidade(){
		
		Session secao = HibernateUtil.getSessionFactory().openSession();
		List<Unidade> listaUnidades = null;
		try {
			
			Query hql = secao.getNamedQuery("Unidade.lista");
			listaUnidades = hql.list();
			System.out.println("Fez a lista");
		} catch (RuntimeException e) {
			throw e;
		}finally{
			secao.close();
		}
		return listaUnidades;
		
	}//Fim Pesquisa Lista

	public Unidade pesquisaCodigo(Long codigo){
		
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Unidade unidade = null;
		try{
			Query hql = secao.getNamedQuery("Unidade.buscarCodigo");
			hql.setLong("codigo", codigo);
			unidade = (Unidade) hql.uniqueResult();
			System.out.println("Fez a consulta");
			
		}catch(RuntimeException e){
			throw e;
		}finally{
			secao.close();
		}
		
		return unidade;
	}//FimPesquisaPorCodigo

}
