package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.EmployeeDAO;
import DAO.EmployeeDAOImpl;
import Models.Employee;

/**
 * Servlet implementation class SignUpController
 */
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpController() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		EmployeeDAO dao = new EmployeeDAOImpl();
		String[] data = {
						request.getParameter("username"),	request.getParameter("password"),	request.getParameter("fname"),
						request.getParameter("lname"),		request.getParameter("email"),		request.getParameter("address"),
						request.getParameter("phonenumber")};
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RequestDispatcher dis = null;
		if (dao.addEmployee( new Employee(-1, data[0], data[1], data[2], data[3], data[4], data[5], data[6], new Date(System.currentTimeMillis()) )))
		{
			dis = request.getRequestDispatcher("Login.html");
			dis.include(request, response);
			out.println("Account Created.");
			
		}
		else
		{
			dis = request.getRequestDispatcher("SignUp.html");
			dis.include(request, response);
			out.println("Sign up failed");
		}
	}

}
