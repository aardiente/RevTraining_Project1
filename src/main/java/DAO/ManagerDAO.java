package DAO;

import Models.Manager;

public interface ManagerDAO {
	public boolean addManager(Manager obj);
	public Manager searchById(int id);
	public Manager searchByUsername(String username);
	public boolean updateManager(Manager obj);
}
