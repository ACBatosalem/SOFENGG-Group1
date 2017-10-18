package cso.dlsu.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cso.dlsu.bean.Organization;

/**
 * @author Batosalem, Angelika 
 * @author Eroles, Carlo Miguel 
 * @author Respicio, Michael Ryan
 * @author Ticug, Jonal Ray 
 * @version 1.0 
 */
public class HomeAction implements ActionHandler {

	/**
	 * The default or home action of the controller.
	 * Checks if the user exists in the session. If the user exists, the function 
	 * redirects the user either to the homeAPS if the username is 'APS' or to the 
	 * homeORG otherwise. If the user does not exist, the user is redirected to the
	 * login page.
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
		/* checks if user in session is null */
		if(request.getSession().getAttribute("user") == null)
			/* redirects to the login page if null */
			request.getRequestDispatcher("loginreg.jsp").forward(request, response);
		else if (((Organization)(request.getSession().getAttribute("user"))).getUserName().equals("APS"))
			/* if user is not null and equal to "APS" redirect to homeAPS */
			response.sendRedirect("homeAPS");
		else
			/* otherwise redirect to the homeORG */
			response.sendRedirect("homeORG");
	}

}
