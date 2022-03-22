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

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

class Project1LoginSteps 
{
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

	@Given("new tab is open")
	public void new_tab_is_open() 
	{
		driver.get("http://localhost:8080/Project_1/");
		driver.manage().window().maximize();
	}

	@Given("user is on login page of project1")
	public void user_is_on_login_page_of_project1() 
	{
		driver.findElement( By.xpath("//*[@id=\"navbarCollapse\"]/ul/li[2]/a") ).click();
	}

	@When("user enters {string} and {string}")
	public void user_enters_and(String string, String string2)
	{
		driver.findElement(By.id("usernameTB")).sendKeys(string);
		driver.findElement(By.id("passwordTB")).sendKeys(string2);
	}

	@When("user clicks on login button of project1")
	public void user_clicks_on_login_button_of_project1() 
	{
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("user is navigated to the home page of project1")
	public void user_is_navigated_to_the_home_page_of_project1()
	{
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

}
