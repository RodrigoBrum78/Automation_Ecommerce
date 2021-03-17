package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	
	private WebDriver driver;
	
	List<WebElement> listProduits = new ArrayList();
	//va localizer les produits que sont affiches dans le home page	
	private By produits = By.className("product-description");
	//va localizer le panier vide dans le home page
	private By textProduitsPanier = By.className("cart-products-count");
	
	private By descriptionsProduits = By.cssSelector(".product-description a");
	
	private By prixProduits = By.className("price");
	
	private By linkSignIn = By.cssSelector("#_desktop_user_info span.hidden-sm-down");
	
	private By userConnecte = By.cssSelector("#_desktop_user_info span.hidden-sm-down");
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	private void chargerListProduits() {
		//garde tous les produits de la home page dans une liste
		listProduits = driver.findElements(produits);
	}
	
	public int compterProduits() {
		
		chargerListProduits();
		//renvoie la quantité totale de produits dans la liste 
		return listProduits.size();
	}
	
	public int obtenirProduitsPanier() {
		//va recuperer le text du panier vide
		String quantiteProduitsPanier = driver.findElement(textProduitsPanier).getText();
		//va supprimer les parenthèses du text
		quantiteProduitsPanier = quantiteProduitsPanier.replace("(", "");
		quantiteProduitsPanier = quantiteProduitsPanier.replace(")", "");
		//va changer de string par un integer
		int nbProduitsPanier = Integer.parseInt(quantiteProduitsPanier);
		//renvoie la quantité totale de produits
		return nbProduitsPanier;
	}
	
	public String obtenirNomProduit(int indice) {
		return driver.findElements(descriptionsProduits).get(indice).getText();
	}
	
	public String obtenirPrixProduit(int indice) {
		return driver.findElements(prixProduits).get(indice).getText();
	}
	
	public ProduitPage cliquerProduit(int indice) {
		driver.findElements(descriptionsProduits).get(indice).click();
		
		return new ProduitPage(driver);
	}
	
	public LoginPage cliquerLienSignIn() {
		driver.findElement(linkSignIn).click();
		return new LoginPage(driver);
	}
	
	public boolean userConnecte(String text) {
		return text.contentEquals(driver.findElement(userConnecte).getText());
	}

}
