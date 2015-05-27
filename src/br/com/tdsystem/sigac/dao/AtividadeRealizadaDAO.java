package br.com.tdsystem.sigac.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.tdsystem.sigac.modelo.AtividadeRealizada;
import br.com.tdsystem.sigac.util.HibernateUtil;

public class AtividadeRealizadaDAO {

	public void salvar(AtividadeRealizada atividadeRealizada){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.save(atividadeRealizada);
			transaction.commit();
			System.out.println("Gravou!");
		} catch (RuntimeException e) {
			System.out.println("Erro :" + e.getMessage());
			transaction.rollback();
		}finally{
			session.close();
		}
	}
}
