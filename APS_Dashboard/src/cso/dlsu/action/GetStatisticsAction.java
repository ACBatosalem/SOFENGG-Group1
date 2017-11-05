package cso.dlsu.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cso.dlsu.bean.Organization;
import cso.dlsu.service.DocumentService;
import cso.dlsu.service.OrganizationService;

public class GetStatisticsAction implements ActionHandler {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = (int) request.getSession().getAttribute("sessionID");
		Organization user = OrganizationService.getOrg(id);
		
		if (user.getUserName().equals("APS")) {
			request.getSession().setAttribute("academic", DocumentService.getCountByNature("Academic"));
			request.getSession().setAttribute("nonacademic", DocumentService.getCountByNature("Non-academic"));
			request.getRequestDispatcher("statistics.jsp").forward(request, response);
		} else {
			request.getSession().setAttribute("academic", DocumentService.getCountByNature("Academic"));
			request.getSession().setAttribute("nonacademic", DocumentService.getCountByNature("Non-academic"));
			request.getRequestDispatcher("statistics.jsp").forward(request, response);
		}
	}

}
