package br.com.tdsystem.sigac.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.tdsystem.sigac.modelo.Coordenador;
import br.com.tdsystem.sigac.util.Constante;
import br.com.tdsystem.sigac.util.HibernateUtil;

public class CoordenadorDAO {
	
	public void salvar(Coordenador coordenador){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(coordenador);
			transaction.commit();
			System.out.println("Gravou");
		} catch (Exception e) {
			System.out.println("ErroDAO: " + e.getMessage());
			System.out.println("ErroDAO: " + e.getCause());
			transaction.rollback();
		}finally{
			session.close();
		}
	}
	
	public void excluir(Coordenador coordenador){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(coordenador);
			transaction.commit();
			System.out.println("Excluiu!");
		} catch (RuntimeException e) {
			System.out.println("Nao excluiu");
			System.out.println("Erro: " + e.getMessage());
			transaction.rollback();
		}finally{
			session.close();
		}
	}
	
	public void editar(Coordenador coordenador){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(coordenador);
			transaction.commit();
			System.out.println("Editou!");
		} catch (RuntimeException e) {
			System.out.println("Nao editou!");
			System.out.println("Erro: " + e.getMessage());
			transaction.rollback();
		}finally{
			session.close();
		}
	}

	public Coordenador pesquisaCodigo(Long codigo){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Coordenador coordenador = null;
		try {
			
			Query hql = session.getNamedQuery(Constante.NamedQueries.COORDENADOR_RECUPERA_CODIGO);
			hql.setLong("codigo", codigo);
			
			coordenador = (Coordenador) hql.uniqueResult();
			System.out.println(" " + coordenador.toString());
			
		} catch (RuntimeException e) {
			System.out.println("Erro: " + e.getMessage());
		}finally{
			session.close();
		}
		
		return coordenador;
	}
	
	public Coordenador pesquisaRA(String ra){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Coordenador coordenador = null;
		try {
			
			Query hql = session.getNamedQuery(Constante.NamedQueries.COORDENADOR_RECUPERAR_RA);
			hql.setString("ra", ra);
			
			coordenador = (Coordenador) hql.uniqueResult();
			System.out.println(" " + coordenador.toString());
			
		} catch (RuntimeException e) {
			System.out.println("Erro: " + e.getMessage());
		}finally{
			session.close();
		}
		
		return coordenador;
	}
	
	@SuppressWarnings("unchecked")
	public List<Coordenador> listarCoordenadores(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Coordenador> listaCoordenadores = null;
		
		try {
			Query hql = session.getNamedQuery(Constante.NamedQueries.COORDENADOR_RECUPERA_LISTA);
			listaCoordenadores = hql.list();
			
		} catch (RuntimeException e) {
			System.out.println("Erro: " + e.getMessage());
		}finally{
			session.close();
		}
		
		return listaCoordenadores;
		
	}
	
}
