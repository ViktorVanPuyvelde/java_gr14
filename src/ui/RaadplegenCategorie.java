package ui;

import java.util.List;

import domein.Categorie;
import domein.CategorieController;
import domein.Sdg;

public class RaadplegenCategorie {

	public static void main(String[] args) {
		CategorieController controller = new CategorieController();
		List<Sdg> sdgs = controller.geefSdgsVoorCategorie(controller.geefCategorien().get(1));
		
		System.out.println(String.format("%s", sdgs.isEmpty()));
		
		sdgs.forEach(s -> System.out.println(s.toString()));
//		List<Categorie> cats = controller.geefCategorien();
//		
//		System.out.println(String.format("%s", cats.isEmpty()));
//		
//		cats.forEach(c -> System.out.println(c.getSdgs()));

	}

}
