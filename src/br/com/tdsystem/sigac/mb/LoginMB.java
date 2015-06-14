package br.com.tdsystem.sigac.mb;

import java.security.NoSuchAlgorithmException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import br.com.tdsystem.sigac.dao.LoginDAO;
import br.com.tdsystem.sigac.modelo.PerfilEnum;
import br.com.tdsystem.sigac.modelo.Usuario;
import br.com.tdsystem.sigac.util.FacesUtil;

@ManagedBean(name="loginMB")
@SessionScoped
public class LoginMB {

	private Usuario usuario;
	private LoginDAO loginDAO;
	private Boolean loggedIn;
	private String ra;
	private String password;
	private PerfilEnum perfil;

	public Usuario getUsuario() {
		return usuario;
	}

	public LoginMB() {
		loginDAO = new LoginDAO();
		loggedIn = Boolean.FALSE;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getRa() {
		return ra;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void login(ActionEvent event) throws NoSuchAlgorithmException {

		RequestContext requestContext = RequestContext.getCurrentInstance();
		FacesMessage message = null;
		loggedIn = Boolean.FALSE;

		if (ra != null && password != null) {
			try {
				loggedIn = Boolean.TRUE;
				usuario = loginDAO.recuperarUsuario(ra, password, perfil);
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, 	"Bem vindo(a) " + usuario.getUsuario().getNome(), "");
			} catch (NoResultException e) {
				loggedIn = Boolean.FALSE;
				message = new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Loggin Error", "Verifique usuário e senha!\n"
								+ " Ou contate administrador.");
			}
		} else {
			loggedIn = Boolean.FALSE;
			message = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Loggin Error", "Infrorme usuario e senha");
		}
		FacesContext.getCurrentInstance().addMessage(null, message);
		requestContext.addCallbackParam("loggedIn", loggedIn);
	}

	public String sair() {
		String retorno = "/templates/index.xhtml";
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
			session.invalidate();
			FacesUtil.exibirMensagemErro("Sessão Finalizada");
		} catch (Exception e) {
			FacesUtil.exibirMensagemErro("Erro ao deslogar" + e.getMessage());
		}
		return retorno;
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
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public Boolean getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

}
