package br.com.tdsystem.sigac.mb;

import java.io.Serializable;
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
public class LoginMB implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	private LoginDAO loginDAO;
	private Boolean loggedIn;
	private String ra;
	private String password;
	private PerfilEnum perfil;
	
	//Metodo Construtor
	public LoginMB() {
		loginDAO = new LoginDAO();
		loggedIn = Boolean.FALSE;
	}

	//Metodo que verifica a existencia de um usuario no banco
	public void login(ActionEvent event) throws NoSuchAlgorithmException {

		//Recupera o contexto da aplicação
		RequestContext requestContext = RequestContext.getCurrentInstance();
		FacesMessage message = null;
		loggedIn = Boolean.FALSE;

		if (ra != null && password != null) {
			try {
				loggedIn = Boolean.TRUE;
				
				//Usuario recebe um Usuario cajo exista no banco
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
		//Adciona uma mensagem da tela index.xhtml com as boas vindas
		FacesContext.getCurrentInstance().addMessage(null, message);
		requestContext.addCallbackParam("loggedIn", loggedIn);
	}

	//Metodo que invalida a Sessao do Usuário e retorna a página Index.xhtml
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
	
	//Metodo para adcionar uma mensagem no contexto da aplicação
	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	
	//Metodos Get e Set
	
	public Usuario getUsuario() {
		return usuario;
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

	public PerfilEnum getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil;
	}

	public void criarConta(ActionEvent actionEvent) {
		addMessage("Criar nova conta");
	}

	public Boolean getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

}
