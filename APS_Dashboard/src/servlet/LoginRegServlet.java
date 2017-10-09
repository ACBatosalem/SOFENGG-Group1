package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import bean.Organization;
import service.OrganizationService;

/**
 * Servlet implementation class LoginRegServlet
 */
@WebServlet(urlPatterns={"/login/*", "/logout"})
public class LoginRegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginRegServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String path = request.getServletPath();
		
		switch (path) {
		case "/login" : 
			LoginUser(request, response);
			break;
		case "/logout" :
			Logout(request, response);
			break;
		}
	}

	private void LoginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("user");
		String password = request.getParameter("password");
		
		Organization user = OrganizationService.getOrgByUsername(username);
		response.setContentType("text/html;charset=UTF-8");
		if (user == null) {
			response.getWriter().write("This user is invalid or unauthorized!");
		} else if (!user.getPassword().equals(password)) {
			response.getWriter().write("The password is incorrect.");
		} else if (user.getAlias().equals("CSO")) {
			response.getWriter().write("aps");
		} else {
			response.getWriter().write("org");
		}
	}
	
	private void Logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		request.getSession().invalidate();
		System.out.println("Logged out");
		response.sendRedirect("loginreg.jsp");
	}


	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
