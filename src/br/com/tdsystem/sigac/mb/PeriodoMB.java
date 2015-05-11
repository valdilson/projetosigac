package br.com.tdsystem.sigac.mb;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.tdsystem.sigac.dao.PeriodoDAO;
import br.com.tdsystem.sigac.modelo.Periodo;
import br.com.tdsystem.sigac.util.FacesUtil;

@ManagedBean
@ViewScoped
public class PeriodoMB implements Serializable {

	public PeriodoMB() {
		listarPeriodos();
		periodo = new Periodo();
	}

	private static final long serialVersionUID = 1L;

	private List<Periodo> listaPeriodo = null;
	private List<Periodo> filtroPeriodos = null;
	
	private Periodo periodo = null;
	private PeriodoDAO periodoDAO = null;
		

	public Periodo getPeriodo() {
		if (periodo == null) {
			periodo = new Periodo();
		}

		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	public PeriodoDAO getPeriodoDAO() {
		return periodoDAO;
	}

	public void setPeriodoDAO(PeriodoDAO periodoDAO) {
		this.periodoDAO = periodoDAO;
	}

	public List<Periodo> getListaPeriodo() {
		return listaPeriodo;
	}

	public void setListaPeriodo(List<Periodo> listaPeriodo) {
		this.listaPeriodo = listaPeriodo;
	}

	public List<Periodo> getFiltroPeriodos() {
		return filtroPeriodos;
	}

	public void setFiltroPeriodos(List<Periodo> filtroPeriodos) {
		this.filtroPeriodos = filtroPeriodos;
	}

	public void salvar() {

		try {

			periodoDAO = new PeriodoDAO();
			periodoDAO.salvar(periodo);
			
			periodo = new Periodo();
			
			FacesUtil.exibirMensagemSucesso("Cadastro feito com Sucesso!");

		} catch (RuntimeException e) {
			FacesUtil.exibirMensagemErro("Erro ao gravar registro!"
					+ e.getMessage());
		}
	}

	public void excluir(Periodo periodo) {

		try {
			periodoDAO = new PeriodoDAO();
			periodoDAO.excluir(periodo);
			listaPeriodo.remove(periodo);
			FacesUtil.exibirMensagemSucesso("Exclusão feita com Sucesso!");

		} catch (RuntimeException e) {
			FacesUtil.exibirMensagemErro("Erro ao excluir registro!"
					+ e.getMessage());
		}
	}

	public void selecionaEdicao(Periodo periodo) {
		this.periodo = periodo;
	}

	public void editar() {

		try {
			periodoDAO = new PeriodoDAO();
			periodoDAO.editar(periodo);
			periodo = new Periodo();
			FacesUtil.exibirMensagemSucesso("Edição feita com Sucesso!");

		} catch (RuntimeException e) {
			FacesUtil.exibirMensagemErro("Erro ao editar registro!"
					+ e.getMessage());
		}
	}

	public void listarPeriodos() {

		try {

			periodoDAO = new PeriodoDAO();
			listaPeriodo = periodoDAO.listaPeriodo();

		} catch (RuntimeException e) {
			FacesUtil.exibirMensagemErro("Não retornou registro!"
					+ e.getMessage());
		}
	}

	public void pesquisaCodigo() {

		try {
			String valorRecebido = FacesUtil.getParametro("codigoPeriodo");
			if (valorRecebido != null) {
				periodoDAO = new PeriodoDAO();
				Long codigo = Long.parseLong(valorRecebido);
				periodo = periodoDAO.pesquisaCodigo(codigo);
			}

		} catch (RuntimeException e) {
			FacesUtil.exibirMensagemErro("Não retornou registro!"
					+ e.getMessage());
		}
	}

}
