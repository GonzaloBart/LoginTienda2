package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LoginDao;

/**
 * Servlet implementation class ServLogin
 */
@WebServlet("/ServLogin")
public class ServLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

		response.setContentType("text/html"); 
		
		PrintWriter out = response.getWriter();  

		String n=request.getParameter("email");  
		String p=request.getParameter("pass"); 

		if (LoginDao.checkUserLogin(n, p)) {
			response.sendRedirect(request.getContextPath() + "/landing-page.html");
		} else {
			out.print("<h1>Sorry username or password error<h1>");
			response.sendRedirect("index.html");
		}

		out.close();

		/**
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  



		if(LoginDao.validate(n, p)){  
			RequestDispatcher rd=request.getRequestDispatcher("servlet2");  
			rd.forward(request,response);  
		}  
		else{  
			out.print("Sorry username or password error");  
			RequestDispatcher rd=request.getRequestDispatcher("index.html");  
			rd.include(request,response);  
		}  

		out.close();  */

	}

}
