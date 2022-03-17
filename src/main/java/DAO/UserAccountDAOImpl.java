package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import services.DBConnection;

public class UserAccountDAOImpl implements UserAccountDAO {

	private static final String checkUser = "select user_name from useraccount where user_name = ?";
	private static final String checkEmail = "select email from contact_info join useraccount on fk_infoid = info_id where email = ?";
	
	@Override
 	public boolean checkUsername(String username) // Returns true if the username isn't taken
	{
		PreparedStatement state = null;
		boolean flag = true;
		Connection con = null;
		
		try 
		{
			con = DBConnection.getConnection();
			state = con.prepareStatement(checkUser);
			state.setString(1, username);
			
			if(state.execute())
				if(state.getResultSet().next())
					flag = false;
			
			
			state.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally
		{
			DBConnection.closeConnection(con);
		}
		
		return flag;
	}

	@Override
	public boolean checkEmail(String email) {
		PreparedStatement state = null;
		boolean flag = true;
		Connection con = null;
		
		try 
		{
			con = DBConnection.getConnection();
			state = con.prepareStatement(checkEmail);
			state.setString(1, email);
			
			if(state.execute())
				if(state.getResultSet().next())
					flag = false;
			
			
			state.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally
		{
			DBConnection.closeConnection(con);
		}
		
		return flag;
	}

}
