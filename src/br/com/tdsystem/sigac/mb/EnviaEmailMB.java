package br.com.tdsystem.sigac.mb;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.mail.EmailException;

import br.com.tdsystem.sigac.util.Email;

@ManagedBean
@ViewScoped
public class EnviaEmailMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public void sendEmail() throws EmailException{
		Email.sendEmail();
	}

}
