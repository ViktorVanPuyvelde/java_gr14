package test;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

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
import domein.Datasource;
import domein.Mvo;
import domein.MvoController;
import domein.Sdg;
import exceptions.InformationRequiredException;
import repository.MvoDao;

@ExtendWith(MockitoExtension.class)
class TestMvo {
	
	@Mock
	private MvoDao mvoRepo;
	
	@InjectMocks
	private MvoController controller;
	
	@Test
	public void testCreateMvo() throws InformationRequiredException {
		Mvo mvo = new Mvo("mvo", new Sdg("sdg", null, null, null), new ArrayList<>(Arrays.asList("info")), 1, new Datasource("da", false), null);

		Assertions.assertFalse(controller.geefMvos().contains(mvo));
		controller.voegMvoToe("mvo", new Sdg("sdg", null, null, null), new ArrayList<>(Arrays.asList("info")), 1, new Datasource("da", false), null);
		Assertions.assertTrue(controller.geefMvos().contains(mvo));		
	}

	private static Stream<Arguments> fouteMvo(){
		return Stream.of(
				Arguments.of("", null, null, -1, null, null),
				Arguments.of("mvo", null, null, 100, null, null),
				Arguments.of("mvo", new Sdg("sdg", null, null, null), null, 100, null, null),
				Arguments.of("mvo", new Sdg("sdg", null, null, null), new ArrayList<>(Arrays.asList("info")), 100, null, null),
				Arguments.of("mvo", new Sdg("sdg", null, null, null), null, 100, new Datasource("da", false), null),
				Arguments.of("mvo", null, new ArrayList<>(Arrays.asList("info")), 100, new Datasource("da", false), null),
				Arguments.of(null, null, null, -1, null, null),
				Arguments.of(null, new Sdg("sdg", null, null, null), new ArrayList<>(Arrays.asList("info")), 100, new Datasource("da", false), null),				
				Arguments.of("", new Sdg("sdg", null, null, null), new ArrayList<>(Arrays.asList("info")), 100, new Datasource("da", false), null),		
				Arguments.of("", new Sdg("sdg", null, null, null), new ArrayList<>(Arrays.asList("info")), -1, new Datasource("da", false), null)		
			);
	}
	@ParameterizedTest
	@MethodSource("fouteMvo")
	public void testCreateMvoWithException(String naam, Sdg sdg, ArrayList<String> info, int goal, Datasource datasource, Mvo superMvo) throws InformationRequiredException {
		Mvo mvo = new Mvo("mvo", new Sdg("sdg", null, null, null), new ArrayList<>(Arrays.asList("info")), 0, new Datasource("da", false), null);

		Assertions.assertFalse(controller.geefMvos().contains(mvo));
		Assertions.assertThrows(InformationRequiredException.class, () -> controller.voegMvoToe(naam, sdg, info, goal, datasource, superMvo));
		Assertions.assertFalse(controller.geefMvos().contains(mvo));		
	}
	
	@Test
	public void testRaadpleegMvo() {
		List<Mvo> mvos = controller.geefMvos();
		Assertions.assertFalse(mvos.isEmpty());
		Assertions.assertEquals(new Mvo("Waterverbruik", null, null, 0, null, null), mvos.get(0));
	}
	
	private static Stream<Arguments> mvos(){
		return Stream.of(
				Arguments.of(new Mvo("mvo1", null, null, 0, null, null)),
				Arguments.of(new Mvo("mvo2", null, null, 0, null, null)),
				Arguments.of(new Mvo("mvo3", null, null, 0, null, null))
			);
	}
	
	@ParameterizedTest
	@MethodSource("mvos")
	public void testRaadpleegMvo(Mvo m) {
		Mockito.when(mvoRepo.geefMvoMetNaam(m.getName())).thenReturn(m);
		Assertions.assertEquals(m, controller.geefMvoMetNaam(m.getName()));
		Mockito.verify(mvoRepo).geefMvoMetNaam(m.getName());		
	}
	
	@Test
	public void testUpdateMvo() throws InformationRequiredException {
		Mvo m = controller.geefMvos().get(0);

		m.setName("updateMvo");
		controller.update(m);
		Assertions.assertEquals("updateMvo", controller.geefMvos().get(0).getName());
		
		//Terug naar originele waarde zetten
		m.setName("Waterverbruik");
		controller.update(m);		
	}
	
//	@ParameterizedTest
//	@MethodSource("fouteMvo")
//	public void testUpdateMvoWithException(String naam, Sdg sdg, ArrayList<String> info, int goal, Datasource datasource, Mvo superMvo){
//		Assertions.assertThrows(InformationRequiredException.class, () -> controller.update(new Mvo(naam, sdg, info, goal, datasource, superMvo)));
//	}

}
