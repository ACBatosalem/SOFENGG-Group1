package cso.dlsu.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Batosalem, Angelika 
 * @author Eroles, Carlo Miguel 
 * @author Respicio, Michael Ryan
 * @author Ticug, Jonal Ray 
 * @version 1.0 
 */
public interface ActionHandler {
	/**
	 * This is the main function of the ActionHander that is being used by the
	 * APSController to execute a specific function using the requests and response.
	 * @param request 	a HTTPServletRequest object which is being used by this 
	 * 					handler which provides information for HTTP requests
	 * @param response 	a HTTPServletResponse object which is being used by this 
	 * 					handler to access certain HTTP response and cookies
	 * @throws ServletException
	 * @throws IOException
	 */
	public void execute(HttpServletRequest request, HttpServletResponse response)
  	throws ServletException, IOException;
}
