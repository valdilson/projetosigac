package br.com.tdsystem.sigac.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.tdsystem.sigac.dao.AtividadeDAO;
import br.com.tdsystem.sigac.modelo.Atividade;
import br.com.tdsystem.sigac.util.FacesUtil;

@ManagedBean
@ViewScoped
public class AtividadeMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Atividade> listaAtividades = null;
	private List<Atividade> filtroAtividades = null;
	private Atividade atividade = null;
	private AtividadeDAO atividadeDAO =  null;
	
	public AtividadeMB() {
		listarAtividades();
	}

	public List<Atividade> getListaAtividades() {
		return listaAtividades;
	}

	public void setListaAtividades(List<Atividade> listaAtividades) {
		this.listaAtividades = listaAtividades;
	}

	public List<Atividade> getFiltroAtividades() {
		return filtroAtividades;
	}

	public void setFiltroAtividades(List<Atividade> filtroAtividades) {
		this.filtroAtividades = filtroAtividades;
	}

	public Atividade getAtividade() {
		if(atividade == null){
			atividade = new Atividade();
		}
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public AtividadeDAO getAtividadeDAO() {
		return atividadeDAO;
	}

	public void setAtividadeDAO(AtividadeDAO atividadeDAO) {
		this.atividadeDAO = atividadeDAO;
	}
	
	public void selecionaEdicao(Atividade atividade){
		this.atividade = atividade;
	}

	public void salvar() {

		try {
			
			atividadeDAO = new AtividadeDAO();
			atividadeDAO.salvar(atividade);
			FacesUtil.exibirMensagemSucesso("Cadastro feito com Sucesso!");

		} catch (RuntimeException e) {
			FacesUtil.exibirMensagemErro("Erro ao gravar registro!"
					+ e.getMessage());
		}
	}

	public void excluir(Atividade atividade) {

		try {
			atividadeDAO = new AtividadeDAO();
			atividadeDAO.excluir(atividade);
			FacesUtil.exibirMensagemSucesso("Exclusão feita com Sucesso!");

		} catch (RuntimeException e) {
			FacesUtil.exibirMensagemErro("Erro ao excluir registro!"
					+ e.getMessage());
		}
	}

	public void editar() {

		try {
			atividadeDAO = new AtividadeDAO();
			atividadeDAO.editar(atividade);
			FacesUtil.exibirMensagemSucesso("Edição feita com Sucesso!");

		} catch (RuntimeException e) {
			FacesUtil.exibirMensagemErro("Erro ao editar registro!"
					+ e.getMessage());
		}
	}

	public void listarAtividades() {

		try {
			atividadeDAO = new AtividadeDAO();
			listaAtividades = atividadeDAO.listaAtividade();

		} catch (RuntimeException e) {
			FacesUtil.exibirMensagemErro("Não retornou registro!"
					+ e.getMessage());
		}
	}

	public void pesquisaCodigo() {

		try {
			String valorRecebido = FacesUtil.getParametro("codigoAtividade");
			if (valorRecebido != null) {
				atividadeDAO = new AtividadeDAO();
				Long codigo = Long.parseLong(valorRecebido);
				atividade = atividadeDAO.pesquisaCodigo(codigo);
			}

		} catch (RuntimeException e) {
			FacesUtil.exibirMensagemErro("Não retornou registro!"
					+ e.getMessage());
		}
	}

}
