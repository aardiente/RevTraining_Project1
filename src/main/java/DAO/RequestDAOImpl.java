package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import Models.Employee;
import Models.Manager;
import Models.ReimbursmentTicket;
import services.DBConnection;

public class RequestDAOImpl implements RequestDAO 
{
	private static final String creRequestSQL = "{? = call createreimburmentrequest(?,?,?)}";
	private static final String getAllPendingSQL = "select request_id, request_amount, request_description, request_date, request_status from ReimbursmentRequest where fk_employeeid_owner = ?";
	private static final String getAllArchivedSQL = "select reimbursement_id, reimbursement_amount, reimbursement_description, reimbursement_date, reimbursement_status, reimbursement_archived_date, fk_managerid from ReimbursmentArchive where fk_employeeid_owner = ?";
	private static final String archiveRequestSQL = "{? = call archiveReimbursement(?, ?, ?, ?, ?, ?)}"; // Id ref = amount, desc, date, status, ownerId, manId
	private static final String getAllArchived = "select reimbursement_id, reimbursement_amount, reimbursement_description, reimbursement_date, reimbursement_status, reimbursement_archived_date, fk_managerid, fk_employeeid_owner from ReimbursmentArchive";
													
	
	@Override
    public boolean createRequest(ReimbursmentTicket obj) 
	{
		Connection con = DBConnection.getConnection();
		boolean flag = false;
		try 
		{
			CallableStatement state = con.prepareCall(creRequestSQL);
			state.registerOutParameter(1, Types.INTEGER);
			
			state.setFloat(2, obj.getAmount());
			state.setString(3, obj.getDescription());
			state.setInt(4, obj.getOwner().getId());
			
			
			if(state.executeUpdate() > 0)
			{
				ResultSet set = state.getResultSet();
				
				obj.setId(set.getInt(1));
				flag = true;
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			DBConnection.closeConnection(con);
		}
		
		return flag;
	}

	@Override
	public boolean updateRequest(ReimbursmentTicket obj) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean archiveRequest(ReimbursmentTicket obj, Manager manager) 
	{
		CallableStatement state = null;
		Connection con = null;
		boolean flag = false;
		
		try 
		{
			con = DBConnection.getConnection();
			state = con.prepareCall(archiveRequestSQL);
			state.registerOutParameter(1, Types.INTEGER);
			state.setFloat(2, obj.getAmount());
			state.setString(3, obj.getDescription());
			state.setDate(4, obj.getRequestDate());
			state.setInt(5, obj.getStatus().getStatusAsInt());
			state.setInt(6, obj.getOwner().getId());
			state.setInt(7, manager.getId());
			
			
			
			obj.setAuditor(manager);
			obj.setCloseDate(new Date(System.currentTimeMillis()));
			
			if(state.executeUpdate() > 0)
				flag = true;
			
			state.close();
			
		} catch (SQLException e) 
		{
			e.printStackTrace();
		} finally
		{
			DBConnection.closeConnection(con);
		}
		
		
		return flag;
	}

	@Override
	public ArrayList<ReimbursmentTicket> getPendingById(int id) 
	{
		ArrayList<ReimbursmentTicket> tickList = new ArrayList<ReimbursmentTicket>();
		
		Connection con = DBConnection.getConnection();
		try 
		{
			PreparedStatement state = con.prepareStatement(getAllPendingSQL);
			state.setInt(1, id);
			if(state.execute())
			{
				ResultSet set = state.getResultSet();
				Employee temp = null;
				EmployeeDAO dao = new EmployeeDAOImpl();
				while(set.next())
				{
					temp = dao.searchById(id);
					tickList.add(new ReimbursmentTicket(set.getInt(1), set.getFloat(2), set.getString(3), set.getDate(4), set.getInt(5), temp));
				}
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}finally
		{
			DBConnection.closeConnection(con);
		}
		
		return tickList;
	}

	@Override
	public ArrayList<ReimbursmentTicket> getAllById(int id) 
	{
		ArrayList<ReimbursmentTicket> tickList = new ArrayList<ReimbursmentTicket>();
		PreparedStatement state = null;
		Connection con = DBConnection.getConnection();
		try 
		{
			state = con.prepareStatement(getAllArchivedSQL);
			state.setInt(1, id);
			if(state.execute())
			{
				ResultSet set = state.getResultSet();
				Employee temp = null;
				EmployeeDAO dao = new EmployeeDAOImpl();
				ManagerDAO mdao = new ManagerDAOImpl();
				
				Manager mTemp = null;
				while(set.next())
				{
					temp = dao.searchById(id);
					mTemp = mdao.searchById(set.getInt(7));
					tickList.add(new ReimbursmentTicket(set.getInt(1), set.getFloat(2), set.getString(3), set.getDate(4), set.getDate(6), set.getInt(5), temp, mTemp));
				}
				
			}
		} catch (SQLException e) 
		{
			System.out.println(e.getMessage());
		}finally
		{
			DBConnection.closeConnection(con);
			try {
				state.close();
			} catch (NullPointerException|SQLException e) {
				e.printStackTrace();
			}
		}
		
		return tickList;
	}

	@Override
	public ArrayList<ReimbursmentTicket> getAllPending() 
	{
		ArrayList<ReimbursmentTicket> tickList = new ArrayList<ReimbursmentTicket>();
		Statement state = null;
		Connection con = DBConnection.getConnection();
		try 
		{
			state = con.createStatement();

			if(state.execute("select request_id, request_amount, request_description, request_date, request_status, fk_employeeid_owner from ReimbursmentRequest"))
			{
				ResultSet set = state.getResultSet();
				Employee temp = null;
				EmployeeDAO dao = new EmployeeDAOImpl();
				
				Manager mTemp = null;
				while(set.next())
				{
					temp = dao.searchById(set.getInt(6));
					tickList.add(new ReimbursmentTicket(set.getInt(1), set.getFloat(2), set.getString(3), set.getDate(4), set.getInt(5), temp));
				}
				
			}
			state.close();
		} catch (SQLException e) 
		{
			System.out.println(e.getMessage());
		}finally
		{
			DBConnection.closeConnection(con);
			try {
				state.close();
			} catch (NullPointerException|SQLException e) {
				e.printStackTrace();
			}
		}
		
		return tickList;
	}

	@Override
	public ArrayList<ReimbursmentTicket> getAllArchived() 
	{
		Statement state = null;
		Connection con = null;
		ArrayList<ReimbursmentTicket> tList = new ArrayList<ReimbursmentTicket>();
		
		try 
		{
			con = DBConnection.getConnection();
			state = con.createStatement();
			
			if(state.execute(getAllArchived))
			{
				ResultSet set = state.getResultSet();
				Employee temp = null;
				EmployeeDAO dao = new EmployeeDAOImpl();
				ManagerDAO mdao = new ManagerDAOImpl();
				
				Manager mTemp = null;
				while(set.next())
				{
					// 1 rId, 2 rAmount, 3 rDesc, 4 rDate, 5 rStatus, 6 rArcDate, 7 fkMan, 8 fkEmp
					temp = dao.searchById(set.getInt(8));
					mTemp = mdao.searchById(set.getInt(7));
					tList.add(new ReimbursmentTicket(set.getInt(1), set.getFloat(2), set.getString(3), set.getDate(4), set.getDate(6), set.getInt(5), temp, mTemp));
				}
			}
			
		} catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			DBConnection.closeConnection(con);
		}
		return tList;
	}

}
