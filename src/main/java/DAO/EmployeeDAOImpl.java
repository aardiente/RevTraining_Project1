package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.postgresql.ssl.DbKeyStoreSocketFactory.DbKeyStoreSocketException;

import Controllers.LoginController;
import Models.Employee;
import Models.Manager;
import services.DBConnection;

public class EmployeeDAOImpl implements EmployeeDAO 
{
	private Connection con; // This is a problem we need to sort out. Figure out a way to automate connections closing, make update DBConnection Class
	private static Logger log = Logger.getLogger(EmployeeDAOImpl.class.getName());
	
	private String addEmployeeSql = "{? = call addEmployee(?, ?, ?, ?, ?, ?, ?)}";
	private String findEmpByUserName = "select searchEmployeeByUserName(?)";
	private String getAllEmployees = "call getAllEmployees()";
	private String deleteEmployee = "call deleteEmployee(?)";
	private String backupSearch = "select employee_id, user_name, user_password, first_name, last_name, email, address, phone_number, date_created from Employee join public.UserAccount on fk_userid = user_id join contact_info on fk_infoid = info_id where user_name = ?";
	private String getById = "select employee_id, user_name, user_password, first_name, last_name, email, address, phone_number, date_created from Employee join public.UserAccount on fk_userid = user_id join contact_info on fk_infoid = info_id where employee_id = ?";
	private static final String updateEmployee
	= "UPDATE public.contact_info SET first_name=?, last_name=?, address=?, phone_number=? from useraccount u join employee e on u.user_id = e.fk_userid join contact_info c on c.info_id = u.fk_infoid WHERE e.employee_id =?";
	private static final String updateBackup = "update contact_info set first_name=?, last_name=?, address=?, phone_number=? where email = ?"; 
	
	
	@Override
	public boolean addEmployee(Employee ref) 
	{
		boolean flag = false;
		
		con = DBConnection.getConnection();
		try 
		{
			CallableStatement state = con.prepareCall(addEmployeeSql);
			state.registerOutParameter(1, Types.INTEGER);
			
			state.setString(2, ref.getUsername().toLowerCase());
			state.setString(3, ref.getPassword());
			state.setString(4, ref.getFirstName());
			state.setString(5, ref.getLastName());
			state.setString(6, ref.getEmail());
			state.setString(7, ref.getAddress());
			state.setString(8, ref.getPhoneNum());
			log.info("Attempting to addEmployee() " + ref);
			
			if(state.executeUpdate() == 0)
			{
				ref.setId( state.getInt(1) );
				flag = true;
			}
			log.info("Attempt to addEmployee() " + ref + " | " + (flag ? " was successful" : " has failed"));
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
			log.error("SQLException in addEmployee() | " + e.getMessage());
		}
		catch (NullPointerException e)
		{
			log.error("NullPointerException in addEmployee() | " + e.getMessage());
		}catch (Exception e)
		{
			e.printStackTrace();
			log.error("Caught loose exception in addEmployee() | " + e.getMessage());
		}
		finally
		{
			DBConnection.closeConnection(con);
		}
		
		return flag;
	}

	@Override
	public boolean updateEmployee(Employee obj) 
	{
		boolean flag = false;
		
		PreparedStatement state = null;
		Connection con = null;
		
		try 
		{
			con = DBConnection.getConnection();
			state = con.prepareStatement(updateBackup);
			state.setString(1, obj.getFirstName());
			state.setString(2, obj.getLastName());
			state.setString(3, obj.getAddress());
			state.setString(4, obj.getPhoneNum());
			state.setString(5, obj.getEmail());
			//state.setInt(5, obj.getId());
			
			flag = state.executeUpdate() > 0;
			
			log.info("Update attempt in updateEmployee() | " + obj + (flag ? " was successful" : " has failed"));
			state.close();
			
		} catch (SQLException e) 
		{
			e.printStackTrace();
			log.error("SQLException in updateEmployee() | " + e.getMessage() + "\n");
		} finally
		{
			DBConnection.closeConnection(con);
		}
		
		
		
		return flag;
	}

