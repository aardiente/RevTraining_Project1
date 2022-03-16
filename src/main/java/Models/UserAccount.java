package Models;

import java.sql.Date;
import java.util.Objects;

public class UserAccount
{
	protected int id; // If the id is -1 this object wasn't created by the database
	protected String username, password, firstName, lastName, email, phoneNum, address;
	protected Date dateActivated;
	
	public UserAccount(int id, String username, String password, String firstName, String lastName, String email, String phoneNum, String address, Date dateActivated) 
	{
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNum = phoneNum;
		this.address = address;
		this.dateActivated = dateActivated;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateActivated() {
		return dateActivated;
	}

	public void setDateActivated(Date dateActivated) {
		this.dateActivated = dateActivated;
	}

	@Override
	public String toString() {
		return "UserAccount [id=" + id + ", username=" + username + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", phoneNum=" + phoneNum + ", address="
				+ address + ", dateActivated=" + dateActivated + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, dateActivated, email, firstName, id, lastName, password, phoneNum, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserAccount other = (UserAccount) obj;
		return Objects.equals(address, other.address)
				&& Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName) && id == other.id
				&& Objects.equals(lastName, other.lastName) && Objects.equals(password, other.password)
				&& Objects.equals(phoneNum, other.phoneNum) && Objects.equals(username, other.username);
	}
	
	
	
}
