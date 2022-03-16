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
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(); 
		RequestDispatcher dis = null;

		Employee emp = (Employee) session.getAttribute("CurEmp");
		String amountStr = request.getParameter("amountTB");
		System.out.println(amountStr);
		
		if(emp != null)
		{
			RequestDAO dao = new RequestDAOImpl();
			ReimbursmentTicket ref = new ReimbursmentTicket(-1, Float.valueOf(amountStr), null, 0, emp);
			dao.createRequest(ref);
	
		}

		session.setAttribute("CurEmp", emp);
		dis = request.getRequestDispatcher("CreateRequest.jsp");
		dis.include(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

	}

}