	@Override
	public Employee searchByUsername(String username) 
	{
		con = DBConnection.getConnection();
		Employee temp = null;
		
		try 
		{
			CallableStatement state = con.prepareCall(backupSearch);
			state.setString(1, username);
			
			if(state.execute())
			{
				ResultSet set = state.getResultSet();
				
				if(set.next())
				{
					temp = new Employee(Integer.valueOf(set.getString(1)), set.getString(2), set.getString(3), set.getString(4),
							set.getString(5), set.getString(6), set.getString(8), set.getString(7), set.getDate(9)); // swap address and phone currently being assigned wrong
				}
				log.info("Result of searchByUsername() | " + temp);
			}
			
			state.close();
			
		} catch (SQLException e) 
		{
			e.printStackTrace();
			log.error("SQLException in searchByUsername() | " + e.getMessage());
		} catch (Exception e)
		{
			e.printStackTrace();
			log.error("Loose exception in searchByUsername() | " + e.getMessage());
		}
		finally
		{
			DBConnection.closeConnection(con);
		}
		
		return temp;
	}
	public boolean isEmployee(String username)
	{
		PreparedStatement state = null;
		Connection con = null;
		boolean flag = false;
		try 
		{
			con = DBConnection.getConnection();
			state = con.prepareStatement("select user_id from useraccount join Employee on fk_userid = user_id where user_name = ?");
			state.setString(1, username);
			
			if(state.execute())
			{
				ResultSet set = state.getResultSet();
				
				if(set.next())
					flag = true;
			}
			
			log.info("Result of isEmployee() | " + flag);
			state.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQLException in isEmployee() | " + e.getMessage());
		} finally
		{
			DBConnection.closeConnection(con);
		}
		return flag;
	}

	@Override
	public Employee searchById(int id) 
	{
		Employee emp = null;
		Connection con = DBConnection.getConnection();
		try 
		{
			PreparedStatement state = con.prepareStatement(getById);
			state.setInt(1, id);
			
			if(state.execute())
			{
				ResultSet set = state.getResultSet();
				if(set.next())
				{
					emp = new Employee(Integer.valueOf(set.getString(1)), set.getString(2), set.getString(3), set.getString(4),
							set.getString(5), set.getString(6), set.getString(7), set.getString(8), set.getDate(9));
				}
			}
			state.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQLException in isEmployee() | " + e.getMessage());
		} finally
		{
			DBConnection.closeConnection(con);
		}
		
		
		
		return emp;
	}


	@Override
	public ArrayList<Employee> getAllEmployees() 
	{
		ArrayList<Employee> empList = new ArrayList<Employee>();
		
		con = DBConnection.getConnection();
		
		try 
		{
			Statement state = con.createStatement();
			state.execute(getAllEmployees);
			
			ResultSet set = state.getResultSet();
			
			while(set.next())
			{
				empList.add(new Employee(set.getInt(1), set.getString(2), set.getString(3), set.getString(4),
						set.getString(5), set.getString(6), set.getString(7), set.getString(8), set.getDate(9)));
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
			log.error("SQLException in getAllEmployees() | " + e.getMessage());
		} catch (Exception e)
		{
			e.printStackTrace();
			log.error("Loose exception in getAllEmployees() | " + e.getMessage());
		}
		finally
		{
			DBConnection.closeConnection(con);	
		}

		return empList;
	}

	@Override
	public boolean deleteEmployee(Employee ref) 
	{
		boolean flag = false;
		
		try
		{
			con = DBConnection.getConnection();
			CallableStatement state = con.prepareCall(deleteEmployee);
			state.setString(1, ref.getUsername());
			
			flag = (state.executeUpdate() == 0);
		} catch (SQLException e) 
		{
			e.printStackTrace();
			log.error("SQLException in deleteEmployee() | " + e.getMessage());
		} catch(Exception e)
		{
			e.printStackTrace();
			log.error("Loose exception in deleteEmployee() | " + e.getMessage());
		}
		finally
		{
			DBConnection.closeConnection(con);
		}
		
		return flag;
	}

	@Override
	public boolean verifyLogin(String username, String password) 
	{
		boolean flag = false;
		con = DBConnection.getConnection();
		try 
		{
			PreparedStatement state = con.prepareStatement("select * from useraccount where user_name = ? and user_password = ?");
			state.setString(1, username);
			state.setString(2, password);
			
			ResultSet set = state.executeQuery();
			flag = set.next();
			
		} catch (SQLException e) 
		{
			e.printStackTrace();
			log.error("SQLException in deleteEmployee() | " + e.getMessage());
		}finally
		{
			DBConnection.closeConnection(con);
		}
		
		return flag;
	}
	
}
