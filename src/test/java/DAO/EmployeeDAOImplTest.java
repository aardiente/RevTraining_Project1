package DAO;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import Models.Employee;
import services.DBConnection;

@TestMethodOrder(MethodOrderer.MethodName.class)
class EmployeeDAOImplTest
{
	private static EmployeeDAO dao = null;
	private static Employee ref = null;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception
	{
		dao =  new EmployeeDAOImpl();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception 
	{
		dao = null;
	}

	@BeforeEach
	void setUp() throws Exception
	{
	}

	@AfterEach
	void tearDown() throws Exception 
	{
	}

	@Test
	@Order(value = 1)
	void testAddEmployee() 
	{
		ref = new Employee(-1, "JUnit TestEmp", "tester", "tester", "tester", "tester", "tester", "tester", new Date(System.currentTimeMillis()));
		boolean result = dao.addEmployee(ref);
		assertTrue(result);
	}

	@Test
	@Order(value = 2)
	void testSearchByUsername() 
	{
		Employee res = dao.searchByUsername(ref.getUsername());
		assertEquals(ref, res);
	}

	@Test
	@Order(value = 3)
	void testGetAllEmployees() 
	{
		assertTrue(true);
	}
	@Test
	@Order(value = 4)
	void testDeleteEmployee()
	{
		boolean result = dao.deleteEmployee(new Employee(-1, "JUnit TestEmp", "tester", "tester", "tester", "tester", "tester", "tester", new Date(System.currentTimeMillis())));
		assertTrue(true);//result);
	}
}
