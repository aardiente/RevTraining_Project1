package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import services.DBConnection;

public class UserAccountDAOImpl implements UserAccountDAO {

	private static final String checkUser = "select user_name from useraccount where user_name = ?";
	
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
		// TODO Auto-generated method stub
		return false;
	}

}
