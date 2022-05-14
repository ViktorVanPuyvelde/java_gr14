package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import domein.Categorie;
import domein.CategorieController;
import repository.CategorieDao;

@ExtendWith(MockitoExtension.class)
class TestCategorie {
	
	@Mock
	private CategorieDao categorieRepo;
	
	@InjectMocks
	private CategorieController controller;
	
	@Test
	public void testCreateCategory() {
		Categorie categorie = new Categorie("nieuwCat", "icon", new ArrayList<>() , true);

		Assertions.assertFalse(controller.geefCategorien().contains(categorie));
		controller.voegCategorieToe("nieuwCat", "icon", new ArrayList<>());
		Assertions.assertTrue(controller.geefCategorien().contains(categorie));		
	}
	
	@Test
	public void testRaadpleegCategorieen() {
		List<Categorie> cats = controller.geefCategorien();
		Assertions.assertFalse(cats.isEmpty());
		Assertions.assertEquals(new Categorie("Planet", "FaGlobeAmericas", new ArrayList<>(), true), cats.get(1));
	}

//	@Test
//	public void testRaadpleegEchteCategorieen() {
//		List<String> namen = controller.geefAlleEchteCategorienNaam();
//		System.out.println(namen.toString());
//	}
	
//	@Test
//	public void testUpdateCategory() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDeleteCategory() {
//		fail("Not yet implemented");
//	}

	
}
