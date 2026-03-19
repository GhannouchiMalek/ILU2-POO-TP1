package histoire;

import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;
import villagegaulois.VillageSansChefException;

public class ScenarioCasDegrade {

	public static void main(String[] args) {

		System.out.println("=== Test 1 : libererEtal sur un étal non occupé ===");
		Etal etal = new Etal();
		String result = etal.libererEtal(); // NullPointerException interceptée dans la méthode
		System.out.println("Après libererEtal : \"" + result + "\"");
		System.out.println("Fin du test 1\n");

		// ---------------------------------------------------------------
		System.out.println("=== Test 2a : acheterProduit avec acheteur null ===");
		Etal etal2 = new Etal();
		Gaulois vendeur = new Gaulois("Bonemine", 7);
		etal2.occuperEtal(vendeur, "fleurs", 10);
		String res2 = etal2.acheterProduit(5, null); // NullPointerException interceptée dans la méthode
		System.out.println("Résultat : \"" + res2 + "\"");
		System.out.println("Fin du test 2a\n");

		// ---------------------------------------------------------------
		System.out.println("=== Test 2b : acheterProduit avec quantité négative ===");
		Etal etal3 = new Etal();
		etal3.occuperEtal(vendeur, "fleurs", 10);
		try {
			System.out.print(etal3.acheterProduit(-3, new Gaulois("Astérix", 8)));
		} catch (IllegalArgumentException e) {
			System.err.println("IllegalArgumentException interceptée : " + e.getMessage());
		}
		System.out.println("Fin du test 2b\n");

		// ---------------------------------------------------------------
		System.out.println("=== Test 2c : acheterProduit sur un étal non occupé ===");
		Etal etal4 = new Etal();
		try {
			System.out.print(etal4.acheterProduit(2, new Gaulois("Obélix", 25)));
		} catch (IllegalStateException e) {
			System.err.println("IllegalStateException interceptée : " + e.getMessage());
		}
		System.out.println("Fin du test 2c\n");

		// ---------------------------------------------------------------
		System.out.println("=== Test 3 : afficherVillageois sans chef ===");
		Village villSansChef = new Village("Village Fantôme", 5, 3);
		try {
			System.out.print(villSansChef.afficherVillageois());
		} catch (VillageSansChefException e) {
			System.err.println("VillageSansChefException interceptée : " + e.getMessage());
		}
		System.out.println("Fin du test 3\n");

		System.out.println("=== Fin de tous les tests ===");
	}
}
