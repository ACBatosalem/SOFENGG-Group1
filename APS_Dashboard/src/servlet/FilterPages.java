package servlet;

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
public class FilterPages implements Filter {

    /**
     * Default constructor. 
     */
    public FilterPages() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		if(((HttpServletRequest)request).getServletPath().endsWith(".jsp")){
			System.out.println(((HttpServletRequest)request).getServletPath());
			
			if(((HttpServletRequest)request).getServletPath().startsWith("/loginreg"))
				((HttpServletResponse) response).sendRedirect("loginpage");
			
			else if(((HttpServletRequest)request).getServletPath().startsWith("/home_aps"))
				((HttpServletResponse) response).sendRedirect("success_APS");
			
			else if(((HttpServletRequest)request).getServletPath().startsWith("/home_org"))
				((HttpServletResponse) response).sendRedirect("success");
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
