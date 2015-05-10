package br.com.tdsystem.sigac.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.tdsystem.sigac.modelo.Turma;
import br.com.tdsystem.sigac.util.HibernateUtil;

public class TurmaDAO {
	
	// Metodo para salvar uma nova turma
	public void salvar(Turma turma) {
		
		// Cria uma session atraves do fabrica de session com metodo criado no hibernate.cfg.xml
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		//Hibernate trabalha com conceito transações, ou seja, tenta-se realizar 
		//um processo, caso de errado ele desfaz.
		
		//tenta
		try {
			transaction = session.beginTransaction();
			session.save(turma);
			transaction.commit(); // commit confirma a transaction.
			System.out.println("Gravou");
			
		// em caso de erro 
		} catch (RuntimeException ex) {
			if (transaction != null) {
				System.out.println("Errou" +ex.getMessage());
				transaction.rollback(); // em caso de algum erro, o rolback desfaz tudo.
				
			}
			throw ex; //repropaga a excecao
		} finally {
			session.close(); // fecha a session
		}
	}
		//Confirma que a consulta sempre vai retornar uma lista de fabricantes e nao uma lista generica 
	@SuppressWarnings("unchecked")
	//Metodo para listar
	public List<Turma> listaTurma() {
		// Cria uma session atraves do fabrica de session com metodo criado no hibernate.cfg.xml
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Turma> turmas = null;
		try {
													//Nome da NamedQuery criado no modelo 
			Query consulta = session.getNamedQuery("Turma.lista");
			turmas = consulta.list(); // variavel tipo lista que recebe a consulta 
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			session.close();
		}
		return turmas;
	}

	//Metodo para buscar por codigo		paramentro tipo long
	public Turma pesquisaCodigo(Long codigo) {
		// Cria uma session atraves do fabrica de session com metodo criado no hibernate.cfg.xml
		Session session = HibernateUtil.getSessionFactory().openSession();
		Turma turmas = null;
		try {
													//Nome da NamedQuery criado no modelo 
			Query consulta = session.getNamedQuery("Turma.codigo");
			consulta.setLong("codigo", codigo); // (campo digitado , valor variavel)
			turmas = (Turma) consulta.uniqueResult(); // busca por chave primaria retorna sempre valor unico
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			session.close();
		}
		return turmas;
	}

	//Metodo para excluir
	public void excluir(Turma turma) {
		// Cria uma session atraves do fabrica de session com metodo criado no hibernate.cfg.xml
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.delete(turma);
			transaction.commit();
		} catch (RuntimeException ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw ex;
		} finally {
			session.close();
		}

	}
	
	public void editar(Turma turma) {
		//Metodo para excluir receendo o codigo, com buscarPorCodigo dentro do metodo
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.update(turma);

			transaction.commit();
		} catch (RuntimeException ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw ex;
		} finally {
			session.close();
		}
	}

}