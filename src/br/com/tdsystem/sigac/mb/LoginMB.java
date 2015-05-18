package br.com.tdsystem.sigac.mb;

import java.security.NoSuchAlgorithmException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.NoResultException;

import org.primefaces.context.RequestContext;

import br.com.tdsystem.sigac.dao.LoginDAO;
import br.com.tdsystem.sigac.modelo.PerfilEnum;
import br.com.tdsystem.sigac.modelo.Usuario;

@SessionScoped
@ManagedBean
public class LoginMB {

	private Usuario usuario;
	private LoginDAO loginDAO;

	public Usuario getUsuario() {
		return usuario;
	}

	public LoginMB() {
		loginDAO = new LoginDAO();
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	private String username;
    
    private String password;
    
    private PerfilEnum perfil;
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
   
    public void login(ActionEvent event) throws NoSuchAlgorithmException {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean loggedIn = false;
         
        if(username != null && password != null) {
            try {
            	loggedIn = true;
                usuario = loginDAO.recuperarUsuario(username, password, perfil);
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem vindo", username);
			} catch (NoResultException e) {
				loggedIn = false;
				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");				
			}
        } else {
        	loggedIn = false;
        	message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Infrorme usuario e senha");				
        }
         
        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);
    }

    public void sair() {
    	FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Valeu truta", "Volte sempre"));

    }
    
	public PerfilEnum getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil;
	}
    
	public void criarConta(ActionEvent actionEvent) {
        addMessage("Criar nova conta");
        
    }
     
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
}
