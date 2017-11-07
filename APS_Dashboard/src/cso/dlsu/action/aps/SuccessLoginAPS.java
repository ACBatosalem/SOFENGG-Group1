package cso.dlsu.action.aps;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cso.dlsu.action.ActionHandler;
import cso.dlsu.service.DashboardService;
import cso.dlsu.service.OrganizationService;

public class SuccessLoginAPS implements ActionHandler {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("orgs", OrganizationService.getAllOrgs());
		request.getSession().setAttribute("dashboard_data", DashboardService.getAllDashboardData("none"));
		request.getRequestDispatcher("homeAPS/home_aps.jsp").forward(request, response);
	}
}
