package Models;

import java.sql.Date;
import java.util.Objects;

public class Employee extends UserAccount
{
	/******************************************************************************************************************************************************************************/
	// Constructers 
	public Employee(int id, String username, String password, String firstName, String lastName, String email, String phoneNum, String address, Date dateActivated) 
	{
		super(id, username, password, firstName, lastName, email, phoneNum, address, dateActivated);
	}
	public Employee( String username, String password, String firstName, String lastName, String email, String phoneNum, String address, Date dateActivated)
	{
		super(-1, username, password, firstName, lastName, email, phoneNum, address, dateActivated);
	}
	/******************************************************************************************************************************************************************************/
	// Overrides
	@Override
	public String toString() 
	{
		return "[Employee Id: " + id + " | Username: " + username + " | Password: " + password.hashCode() + " | Name: " + firstName + lastName + " | Email: " + email 
				+ " | Phone Number: " + phoneNum + " | Address: " + address + " | Date Activated: " + dateActivated + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(address, dateActivated, email, firstName, id, lastName, password, phoneNum, username);
	}
	@Override
	public boolean equals(Object obj) 
	{
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(address, other.address) && Objects.equals(dateActivated, other.dateActivated)
				&& Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName) && id == other.id
				&& Objects.equals(lastName, other.lastName) && Objects.equals(password, other.password)
				&& Objects.equals(phoneNum, other.phoneNum) && Objects.equals(username, other.username);
	}
	
	
	
}
