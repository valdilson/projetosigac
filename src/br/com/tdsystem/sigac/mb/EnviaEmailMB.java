package br.com.tdsystem.sigac.mb;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.tdsystem.sigac.util.EnviaEmail;

@ManagedBean
@ViewScoped
public class EnviaEmailMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	EnviaEmail enviaEmail = new EnviaEmail("smtp.task.com.br", "25");

	public void enviaEmail(){
		enviaEmail.sendMail("thiago@momoconfeitaria.com.br", "thiago.krathos@gmail.com", 
							"teste", "Ola teste");
	}

}
