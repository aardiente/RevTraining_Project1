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

import DAO.EmployeeDAOImpl;
import DAO.RequestDAO;
import DAO.RequestDAOImpl;
import Models.Employee;
import Models.ReimbursmentTicket;
import services.ControllerHelper;

/**
 * Servlet implementation class ManageRequestsController
 */
public class ManageRequestsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageRequestsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDAO dao = new RequestDAOImpl();
		HttpSession session = request.getSession(); 
		RequestDispatcher dis = null;
		
		ArrayList<ReimbursmentTicket> tickList = (session.getAttribute("formList") == null) ? null : (ArrayList<ReimbursmentTicket>)session.getAttribute("formList");

		Object edit = request.getParameter("editBtn");
		Object view = request.getParameter("viewBtn");
		
		if(session.getAttribute("editFlag") == null)
			session.setAttribute("editFlag", false);
		
		boolean editFlag = ((Boolean)session.getAttribute("editFlag")).booleanValue();
		
		if(edit != null)
			session.setAttribute("editFlag", !editFlag);

		else if(request.getParameter("searchBtn") != null)
		{
			String uname = request.getParameter("searchTB");
			if(uname != null)
			{
				Employee search = new EmployeeDAOImpl().searchByUsername(uname);
				
				if(search != null)
					tickList = new RequestDAOImpl().getPendingById(search.getId());

			}
		}
		else if(view != null)
		{
			tickList = dao.getAllPending();
		}

		session.setAttribute("formList", tickList);
		dis = request.getRequestDispatcher("ManagePending.jsp");
		dis.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDAO dao = new RequestDAOImpl();
		HttpSession session = request.getSession(); 
		RequestDispatcher dis = null;
		
		ArrayList<ReimbursmentTicket> tickList = (session.getAttribute("formList") == null) ? null : (ArrayList<ReimbursmentTicket>)session.getAttribute("formList");
		Object view = request.getParameter("viewBtn");
		Object edit = request.getParameter("editBtn");
		
		if(session.getAttribute("editFlag") == null)
			session.setAttribute("editFlag", false);
		
		if( view != null )
		{
			tickList = dao.getAllPending();
			session.setAttribute("editFlag", false);
		}
		if(edit != null)
		{
			session.setAttribute("editFlag", true);
		}
		if(request.getParameter("searchBtn") != null)
		{
			String uname = request.getParameter("searchTB");
			if(uname != null)
			{
				Employee search = new EmployeeDAOImpl().searchByUsername(uname);
				
				if(search != null)
				{
					tickList = new RequestDAOImpl().getPendingById(search.getId());
					
					for(ReimbursmentTicket t : tickList)
						System.out.println(t);
				}
			}
		}

		session.setAttribute("formList", tickList);
		dis = request.getRequestDispatcher("ManagePending.jsp");
		dis.include(request, response);
	}

}
