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
import DAO.RequestDAO;
import DAO.RequestDAOImpl;
import Models.Employee;
import Models.ReimbursmentTicket;

/**
 * Servlet implementation class CreateRequestController
 */
public class CreateRequestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateRequestController() {
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
		response.setContentType("text/html");
		
		String amountStr = request.getParameter("AmountField");
		
		PrintWriter out = response.getWriter();
	

		//out.println("<html><body>");
		//out.println("Login attempt to " + user + (result ? " is successful" : " has failed" ) + "</body></html>");
		
		HttpSession session = request.getSession(); 
		RequestDispatcher dis = null;

		Employee emp = (Employee) session.getAttribute("CurEmp");
		
		if(emp != null)
		{
			RequestDAO dao = new RequestDAOImpl();
			ReimbursmentTicket ref = new ReimbursmentTicket(-1, Float.valueOf(amountStr), null, 0, emp);
			dao.createRequest(ref);
	
		}

		dis = request.getRequestDispatcher("EmployeeHome.jsp");
		dis.include(request, response);

	}

}
