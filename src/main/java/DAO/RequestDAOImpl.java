package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import Models.Employee;
import Models.Manager;
import Models.ReimbursmentTicket;
import services.DBConnection;

public class RequestDAOImpl implements RequestDAO 
{
	private static String creRequestSQL = "{? = call createreimburmentrequest(?, ?)}";
	private static String getAllPendingSQL = "select request_id, request_amount, request_date, request_status from ReimbursmentRequest where fk_employeeid_owner = ?";
	private static String getAllArchivedSQL = "select request_id, request_amount, request_date, request_status, reimbursement_archived_date, fk_managerid from ReimbursmentArchive where fk_employeeid_owner = ?";
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
			state.setInt(3, obj.getOwner().getId());
			
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
	public boolean updateRequest(ReimbursmentTicket obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean archiveRequest(ReimbursmentTicket obj, Manager manager) {
		// TODO Auto-generated method stub
		return false;
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
					tickList.add(new ReimbursmentTicket(set.getInt(1), set.getFloat(2), set.getDate(3), set.getInt(4), temp));
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
					mTemp = mdao.getById(set.getInt(6));
					tickList.add(new ReimbursmentTicket(set.getInt(1), set.getFloat(2), set.getDate(3), set.getDate(5), set.getInt(4), temp, mTemp));
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

}
