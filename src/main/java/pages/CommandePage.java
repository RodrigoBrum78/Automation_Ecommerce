package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import UTIL.Functions;


public class CommandePage {
	
	private WebDriver driver;
	
	public CommandePage(WebDriver driver) {
		this.driver = driver;
	}
	
	private By messageCommnadeConfirmer = By.cssSelector("#content-hook_order_confirmation h3");
	
	private By emailConfirmation = By.cssSelector("#content-hook_order_confirmation p");
	
	private By totalProduits = By.cssSelector("div.order-confirmation-table div.order-line div.row div.bold");
	
	private By totalTaxesInclus = By.cssSelector("div.order-confirmation-table table tr.total-value td:nth-child(2)");
	
	private By methodePaiement = By.cssSelector("#order-details ul li:nth-child(2)");
	
	public String obtenir_messageCommandeConfirmer(){
		return driver.findElement(messageCommnadeConfirmer).getText();
	}
	
	public String obtenir_emailConfirmation() {
		String text = driver.findElement(emailConfirmation).getText();
		
		text = Functions.effacerText(text, "An email has been sent to the ");
		text = Functions.effacerText(text, " address.");
		
		return text;
	}
	
	public Double obtenir_totalProduits() {
		return Functions.supprimerSymbol$(driver.findElement(totalProduits).getText());
	}
	
	public Double obtenir_totalTaxesInclus(){
		return Functions.supprimerSymbol$(driver.findElement(totalTaxesInclus).getText());
	}
	
	public String obtenir_methodePaiement() {
		return Functions.effacerText(driver.findElement(methodePaiement).getText(),"Payment method: Payments by");
	}
	
	
}
