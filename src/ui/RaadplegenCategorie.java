package ui;

import domein.Categorie;
import domein.DomeinController;

public class RaadplegenCategorie {

	public static void main(String[] args) {
		DomeinController dc = new DomeinController();
		
		Categorie c = dc.raadpleegCategorie(dc.geefAlleCategories().get(1));
		
		System.out.println(c.toString());
		
		System.out.println(c.getSdgs());

	}

}
