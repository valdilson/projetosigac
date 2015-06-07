package br.com.tdsystem.sigac.mb;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.tdsystem.sigac.dao.AlunoDAO;
import br.com.tdsystem.sigac.dao.AtividadeDAO;
import br.com.tdsystem.sigac.dao.AtividadeRealizadaDAO;
import br.com.tdsystem.sigac.modelo.Aluno;
import br.com.tdsystem.sigac.modelo.Atividade;
import br.com.tdsystem.sigac.modelo.AtividadeRealizada;
import br.com.tdsystem.sigac.util.FacesUtil;

import javax.servlet.ServletContext;

@ManagedBean
@ViewScoped
public class AtividadeRealizadaMB implements Serializable {
	private static final long serialVersionUID = 1L;

	public AtividadeRealizadaMB() {
		InputStream stream = ((ServletContext)FacesContext.getCurrentInstance()
				.getExternalContext().getContext()).getResourceAsStream("/resources/imagens/logoPrincipal.jpg");
        file = new DefaultStreamedContent(stream, "image/jpg", "downloaded_logoPrincipal.jpg");
		atividadeRealizada = new AtividadeRealizada();
		preencheListas();
	}

	private StreamedContent file;
	
	private AtividadeRealizada atividadeRealizada = null;
	private Aluno aluno = null;

	private List<Aluno> listaDeAlunos = null;
	private List<Atividade> listaDeAtividades = null;
	private List<Atividade> filtroDeAtividades = null;

	private AlunoDAO alunoDAO = null;
	private AtividadeDAO atividadeDAO = null;
	private AtividadeRealizadaDAO atividadeRealizadaDAO = null;


	 public void handleFileUpload(FileUploadEvent event) {
	        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
	        FacesContext.getCurrentInstance().addMessage(null, message);
	    }
	
	public void preencheListas() {
		try {
			alunoDAO = new AlunoDAO();
			atividadeDAO = new AtividadeDAO();
			
			listaDeAlunos = alunoDAO.listarAlunos();
			listaDeAtividades = atividadeDAO.listaAtividade();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			System.out.println("Erro: " + e.getCause());
		}

	}
    
	public void salvar() {
		
		try {			
			atividadeRealizadaDAO = new AtividadeRealizadaDAO();
			atividadeRealizadaDAO.salvar(atividadeRealizada);
			FacesUtil
					.exibirMensagemSucesso("Comprovante Submetido com sucesso!");
		} catch (Exception e) {
			FacesUtil.exibirMensagemErro("Erro ao submeter comprovante!"
					+ e.getMessage());
		}

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

	public AlunoDAO getAlunoDAO() {
		return alunoDAO;
	}

	public void setAlunoDAO(AlunoDAO alunoDAO) {
		this.alunoDAO = alunoDAO;
	}

	public AtividadeDAO getAtividadeDAO() {
		return atividadeDAO;
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

}
