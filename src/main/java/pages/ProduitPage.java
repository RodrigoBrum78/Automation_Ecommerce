package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;




public class ProduitPage {
	
	private WebDriver driver;
	/*annotations pour les éléments de la page*/
	
	private By nomProduit = By.className("h1");
	
	private By prixProduit = By.cssSelector(".current-price span:nth-child(1)");
	
	private By tailleProduit = By.id("group_1");
	
	private By choisirCouleurNoir = By.xpath("//ul[@id='group_2']//input[@value='11']");
	
	private By quantiteProduit = By.id("quantity_wanted");
	
	private By btnAddtoCart = By.className("add-to-cart");
	
	public ProduitPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String obtenirNomProduit() {
		return driver.findElement(nomProduit).getText();
	}
	
	public String obtenirPrixProduit() {
		return driver.findElement(prixProduit).getText();
	}
	
	public Select trouverDropdownTaille () {
		return new Select(driver.findElement(tailleProduit));
	}
	
	public List<String> obtenirOptionsSelectionnee(){
		//creation de notre list des options existant dans le dropdown
		List<WebElement> elementsSelectionnee = trouverDropdownTaille().getAllSelectedOptions();
		//pour chaque option dans le dropdown, nous ajoutons a variable element	
		List<String> listOptionsSelectionnee = new ArrayList();
			for(WebElement element : elementsSelectionnee) {
			listOptionsSelectionnee.add(element.getText());
		}
		return listOptionsSelectionnee;
	}
	
	public void selectionerOptionDropdown(String option) {
		//nous irons choisir une option visible dans le dropdown
		trouverDropdownTaille().selectByVisibleText(option);
	}
	
	public void selectionnerCouleurNoir() {
		driver.findElement(choisirCouleurNoir).click();
	}
	
	public void choisirQuantite(int nb) {
		driver.findElement(quantiteProduit).clear();  //supprimera le contenu par default avant de envoyer la quantite choisi
		driver.findElement(quantiteProduit).sendKeys(Integer.toString(nb));
	}
	
	public ModalProduitsPage cliquerAddToCart() {
		driver.findElement(btnAddtoCart).click();
		return new ModalProduitsPage(driver);
	}

}
