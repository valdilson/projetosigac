package br.com.tdsystem.sigac.mb;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.chart.PieChartModel;

import br.com.tdsystem.sigac.dao.AtividadeDAO;
import br.com.tdsystem.sigac.dao.AtividadeRealizadaDAO;
import br.com.tdsystem.sigac.modelo.Aluno;
import br.com.tdsystem.sigac.modelo.Atividade;
import br.com.tdsystem.sigac.modelo.AtividadeRealizada;
import br.com.tdsystem.sigac.modelo.IPessoa;
import br.com.tdsystem.sigac.util.FacesUtil;
import br.com.tdsystem.sigac.util.FormataData;

@ManagedBean
@ViewScoped
public class AtividadeRealizadaMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private UploadedFile uploadfile;
	private Date dataEvento = null, dataUpload = null;
	private StreamedContent file;
	private StreamedContent fileDownload;
	private InputStream is = null;
	private ByteArrayOutputStream bos = null;
	
	private AtividadeRealizada atividadeRealizada = null;
	private Aluno aluno = null;
	private Atividade atividade = null;

	private List<Aluno> listaDeAlunos = null;
	private List<Atividade> listaDeAtividades = null;
	private List<Atividade> filtroDeAtividades = null;
	
	private List<AtividadeRealizada> listaDeAtividadesRealizadas = null;
	private List<AtividadeRealizada> filtroDeAtividadesRealizadas = null;

	private AtividadeDAO atividadeDAO = null;
	private AtividadeRealizadaDAO atividadeRealizadaDAO = null;
	
	private PieChartModel graficoAtividades;
	
	public AtividadeRealizadaMB() {
		atividadeRealizada = new AtividadeRealizada();
		dataEvento = new Date();
		dataUpload = new Date();
		preencheListas();
		IPessoa usuario = FacesUtil.getUsuarioLogado().getUsuario();
		if (usuario instanceof Aluno) {
			aluno = (Aluno) usuario;			
			aluno.setHorasRealizadas(0);
			atualizarHorasRealizadas(aluno);
		}
		InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext())
				.getResourceAsStream("/resources/download/modeloAC.pdf");
        file = new DefaultStreamedContent(stream, "download/pdf", "downloaded_modeloAC.pdf");
        atualizarGrafico();
	}
	
	private static void atualizarHorasRealizadas(Aluno aluno) {
		for (AtividadeRealizada atividadeRealizada : aluno.getAtividadesRealizadas()) {
			aluno.setHorasRealizadas(aluno.getHorasRealizadas() + atividadeRealizada.getHorasAtividade());
		}
	}
	
	@ManagedProperty(value="#{loginMB}")
	private LoginMB loginMB;

	//UploadArquivo
	public void handleFileUpload(FileUploadEvent event) {

		try {

			is = event.getFile().getInputstream();
			bos = new ByteArrayOutputStream();
			int next = is.read();
			while (next > -1 ) {
				bos.write(next);
				next = is.read();
			}
			bos.flush();
			//aqui estão os bytes do documento upado
			byte[] btFile = bos.toByteArray();
			atividadeRealizada.setComprovante(btFile); 
			
			FacesMessage message = new FacesMessage("Succeso", event.getFile()
					.getFileName() + atividadeRealizada.getComprovante());
			FacesContext.getCurrentInstance().addMessage(null, message);
			
		} catch (Exception ex) {
			Logger.getLogger(AtividadeRealizadaMB.class.getName()).log(
					Level.SEVERE, null, ex);
			FacesUtil.exibirMensagemErro("try :" + ex.getMessage());
		}
	}
	
	public String formataData(){
		Date dataAtual =  new Date();
		String data = FormataData.formataData(dataAtual);
		return data;
	}
	
	public void selecionaEdicao(AtividadeRealizada atividadeRealizada) {
		this.atividadeRealizada = atividadeRealizada;
	}

	public void preencheListas() {
		try {
			atividadeDAO = new AtividadeDAO();
			atividadeRealizadaDAO = new AtividadeRealizadaDAO();
			listaDeAtividades = atividadeDAO.listaAtividade();
			IPessoa usuario = FacesUtil.getUsuarioLogado().getUsuario();
			listaDeAtividadesRealizadas = atividadeRealizadaDAO.listarAtividadesRealizadas(usuario);
			
		} catch (Exception e) {
			System.out.println("Erro nulo: " + e.getMessage());
			System.out.println("Erro: " + e.getCause());
		}

	}
	
	public void verificaAtividadeRepetida(){
		IPessoa usuario = FacesUtil.getUsuarioLogado().getUsuario();
		Boolean grava = Boolean.TRUE;
		atividadeRealizadaDAO = new AtividadeRealizadaDAO();
		List<AtividadeRealizada> listaDeAtividadesRealizadas = atividadeRealizadaDAO.listarAtividadesRealizadas(usuario);
		for (AtividadeRealizada ForAtividadeRealizada : listaDeAtividadesRealizadas) {
			if(ForAtividadeRealizada.getAtividade().getCodigo() == atividadeRealizada.getAtividade().getCodigo()){
				grava = Boolean.FALSE;
			}
		}
		if(grava){
			salvar();
		}else{
			FacesUtil.exibirMensagemAlerta("Atividade já lançada!");
			atividadeRealizada = new AtividadeRealizada();
		}
	}
	
	public void salvar() {
		
		try {
			atividadeRealizada.setDataEvento(FormataData.formataData(getDataEvento()));
			atividadeRealizada.setDataUpload(FormataData.formataData(getDataUpload()));
			
			atividadeRealizadaDAO = new AtividadeRealizadaDAO();
			atividade = atividadeDAO.pesquisaCodigo(atividadeRealizada.getAtividade().getCodigo());
			atividadeRealizada.setHorasAtividade(atividade.getHoras());
			atividadeRealizada.setAluno((Aluno) loginMB.getUsuario().getUsuario());
			
			atividadeRealizadaDAO.salvar(atividadeRealizada);
			//atualizarHorasRealizadas(atividadeRealizada.getAluno());
			preencheListas();
			atualizarGrafico();
			FacesUtil.exibirMensagemSucesso("Comprovante Submetido com sucesso!");
		} catch (Exception e) {
			FacesUtil.exibirMensagemErro("Erro ao submeter comprovante!"
					+ e.getMessage()+e.getCause());
			FacesUtil.exibirMensagemErro("Cause!"
					+ e.getCause());
		}

	}
	
	public void excluir(AtividadeRealizada atividadeRealizada) {

		try {
			atividadeRealizadaDAO = new AtividadeRealizadaDAO();
			atividadeRealizadaDAO.excluir(atividadeRealizada);
			FacesUtil.exibirMensagemSucesso("Exclusão feita com Sucesso!");
			listaDeAtividadesRealizadas.remove(atividadeRealizada);
			preencheListas();
			atualizarGrafico();
		} catch (RuntimeException e) {
			FacesUtil.exibirMensagemErro("Erro ao excluir registro!"
					+ e.getMessage());
		}
	}
	
	public void editar(){
		try {
			atividadeRealizadaDAO = new AtividadeRealizadaDAO();
			atividadeRealizadaDAO.editar(atividadeRealizada);
			atividadeRealizada = new AtividadeRealizada();
			atualizarGrafico();
		} catch (Exception e) {
			FacesUtil.exibirMensagemErro("Erro ao tentar editar registro" + e.getCause());
		}
		
	}
	
	public void atualizarGrafico() {
        
		graficoAtividades = new PieChartModel();
         
        graficoAtividades.setTitle("Relação de Atividades");
        graficoAtividades.setLegendPosition("w");
        graficoAtividades.setShowDataLabels(Boolean.TRUE);
        Map<String, Integer> filter = new HashMap<String, Integer>();
        
        
        List<Atividade> atividades = atividadeDAO.listaAtividade();
        List<AtividadeRealizada> atividadesRealizadas = atividadeRealizadaDAO.listarAtividadesRealizadas(
        		FacesUtil.getUsuarioLogado().getUsuario());
        
        //TODO nao permitir nomes de atividades repetidas
        for (Atividade atividade : atividades) {
        	filter.put(atividade.getNome(), 0);
        }
        Integer cont = null;
        for (AtividadeRealizada atividade : atividadesRealizadas) {
        	cont = filter.get(atividade.getAtividade().getNome());
        	filter.put(atividade.getAtividade().getNome(), ++cont);
		}
        
        Set<Entry<String, Integer>> entrySet = filter.entrySet();
        for (Entry<String, Integer> entry : entrySet) {
			graficoAtividades.set(entry.getKey(), entry.getValue());
		}
        
    }
	
	public void download(AtividadeRealizada atividadeRealizada) {
		InputStream stream = new ByteArrayInputStream(atividadeRealizada.getComprovante());
        fileDownload = new DefaultStreamedContent(stream, "download/pdf", "atividade-" + atividadeRealizada.getCodigo() + ".pdf");
	}

	public List<AtividadeRealizada> getListaDeAtividadesRealizadas() {
		return listaDeAtividadesRealizadas;
	}

	public void setListaDeAtividadesRealizadas(
			List<AtividadeRealizada> listaDeAtividadesRealizadas) {
		this.listaDeAtividadesRealizadas = listaDeAtividadesRealizadas;
	}

	public List<AtividadeRealizada> getFiltroDeAtividadesRealizadas() {
		return filtroDeAtividadesRealizadas;
	}

	public void setFiltroDeAtividadesRealizada(
			List<AtividadeRealizada> filtroDeAtividadesRealizadas) {
		this.filtroDeAtividadesRealizadas = filtroDeAtividadesRealizadas;
	}

	public StreamedContent getFile() {
        return file;
    }

	public AtividadeRealizada getAtividadeRealizada() {
		return atividadeRealizada;
	}

	public void setAtividadeRealizada(AtividadeRealizada atividadeRealizada) {
		this.atividadeRealizada = atividadeRealizada;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Atividade> getListaDeAtividades() {
		return listaDeAtividades;
	}

	public void setListaDeAtividades(List<Atividade> listaDeAtividades) {
		this.listaDeAtividades = listaDeAtividades;
	}

	public List<Atividade> getFiltroDeAtividades() {
		return filtroDeAtividades;
	}

	public void setFiltroDeAtividades(List<Atividade> filtroDeAtividades) {
		this.filtroDeAtividades = filtroDeAtividades;
	}


	public void setAtividadeDAO(AtividadeDAO atividadeDAO) {
		this.atividadeDAO = atividadeDAO;
	}

	public List<Aluno> getListaDeAlunos() {
		return listaDeAlunos;
	}

	public void setListaDeAlunos(List<Aluno> listaDeAlunos) {
		this.listaDeAlunos = listaDeAlunos;
	}

	public UploadedFile getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(UploadedFile uploadfile) {
		this.uploadfile = uploadfile;
	}

	public LoginMB getLoginMB() {
		return loginMB;
	}

	public void setLoginMB(LoginMB loginMB) {
		this.loginMB = loginMB;
	}

	public Date getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(Date dataEvento) {
		this.dataEvento = dataEvento;
	}

	public Date getDataUpload() {
		return dataUpload;
	}

	public void setDataUpload(Date dataUpload) {
		this.dataUpload = dataUpload;
	}

	public StreamedContent getFileDownload() {
		return fileDownload;
	}

	public void setFileDownload(StreamedContent fileDownload) {
		this.fileDownload = fileDownload;
	}

	public PieChartModel getGraficoAtividades() {
		return graficoAtividades;
	}

	public void setGraficoAtividades(PieChartModel graficoAtividades) {
		this.graficoAtividades = graficoAtividades;
	}

}
