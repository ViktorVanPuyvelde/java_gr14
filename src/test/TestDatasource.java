package test;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import domein.Datasource;
import domein.DatasourceController;
import exceptions.InformationRequiredException;
import repository.DatasourceDao;

@ExtendWith(MockitoExtension.class)
class TestDatasource {

	@Mock
	private DatasourceDao datasourceRepo;
	
	@InjectMocks
	private DatasourceController controller;
	
	@Test
	public void testCreateDatasource() throws InformationRequiredException {
		Datasource datasource = new Datasource("nieuwD", false);

		Assertions.assertFalse(controller.geefDatasources().contains(datasource));
		controller.voegDatasourceToe("nieuwD", false);
		Assertions.assertTrue(controller.geefDatasources().contains(datasource));
	}

	private static Stream<Arguments> fouteDatasource(){
		return Stream.of(
				Arguments.of(null, null),
				Arguments.of("", false),
				Arguments.of("D", null)
			);
	}	
	
	@ParameterizedTest
	@MethodSource("fouteDatasource")
	public void testCreateCategoryWithException(String naam, boolean b){
		Datasource d = new Datasource("data", false);

		Assertions.assertFalse(controller.geefDatasources().contains(d));
		Assertions.assertThrows(InformationRequiredException.class, () -> controller.voegDatasourceToe(naam, b));
		Assertions.assertFalse(controller.geefDatasources().contains(d));
	}

	
	@Test
	public void testRaadpleegDatasources() {
		List<Datasource> dats = controller.geefDatasources();
		Assertions.assertFalse(dats.isEmpty());
		Assertions.assertEquals(new Datasource("Algemeen", false), dats.get(0));
	}

	@ParameterizedTest
	@ValueSource(strings = {"nieuwD", "datasource"})
	public void testRaadpleegDatasourceMetNaam(String naam) {
		Datasource d = new Datasource(naam, false);
		Mockito.when(datasourceRepo.geefDatasourceDoorNaam(naam)).thenReturn(d);
		
		Assertions.assertEquals(naam, controller.geefDatasourceDoorNaam(naam).getName());
		
		Mockito.verify(datasourceRepo).geefDatasourceDoorNaam(naam);
	}	
	
	@Test
	public void testUpdateDatasource() throws InformationRequiredException {
		Datasource d = controller.geefDatasources().get(0);
		controller.updateDatasource(d, "nieuwData", false);
		Assertions.assertEquals("nieuwData", controller.geefDatasources().get(0).getName());
		
		//Terug naar originele waarde zetten
		d.setName("Algemeen");
		controller.updateDatasource(d, "Algemeen", false);		
	}

	
	@ParameterizedTest
	@MethodSource("fouteDatasource")
	public void testUpdateCategoryWithException(String naam, boolean b){
		Assertions.assertThrows(InformationRequiredException.class, () -> controller.updateDatasource(new Datasource(naam, b), naam, b));
	}

	@Test
	public void testDeleteCategory() throws InformationRequiredException {
		controller.voegDatasourceToe("nieuwDat", false);

		Datasource d = controller.getDatasource("nieuwDat");
		Assertions.assertTrue(controller.geefDatasources().contains(d));
		controller.deleteDatasource(d);
		Assertions.assertFalse(controller.geefDatasources().contains(d));		
	}

	
}
