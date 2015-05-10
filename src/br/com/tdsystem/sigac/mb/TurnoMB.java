package br.com.tdsystem.sigac.mb;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.tdsystem.sigac.dao.TurnoDAO;
import br.com.tdsystem.sigac.modelo.Turno;
import br.com.tdsystem.sigac.util.FacesUtil;

@ManagedBean
@ViewScoped
public class TurnoMB implements Serializable {

	public TurnoMB() {
		listarTurnos();
		turno = new Turno();
	}

	private static final long serialVersionUID = 1L;

	private List<Turno> listaTurno = null;
	private List<Turno> filtroTurnos = null;
	
	private Turno turno = null;
	private TurnoDAO turnoDAO = null;
		

	public Turno getTurno() {
		if (turno == null) {
			turno = new Turno();
		}

		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public TurnoDAO getTurnoDAO() {
		return turnoDAO;
	}

	public void setTurnoDAO(TurnoDAO turnoDAO) {
		this.turnoDAO = turnoDAO;
	}

	public List<Turno> getListaTurno() {
		return listaTurno;
	}

	public void setListaTurno(List<Turno> listaTurno) {
		this.listaTurno = listaTurno;
	}

	public List<Turno> getFiltroTurnos() {
		return filtroTurnos;
	}

	public void setFiltroTurnos(List<Turno> filtroTurnos) {
		this.filtroTurnos = filtroTurnos;
	}

	public void salvar() {

		try {

			turnoDAO = new TurnoDAO();
			turnoDAO.salvar(turno);
			
			turno = new Turno();
			
			FacesUtil.exibirMensagemSucesso("Cadastro feito com Sucesso!");

		} catch (RuntimeException e) {
			FacesUtil.exibirMensagemErro("Erro ao gravar registro!"
					+ e.getMessage());
		}
	}

	public void excluir(Turno turno) {

		try {
			turnoDAO = new TurnoDAO();
			turnoDAO.excluir(turno);
			listaTurno.remove(turno);
			FacesUtil.exibirMensagemSucesso("Exclusão feita com Sucesso!");

		} catch (RuntimeException e) {
			FacesUtil.exibirMensagemErro("Erro ao excluir registro!"
					+ e.getMessage());
		}
	}

	public void selecionaEdicao(Turno turno) {
		this.turno = turno;
	}

	public void editar() {

		try {
			turnoDAO = new TurnoDAO();
			turnoDAO.editar(turno);
			turno = new Turno();
			FacesUtil.exibirMensagemSucesso("Edição feita com Sucesso!");

		} catch (RuntimeException e) {
			FacesUtil.exibirMensagemErro("Erro ao editar registro!"
					+ e.getMessage());
		}
	}

	public void listarTurnos() {

		try {

			turnoDAO = new TurnoDAO();
			listaTurno = turnoDAO.listaTurno();

		} catch (RuntimeException e) {
			FacesUtil.exibirMensagemErro("Não retornou registro!"
					+ e.getMessage());
		}
	}

	public void pesquisaCodigo() {

		try {
			String valorRecebido = FacesUtil.getParametro("codigoTurno");
			if (valorRecebido != null) {
				turnoDAO = new TurnoDAO();
				Long codigo = Long.parseLong(valorRecebido);
				turno = turnoDAO.pesquisaCodigo(codigo);
			}

		} catch (RuntimeException e) {
			FacesUtil.exibirMensagemErro("Não retornou registro!"
					+ e.getMessage());
		}
	}

}