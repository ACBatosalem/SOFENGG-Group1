package cso.dlsu.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cso.dlsu.bean.Organization;
import cso.dlsu.service.OrganizationService;
/**
 * @author Batosalem, Angelika 
 * @author Eroles, Carlo Miguel 
 * @author Respicio, Michael Ryan
 * @author Ticug, Jonal Ray 
 * @version 1.0 
 */
public class LoginAction implements ActionHandler {

	/**
	 * This function accepts the user and password from the request and is 
	 * being called by an ajax request in login.js and returns using the writer. 
	 * Checks if the username exists in the database and returns the user. 
	 * if user is null, the message returns an invalid or unauthorized,
	 * if user exists, but the password is wrong returns a wrong message error,
	 * if the user exists and password is correct and username is equal to "APS"
	 * 			redirects to the homeAPS
	 * otherwise redirects to the homeORG  
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
		/* retrieves the username and the password */
		String username = request.getParameter("user");
		String password = request.getParameter("password");
		
		/* tries to retrieve the username */
		Organization user = OrganizationService.getOrgByUsername(username);
		
		/* sets the content type to a text */
		response.setContentType("text/html;charset=UTF-8");
		
		/* if user is null, returns error message */
		if (user == null)
			response.getWriter().write("This user is invalid or unauthorized!");
		/* if user is not null, but wrong password, returns error message */
		else if (!user.getPassword().equals(password))
			response.getWriter().write("The password is incorrect.");
		/* if user is not null, but correct password and the username is equal to "APS", returns 'aps'*/
		else if (user.getUserName().equals("APS")) {
			response.getWriter().write("aps");
			HttpSession session = request.getSession();
			session.setAttribute("sessionID", user.getId());
			session.setAttribute("user", user);
			session.setAttribute("orgs", OrganizationService.getAllOrgs());
		/* otherwise, returns 'org'*/
		} else {
			response.getWriter().write("org");
			HttpSession session = request.getSession();
			session.setAttribute("sessionID", user.getId());
			session.setAttribute("user", user);
		}
	}
}
