package br.com.tdsystem.sigac.mb;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.tdsystem.sigac.dao.TurmaDAO;
import br.com.tdsystem.sigac.modelo.Turma;
import br.com.tdsystem.sigac.util.FacesUtil;

@ManagedBean
@ViewScoped
public class TurmaMB implements Serializable {

	public TurmaMB() {
		listarTurmas();
		turma = new Turma();
	}

	private static final long serialVersionUID = 1L;

	private List<Turma> listaTurma = null;
	private List<Turma> filtroTurmas = null;
	
	private Turma turma = null;
	private TurmaDAO turmaDAO = null;
		

	public Turma getTurma() {
		if (turma == null) {
			turma = new Turma();
		}

		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public TurmaDAO getTurmaDAO() {
		return turmaDAO;
	}

	public void setTurmaDAO(TurmaDAO turmaDAO) {
		this.turmaDAO = turmaDAO;
	}

	public List<Turma> getListaTurma() {
		return listaTurma;
	}

	public void setListaTurma(List<Turma> listaTurma) {
		this.listaTurma = listaTurma;
	}

	public List<Turma> getFiltroTurmas() {
		return filtroTurmas;
	}

	public void setFiltroTurmas(List<Turma> filtroTurmas) {
		this.filtroTurmas = filtroTurmas;
	}

	public void salvar() {

		try {

			turmaDAO = new TurmaDAO();
			turmaDAO.salvar(turma);
			
			turma = new Turma();
			
			FacesUtil.exibirMensagemSucesso("Cadastro feito com Sucesso!");

		} catch (RuntimeException e) {
			FacesUtil.exibirMensagemErro("Erro ao gravar registro!"
					+ e.getMessage());
		}
	}

	public void excluir(Turma turma) {

		try {
			turmaDAO = new TurmaDAO();
			turmaDAO.excluir(turma);
			listaTurma.remove(turma);
			FacesUtil.exibirMensagemSucesso("Exclusão feita com Sucesso!");

		} catch (RuntimeException e) {
			FacesUtil.exibirMensagemErro("Erro ao excluir registro!"
					+ e.getMessage());
		}
	}

	public void selecionaEdicao(Turma turma) {
		this.turma = turma;
	}

	public void editar() {

		try {
			turmaDAO = new TurmaDAO();
			turmaDAO.editar(turma);
			turma = new Turma();
			FacesUtil.exibirMensagemSucesso("Edição feita com Sucesso!");

		} catch (RuntimeException e) {
			FacesUtil.exibirMensagemErro("Erro ao editar registro!"
					+ e.getMessage());
		}
	}

	public void listarTurmas() {

		try {

			turmaDAO = new TurmaDAO();
			listaTurma = turmaDAO.listaTurma();

		} catch (RuntimeException e) {
			FacesUtil.exibirMensagemErro("Não retornou registro!"
					+ e.getMessage());
		}
	}

	public void pesquisaCodigo() {

		try {
			String valorRecebido = FacesUtil.getParametro("codigoTurma");
			if (valorRecebido != null) {
				turmaDAO = new TurmaDAO();
				Long codigo = Long.parseLong(valorRecebido);
				turma = turmaDAO.pesquisaCodigo(codigo);
			}

		} catch (RuntimeException e) {
			FacesUtil.exibirMensagemErro("Não retornou registro!"
					+ e.getMessage());
		}
	}

}
