package Controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


import DAO.RequestDAO;
import DAO.RequestDAOImpl;
import Models.Manager;
import Models.ReimbursmentTicket;

/**
 * Servlet implementation class ArchiveController
 */

public class ArchiveController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Logger log = Logger.getLogger(ArchiveController.class.getName());
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArchiveController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDAO dao = new RequestDAOImpl();
		HttpSession session = request.getSession(); 
		RequestDispatcher dis = null;
		
		ArrayList<ReimbursmentTicket> tickList = (session.getAttribute("formList") == null) ? null : (ArrayList<ReimbursmentTicket>)session.getAttribute("formList");
		
		for(ReimbursmentTicket t : tickList)
		{
			String val = request.getParameter("radio" + t.getId());
			
			if(val.equals("Approve"))
			{
				Manager ref = (Manager)session.getAttribute("CurMan");
				t.setStatus(ReimbursmentTicket.statusFlag.Approved);
				log.info("Attempt to approve " + t + " was " + dao.archiveRequest(t, ref));
				
				
			}
			else if (val.equals("Deny"))
			{
				Manager ref = (Manager)session.getAttribute("CurMan");
				t.setStatus(ReimbursmentTicket.statusFlag.Denied);
				log.info("Attempt to deny " + t + " was " + dao.archiveRequest(t, ref));
			}
		}
		tickList = dao.getAllPending();
		
		session.setAttribute("formList", tickList);
		log.info("formList set to " + tickList);
		
		dis = request.getRequestDispatcher("ManagePending.jsp");
		dis.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
