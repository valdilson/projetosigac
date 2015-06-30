package br.com.tdsystem.sigac.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.tdsystem.sigac.mb.LoginMB;

/**
 * Servlet Filter implementation class LoginFilter
 * Classe respons�vel pelo filtro de navega��o, qualquer requisi��o passa por esse filtro
 * Anota��o WebFilter("/*"), representa pelo * que qualquer "coisa" ir� ser filtrada.
 */
@WebFilter("/*")
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        super();
    }
    
    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
    }
 
    /**
     * @see Filter#destroy()
     */
    public void destroy() {
    }
 
    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	/*
    	 * A implementa��o JSF abstrai a manipula��o de sess�o e cookies para o programador, por�m em alguns casos
    	 * como esse, precisamos recuperar a sess�oe manipular os objetos request e response manualmente como agora.
    	 * abaixo estamos recuperando a requisi��o e a sess�o atual.
    	 */
    	
    	//Recuperando a requisi��o no servidor feita com o objeto request;
    	HttpServletRequest req = (HttpServletRequest) request;
    	
    	//recuperando a sessao atual
    	HttpSession session = req.getSession();

    	//Recuperando o usu�rio da esepecifico da sessao, caso exista
    	LoginMB mbLogin = (LoginMB) session.getAttribute("loginMB");
    	System.out.println(req.getRequestURI());
    	
    	//Como qualquer requisi��o passa pelo Filter, estamos verificando as requisi��es do primesFaces, CSS, Imagens
    	// e liberando para serem renderizadas
    	if (req.getRequestURI().startsWith("/sigac2/*/javax.faces.resource/") || 
    			req.getRequestURI().startsWith("/sigac2/resources/imagens/") || 
    			req.getRequestURI().startsWith("/sigac2/javax.faces.resource/")) {
    	    chain.doFilter(request, response);
    	} else {
    		//Caso o retorno de recuperar o usuario seja nulo, entendemos que n�o existe ningu�m na sessao
    		//e retornamos a p�gina de login
    		if (mbLogin == null || !mbLogin.getLoggedIn()) {
    			RequestDispatcher rd = req.getRequestDispatcher("/templates/index.xhtml");
    			rd.forward(request, response);
    		} else {
    			//caso retorne algum usu�rio, libera o request(requisi��o feita, por exemplo a pagina index.xhtml)
    			// e o response. Os dois objetos Java que sabemos trabalhar(manipular)
    			chain.doFilter(request, response);        	
    		}    		
    	}
    }
}
