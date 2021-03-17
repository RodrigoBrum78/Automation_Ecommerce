package homepage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import UTIL.Functions;
import base.BaseTests;
import pages.CheckoutPage;
import pages.CommandePage;
import pages.LoginPage;
import pages.ModalProduitsPage;
import pages.PanierPage;
import pages.ProduitPage;

public class HomePageTests extends BaseTests {
	
	/*dans ce test nous compterons et validerons la quantité de produits dans la page d'accueil */
	@Test
	public void testCompterProduits() {
		
		ouvrirPageInitial();
		assertThat(homePage.compterProduits(), is(8));
	}
	/*ici, nous validerons que notre panier est vide*/
	@Test 
	public void testValiderPanierVide() {
		int produitsEnPanier = homePage.obtenirProduitsPanier();
		System.out.println(produitsEnPanier);
	}
	
	/*dans ce test, nous cliquerons sur un produit de la page d'accueil et 
	 * nous validerons le nom et prix*/
	
	ProduitPage produitPage;
	String nomProduit_ProduitPage;
	@Test
	public void testValiderDetailsProduit() {
		int indice = 0;
		
		String nomProduit_HomePage = homePage.obtenirNomProduit(indice);
		String prixProduit_HomePage = homePage.obtenirPrixProduit(indice);
		
		//System.out.println(nomProduit_HomePage);
		//System.out.println(prixProduit_HomePage);
		
		produitPage = homePage.cliquerProduit(indice);
		
		nomProduit_ProduitPage = produitPage.obtenirNomProduit();
		String prixProduit_ProduitPage = produitPage.obtenirPrixProduit();
		
		//System.out.println(nomProduit_ProduitPage);
		//System.out.println(prixProduit_ProduitPage);
		
		assertThat(nomProduit_HomePage.toUpperCase(), is(nomProduit_ProduitPage.toUpperCase()));
		assertThat(prixProduit_HomePage, is(prixProduit_ProduitPage));
	}
	
	LoginPage loginPage;
	@Test
	public void testLoginAvecSucess() {
		// cliquer sur le lien SIG IN dans la HOME PAGE
		loginPage = homePage.cliquerLienSignIn();
		
		//remplir les champs login et mot de passe
		loginPage.remplirEmail("rodrigobrum@test.com");
		loginPage.remplirPassword("rodrigo");
		//cliquer dans le btn sig in pour loger
		loginPage.cliquerBtnSignIn();
		//vérifier si l'utilisateur est connecté
		assertThat(homePage.userConnecte("Rodrigo Brum"), is(true));
		//retourner a home page
	}
	
	ModalProduitsPage modalProduitsPage;
	@Test
	public void inclureProduitsPanier() {
		
		String tailleProduit = "M";
		String couleurPrudit = "Black";
		int quantiteProduit = 3;
		
		//verifier si l'utilisateur est connecte
		if(!homePage.userConnecte("Rodrigo Brum")) {
			testLoginAvecSucess();
			// retourner a home page
			ouvrirPageInitial();
		}
		
		//valider les produits dans home page
		testValiderDetailsProduit();
		//selectionner la taille du produit
		List<String> listOptions = produitPage.obtenirOptionsSelectionnee();
		//System.out.println(listOptions.get(0));
		//System.out.println("Taille de la list: " + listOptions.size());
		
		produitPage.selectionerOptionDropdown(tailleProduit);
		//System.out.println(listOptions.get(0));
		//System.out.println("Taille de la list: " + listOptions.size());
		
		//selectionner couleur noir
		produitPage.selectionnerCouleurNoir();
		
		//choisir la quantite des produits
		produitPage.choisirQuantite(quantiteProduit);
		
		//cliquer dans le btn Add to Cart
	    modalProduitsPage = produitPage.cliquerAddToCart();
		assertTrue(modalProduitsPage.obtneirMsgProduitAjoute().endsWith("Product successfully added to your shopping cart"));
		
		assertThat(modalProduitsPage.obtenirDescriptionProduit().toUpperCase(), is(nomProduit_ProduitPage.toUpperCase()));
		
		//validation des infos du produit
		assertThat(modalProduitsPage.obtenirTailleProduit(), is(tailleProduit));
		assertThat(modalProduitsPage.obtenirCouleurProduit(), is(couleurPrudit));
		assertThat(modalProduitsPage.obtenirQuantiteProduit(), is(Integer.toString(quantiteProduit)));
		
		String prixProduitString = modalProduitsPage.obtenirPrixproduit();
		prixProduitString = prixProduitString.replace("$", ""); //on retire le symbol $ 
		Double prixProduit = Double.parseDouble(prixProduitString); // on change de string par double
		
		String soustotalString = modalProduitsPage.obtenirSousTotal();
		soustotalString = soustotalString.replace("$", ""); //on retire le symbol $ 
		Double sousTotal = Double.parseDouble(soustotalString); // on change de string par double
		
		Double prixTotal = quantiteProduit * prixProduit;
		
		assertThat(sousTotal, is(prixTotal));
	}
	
