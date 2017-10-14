package cso.dlsu.aps.action.organization;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cso.dlsu.aps.action.ActionHandler;
import cso.dlsu.aps.bean.Organization;
import cso.dlsu.aps.service.OrganizationService;

public class UpdatePasswordAction implements ActionHandler{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = (String) request.getSession().getAttribute("sessionun");
		String oldPW = request.getParameter("old_pw");
		String newPW = request.getParameter("new_pw");
		
		Organization org = OrganizationService.getOrgByUsername(username);
		
		if (org.getPassword().equals(oldPW)) {
			OrganizationService.updatePassword(org.getId(), newPW);
			response.getWriter().write("Password successfully changed!");
		}
		
		else response.getWriter().write("Input for current password is incorrect!");
	}

}
