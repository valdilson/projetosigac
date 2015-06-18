package br.com.tdsystem.sigac.util;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

@ManagedBean
@ViewScoped
public class Email {
	
	public void sendEmail() throws EmailException {
	    
		   SimpleEmail email = new SimpleEmail();
		   //Utilize o hostname do seu provedor de email
		   System.out.println("alterando hostname...");
		   email.setHostName("smtp.gmail.com");
		   //Quando a porta utilizada não é a padrão (gmail = 465)
		   email.setSmtpPort(465);
		   //Adicione os destinatários
		   email.addTo("thiago@momoconfeitaria.com.br", "Thiago");
		   //Configure o seu email do qual enviará
		   email.setFrom("thiago.krathos@gmail.com", "Anhanguera");
		   //Adicione um assunto
		   email.setSubject("Test message");
		   //Adicione a mensagem do email
		   email.setMsg("Primeiro email enviado pelo JAVA");
		   //Para autenticar no servidor é necessário chamar os dois métodos abaixo
		   System.out.println("autenticando...");
		   //email.setSSL(true);
		   email.setSSLOnConnect(true);
		   email.setSslSmtpPort("465");
		   //email.setTLS(true);
		   //email.setStartTLSRequired(true);
		   email.setAuthentication("thiago.krathos@gmail.com", "300p3strrr");
		   System.out.println("enviando...");
		   email.send();
		   System.out.println("Email enviado!");
		}
}
