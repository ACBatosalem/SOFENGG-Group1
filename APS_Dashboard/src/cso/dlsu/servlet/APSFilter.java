package cso.dlsu.servlet;

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

import cso.dlsu.bean.Organization;
import cso.dlsu.service.OrganizationService;

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
		HttpServletRequest httpRequest = ((HttpServletRequest)request);
		HttpServletResponse httpResponse = ((HttpServletResponse)response);
		
		String path = ((HttpServletRequest)request).getServletPath();
		/*int id = ((HttpServletRequest)request).getSession().getAttribute("sessionID") != null ? 
				(int)((HttpServletRequest)request).getSession().getAttribute("sessionID") : 0;*/
		//System.out.println(id +" ");
		Organization user = (Organization)((HttpServletRequest)request).getSession().getAttribute("user");
		
		if(path.endsWith(".jsp"))
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/home");
		else if (path.startsWith("/homeAPS")) {
			if(user != null && user.getUserName().equals("APS"))
				chain.doFilter(request, response);
			else if(user != null) 
				httpResponse.sendRedirect(httpRequest.getContextPath() + "/homeORG");
			else httpResponse.sendRedirect(httpRequest.getContextPath() + "/home");
		} else if (path.startsWith("/homeORG")) {
			if(user != null && !user.getUserName().equals("APS"))
				chain.doFilter(request, response);
			else if(user != null) 
				httpResponse.sendRedirect(httpRequest.getContextPath() + "/homeAPS");
			else httpResponse.sendRedirect(httpRequest.getContextPath() + "/home");
		} else if(path.startsWith("/home")){
			if(user != null && !user.getUserName().equals("APS"))
				httpResponse.sendRedirect(httpRequest.getContextPath() + "/homeORG");
			else if(user != null) 
				httpResponse.sendRedirect(httpRequest.getContextPath() + "/homeAPS");
			else chain.doFilter(request, response);
		} else chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}
}
