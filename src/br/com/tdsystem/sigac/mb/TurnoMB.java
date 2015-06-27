package br.com.tdsystem.sigac.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.tdsystem.sigac.dao.TurnoDAO;
import br.com.tdsystem.sigac.modelo.Turno;
import br.com.tdsystem.sigac.util.FacesUtil;
import br.com.tdsystem.sigac.modelo.negocio.Validacao;

@ManagedBean
@ViewScoped
public class TurnoMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Turno> listaTurno = null;
	private List<Turno> filtroTurnos = null;
	
	private Turno turno = null;
	private TurnoDAO turnoDAO = null;
	
	public TurnoMB() {
		listarTurnos();
		turno = new Turno();
	}
		
	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
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
	
	public void cancelarEdicao(){
		turno = new Turno();
	}

	public void salvar() {
		
		if(Validacao.validaCampoTexto(turno.getNome())){
			try {

				turnoDAO = new TurnoDAO();
				turnoDAO.salvar(turno);
				
				turno = new Turno();
				listarTurnos();
				
				FacesUtil.exibirMensagemSucesso("Cadastro feito com Sucesso!");

			} catch (RuntimeException e) {
				if(e.getMessage().equals("could not execute statement")){
					FacesUtil.exibirMensagemErro("Já existe este nome cadastrado!");
				}else{
					FacesUtil.exibirMensagemErro("Erro: " + e.getMessage());
				}
			}
			
		}
	}

	public void excluir(Turno turno) {

		try {
			turnoDAO = new TurnoDAO();
			turnoDAO.excluir(turno);
			listaTurno.remove(turno);
			FacesUtil.exibirMensagemSucesso("Exclusão feita com Sucesso!");

		} catch (RuntimeException e) {
			if(e.getMessage().equals("could not execute statement")){
				FacesUtil.exibirMensagemErro("Recurso está sendo usado em outra tabela,\n"
						+ "verifique!");
			}else{
				FacesUtil.exibirMensagemErro("Erro: " + e.getMessage());
			}
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