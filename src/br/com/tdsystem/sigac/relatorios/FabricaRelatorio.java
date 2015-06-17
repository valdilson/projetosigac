package br.com.tdsystem.sigac.relatorios;

import java.io.ByteArrayOutputStream;
import java.util.List;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.view.JasperViewer;
import br.com.tdsystem.sigac.dao.UnidadeDAO;
import br.com.tdsystem.sigac.modelo.Unidade;

public class FabricaRelatorio {

	private String path; // Caminho base

	private String pathToReportPackage; // Caminho para o package onde estão
										// armazenados os relatorios Jasper

	// Recupera os caminhos para que a classe possa encontrar os relatórios
	public FabricaRelatorio() {
		this.path = this.getClass().getClassLoader().getResource("").getPath();
		this.pathToReportPackage = this.path + "br/com/tdsystem//sigac/jasper/";
		System.out.println(path);
	}

	// Imprime/gera uma lista de Clientes
	@SuppressWarnings("deprecation")
	public void imprimirUnidades(List<Unidade> listaDeUnidades)
			throws Exception {
		JasperReport report = JasperCompileManager.compileReport(this
				.getPathToReportPackage() + "relUnidades.jrxml");

		JasperPrint print = JasperFillManager.fillReport(report, null,
				new JRBeanCollectionDataSource(listaDeUnidades));

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		JRPdfExporter exp = new JRPdfExporter();
		exp.setParameter(JRExporterParameter.JASPER_PRINT, print);
		exp.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
		exp.exportReport();

		// JasperExportManager.exportReportToPdfFile(print,
		// "D:/Relatorio_de_Unidades.pdf");
		// JasperViewer.viewReport(print, true);
	}

	// Imprime/gera uma lista de Unidades
	public void imprimirUnidadesWEB() throws Exception{
					
		UnidadeDAO unidadeDAO = new UnidadeDAO();
		List<Unidade> listaDeUnidades = unidadeDAO.listarUnidade();
		
		JasperReport report = JasperCompileManager.compileReport
				(this.getPathToReportPackage() + "relUnidades.jrxml");
					
		JasperPrint print = JasperFillManager.fillReport(report, null, 
				new JRBeanCollectionDataSource(listaDeUnidades));
			 
		JasperExportManager.exportReportToPdfFile(print, "D:/Relatorio_de_Unidades.pdf");
		//JasperViewer.viewReport(print, true);	
	}

	public String getPathToReportPackage() {
		return this.pathToReportPackage;
	}

	public String getPath() {
		return this.path;
	}

}
