package cucumber;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

class LoginTest {
	static String browser = "chrome";
	static WebDriver driver;
	@BeforeAll
	static void setUpBeforeClass() throws Exception 
	{
		if(browser.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		System.out.println("Test Output");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() 
	{
		driver.get("http://localhost:8080/Project_1/");
		driver.manage().window().maximize();
		driver.findElement( By.xpath("//*[@id=\"navbarCollapse\"]/ul/li[2]/a") ).click();
		driver.findElement(By.id("username")).sendKeys("aardiente");
		driver.findElement(By.id("password")).sendKeys("tester");
		driver.findElement(By.id("loginSubmitBtn")).click();
	}

}
