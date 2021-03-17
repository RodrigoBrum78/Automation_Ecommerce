package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PanierPage {
	
	private WebDriver driver;
	
	public PanierPage(WebDriver driver) {
		this.driver = driver;
	}
	
	/*Localization des elements de la page*/
	
	private By nomProduit = By.cssSelector("div.product-line-info a");
	
	private By prixProduit = By.cssSelector("span.price");
			
	private By tailleProduit = By.xpath("//div[contains(@class,'product-line-grid-body')]//div[3]/span[contains(@class,'value')]"); 
	
	private By couleurProduit = By.xpath("//div[contains(@class,'product-line-grid-body')]//div[4]/span[contains(@class,'value')]");
	
	private By input_quantiteProduit = By.cssSelector("input.js-cart-line-product-quantity");
	
	private By sousTotalProduit = By.cssSelector("span.product-price strong");
	
	private By qteTotalProduit = By.cssSelector("span.js-subtotal");
	
	private By sousTotal = By.cssSelector("#cart-subtotal-products span.value");
	
	private By totalLivraison = By.cssSelector("#cart-subtotal-shipping span.value");
	
	private By totalAvantTaxes = By.cssSelector("div.cart-summary-totals div.cart-summary-line:nth-child(1) span.value");
	
	private By totalAvecTaxes = By.cssSelector("div.cart-summary-totals div.cart-summary-line:nth-child(2) span.value");
	
	private By totalTaxes = By.cssSelector("div.cart-summary-totals div.cart-summary-line:nth-child(3) span.value");
	
	private By btnAllerCheckout = By.cssSelector("a.btn-primary");
	
	/*Creation des methodes*/
	
	public String obtenir_NomProduit() {
		return driver.findElement(nomProduit).getText();
	}
	
	public String obtenir_PrixProduit() {
		return driver.findElement(prixProduit).getText();		
	}
	
	public String obtenir_TailleProduit() {
		return driver.findElement(tailleProduit).getText();
	}
	
	public String obtenir_CouleurProduit() {
		return driver.findElement(couleurProduit).getText();
	}
	
	public String obtenir_InputQuantiteProduit() {
		return driver.findElement(input_quantiteProduit).getAttribute("value");
	}
	
	public String obtenir_SousTotalProduit() {
		return driver.findElement(sousTotalProduit).getText();
	}
	
	public String obtenir_QteTotalProduit() {
		return driver.findElement(qteTotalProduit).getText();
	}
	
	public String obtenir_SousTotal() {
		return driver.findElement(sousTotal).getText();
	}
	
	public String obtenir_TotalLivraison() {
		return driver.findElement(totalLivraison).getText();
	}
	
	public String obtenir_TotalAvantTaxes() {
		return driver.findElement(totalAvantTaxes).getText();
	}
	
	public String obtenir_TotalAvecTaxes() {
		return driver.findElement(totalAvecTaxes).getText();
	}
	
	public String obtenir_TotalTaxes() {
		return driver.findElement(totalTaxes).getText();
	}
	
	public CheckoutPage cliquerBtnToCheckout() {
		driver.findElement(btnAllerCheckout).click();
		return new CheckoutPage(driver);
	}

}
