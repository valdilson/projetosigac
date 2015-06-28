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
import br.com.tdsystem.sigac.modelo.Status;
import br.com.tdsystem.sigac.modelo.StatusAprovacao;
import br.com.tdsystem.sigac.util.FacesUtil;
import br.com.tdsystem.sigac.modelo.negocio.FormataData;

@ManagedBean
@ViewScoped
public class AtividadeRealizadaMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private UploadedFile uploadfile;
	private Date dataUpload = null;
	private StreamedContent file, fileDownload;
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
		dataUpload = new Date();
		preencheListas();
		IPessoa usuario = FacesUtil.getUsuarioLogado().getUsuario();
		if (usuario instanceof Aluno) {
			aluno = (Aluno) usuario;			
			atualizarHorasRealizadas(aluno);
		}
		
		InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext())
				.getResourceAsStream("/resources/download/modeloAC.pdf");
        file = new DefaultStreamedContent(stream, "download/pdf", "downloaded_modeloAC.pdf");
        atualizarGrafico();
	}
	
	private static void atualizarHorasRealizadas(Aluno aluno) {
		aluno.setHorasRealizadas(0);
		for (AtividadeRealizada atividadeRealizada : aluno.getAtividadesRealizadas()) {
			if (atividadeRealizada.getStatusApovacao().equals(StatusAprovacao.APROVADO)) {
				aluno.setHorasRealizadas(aluno.getHorasRealizadas() + atividadeRealizada.getHorasAtividade());				
			}
		}
		if (aluno.getHorasRealizadas() >= aluno.getCurso().getHorasExigidas()) {
			aluno.setStatusApovacao(StatusAprovacao.APROVADO);
		} else {
			aluno.setStatusApovacao(StatusAprovacao.PENDENTE);			
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
			//aqui est�o os bytes do documento upado
			byte[] btFile = bos.toByteArray();
			atividadeRealizada.setComprovante(btFile); 
			
			FacesMessage message = new FacesMessage("Succeso", event.getFile()
					.getFileName() + atividadeRealizada.getComprovante());
			FacesContext.getCurrentInstance().addMessage(null, message);
			
		} catch (Exception ex) {
			Logger.getLogger(AtividadeRealizadaMB.class.getName()).log(Level.SEVERE, null, ex);
			FacesUtil.exibirMensagemErro("try :" + ex.getMessage());
		}
	}
	
	public void validaDocumento(AtividadeRealizada atividadeRealizada){
		atividadeRealizadaDAO = new AtividadeRealizadaDAO();
		atividadeRealizada.setStatusApovacao(StatusAprovacao.APROVADO);
		atividadeRealizadaDAO.editar(atividadeRealizada);
	}
	
	public void invalidaDocumento(AtividadeRealizada atividadeRealizada){
		atividadeRealizadaDAO = new AtividadeRealizadaDAO();
		atividadeRealizada.setStatusApovacao(StatusAprovacao.INVALIDO);
		atividadeRealizadaDAO.editar(atividadeRealizada);
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
			listaDeAtividades = atividadeDAO.listaAtividade(Status.ATIVO);
			IPessoa usuario = FacesUtil.getUsuarioLogado().getUsuario();
			listaDeAtividadesRealizadas = atividadeRealizadaDAO.listarAtividadesRealizadas(usuario);
			if (!listaDeAtividades.isEmpty()) {
				atividadeRealizada.setAtividade(listaDeAtividades.get(0));				
			}
			InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext())
					.getResourceAsStream("/resources/download/modeloAC.pdf");
	        file = new DefaultStreamedContent(stream, "download/pdf", "downloaded_modeloAC.pdf");
			
		} catch (Exception e) {
			System.out.println("Erro nulo: " + e.getMessage());
			System.out.println("Erro: " + e.getCause());
		}

	}
	
	private Boolean verificaAtividadeRepetida(){
		IPessoa usuario = FacesUtil.getUsuarioLogado().getUsuario();
		Boolean grava = Boolean.TRUE;
		atividadeRealizadaDAO = new AtividadeRealizadaDAO();
		List<AtividadeRealizada> listaDeAtividadesRealizadas = atividadeRealizadaDAO.
				listarAtividadesRealizadas(usuario);
		for (AtividadeRealizada ForAtividadeRealizada : listaDeAtividadesRealizadas) {
			if(ForAtividadeRealizada.getAtividade().getCodigo() == atividadeRealizada.
					getAtividade().getCodigo()){
				grava = Boolean.FALSE;
			}
		}
		return grava;
	}
	
	@SuppressWarnings("unused")
	private Boolean repete(){
		atividadeRealizadaDAO = new AtividadeRealizadaDAO();
		AtividadeRealizada compara = atividadeRealizadaDAO.pesquisaRepetida(atividadeRealizada.getAtividade().getCodigo(),
				atividadeRealizada.getAluno().getCodigo());
		if(atividadeRealizada.getAtividade().getCodigo() == compara.getAtividade().getCodigo() &&
				atividadeRealizada.getAluno().getCodigo() == compara.getAluno().getCodigo()){
			FacesUtil.exibirMensagemAlerta("Atividade j� realizada");
			return false;
		}else{
			return true;
		}
	}
	
	public void salvar() {
		if(verificaAtividadeRepetida()){
			try {
				atividadeRealizada.setDataUpload(FormataData.formataData(getDataUpload()));
				
				atividadeRealizadaDAO = new AtividadeRealizadaDAO();
				atividade = atividadeDAO.pesquisaCodigo(atividadeRealizada.getAtividade().getCodigo());
				atividadeRealizada.setHorasAtividade(atividade.getHoras());
				atividadeRealizada.setAluno((Aluno) loginMB.getUsuario().getUsuario());
				atividadeRealizada.setStatusApovacao(StatusAprovacao.PENDENTE);
				
				atividadeRealizadaDAO.salvar(atividadeRealizada);
				atualizarHorasRealizadas(aluno);
				preencheListas();
				atualizarGrafico();
				atividadeRealizada = new AtividadeRealizada();
				dataUpload = new Date();
				
				FacesUtil.exibirMensagemSucesso("Comprovante Submetido com sucesso!");
			} catch (Exception e) {
				FacesUtil.exibirMensagemErro("Erro ao submeter comprovante!"
						+ e.getMessage()+e.getCause());
				FacesUtil.exibirMensagemErro("Cause!"
						+ e.getCause());
			}
		}else{
			FacesUtil.exibirMensagemAlerta("Atividade j� lan�ada!\n"
											+ "ou j� atingiu status aprovado!");
		}

	}
	
	public void excluir(AtividadeRealizada atividadeRealizada) {

		try {
			atividadeRealizadaDAO = new AtividadeRealizadaDAO();
			atividadeRealizadaDAO.excluir(atividadeRealizada);
			FacesUtil.exibirMensagemSucesso("Exclus�o feita com Sucesso!");
			listaDeAtividadesRealizadas.remove(atividadeRealizada);
			preencheListas();
			atualizarGrafico();
			atualizarHorasRealizadas(aluno);
		} catch (RuntimeException e) {
			FacesUtil.exibirMensagemErro("Erro ao excluir registro!"
					+ e.getMessage());
		}
	}
	
	public void editar(){
		if(verificaAtividadeRepetida()){
			try {
				atividadeRealizadaDAO = new AtividadeRealizadaDAO();
				atividadeRealizadaDAO.editar(atividadeRealizada);
				atividadeRealizada = new AtividadeRealizada();
				atualizarGrafico();
			} catch (Exception e) {
				FacesUtil.exibirMensagemErro("Erro ao editar a atividade!"
						+ e.getMessage()+e.getCause());
				FacesUtil.exibirMensagemErro("Cause!"
						+ e.getCause());
			}
		}
	}
	
	public void atualizarGrafico() {
        
		graficoAtividades = new PieChartModel();
         
        graficoAtividades.setTitle("Rela��o de Atividades mais Realizdas");
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
	
	public void atualizarData() {
		System.out.println(atividadeRealizada.getAtividade().getDataEvento());
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
