package br.com.tdsystem.sigac.dao;

import javax.persistence.NoResultException;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.tdsystem.sigac.modelo.IPessoa;
import br.com.tdsystem.sigac.modelo.PerfilEnum;
import br.com.tdsystem.sigac.modelo.Usuario;
import br.com.tdsystem.sigac.util.Constante;
import br.com.tdsystem.sigac.util.HibernateUtil;

public class LoginDAO {

	public Usuario recuperarUsuario(String username, String password, PerfilEnum perfil) throws NoResultException {
		
		Query hql = null;
		IPessoa pessoa = null;
		Session secao = HibernateUtil.getSessionFactory().openSession();
		
		switch (perfil) {
			case ALUNO:
				hql = secao.getNamedQuery(Constante.NamedQueries.ALUNO_RECUPERARPORLOGIN);
				break;
			case COORDENADOR:
				hql = secao.getNamedQuery("Coordenador.recuperarPorLogin");
				break;
	
			default:
			break;
		}
		
		hql.setString("username", username);
		pessoa = (IPessoa) hql.uniqueResult();
		if (pessoa != null && pessoa.getPassword().equals(password)) {
			Usuario u = new Usuario();
			u.setUsuario(pessoa);
			u.setPerfil(perfil);
			return u;
		}
		throw new NoResultException();
	}
	
}
