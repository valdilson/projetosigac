package br.com.tdsystem.sigac.mb;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import br.com.tdsystem.sigac.modelo.PerfilEnum;
import br.com.tdsystem.sigac.modelo.Status;

@ApplicationScoped
@ManagedBean
public class ApplicationMB {

	private List<Status> listaStatus;
	private List<PerfilEnum> listaPerfis;
	
	@PostConstruct
	private void init() {
		listaStatus = Arrays.asList(Status.values());
		listaPerfis = Arrays.asList(PerfilEnum.values());
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
	
}