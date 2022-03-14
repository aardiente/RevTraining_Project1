package services;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import DAO.RequestDAOImpl;
import Models.Employee;
import Models.ReimbursmentTicket;

public class ControllerHelper 
{
	public static void getPendingById(PrintWriter pw, HttpSession ses, Employee emp)
	{
		ArrayList<ReimbursmentTicket> tickList = new RequestDAOImpl().getPendingById(emp.getId());
		
		pw.append("<br/><div class=\"pendingTable\"><table class=\"table table-dark\"><thead>");
		pw.append("<tr> <th scope=\"col\">Id</th> <th scope=\"col\">Amount</th>  <th scope=\"col\">Date</th> <th scope=\"col\">Status</th> </tr> </thead>");
		for(ReimbursmentTicket t : tickList)
		{
			pw.append("<tr> <td>" + t.getId() + "</td><td>" + t.getAmount() + "</td><td>" + t.getRequestDate() + "</td><td>" + t.getStatus() + "</td></tr>");
		}
		pw.append("</table></div>");
	}
	public static void getAllPending(PrintWriter pw, HttpSession ses, ArrayList<ReimbursmentTicket> tickList)
	{
		pw.append("<br/><div class=\"pendingTable\"><table class=\"table table-dark\"><thead>");
		pw.append("<tr> <th scope=\"col\">Ticket Id</th> <th scope=\"col\">Employee Name</th> <th scope=\"col\">Username</th> <th scope=\"col\">Amount</th> <th scope=\"col\">Request Date</th> <th scope=\"col\">Status</th> </tr> </thead>");
		for(ReimbursmentTicket t : tickList)
		{
			pw.append("<tr> <td>" + t.getId() + "</td><td>" + t.getOwner().getFirstName() + " " + t.getOwner().getLastName() + "</td><td>" + t.getOwner().getUsername() + "</td><td>" + t.getAmount() + "</td><td>" + t.getRequestDate() + "</td><td>" + t.getStatus() + "</td></tr>");
		}
		pw.append("</table></div>");
	}
}
