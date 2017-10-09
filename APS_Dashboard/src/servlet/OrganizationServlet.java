package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Organization;
import service.OrganizationService;

/**
 * Servlet implementation class OrganizationServlet
 */
@WebServlet(urlPatterns={"/getAllOrgs", "/addOrg", "/deleteOrg", "/getOrg", "/updateOrg"})
public class OrganizationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrganizationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = request.getServletPath();
		
		switch(path) {
			case "/getAllOrgs" :
				GetAllOrgs(request, response);
				break;
			case "/addOrg":
				AddOrg(request,response);
				break;
			case "/deleteOrg":
				DeleteOrg(request,response);
				break;
			case "/getOrg":
				GetOrg(request,response);
				break;
			case "/updateOrg":
				UpdateOrg(request,response);
				break;
		}
	}
	
	private void UpdateOrg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Organization org = new Organization();
		
		org.setName(request.getParameter("name"));
		org.setAlias(request.getParameter("alias"));
		
		// call studentservice to update student into db
		OrganizationService.updateOrg(id, org);
		response.setContentType("text/html;charset=UTF-8");
		if(OrganizationService.updateOrg(id, org)) {
			response.getWriter().write("updated");
		} else {
			response.getWriter().write("Organization was not updated");
		}
		// getAllStudents
		
	}

	private void GetOrg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		// get student from db
		Organization org = OrganizationService.getOrg(id);
		request.setAttribute("org", org);
		
		// forward the request to a student page
		request.getRequestDispatcher("updateOrg.jsp").forward(request, response);
		
	}

	private void DeleteOrg(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		boolean success = OrganizationService.deleteOrg(id);
		response.getWriter().write(String.valueOf(success));
		
	}

	private void AddOrg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Random r = new Random();
		String password = "";
		Organization org = new Organization();
		
		org.setName(request.getParameter("name"));
		org.setAlias(request.getParameter("alias"));
		
		for(int i = 0; i < 6; i++) {
			password += (char) (97 + r.nextInt(26));
		}
		
		System.out.println(password);
		
		org.setPassword(password);

		response.setContentType("text/html;charset=UTF-8");
		if(OrganizationService.addOrg(org)) {
			response.getWriter().write("added");
		} else {
			response.getWriter().write("Organization not added");
		}
		
	}

	private void GetAllOrgs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Organization> orgs = OrganizationService.getAllOrgs();
		
		// bind studentlist to request scope
		request.setAttribute("orgs", orgs);
		
		// dispatch request to a view (jsp)
		request.getRequestDispatcher("organization.jsp").forward(request, response);
		
	}

}
