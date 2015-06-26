package br.com.tdsystem.sigac.relatorios;

import java.util.List;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.com.tdsystem.sigac.dao.TurnoDAO;
import br.com.tdsystem.sigac.dao.UnidadeDAO;
import br.com.tdsystem.sigac.modelo.Turno;
import br.com.tdsystem.sigac.modelo.Unidade;

public class FabricaRelatorio {

	private String path; // Caminho base

	// Caminho para o package onde estão armazenados os relatorios Jasper
	private String pathToReportPackage;

	// Recupera os caminhos para que a classe possa encontrar os relatï¿½rios
	public FabricaRelatorio() {
		this.path = this.getClass().getClassLoader().getResource("").getPath();
		this.pathToReportPackage = this.path + "br/com/tdsystem//sigac/jasper/";
		System.out.println(path);
	}


	// Imprime/gera uma lista de Unidades
	public byte[] imprimirUnidadesWEB() throws Exception{
					
		UnidadeDAO unidadeDAO = new UnidadeDAO();
		List<Unidade> listaDeUnidades = unidadeDAO.listarUnidade();
		
		JasperReport report = JasperCompileManager.compileReport
				(this.getPathToReportPackage() + "relUnidades.jrxml");
					
		JasperPrint print = JasperFillManager.fillReport(report, null, 
				new JRBeanCollectionDataSource(listaDeUnidades));
			 
		return JasperExportManager.exportReportToPdf(print);	
	}
	
	// Imprime/gera uma lista de Unidades
		public byte[] imprimirTurnosWEB() throws Exception{
						
			TurnoDAO turnoDAO = new TurnoDAO();
			List<Turno> listaDeTurnos = turnoDAO.listaTurno();
			
			JasperReport report = JasperCompileManager.compileReport
					(this.getPathToReportPackage() + "relTurno.jrxml");
						
			JasperPrint print = JasperFillManager.fillReport(report, null, 
					new JRBeanCollectionDataSource(listaDeTurnos));
				 
			//JasperExportManager.exportReportToPdfFile(print, "D:/Relatorio_de_Unidades.pdf");
			return JasperExportManager.exportReportToPdf(print);
		}

	public String getPathToReportPackage() {
		return this.pathToReportPackage;
	}

	public String getPath() {
		return this.path;
	}

}
