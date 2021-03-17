package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	
	private WebDriver driver;
	
	private By email = By.name("email");
	
	private By password = By.name("password");
	
	private By btnSignIn = By.id("submit-login");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void remplirEmail(String text) {
		driver.findElement(email).sendKeys(text);
	}
	
	public void remplirPassword(String text) {
		driver.findElement(password).sendKeys(text);
	}
	
	public void cliquerBtnSignIn() {
		driver.findElement(btnSignIn).click();
	}

}
