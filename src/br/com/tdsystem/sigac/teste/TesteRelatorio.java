package br.com.tdsystem.sigac.teste;

import org.junit.Ignore;
import org.junit.Test;
import br.com.tdsystem.sigac.relatorios.FabricaRelatorio;

public class TesteRelatorio {
	
	@Test
	@Ignore
	public void testaRelatorio() throws Exception{
		
		try {
			FabricaRelatorio fabricaRelatorio = new FabricaRelatorio();
			
			fabricaRelatorio.imprimirUnidadesWEB();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
	
	@Test
	//@Ignore
	public void testaRelatorioTurno() throws Exception{
		
		try {
			FabricaRelatorio fabricaRelatorio = new FabricaRelatorio();
			
			fabricaRelatorio.imprimirTurnosWEB();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			System.out.println("Erro: " + e.getCause());
			System.out.println("Erro: " + e.getStackTrace());
		}
	}
}
