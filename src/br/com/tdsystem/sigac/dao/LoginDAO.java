package br.com.tdsystem.sigac.dao;

import java.security.NoSuchAlgorithmException;

import javax.persistence.NoResultException;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.tdsystem.sigac.modelo.IPessoa;
import br.com.tdsystem.sigac.modelo.PerfilEnum;
import br.com.tdsystem.sigac.modelo.Usuario;
import br.com.tdsystem.sigac.util.Constante;
import br.com.tdsystem.sigac.modelo.negocio.CriptografaSenhaMD5;
import br.com.tdsystem.sigac.util.HibernateUtil;

public class LoginDAO {

	public Usuario recuperarUsuario(String ra, String password, PerfilEnum perfil) throws NoResultException, NoSuchAlgorithmException {
		
		Query hql = null;
		Session secao = HibernateUtil.getSessionFactory().openSession();
		
		switch (perfil) {
			case ALUNO:
				hql = secao.getNamedQuery(Constante.NamedQueries.ALUNO_RECUPERARPORLOGIN);
				break;
				
			case COORDENADOR:
				hql = secao.getNamedQuery(Constante.NamedQueries.COORDENADOR_RECUPERAR_RA);
				break;
	
			default:
			break;
		}
		
		hql.setString("ra", ra);
		IPessoa iPessoa = null;
		iPessoa = (IPessoa) hql.uniqueResult();
		
		if (iPessoa != null && iPessoa.getPassword().equals(CriptografaSenhaMD5.converteSenhaMD5(password))) {
			Usuario u = new Usuario();
			u.setUsuario(iPessoa);
			u.setPerfil(perfil);
			return u;
		}
		
		
		throw new NoResultException();
	}
	
}
