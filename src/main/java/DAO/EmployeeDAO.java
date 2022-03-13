package DAO;

import java.util.ArrayList;

import Models.Employee;

public interface EmployeeDAO 
{
	public boolean addEmployee(Employee ref);
	public boolean deleteEmployee(Employee ref);
	public boolean updateEmployee(Employee ref);
	public boolean verifyLogin(String username, String password);
	public boolean isEmployee(String username);
	public Employee searchByUsername(String username);
	public Employee searchById(int id);
	public ArrayList<Employee> getAllEmployees();
}
