package br.com.tdsystem.sigac.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.tdsystem.sigac.dao.AtividadeDAO;
import br.com.tdsystem.sigac.modelo.Atividade;
import br.com.tdsystem.sigac.util.FacesUtil;
import br.com.tdsystem.sigac.modelo.negocio.Validacao;

@ManagedBean
@ViewScoped
public class AtividadeMB implements Serializable {

	private static final long serialVersionUID = 1L;

	// Declaração dos Objetos
	private Atividade atividade = null;

	private AtividadeDAO atividadeDAO = null;

	private List<Atividade> listaAtividades = null;
	private List<Atividade> filtroAtividades = null;

	// Metodo Construtor
	public AtividadeMB() {
		atividade = new Atividade();
		listarAtividades();
	}

	// Metodo que salva uma atividade
	public void salvar() {

		// Validada Campo nome Vazio
		if (Validacao.validaCampoTexto(atividade.getNome())) {

			try {

				atividadeDAO = new AtividadeDAO();
				atividadeDAO.salvar(atividade);
				atividade = new Atividade();
				listarAtividades();
				FacesUtil.exibirMensagemSucesso("Cadastro feito com Sucesso!");

			} catch (RuntimeException e) {
				FacesUtil.exibirMensagemErro("Erro ao gravar registro!"
						+ e.getCause());
			}
		}
	}

	// Metodo que exclui uma atividade
	public void excluir(Atividade atividade) {

		try {
			atividadeDAO = new AtividadeDAO();
			atividadeDAO.excluir(atividade);
			listaAtividades.remove(atividade);
			FacesUtil.exibirMensagemSucesso("Exclusão feita com Sucesso!");

		} catch (Exception e) {
			if (e.getMessage().equals("could not execute statement")) {
				FacesUtil
						.exibirMensagemErro("Recurso está sendo usado em outra tabela,\n"
								+ "verifique!");
			} else {
				FacesUtil.exibirMensagemErro("Erro: " + e.getMessage());
			}
		}
	}

	// Metodo que edita uma atividade
	public void editar() {

		try {
			atividadeDAO = new AtividadeDAO();
			atividadeDAO.editar(atividade);
			FacesUtil.exibirMensagemSucesso("Edição feita com Sucesso!");
			atividade = new Atividade();
		} catch (RuntimeException e) {
			FacesUtil.exibirMensagemErro("Erro ao editar registro!"
					+ e.getMessage());
		}
	}

	// Metodo para instancia uma nova atividade para cancelar a edição,
	// necessário para
	// o botao editar sumir da View, pois ele renderiza quando o codigo é !=
	// null
	public void cancelarEdicao() {
		atividade = new Atividade();
	}

	// Metodo que lista todas a atividades
	public void listarAtividades() {

		try {
			atividadeDAO = new AtividadeDAO();
			listaAtividades = atividadeDAO.listaAtividade();

		} catch (RuntimeException e) {
			FacesUtil.exibirMensagemErro("Não retornou registro!"
					+ e.getMessage());
		}
	}

	// Metodo que pesquisa por codigo*
	// Não está sendo usado
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
	
	//Metodo que seleciona objeto da tabela da view
	public void selecionaEdicao(Atividade atividade) {
		this.atividade = atividade;
	}

	// Metodos Get e Set's
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
}