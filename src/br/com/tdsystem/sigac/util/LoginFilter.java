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
    	HttpServletRequest req = (HttpServletRequest) request;
    	HttpSession session = req.getSession();

    	LoginMB mbLogin = (LoginMB) session.getAttribute("loginMB");
    	System.out.println(req.getRequestURI());
    	if (req.getRequestURI().startsWith("/sigac2/*/javax.faces.resource/") || 
    			req.getRequestURI().startsWith("/sigac2/resources/imagens/") || 
    			req.getRequestURI().startsWith("/sigac2/javax.faces.resource/")) {
    	    chain.doFilter(request, response);
    	} else {
    		if (mbLogin == null || !mbLogin.getLoggedIn()) {
    			RequestDispatcher rd = req.getRequestDispatcher("/templates/index.xhtml");
    			rd.forward(request, response);
    		} else {
    			chain.doFilter(request, response);        	
    		}    		
    	}
    }
}
