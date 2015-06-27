package br.com.tdsystem.sigac.mb;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class MenuMB implements Serializable{

	private static final long serialVersionUID = 1L;

	public String navegar(String destino){
		Logger.getLogger(this.getClass().getSimpleName()).info(destino);
		return destino;
	}

}
