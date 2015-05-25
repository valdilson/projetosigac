package br.com.tdsystem.sigac.util;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.tdsystem.sigac.dao.CoordenadorDAO;
import br.com.tdsystem.sigac.dao.CursoDAO;
import br.com.tdsystem.sigac.modelo.Atividade;
import br.com.tdsystem.sigac.modelo.Coordenador;
import br.com.tdsystem.sigac.modelo.Curso;
import br.com.tdsystem.sigac.relatorios.CoordenadorRelatorio;
import br.com.tdsystem.sigac.relatorios.CursoRelatorio;
import br.com.tdsystem.sigac.util.HibernateUtil;




public class FabricaRelatorio {

	public List<Coordenador> listarDados(CoordenadorRelatorio coordenador) {
		CoordenadorDAO coordenadorDAO = new CoordenadorDAO();
		List<Coordenador> listaCoordenador = coordenadorDAO
				.listarCoordenadores();

		return listaCoordenador;
	}

	public List<Curso> listarDados(CursoRelatorio curso) {
		CursoDAO cursoDAO = new CursoDAO();
		List<Curso> listaCurso = cursoDAO.listaCurso();
		return listaCurso;
	}

}
