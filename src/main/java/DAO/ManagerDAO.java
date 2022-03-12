package DAO;

import Models.Manager;

public interface ManagerDAO {
	public boolean addManager(Manager obj);
	public Manager getById(int id);
}
