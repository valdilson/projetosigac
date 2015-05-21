package br.com.tdsystem.sigac.teste;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.tdsystem.sigac.dao.CoordenadorDAO;
import br.com.tdsystem.sigac.dao.UnidadeDAO;
import br.com.tdsystem.sigac.modelo.Coordenador;
import br.com.tdsystem.sigac.modelo.Unidade;
import br.com.tdsystem.sigac.util.CriptografaSenhaMD5;

public class CoordenadorTeste {
	
	@Test
	//@Ignore
	public void salvarTeste() throws NoSuchAlgorithmException{
		Coordenador coordenador = new Coordenador();
		CoordenadorDAO coordenadorDAO = new CoordenadorDAO();
		UnidadeDAO unidadeDAO = new UnidadeDAO();
		Unidade unidade = unidadeDAO.pesquisaCodigo(1l);
		
		coordenador.setEmail("efreim@anhanguera.com");
		coordenador.setNome("Efreim Louzada");
		coordenador.setUnidade(unidade);
		coordenador.setPassword(CriptografaSenhaMD5.converteSenhaMD5("123"));
		coordenadorDAO.salvar(coordenador);
	}
	
	@Test
	@Ignore
	public void editarTeste() throws NoSuchAlgorithmException{
		Coordenador coordenador = new Coordenador();
		CoordenadorDAO coordenadorDAO = new CoordenadorDAO();
		UnidadeDAO unidadeDAO = new UnidadeDAO();
		Unidade unidade = unidadeDAO.pesquisaCodigo(1l);
		
		coordenador.setCodigo(1l);
		coordenador.setEmail("editado efreim@anhanguera.com");
		coordenador.setNome("Efreim Louzada Editado");
		coordenador.setUnidade(unidade);
		coordenador.setPassword(CriptografaSenhaMD5.converteSenhaMD5("321"));
		coordenadorDAO.editar(coordenador);
	}
	
	@Test
	@Ignore
	public void excluirTeste(){
		
		CoordenadorDAO coordenadorDAO = new CoordenadorDAO();
		Coordenador coordenador = coordenadorDAO.pesquisaCodigo(1l);
		coordenadorDAO.excluir(coordenador);
	}
	
	@Test
	@Ignore
	public void pesquisaListaTeste(){
		CoordenadorDAO coordenadorDAO = new CoordenadorDAO();
		List<Coordenador> listaDeCoordenadores = 
				coordenadorDAO.listarCoordenadores();
		for (Coordenador coordenador : listaDeCoordenadores) {
			System.out.println(": " + coordenador.toString());
		}
	}
	
	@Test
	public void pesquisaCodigoTeste(){
		CoordenadorDAO coordenadorDAO = new CoordenadorDAO();
		Coordenador coordenador = coordenadorDAO.pesquisaCodigo(2l);
	}
	
}
