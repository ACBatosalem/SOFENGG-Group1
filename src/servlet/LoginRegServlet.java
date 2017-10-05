package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import service.UserService;

/**
 * Servlet implementation class LoginRegServlet
 */
@WebServlet(urlPatterns={"/login", "/register", "/logout", "/relog"})
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String path = request.getServletPath();
		
		switch (path) {
		case "/register" :
			RegisterUser(request, response);
			break;
		case "/login" : 
			LoginUser(request, response);
			break;
		case "/logout" :
			Logout(request, response);
			break;
		case "/relog" :
			Relog(request, response);
			break;
		}
	}

	private void RegisterUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String retype = request.getParameter("retype");
		
		User user = UserService.getUserByEmail(email);
		
		if (user != null && user.getPassword() == null && password.equals(retype)) {
			UserService.updatePassword(user.getId(), password);
		} else if (user == null) {
			//redirect to error page or w/e
			request.getRequestDispatcher("loginreg.jsp").forward(request, response);
			System.out.println("Email is not accepted/authorized for access.");
		} else if (!password.equals(retype)) {
			//redirect to error page or w/e
			request.getRequestDispatcher("loginreg.jsp").forward(request, response);
			System.out.println("Password does not match!");
		} else {
			//redirect to error page or w/e
			request.getRequestDispatcher("loginreg.jsp").forward(request, response);
			System.out.println("Error: unsuccessful");
		}
	}
	
	private void LoginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		User user = UserService.getUserByEmail(email);
		
		if (user != null && user.getPassword().equals(password)) {
			request.getSession().setAttribute("user", email);
			
			if (user.getType().equals("APS")) {
				//redirect to aps successful login page
				request.getRequestDispatcher("loginreg.jsp").forward(request, response);
				System.out.println("Successfully logged in as APS user");
			} else if (user.getType().equals("ORG")) {
				//redirect to org successful login page
				request.getRequestDispatcher("loginreg.jsp").forward(request, response);
				System.out.println("Successfully logged in as ORG user");
			}
		} else {
			//redirect to the error page or w/e
			request.getRequestDispatcher("loginreg.jsp").forward(request, response);
			System.out.println("Login failed!");
		}
	}
	
	private void Logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		request.getSession().invalidate();
		System.out.println("Logged out");
		response.sendRedirect("loginreg.jsp");
	}

	private void Relog(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String username = null;
		
		//check cookie if username exists, set if it does
		
		if (username != null) {
			request.getSession().setAttribute("user", username);
			// go to success page
			request.getRequestDispatcher("loginreg.jsp").forward(request, response);
		} else {
			response.sendRedirect("loginreg.jsp");
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
