package cso.dlsu.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cso.dlsu.bean.Organization;
import cso.dlsu.service.OrganizationService;
/**
 * @author Batosalem, Angelika 
 * @author Eroles, Carlo Miguel 
 * @author Respicio, Michael Ryan
 * @author Ticug, Jonal Ray 
 * @version 1.0 
 */
public class UpdatePasswordAction implements ActionHandler{
	/**
	 * This functions is used in change-password.js gets the old password, 
	 * gets the sessionID and retrives the user and compares the old password 
	 * with the password retrieved from the user. If equal, the service will 
	 * update the password and return a success message, otherwise it will return 
	 * an error message.
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
		/* retrieves the sessionID, old password and new password */
		int id = (int) request.getSession().getAttribute("sessionID");
		String oldPW = request.getParameter("old_pw");
		String newPW = request.getParameter("new_pw");
		
		/* gets the the org based on sessionID */
		Organization org = OrganizationService.getOrg(id);
		
		/* compares the password with the org */
		if (org.getPassword().equals(oldPW)) {
			/* updates if equal */
			OrganizationService.updatePassword(id, newPW);
			/* returns success message */
			response.getWriter().write("Password successfully changed!");
		}
		/* returns error message */
		else response.getWriter().write("Input for current password is incorrect!");
	}

}
