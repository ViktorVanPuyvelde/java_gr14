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
import exceptions.InformationRequiredException;
import repository.CategorieDao;

@ExtendWith(MockitoExtension.class)
class TestCategorie {
	
	@Mock
	private CategorieDao categorieRepo;
	
	@InjectMocks
	private CategorieController controller;
	
	@Test
	public void testCreateCategory() throws InformationRequiredException {
		Categorie categorie = new Categorie("nieuwCat", "icon", new ArrayList<>() , true, new ArrayList<>());

		Assertions.assertFalse(controller.geefCategorien().contains(categorie));
		controller.voegCategorieToe("nieuwCat", "icon", new ArrayList<>(Arrays.asList("Gebruiker")), new ArrayList<>(Arrays.asList(new Sdg("sdg1", null, null, null))));
		Assertions.assertTrue(controller.geefCategorien().contains(categorie));
	}

	private static Stream<Arguments> fouteCategorie(){
		return Stream.of(
				Arguments.of("", "", new ArrayList<>(), new ArrayList<>()),
				Arguments.of("nieuwCat", "", new ArrayList<>(), new ArrayList<>()),
				Arguments.of("nieuwCat", "icon", new ArrayList<>(), new ArrayList<>()),
				Arguments.of("nieuwCat", "icon", new ArrayList<>(Arrays.asList("Gebruiker")), new ArrayList<>()),
				Arguments.of("nieuwCat", "icon", new ArrayList<>(), new ArrayList<>(Arrays.asList(new Sdg("sdg", null, null, null)))),
				Arguments.of("", "icon", new ArrayList<>(Arrays.asList("Gebruiker")), new ArrayList<>(Arrays.asList(new Sdg("sdg", null, null, null)))),
				Arguments.of(null, null, null, null),
				Arguments.of("nieuwCat", null, null, null),
				Arguments.of("nieuwCat", "icon", null, null),
				Arguments.of("nieuwCat", "icon", null, new ArrayList<>(Arrays.asList(new Sdg("sdg", null, null, null))))
			);
	}	
	
	@ParameterizedTest
	@MethodSource("fouteCategorie")
	public void testCreateCategoryWithException(String naam, String icon, List<String> roles, List<Sdg> sdgs){
		Categorie categorie = new Categorie("nieuwCat", "icon", new ArrayList<>() , true, new ArrayList<>());

		Assertions.assertFalse(controller.geefCategorien().contains(categorie));
		Assertions.assertThrows(InformationRequiredException.class, () -> controller.voegCategorieToe(naam, icon, roles, sdgs));
		Assertions.assertFalse(controller.geefCategorien().contains(categorie));
	}

	
	@Test
	public void testRaadpleegCategorieen() {
		List<Categorie> cats = controller.geefCategorien();
		Assertions.assertFalse(cats.isEmpty());
		Assertions.assertEquals(new Categorie("Planet", "FaGlobeAmericas", new ArrayList<>(), true, new ArrayList<>()), cats.get(1));
	}
	
	private static Stream<Arguments> echteCategorieen(){
		return Stream.of(
				Arguments.of(new Categorie[]{new Categorie("nieuw", "icon", new ArrayList<>(), true, new ArrayList<>()), new Categorie("nieuwCat", "icon2", new ArrayList<>(), true, new ArrayList<>())}, 2),
				Arguments.of(new Categorie[]{new Categorie("nieuw1", "icon", new ArrayList<>(), true, new ArrayList<>())}, 1),
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
				Arguments.of(new Sdg[]{new Sdg("sdg1", "img1", new ArrayList<>(), null), new Sdg("sdg2", "img2", new ArrayList<>(), null)}, new Categorie("nieuw1", "icon", new ArrayList<>(), true, new ArrayList<>())),
				Arguments.of(new Sdg[]{new Sdg("sdg1", "img1", new ArrayList<>(), null)}, new Categorie("nieuw1", "icon", new ArrayList<>(), true, new ArrayList<>())),
				Arguments.of(new Sdg[]{}, new Categorie("nieuw1", "icon", new ArrayList<>(), true, new ArrayList<>()))
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

	@Test
	public void testUpdateCategory() throws InformationRequiredException {
		Categorie c = controller.geefCategorien().get(1);
		c.setName("nieuwCat");
		controller.pasCategorieAan(c);
		Assertions.assertEquals("nieuwCat", controller.geefCategorien().get(1).getName());
		
		//Terug naar originele waarde zetten
		c.setName("Planet");
		controller.pasCategorieAan(c);		
	}

	
	@ParameterizedTest
	@MethodSource("fouteCategorie")
	public void testUpdateCategoryWithException(String naam, String icon, List<String> roles, List<Sdg> sdgs){
		Assertions.assertThrows(InformationRequiredException.class, () -> controller.pasCategorieAan(new Categorie(naam, icon, roles, false, sdgs)));
	}

	@Test
	public void testDeleteCategory() throws InformationRequiredException {
		controller.voegCategorieToe("nieuwCat", "icon", new ArrayList<>(Arrays.asList("gebruiker")), new ArrayList<>(Arrays.asList(new Sdg("sdg1", null, null, null))));

		Categorie c = controller.getCategorie("nieuwCat");
		Assertions.assertTrue(controller.geefCategorien().contains(c));
		controller.verwijderCategorie(c);
		Assertions.assertFalse(controller.geefCategorien().contains(c));		
	}

	
}
