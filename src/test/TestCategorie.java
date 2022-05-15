package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import domein.Categorie;
import domein.CategorieController;
import domein.Sdg;
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
	
	private static Stream<Arguments> echteCategorieen(){
		return Stream.of(
				Arguments.of(new Categorie[]{new Categorie("nieuw", "icon", new ArrayList<>(), true), new Categorie("nieuwCat", "icon2", new ArrayList<>(), true)}, 2),
				Arguments.of(new Categorie[]{new Categorie("nieuw1", "icon", new ArrayList<>(), true)}, 1),
				Arguments.of(new Categorie[]{}, 0)
			);
	}
	
	@ParameterizedTest
	@MethodSource("echteCategorieen")
	public void testRaadpleegEchteCategorieen(Categorie[] array, int expectedEchteCat) {
		Mockito.when(categorieRepo.geefAlleEchteCategorieen()).thenReturn(Arrays.asList(array));

		List<String> echteCat = controller.geefAlleEchteCategorienNaam();		
		Assertions.assertEquals(expectedEchteCat, echteCat.size());
		
		Mockito.verify(categorieRepo).geefAlleEchteCategorieen();
	}

	private static Stream<Arguments> sdgsVoorCategorie(){
		return Stream.of(
				Arguments.of(new Sdg[]{new Sdg("sdg1", "img1", new ArrayList<>(), null), new Sdg("sdg2", "img2", new ArrayList<>(), null)}, new Categorie("nieuw1", "icon", new ArrayList<>(), true)),
				Arguments.of(new Sdg[]{new Sdg("sdg1", "img1", new ArrayList<>(), null)}, new Categorie("nieuw1", "icon", new ArrayList<>(), true)),
				Arguments.of(new Sdg[]{}, new Categorie("nieuw1", "icon", new ArrayList<>(), true))
			);
	}
	
	@ParameterizedTest
	@MethodSource("sdgsVoorCategorie")
	public void testRaadpleegSdgCategorie(Sdg[] array, Categorie c) {
		Mockito.when(categorieRepo.geefSdgVoorCategorie(c.getName())).thenReturn(Arrays.asList(array));

		List<Sdg> sdgs = controller.geefSdgsVoorCategorie(c);
		Assertions.assertEquals(c.getSdgs().size(), sdgs.size());
		Assertions.assertArrayEquals(array, sdgs.toArray(new Sdg[sdgs.size()]));
		
		Mockito.verify(categorieRepo).geefSdgVoorCategorie(c.getName());
	}

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
