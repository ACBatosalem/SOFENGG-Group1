package cso.dlsu.aps.servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class FilterPage
 */
@WebFilter("/*")
public class JSPFilter implements Filter {

    public JSPFilter() {
    	
    }

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(((HttpServletRequest)request).getServletPath().startsWith("/loginreg"))
			((HttpServletResponse) response).sendRedirect("loginpage");
		
		else if(((HttpServletRequest)request).getServletPath().startsWith("/home_aps"))
			((HttpServletResponse) response).sendRedirect("success_APS");
		
		else if(((HttpServletRequest)request).getServletPath().startsWith("/home_org"))
			((HttpServletResponse) response).sendRedirect("success");
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}
}
