package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import UTIL.Functions;

public class CheckoutPage {
	
	private WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
	}
	
	/*CREATION DES VARIABLES AVEC LOCALISATION DES ELEMENTS DANS LA PAGE*/
	
	private By totalAvecTaxes = By.cssSelector("div.cart-total span.value");
	
	private By nomClient = By.cssSelector("div.address");
	
	private By btnContinue = By.name("confirm-addresses");
	
	private By livrasionValeur = By.cssSelector("span.carrier-price");
	
	private By btnContinueShipping = By.name("confirmDeliveryOption");
	
	private By radioPaybyCheck = By.id("payment-option-1");
	
	private By amountPayByCheck = By.cssSelector("#payment-option-1-additional-information > section > dl > dd:nth-child(2)");
	
	private By checkboxIAgree = By.id("conditions_to_approve[terms-and-conditions]");
	
	private By btnConfirmerCommande = By.cssSelector("#payment-confirmation");
	
	
	public String obtenir_totalAvecTaxes() {
		return driver.findElement(totalAvecTaxes).getText();
	}
	
	public String obtenir_nomClient() {
		return driver.findElement(nomClient).getText();
	}
	
	public void cliquer_btnContinue() {
		driver.findElement(btnContinue).click();
	}
	
	public String obtenir_livarisonValeur() {
		return driver.findElement(livrasionValeur).getText();
	}
	
	public void obtenir_btnContinueShipping() {
		driver.findElement(btnContinueShipping).click();
	}
	
	public void selectionee_radioPayByCheck() {
		driver.findElement(radioPaybyCheck).click();
	}
	
	public String obtenir_amountPayByCheck() {
		String trouvee_amountPayByCheck = driver.findElement(amountPayByCheck).getText();
		//on va suprimmer le text " (tax incl.)" et retouner $26.12
		trouvee_amountPayByCheck = Functions.effacerText(trouvee_amountPayByCheck, " (tax incl.)"); 
		return trouvee_amountPayByCheck;
		
	}
	
	public void cliquer_IAgree() {
		driver.findElement(checkboxIAgree).click();
	}
	
	//retourne vrai si la case est cochée
	public boolean checkBoxIAgreeSelectionee() {
		return driver.findElement(checkboxIAgree).isSelected();
	}
	
	public CommandePage obtenir_btnConfirmerCommande() {
		driver.findElement(btnConfirmerCommande).click();
		return new CommandePage(driver);
	}
}
