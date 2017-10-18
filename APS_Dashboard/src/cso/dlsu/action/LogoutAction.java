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
public class LogoutAction implements ActionHandler {
	/**
	 * This function invalidates the session and redirects to the home page  
	 * @param request 	a HTTPServletRequest object which is being used by this 
	 * 					handler which provides information for HTTP requests
	 * @param response 	a HTTPServletResponse object which is being used by this 
	 * 					handler to access certain HTTP response and cookies
	 * @throws ServletException
	 * @throws IOException
	 * @see cso.dlsu.action.ActionHandler#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* invalidate the session */
		request.getSession().invalidate();
		
		/* redirects to the home page */
		response.sendRedirect("home");
	}
	
}
