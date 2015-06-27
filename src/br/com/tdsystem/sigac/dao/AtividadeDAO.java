package br.com.tdsystem.sigac.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.tdsystem.sigac.modelo.Atividade;
import br.com.tdsystem.sigac.modelo.Status;
import br.com.tdsystem.sigac.util.Constante;
import br.com.tdsystem.sigac.util.HibernateUtil;

@SuppressWarnings("unchecked")
public class AtividadeDAO {
	
	public void salvar(Atividade atividade){
		
		//Abre a fabrica de Sessao e cria uma sessao
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			transaction = secao.beginTransaction();
			secao.save(atividade);
			
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
	}

	public void excluir(Atividade atividade){
		
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			
			transaction = secao.beginTransaction();
			secao.delete(atividade);
			transaction.commit();
			
		} catch (RuntimeException e) {
			if (transaction != null) {
				transaction.rollback();
				System.out.println("ErroDAO: "+e.getCause());
			}
			throw e;
		}finally{
			secao.close();
		}
	}//Fim Excluir
	
	public void editar(Atividade atividade){
		
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			transaction = secao.beginTransaction();
			secao.update(atividade);
			
			transaction.commit();
			
		} catch (RuntimeException e) {
			if (transaction != null){
				transaction.rollback();
			}throw e;
		}finally{
			secao.close();
		}
	}//Fim Editar

	public List<Atividade> listaAtividade(Status status){
		
		Session secao = HibernateUtil.getSessionFactory().openSession();
		List<Atividade> listaAtividades = null;
		try {
			
			Query hql = secao.getNamedQuery(Constante.NamedQueries.ATIVIDADE_LISTA);
			listaAtividades = hql.list();
			
		} catch (RuntimeException e) {
			throw e;
		}finally{
			secao.close();
		}
		return listaAtividades;
		
	}//Fim Pesquisa Lista
	
	//Lista completa
	public List<Atividade> listaAtividade(){
		
		Session secao = HibernateUtil.getSessionFactory().openSession();
		List<Atividade> listaAtividades = null;
		try {
			
			Query hql = secao.getNamedQuery(Constante.NamedQueries.ATIVIDADE_LISTA);
			listaAtividades = hql.list();
			
		} catch (RuntimeException e) {
			throw e;
		}finally{
			secao.close();
		}
		return listaAtividades;
		
	}//Fim Pesquisa Lista


	public Atividade pesquisaCodigo(Long codigo){
		
		Session secao = HibernateUtil.getSessionFactory().openSession();
		Atividade atividade = null;
		try{
			Query hql = secao.getNamedQuery("Atividade.codigo");
			hql.setLong("codigo", codigo);
			atividade = (Atividade) hql.uniqueResult();
			
		}catch(RuntimeException e){
			throw e;
		}finally{
			secao.close();
		}
		
		return atividade;
	}//FimPesquisaPorCodigo


}
