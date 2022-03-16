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
 * Servlet implementation class UpdateEmployeeController
 */
public class UpdateEmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateEmployeeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmployeeDAO dao = new EmployeeDAOImpl();
		String[] data = {
						request.getParameter("username"),	request.getParameter("password"),	request.getParameter("fname"),				// 0 1 2
						request.getParameter("lname"),		request.getParameter("email"),		request.getParameter("phonenumber"), 		// 3 4 5
						request.getParameter("address"),	request.getParameter("city"),		request.getParameter("state"), 				// 6 7 8
						request.getParameter("zip"), 		request.getParameter("country")};	// 9 10 11
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RequestDispatcher dis = null;
		
		HttpSession session = request.getSession(); 
		Employee emp = (Employee)session.getAttribute("CurEmp");
		
		if(emp != null)
		{
			emp.setFirstName(data[2]);
			emp.setLastName(data[3]);
			emp.setEmail(data[4]);
			emp.setPhoneNum(data[5]);
			String add = data[6] + ", " + data[7] + " " + data[8] + " " + data[9] + " " + data[10];
			emp.setAddress(add);
			
			System.out.println(emp);
			dao.updateEmployee(emp);
		}
		
		dis = request.getRequestDispatcher("UpdateEmployee.jsp");
		dis.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
