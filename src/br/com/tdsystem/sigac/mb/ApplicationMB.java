package br.com.tdsystem.sigac.mb;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import br.com.tdsystem.sigac.modelo.PerfilEnum;
import br.com.tdsystem.sigac.modelo.Periodo;
import br.com.tdsystem.sigac.modelo.Status;
import br.com.tdsystem.sigac.modelo.StatusAprovacao;

@ApplicationScoped
@ManagedBean
public class ApplicationMB implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<Status> listaStatus;
	private List<PerfilEnum> listaPerfis;
	private List<Periodo> listaPeriodos;
	private List<StatusAprovacao> listaStatusAprovacao;
	
	@PostConstruct
	private void init() {
		listaStatus = Arrays.asList(Status.values());
		listaPerfis = Arrays.asList(PerfilEnum.values());
		listaPeriodos = Arrays.asList(Periodo.values());
		listaStatusAprovacao = Arrays.asList(StatusAprovacao.values());
	}

	public List<Status> getListaStatus() {
		return listaStatus;
	}

	public void setListaStatus(List<Status> listaStatus) {
		this.listaStatus = listaStatus;
	}

	public List<PerfilEnum> getListaPerfis() {
		return listaPerfis;
	}

	public void setListaPerfis(List<PerfilEnum> listaPerfis) {
		this.listaPerfis = listaPerfis;
	}

	public List<Periodo> getListaPeriodos() {
		return listaPeriodos;
	}

	public void setListaPeriodos(List<Periodo> listaPeriodos) {
		this.listaPeriodos = listaPeriodos;
	}

	public List<StatusAprovacao> getListaStatusAprovacao() {
		return listaStatusAprovacao;
	}

	public void setListaStatusAprovacao(List<StatusAprovacao> listaStatusAprovacao) {
		this.listaStatusAprovacao = listaStatusAprovacao;
	}

}
