package DAO;

import java.util.ArrayList;

import Models.Manager;
import Models.ReimbursmentTicket;

public interface RequestDAO 
{
	public boolean createRequest(ReimbursmentTicket obj);
	public boolean updateRequest(ReimbursmentTicket obj);
	public boolean archiveRequest(ReimbursmentTicket obj, Manager manager);
	
	public ArrayList<ReimbursmentTicket> getPendingById(int id);
	public ArrayList<ReimbursmentTicket> getAllById(int id);
	public ArrayList<ReimbursmentTicket> getAllPending();
	public ArrayList<ReimbursmentTicket> getAllArchived();
}
