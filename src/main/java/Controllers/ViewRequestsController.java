package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.RequestDAO;
import DAO.RequestDAOImpl;
import Models.Employee;
import Models.ReimbursmentTicket;
import services.ControllerHelper;

/**
 * Servlet implementation class ViewRequestsController
 */
public class ViewRequestsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewRequestsController() {
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
		RequestDAO dao = new RequestDAOImpl();
		HttpSession session = request.getSession(); 
		PrintWriter out = response.getWriter();
		RequestDispatcher dis = null;
		Employee emp = (Employee)session.getAttribute("CurEmp");
		
		if(request.getParameter("pendingBtn") != null)
		{
			ControllerHelper.getPendingById(out, session, emp);
			dis = request.getRequestDispatcher("ViewRequests.jsp");
			dis.include(request, response);
		}
		else if(request.getParameter("viewAllBtn") != null)
		{
			ArrayList<ReimbursmentTicket> tickList = dao.getAllById(emp.getId());
			
			ControllerHelper.getAllPending(out, session, tickList);
			ControllerHelper.getPendingById(out, session, emp);
			dis = request.getRequestDispatcher("ViewRequests.jsp");
			dis.include(request, response);
		}
			
	}
}
