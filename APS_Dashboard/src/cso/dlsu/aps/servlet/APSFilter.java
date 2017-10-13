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
public class APSFilter implements Filter {

    public APSFilter() {
    	
    }

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(((HttpServletRequest)request).getServletPath().endsWith(".jsp"))
			((HttpServletResponse)response).sendRedirect("home");
		else if (((HttpServletRequest)request).getServletPath().startsWith("/homeAPS")){
			if((String) ((HttpServletRequest)request).getSession().getAttribute("sessionun") != null &&
				((String) ((HttpServletRequest)request).getSession().getAttribute("sessionun")).equals("CSO")) {
				System.out.println(((HttpServletRequest)request).getServletPath());
				chain.doFilter(request, response);
			} else ((HttpServletResponse)response).sendRedirect("/APS_Dashboard/home");
		}
		else if (((HttpServletRequest)request).getServletPath().startsWith("/homeORG")){
			if((String) ((HttpServletRequest)request).getSession().getAttribute("sessionun") != null &&
				!((String) ((HttpServletRequest)request).getSession().getAttribute("sessionun")).equals("CSO")) {
				System.out.println(((HttpServletRequest)request).getServletPath());
				chain.doFilter(request, response);
			} else ((HttpServletResponse)response).sendRedirect("/APS_Dashboard/home");
		}
		else
			chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}
}
