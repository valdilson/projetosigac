package br.com.tdsystem.sigac.teste;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.tdsystem.sigac.dao.PeriodoDAO;
import br.com.tdsystem.sigac.modelo.Status;
import br.com.tdsystem.sigac.modelo.Periodo;

public class TestePeriodo {

	@Test
	//@Ignore
	public void testeSalvar() {
		
		Periodo periodo = new Periodo();
		PeriodoDAO periodoDAO = new PeriodoDAO();
		
		periodo.setNome("Periodo1");
		periodo.setStatus(Status.ATIVO);		
		periodoDAO.salvar(periodo);
		
	}
	
	@Test
	@Ignore
	 public void testeListar(){
		 
		 PeriodoDAO periodoDAO = new PeriodoDAO();
		 List<Periodo> listaPeriodo = periodoDAO.listaPeriodo();		 
		 for (Periodo periodo : listaPeriodo) {
			
			 System.out.println(periodo.toString());
		}
		 
	 }
}
