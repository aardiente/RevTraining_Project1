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
import DAO.ManagerDAO;
import DAO.ManagerDAOImpl;
import Models.Employee;
import Models.Manager;

/**
 * Servlet implementation class UpdateManager
 */
public class UpdateManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ManagerDAO dao = new ManagerDAOImpl();
		String resName = "resFlag";
		
		String[] data = {
						request.getParameter("username"),	request.getParameter("password"),	request.getParameter("fname"),				// 0 1 2
						request.getParameter("lname"),		request.getParameter("email"),		request.getParameter("phonenumber"), 		// 3 4 5
						request.getParameter("address"),	request.getParameter("city"),		request.getParameter("state"), 				// 6 7 8
						request.getParameter("zip"), 		request.getParameter("country")};	// 9 10 11
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RequestDispatcher dis = null;
		
		HttpSession session = request.getSession(); 
		Manager man = (Manager)session.getAttribute("CurMan");
		session.setAttribute(resName, null);
		
		if(man != null)
		{
			man.setFirstName(data[2]);
			man.setLastName(data[3]);
			man.setEmail(data[4]);
			man.setPhoneNum(data[5]);
			String add = data[6] + ", " + data[7] + " " + data[8] + " " + data[9] + " " + data[10];
			man.setAddress(add);
			
			System.out.println(man);
			if(dao.updateManager(man))
				session.setAttribute(resName, true);
			else
				session.setAttribute(resName, false);
		}
		
		dis = request.getRequestDispatcher("UpdateManager.jsp");
		dis.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
