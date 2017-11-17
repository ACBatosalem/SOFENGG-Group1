package cso.dlsu.servlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalTime;

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
import main.java.GSheetsConnection;
import main.java.NoInternetException;

/**
 * Servlet Filter implementation class FilterPage
 */
@WebFilter("/*")
public class APSFilter implements Filter {
	public static int dataGathered = 0;
	public APSFilter() {
    	
    }

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = ((HttpServletRequest)request);
		HttpServletResponse httpResponse = ((HttpServletResponse)response);

		if(dataGathered == 0) {
			/*
	        String spreadsheetId = "1ksvxm8IB0PXlR4C09GFDsb3I3o1qFDXLmDTcmMIrLlY";
	        String range = "'CSO:APS'";
	        
	        List <LocalTime> times = new ArrayList <LocalTime> ();
	        
	        times.add(LocalTime.of(8, 0));
	        times.add(LocalTime.of(12, 0));
	        times.add(LocalTime.of(15,5));
	        times.add(LocalTime.of(16, 0));
	        
	        try {
				GSheetsConnection.start(spreadsheetId, range, times);
				dataGathered = 1;
			} catch (NoInternetException e) {
				e.reconnect();
			}
			*/
		}
		String path = ((HttpServletRequest)request).getServletPath();
		
		Organization user = (Organization)((HttpServletRequest)request).getSession().getAttribute("user");
		
		if(path.endsWith(".jsp"))
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/home");
		else if (path.startsWith("/homeAPS")) {
			if(user != null) {
				httpResponse.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
				httpResponse.setHeader("Pragma", "no-cache");
				httpResponse.setDateHeader("Expires", 0);
				if(user.getUserName().equals("APS"))
					chain.doFilter(request, response);
				else
					httpResponse.sendRedirect(httpRequest.getContextPath() + "/homeORG");
			} else httpResponse.sendRedirect(httpRequest.getContextPath() + "/home");
		} else if (path.startsWith("/homeORG")) {
			if(user != null) {
				httpResponse.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
				httpResponse.setHeader("Pragma", "no-cache");
				httpResponse.setDateHeader("Expires", 0);
				if(!user.getUserName().equals("APS"))
					chain.doFilter(request, response);
				else
					httpResponse.sendRedirect(httpRequest.getContextPath() + "/homeAPS");
			}
			else httpResponse.sendRedirect(httpRequest.getContextPath() + "/home");
		} else if(path.startsWith("/home")){
			if(user != null) {
				httpResponse.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
				httpResponse.setHeader("Pragma", "no-cache");
				httpResponse.setDateHeader("Expires", 0);
				if(!user.getUserName().equals("APS"))
					httpResponse.sendRedirect(httpRequest.getContextPath() + "/homeORG");
				else
					httpResponse.sendRedirect(httpRequest.getContextPath() + "/homeAPS");
			}
			else chain.doFilter(request, response);
		} else chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}
}
