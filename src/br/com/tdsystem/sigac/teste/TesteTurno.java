package br.com.tdsystem.sigac.teste;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.tdsystem.sigac.dao.TurnoDAO;
import br.com.tdsystem.sigac.modelo.Status;
import br.com.tdsystem.sigac.modelo.Turno;

public class TesteTurno {

	@Test
	//@Ignore
	public void testeSalvar() {
		
		Turno turno = new Turno();
		TurnoDAO turnoDAO = new TurnoDAO();
		
		turno.setNome("Manha");
		turno.setStatus(Status.ATIVO);		
		turnoDAO.salvar(turno);
		
		turno = new Turno();
		turno.setNome("Tarde");
		turno.setStatus(Status.ATIVO);		
		turnoDAO.salvar(turno);
		
		turno = new Turno();
		turno.setNome("Noite");
		turno.setStatus(Status.ATIVO);		
		turnoDAO.salvar(turno);
		
	}
	
	@Test
	@Ignore
	 public void testeListar(){
		 
		 TurnoDAO turnoDAO = new TurnoDAO();
		 List<Turno> listaTurno = turnoDAO.listaTurno();		 
		 for (Turno turno : listaTurno) {
			
			 System.out.println(turno.toString());
		}
		 
	 }
}
