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
		
		if(request.getParameter("pendingBtn") != null)
		{
			getPending(out, session);
			dis = request.getRequestDispatcher("ViewRequests.jsp");
			dis.include(request, response);
		}
		else if(request.getParameter("viewAllBtn") != null)
		{
			Employee emp = (Employee)session.getAttribute("CurEmp");
			ArrayList<ReimbursmentTicket> tickList = dao.getAllById(emp.getId());
			
			out.println("<br/><div class=\"pendingTable\"><table class=\"table table-dark\"><thead>");
			out.println("<tr> <th scope=\"col\">Id</th> <th scope=\"col\">Amount</th>  <th scope=\"col\">Request Date</th> <th scope=\"col\">Archive Date</th> <th scope=\"col\">Status</th> </tr> </thead>");
			for(ReimbursmentTicket t : tickList)
			{
				out.println("<tr> <td>" + t.getId() + "</td><td>" + t.getAmount() + "</td><td>" + t.getRequestDate() + "</td><td>" + t.getCloseDate() + "</td><td>" + t.getStatus() + "</td></tr>");
			}
			out.println("</table></div>");
			getPending(out, session);
			dis = request.getRequestDispatcher("ViewRequests.jsp");
			dis.include(request, response);
		}
			
	}
	private void getPending(PrintWriter pw, HttpSession ses)
	{
		Employee emp = (Employee)ses.getAttribute("CurEmp");
		ArrayList<ReimbursmentTicket> tickList = new RequestDAOImpl().getPendingById(emp.getId());
		
		pw.println("<br/><div class=\"pendingTable\"><table class=\"table table-dark\"><thead>");
		pw.println("<tr> <th scope=\"col\">Id</th> <th scope=\"col\">Amount</th>  <th scope=\"col\">Date</th> <th scope=\"col\">Status</th> </tr> </thead>");
		for(ReimbursmentTicket t : tickList)
		{
			pw.println("<tr> <td>" + t.getId() + "</td><td>" + t.getAmount() + "</td><td>" + t.getRequestDate() + "</td><td>" + t.getStatus() + "</td></tr>");
		}
		pw.println("</table></div>");
	}


}
