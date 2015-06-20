package br.com.tdsystem.sigac.teste;

import java.util.List;

import org.junit.Test;

import br.com.tdsystem.sigac.dao.UnidadeDAO;
import br.com.tdsystem.sigac.modelo.Unidade;
import br.com.tdsystem.sigac.relatorios.FabricaRelatorio;

public class TesteRelatorio {
	
	@Test
	public void testaRelatorio() throws Exception{
		UnidadeDAO unidadeDAO = new UnidadeDAO();
		List<Unidade> listaDeUnidades = unidadeDAO.listarUnidade();
		
		try {
			FabricaRelatorio fabricaRelatorio = new FabricaRelatorio();
			
			fabricaRelatorio.imprimirUnidades(listaDeUnidades);
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		
	}
}
