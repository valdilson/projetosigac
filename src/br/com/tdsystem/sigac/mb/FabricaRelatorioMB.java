package br.com.tdsystem.sigac.mb;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.tdsystem.sigac.dao.UnidadeDAO;
import br.com.tdsystem.sigac.modelo.Unidade;
import br.com.tdsystem.sigac.relatorios.FabricaRelatorio;
import br.com.tdsystem.sigac.util.FacesUtil;

@ManagedBean
@ViewScoped
public class FabricaRelatorioMB implements Serializable {
	
	private StreamedContent file;
	
	private static final long serialVersionUID = 1L;
	
	public void gerarUnidadesPDF(){
		
		try {
			
			UnidadeDAO unidadeDAO = new UnidadeDAO();
			List<Unidade> listaDeUnidades = unidadeDAO.listarUnidade();
			FacesContext context = FacesContext.getCurrentInstance();

			HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

			ServletOutputStream responseStream = response.getOutputStream();

			InputStream caminho = getClass().getResourceAsStream("../jasper/relUnidades.jrxml");

			response.setContentType("application/pdf");
			
			response.setHeader("Content-Disposition","attachment; filename=\"relUnidades.pdf\"");

			//Abaixo temos o padrao para invocao

			JasperReport pathReport = JasperCompileManager.compileReport(caminho);

			//relatorio gerado

			JasperPrint preencher = JasperFillManager.fillReport(pathReport, null,
					new JRBeanCollectionDataSource(listaDeUnidades));

			JasperExportManager.exportReportToPdfStream(preencher,responseStream);

			responseStream.flush();

			responseStream.close();

			context.renderResponse();

			context.responseComplete();
			
		} catch (Exception e) {
			FacesUtil.exibirMensagemErro("Erro: " + e.getMessage());
		}
	}
	
	public void relatorioView(int relatorio){
		FabricaRelatorio objrelatorio = new FabricaRelatorio();
		
		String nome = null;
		byte[] documento = null;
		try {
			switch (relatorio) {
			case 0:				
				documento = objrelatorio.imprimirUnidadesWEB();
				nome = "relatorio-unidades";
				break;
				
			case 1:
				documento = objrelatorio.imprimirTurnosWEB();
				nome = "relatorio-turnos";
				break;
				
			case 2:
				documento = objrelatorio.imprimirTurnosWEB();
				nome = "";
				break;

			default:
				break;
			}
			InputStream stream = new ByteArrayInputStream(documento);
			file = new DefaultStreamedContent(stream, "download/pdf", nome + ".pdf");
			System.out.println("nao deu erro");
		} catch (Exception e) {
			FacesUtil.exibirMensagemErro("Erro: \n" + e.getCause()+"\n");
			System.out.println("Erro: \n" + e.getCause()+"\n");
		}
	}

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}
}
