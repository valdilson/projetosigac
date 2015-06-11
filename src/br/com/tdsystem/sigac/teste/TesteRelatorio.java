package br.com.tdsystem.sigac.teste;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.tdsystem.sigac.modelo.Coordenador;
import br.com.tdsystem.sigac.modelo.Curso;
import br.com.tdsystem.sigac.relatorios.CoordenadorRelatorio;
import br.com.tdsystem.sigac.relatorios.CursoRelatorio;
import br.com.tdsystem.sigac.util.FabricaRelatorio;

public class TesteRelatorio {
	
	@Test
	//@Ignore
	public void TesteCoordenador() {
		CoordenadorRelatorio c = new CoordenadorRelatorio();		
		FabricaRelatorio relatorio = new FabricaRelatorio();
		List<Coordenador> lista = relatorio.listarDados(c);
		System.out.println(lista);
	}

	@Test
	@Ignore
	public void TesteCurso() {

		CursoRelatorio c = new CursoRelatorio();
		FabricaRelatorio relatorio = new FabricaRelatorio();
		List<Curso> lista = relatorio.listarDados(c);
		System.out.println(lista);
	}
}
