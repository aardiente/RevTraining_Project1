package Controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.EmployeeDAO;
import DAO.EmployeeDAOImpl;
import DAO.ManagerDAOImpl;
import Models.Employee;
import Models.Manager;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController()
    {
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
		response.setContentType("text/html");
		
		String user = request.getParameter("loginUser");
		String pass = request.getParameter("loginPass");
		PrintWriter out = response.getWriter();
		
		EmployeeDAO dao = new EmployeeDAOImpl();
		boolean result =  dao.verifyLogin(user, pass);
		out.println("<html><footer>");
		out.println("Login attempt to " + user + (result ? " is successful" : " has failed" ) + "</footer></html>");
		
		HttpSession session = request.getSession(); 
		RequestDispatcher dis = null;
		if(result)
		{
			if(dao.isEmployee(user))
			{
				Employee temp = dao.searchByUsername(user);
				
				if(temp != null)
					session.setAttribute("CurEmp", temp);
				
				System.out.println(temp);
			
				dis = request.getRequestDispatcher("EmployeeHome.jsp");
			}
			else
			{
				Manager temp = new ManagerDAOImpl().getByUsername(user);
				
				if(temp != null)
					session.setAttribute("CurMan", temp);
				
				System.out.println(temp);
				dis = request.getRequestDispatcher("ManagerHome.jsp");
			}
			session.setAttribute("username", user);
			session.setAttribute("password", pass);
		}
		else
		{
			dis = request.getRequestDispatcher("Login.html");
		}
		dis.include(request, response);
	}

}
