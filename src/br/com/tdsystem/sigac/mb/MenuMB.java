package br.com.tdsystem.sigac.mb;

import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class MenuMB {
	
	public String navegar(String destino){
		Logger.getLogger(this.getClass().getSimpleName()).info(destino);
		return destino;
	}

}