	         /*  valeurs attendues  */
	
	String attendue_NomProduit = "Hummingbird printed t-shirt";
	Double attendue_PrixProduit = 19.12;
	String attendue_TailleProduit = "M";
	String attendue_CouleurProduit = "Black";
	int attendue_InputQuantiteProduit = 3;
	Double attendue_SousTotalProduit = attendue_PrixProduit * attendue_InputQuantiteProduit;
	
	int attendue_numeroItemsTotal = attendue_InputQuantiteProduit;
	Double attendue_sousTotal = attendue_SousTotalProduit;
	Double attendue_TotalLivraison = 7.00;
	Double attendue_TotalAvantTaxes = attendue_sousTotal + attendue_TotalLivraison;
	Double attendue_TotalTaxes = 0.00;
	Double attendue_TotalAvecTaxes = attendue_TotalAvantTaxes + attendue_TotalTaxes;
	
	String attendue_nomClient = "Rodrigo Brum";
	
	
	PanierPage panierpage;
	@Test
	public void allerAuPanier() {
		//--Pre requis: produit inclus dans la fenetre ModalProduitsPage
		inclureProduitsPanier();
		
		
		panierpage = modalProduitsPage.cliquerBtnChekOut();
		
		//valider les elements dans la fenetre du panier
		System.out.println("*** FENETRE DU PANIER ***");
		System.out.println(panierpage.obtenir_NomProduit());
		System.out.println(Functions.supprimerSymbol$(panierpage.obtenir_PrixProduit()));
		System.out.println(panierpage.obtenir_TailleProduit());
		System.out.println(panierpage.obtenir_CouleurProduit());
		System.out.println(panierpage.obtenir_InputQuantiteProduit());
		System.out.println(Functions.supprimerSymbol$(panierpage.obtenir_SousTotalProduit()));
		
		System.out.println("*** TOTAL ITEMS ***");
		
		System.out.println(Functions.supprimerNomItems(panierpage.obtenir_QteTotalProduit()));
		System.out.println(Functions.supprimerSymbol$(panierpage.obtenir_SousTotal()));
		System.out.println(Functions.supprimerSymbol$(panierpage.obtenir_TotalLivraison()));
		System.out.println(Functions.supprimerSymbol$(panierpage.obtenir_TotalAvantTaxes()));
		System.out.println(Functions.supprimerSymbol$(panierpage.obtenir_TotalAvecTaxes()));
		System.out.println(Functions.supprimerSymbol$(panierpage.obtenir_TotalTaxes()));
		
		//ASSERTIONS HAMCREST
		/*assertThat(panierpage.obtenir_NomProduit() , is(attendue_NomProduit));
		assertThat(Functions.supprimerSymbol$(panierpage.obtenir_PrixProduit()) , is(attendue_PrixProduit));
		assertThat(panierpage.obtenir_TailleProduit() , is(attendue_TailleProduit));
		assertThat(panierpage.obtenir_CouleurProduit() , is(attendue_CouleurProduit));
		assertThat(Integer.parseInt(panierpage.obtenir_InputQuantiteProduit()) , is(attendue_InputQuantiteProduit));
		assertThat(Functions.supprimerSymbol$(panierpage.obtenir_SousTotalProduit()) , is(attendue_SousTotalProduit));
		assertThat(Functions.supprimerNomItems(panierpage.obtenir_QteTotalProduit()) , is(attendue_numeroItemsTotal));
		assertThat(Functions.supprimerSymbol$(panierpage.obtenir_SousTotal()) , is(attendue_sousTotal));
		assertThat(Functions.supprimerSymbol$(panierpage.obtenir_TotalLivraison()) , is(attendue_TotalLivraison));
		assertThat(Functions.supprimerSymbol$(panierpage.obtenir_TotalAvantTaxes()) , is(attendue_TotalAvantTaxes));
		assertThat(Functions.supprimerSymbol$(panierpage.obtenir_TotalTaxes()), is(attendue_TotalTaxes));
		assertThat(Functions.supprimerSymbol$(panierpage.obtenir_TotalAvecTaxes()) , is(attendue_TotalAvecTaxes));*/
		
		//ASSERTIONS JUNIT
		
		assertEquals(attendue_NomProduit,panierpage.obtenir_NomProduit() );
		assertEquals(attendue_PrixProduit,Functions.supprimerSymbol$(panierpage.obtenir_PrixProduit()));
		assertEquals(attendue_TailleProduit,panierpage.obtenir_TailleProduit());
		assertEquals(attendue_CouleurProduit,panierpage.obtenir_CouleurProduit());
		assertEquals(attendue_InputQuantiteProduit,Integer.parseInt(panierpage.obtenir_InputQuantiteProduit()));
		assertEquals(attendue_SousTotalProduit,Functions.supprimerSymbol$(panierpage.obtenir_SousTotalProduit()));
		assertEquals(attendue_numeroItemsTotal,Functions.supprimerNomItems(panierpage.obtenir_QteTotalProduit()));
		assertEquals(attendue_sousTotal,Functions.supprimerSymbol$(panierpage.obtenir_SousTotal()));
		assertEquals(attendue_TotalLivraison,Functions.supprimerSymbol$(panierpage.obtenir_TotalLivraison()));
		assertEquals(attendue_TotalAvantTaxes,Functions.supprimerSymbol$(panierpage.obtenir_TotalAvantTaxes()));
		assertEquals(attendue_TotalTaxes,Functions.supprimerSymbol$(panierpage.obtenir_TotalTaxes()));
		assertEquals(attendue_TotalAvecTaxes,Functions.supprimerSymbol$(panierpage.obtenir_TotalAvecTaxes()));
			
	}
	
	
	CheckoutPage checkoutpage;
	@Test
	public void allerCheckout() {
		//pre requis: avoir produits dans le panier
		allerAuPanier();
		
		// cliquer sur le btn Checkout
		checkoutpage = panierpage.cliquerBtnToCheckout();
	
		//valider les infos dans la fenetre
		assertThat(Functions.supprimerSymbol$(checkoutpage.obtenir_totalAvecTaxes()), is(attendue_TotalAvecTaxes));
		//assertThat(checkoutpage.obtenir_nomClient(), is(attendue_nomClient));
		assertTrue(checkoutpage.obtenir_nomClient().startsWith(attendue_nomClient));
		
		//cliquer dans le BTN Continue
		checkoutpage.cliquer_btnContinue();
		
		//valider un element de la page - 
		String livraison_valeur = checkoutpage.obtenir_livarisonValeur(); //va nous donner - $7 tax excl.
		livraison_valeur = Functions.effacerText(livraison_valeur, " tax excl."); //on efface le text - tax excl.
		Double livraison_valeurTrouvee = Functions.supprimerSymbol$(livraison_valeur); // on efface le symbol $.
		
		assertThat(livraison_valeurTrouvee, is(attendue_TotalLivraison));
		//cliquer BTN continue shipping
		checkoutpage.obtenir_btnContinueShipping();
		
		//selectionner l'option pay by check
		checkoutpage.selectionee_radioPayByCheck();
		
		//valide le valeur du check
		//nous recevons le text - $26.12
		String amount_payByCheck = checkoutpage.obtenir_amountPayByCheck();
		//nous allons effacer le symbol $ du text
		Double trouvee_amountPayByCheck = Functions.supprimerSymbol$(amount_payByCheck);
		assertThat(trouvee_amountPayByCheck ,is(attendue_TotalAvecTaxes));
		
		//cliquer dans l'options I AGREE
		checkoutpage.cliquer_IAgree();
		assertTrue(checkoutpage.checkBoxIAgreeSelectionee());
	}
	
	@Test
	public void terminerCommande() {
		
		//pre-requis: terminer le checkout 
		allerCheckout();
		
		//cliquer botao confirmer commande
		CommandePage commandepage = checkoutpage.obtenir_btnConfirmerCommande();
		assertTrue(commandepage.obtenir_messageCommandeConfirmer().endsWith("YOUR ORDER IS CONFIRMED"));
		
		assertThat(commandepage.obtenir_emailConfirmation(), is("rodrigobrum@test.com"));
		
		assertThat(commandepage.obtenir_totalProduits(), is(attendue_SousTotalProduit));
		
		assertThat(commandepage.obtenir_totalTaxesInclus(), is(attendue_TotalAvecTaxes));
		
		assertThat(commandepage.obtenir_methodePaiement(), is(" check"));
		
	}
	
	
		

}      
