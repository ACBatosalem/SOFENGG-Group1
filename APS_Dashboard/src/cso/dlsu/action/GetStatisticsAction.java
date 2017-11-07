package cso.dlsu.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cso.dlsu.bean.Organization;
import cso.dlsu.service.DashboardService;
import cso.dlsu.service.OrganizationService;


public class GetStatisticsAction implements ActionHandler {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = (int) request.getSession().getAttribute("sessionID");
		Organization user = OrganizationService.getOrg(id);
		
		if (user.getUserName().equals("APS")) {
			int acads = DashboardService.getAcademicCount();
			request.getSession().setAttribute("academic", acads);
			request.getSession().setAttribute("nonacademic", DashboardService.getTotalCount() - acads);
			request.getRequestDispatcher("statistics.jsp").forward(request, response);
		} else {
			int acads = DashboardService.getAcademicCount(user.getId());
			request.getSession().setAttribute("academic", acads);
			request.getSession().setAttribute("nonacademic", DashboardService.getTotalCount(user.getId()) - acads);
			request.getRequestDispatcher("statistics.jsp").forward(request, response);
		}
	}

}
