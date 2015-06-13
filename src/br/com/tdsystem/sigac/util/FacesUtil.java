package br.com.tdsystem.sigac.util;

import java.util.Map;

import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.MethodExpressionActionListener;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.tdsystem.sigac.mb.LoginMB;
import br.com.tdsystem.sigac.modelo.Usuario;

/**
 * Classe utilit�ria para desenvolvimento JSF
 */
public class FacesUtil {

	public static String getRequestParameter(String name) {
		return (String) FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get(name);
	}

	public static Usuario getUsuarioLogado() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		HttpServletRequest request = (HttpServletRequest)externalContext.getRequest();
		LoginMB loginMB = (LoginMB) request.getSession().getAttribute("loginMB");
		return loginMB.getUsuario();
	}
	
	public static void exibirMensagemSucesso(String mensagem) {
		exibirMensagem(FacesMessage.SEVERITY_INFO, mensagem);
	}

	public static void exibirMensagemAlerta(String mensagem) {
		exibirMensagem(FacesMessage.SEVERITY_WARN, mensagem);
	}

	public static void exibirMensagemErro(String mensagem) {
		exibirMensagem(FacesMessage.SEVERITY_ERROR, mensagem);
	}

	private static void exibirMensagem(FacesMessage.Severity severity,
			String mensagem) {
		FacesMessage facesMessage = new FacesMessage(severity, mensagem, "");
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}

	public static ExternalContext getExternalContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}

	@SuppressWarnings("rawtypes")
	public static Map getSessionMap() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap();
	}

	public static ServletContext getServletContext() {
		return (ServletContext) FacesContext.getCurrentInstance()
				.getExternalContext().getContext();
	}

	public static HttpServletRequest getServletRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}

	public static HttpServletResponse getServletResponse() {
		return (HttpServletResponse) FacesContext.getCurrentInstance()
				.getExternalContext().getResponse();
	}

	public static MethodExpression createMethodExpression(
			String valueExpression, Class<?> expectedReturnType,
			Class<?>[] expectedParamTypes) {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExpressionFactory factory = fc.getApplication().getExpressionFactory();

		return factory.createMethodExpression(fc.getELContext(),
				valueExpression, expectedReturnType, expectedParamTypes);
	}

	public static MethodExpressionActionListener createMethodActionListener(
			String valueExpression, Class<?> expectedReturnType,
			Class<?>[] expectedParamTypes) {
		return new MethodExpressionActionListener(createMethodExpression(
				valueExpression, expectedReturnType, expectedParamTypes));
	}

	public static String getParametro(String parametro) {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		ExternalContext externalContext = facesContext.getExternalContext();

		Map<String, String> parametros = externalContext
				.getRequestParameterMap();

		String valorCapturado = parametros.get(parametro);

		return valorCapturado;
	}
}
