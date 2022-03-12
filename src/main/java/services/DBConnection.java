package services;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class DBConnection 
{
	private static String url;
	private static String user;
	private static String pass;
	private static String driver;
	private static final String propPath = "B:\\Revature_Training\\Eclipse\\Project_1\\db.properties";
	
	//private static Connection dbConnection;
	
	public static Connection getConnection()
	{
        try // initializing a JDBC type 4 driver
        {
        	FileReader rdr = new FileReader(propPath);
        	Properties prop = new Properties();
        	prop.load(rdr);
        	
        	url = prop.getProperty("url");
        	driver = prop.getProperty("driver");
        	pass = prop.getProperty("password");
        	user = prop.getProperty("username");
        	
        	Class.forName(driver);

        	Connection temp = DriverManager.getConnection(url, user, pass);
        	System.out.println("Connection initialized");
        	return temp;
      
		} catch (SQLException | ClassNotFoundException e) 
        {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
	}
	
	
	public static void closeConnection(Connection con)
	{
		try 
		{
			if(!con.isClosed())
				con.close();	

			con = null;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
