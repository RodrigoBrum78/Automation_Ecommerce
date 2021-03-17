package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class ModalProduitsPage {
	
	private WebDriver driver;
	
	public ModalProduitsPage(WebDriver driver) {
		this.driver = driver;
	}
	/*Localization des elements dans la page*/
	
	private By msgProduitAjoute = By.id("myModalLabel");
	
	private By descriptionProduit = By.className("product-name");
	
	private By prixProduit = By.cssSelector("div.modal-body p.product-price");
	
	private By listValeursInformes = By.cssSelector("div.divide-right .col-md-6:nth-child(2) span strong");
	
	private By sousTotal = By.cssSelector(".cart-content p:nth-child(2) span.value");
	
	private By btnCheckOut = By.cssSelector("div.cart-content-btn a.btn-primary");
	
	public String obtneirMsgProduitAjoute() {
		//on va attendre jusqu'a 5s pour trouver l'element - myModalLabel
		FluentWait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(8)).
							pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(msgProduitAjoute));
						
		return driver.findElement(msgProduitAjoute).getText();
	}
	
	public String obtenirDescriptionProduit() {
		return driver.findElement(descriptionProduit).getText();
	}
	
	public String obtenirPrixproduit() {
		return driver.findElement(prixProduit).getText();
	}
	
	public String obtenirTailleProduit() {
		return driver.findElements(listValeursInformes).get(0).getText();
	}
	
	public String obtenirCouleurProduit() {
		return driver.findElements(listValeursInformes).get(1).getText();
	}
	
	public String obtenirQuantiteProduit() {
		return driver.findElements(listValeursInformes).get(2).getText();
	}
	
	public String obtenirSousTotal() {
		return driver.findElement(sousTotal).getText();
	}
	
	public PanierPage cliquerBtnChekOut() {
		driver.findElement(btnCheckOut).click();
		return new PanierPage(driver);
	}
	
	

}
