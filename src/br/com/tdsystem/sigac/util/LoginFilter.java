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
 * Classe responsável pelo filtro de navegação, qualquer requisição passa por esse filtro
 * Anotação WebFilter("/*"), representa pelo * que qualquer "coisa" irá ser filtrada.
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
    	 * A implementação JSF abstrai a manipulação de sessão e cookies para o programador, porém em alguns casos
    	 * como esse, precisamos recuperar a sessãoe manipular os objetos request e response manualmente como agora.
    	 * abaixo estamos recuperando a requisição e a sessão atual.
    	 */
    	
    	//Recuperando a requisição no servidor feita com o objeto request;
    	HttpServletRequest req = (HttpServletRequest) request;
    	
    	//recuperando a sessao atual
    	HttpSession session = req.getSession();

    	//Recuperando o usuário da esepecifico da sessao, caso exista
    	LoginMB mbLogin = (LoginMB) session.getAttribute("loginMB");
    	System.out.println(req.getRequestURI());
    	
    	//Como qualquer requisição passa pelo Filter, estamos verificando as requisições do primesFaces, CSS, Imagens
    	// e liberando para serem renderizadas
    	if (req.getRequestURI().startsWith("/sigac2/*/javax.faces.resource/") || 
    			req.getRequestURI().startsWith("/sigac2/resources/imagens/") || 
    			req.getRequestURI().startsWith("/sigac2/javax.faces.resource/")) {
    	    chain.doFilter(request, response);
    	} else {
    		//Caso o retorno de recuperar o usuario seja nulo, entendemos que não existe ninguém na sessao
    		//e retornamos a página de login
    		if (mbLogin == null || !mbLogin.getLoggedIn()) {
    			RequestDispatcher rd = req.getRequestDispatcher("/templates/index.xhtml");
    			rd.forward(request, response);
    		} else {
    			//caso retorne algum usuário, libera o request(requisição feita, por exemplo a pagina index.xhtml)
    			// e o response. Os dois objetos Java que sabemos trabalhar(manipular)
    			chain.doFilter(request, response);        	
    		}    		
    	}
    }
}
