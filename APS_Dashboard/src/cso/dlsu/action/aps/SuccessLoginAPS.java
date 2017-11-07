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
		String f = request.getParameter("filter");
		String org = request.getParameter("org");
		if (org == null || org.equals("All")) {
			request.setAttribute("orgName", "All");
			if (f == null) {
				request.setAttribute("filter", "tt");
				request.getSession().setAttribute("dashboard_data", DashboardService.getAllDashboardData("none"));
			} else if (f.equals("tf")) {
				request.setAttribute("filter", "tf");
				request.getSession().setAttribute("dashboard_data", DashboardService.getAllDashboardData("acads"));
			} else if (f.equals("ft")) {
				request.setAttribute("filter", "ft");
				request.getSession().setAttribute("dashboard_data", DashboardService.getAllDashboardData("non-acads"));
			} else {
				request.setAttribute("filter", "tt");
				request.getSession().setAttribute("dashboard_data", DashboardService.getAllDashboardData("none"));
			}
		} else {
			request.setAttribute("orgName", org);
			if (f == null) {
				request.setAttribute("filter", "tt");
				request.getSession().setAttribute("dashboard_data", DashboardService.getOrgDashboardData(org, "none"));
			} else if (f.equals("tf")) {
				request.setAttribute("filter", "tf");
				request.getSession().setAttribute("dashboard_data", DashboardService.getOrgDashboardData(org, "acads"));
			} else if (f.equals("ft")) {
				request.setAttribute("filter", "ft");
				request.getSession().setAttribute("dashboard_data", DashboardService.getOrgDashboardData(org, "non-acads"));
			} else {
				request.setAttribute("filter", "tt");
				request.getSession().setAttribute("dashboard_data", DashboardService.getOrgDashboardData(org, "none"));
			}
		}
		
		request.getRequestDispatcher("homeAPS/home_aps.jsp").forward(request, response);
	}
}
