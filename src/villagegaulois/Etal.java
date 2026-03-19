package villagegaulois;

import personnages.Gaulois;

public class Etal {
	private Gaulois vendeur;
	private String produit;
	private int quantiteDebutMarche;
	private int quantite;
	private boolean etalOccupe = false;

	public boolean isEtalOccupe() {
		return etalOccupe;
	}

	public Gaulois getVendeur() {
		return vendeur;
	}
	
	// Getters ajoutes pour afficherMarche()
	public String getProduit() {
		return produit;
	}

	public int getQuantite() {
		return quantite;
	}

	public void occuperEtal(Gaulois vendeur, String produit, int quantite) {
		this.vendeur = vendeur;
		this.produit = produit;
		this.quantite = quantite;
		quantiteDebutMarche = quantite;
		etalOccupe = true;
	}

	public String libererEtal() {
		try {
			StringBuilder chaine = new StringBuilder(
					"Le vendeur " + vendeur.getNom() + " quitte son étal, ");
			int produitVendu = quantiteDebutMarche - quantite;
			if (produitVendu > 0) {
				chaine.append("il a vendu " + produitVendu + " " + produit
						+ " parmi les " + quantiteDebutMarche + " qu'il voulait vendre.\n");
			} else {
				chaine.append("il n'a malheureusement rien vendu.\n");
			}
			// reinit de l'étal
			etalOccupe = false;
			vendeur = null;
			produit = null;
			quantite = 0;
			quantiteDebutMarche = 0;
			return chaine.toString();
		} catch (NullPointerException e) {
			System.err.println("Erreur : tentative de libérer un étal qui n'a pas été occupé.");
			return "";
		}
	}

	public String acheterProduit(int quantiteAcheter, Gaulois acheteur) {
		//  l'étal doit être occupé
		if (!etalOccupe) {
			throw new IllegalStateException("L'étal doit être occupé pour acheter un produit.");
		}
		// la quantité doit être positive
		if (quantiteAcheter < 1) {
			throw new IllegalArgumentException("La quantité achetée doit être positive.");
		}

		try {
			// declenche NullPointerException si acheteur est null
			String nomAcheteur = acheteur.getNom();
			
			StringBuilder chaine = new StringBuilder();
			chaine.append(nomAcheteur + " veut acheter " + quantiteAcheter
					+ " " + produit + " à " + vendeur.getNom());
			
			if (quantite == 0) {
				chaine.append(", malheureusement il n'y en a plus !");
				quantiteAcheter = 0;
			}
			if (quantiteAcheter > quantite) {
				chaine.append(", comme il n'y en a plus que " + quantite + ", "
						+ nomAcheteur + " vide l'étal de "
						+ vendeur.getNom() + ".\n");
				quantiteAcheter = quantite;
				quantite = 0;
			}
			if (quantite != 0) {
				quantite -= quantiteAcheter;
				chaine.append(". " + nomAcheteur
						+ ", est ravi de tout trouver sur l'étal de "
						+ vendeur.getNom() + "\n");
			}
			return chaine.toString();
		} catch (NullPointerException e) {
			// Gestion : on affiche la pile d'erreur et on retourne une chaîne vide
			e.printStackTrace();
			return "";
		}
	}

	public boolean contientProduit(String produit) {
		// pour eviter NullPointerException si this.produit n'est pas initialise
		return produit != null && produit.equals(this.produit);
	}

}


