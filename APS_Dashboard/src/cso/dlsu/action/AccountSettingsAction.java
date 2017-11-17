package cso.dlsu.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * @author Batosalem, Angelika 
 * @author Eroles, Carlo Miguel 
 * @author Respicio, Michael Ryan
 * @author Ticug, Jonal Ray 
 * @version 1.0 
 */
public class AccountSettingsAction implements ActionHandler {

	/**
	 * This function checks for a sessionID attribute in the session and redirects
	 * the page to the account_settings.jsp and forwards the needed request and response. 
	 * If the sessionID does not exist, it redirects to the home page.
	 * @param request 	a HTTPServletRequest object which is being used by this 
	 * 					handler which provides information for HTTP requests.    
	 * @param response 	a HTTPServletResponse object which is being used by this
	 * 					handler to access certain HTTP response and cookies.
	 * @throws ServletException                                                 
	 * @throws IOException                                                      
	 * @see cso.dlsu.action.ActionHandler#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Checks the sessionID */
		if ((Integer)request.getSession().getAttribute("sessionID") != null) {
			/* Redirect to change password page */
			request.getRequestDispatcher("account_settings.jsp").forward(request, response);
		} else {
			/* Redirects to the home page */
			response.sendRedirect(request.getContextPath() + "/home");
		}
	}
}
