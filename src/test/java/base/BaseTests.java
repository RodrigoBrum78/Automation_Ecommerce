package base;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.HomePage;

public class BaseTests {
	
	private static WebDriver driver;
	protected HomePage homePage; 
	
	@BeforeAll
	public static void demarrer() {
		driver = new FirefoxDriver();
	}
	
	@BeforeEach 
	public void ouvrirPageInitial() {
		driver.get("https://marcelodebittencourt.com/demoprestashop/");
		homePage = new HomePage(driver);
	}
	
	@AfterAll
	public static void fermer() throws Exception {
		
		Thread.sleep(3000);
		driver.quit();
	}

}
