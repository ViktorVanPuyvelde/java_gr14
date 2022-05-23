package test;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
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
	
	@Test
	public void testCreateCategoryWithException(){
		Datasource d = new Datasource("", false);

		Assertions.assertFalse(controller.geefDatasources().contains(d));
		Assertions.assertThrows(InformationRequiredException.class, () -> controller.voegDatasourceToe("", false));
		Assertions.assertFalse(controller.geefDatasources().contains(d));
	}

	
	@Test
	public void testRaadpleegDatasources() {
		List<Datasource> dats = controller.geefDatasources();
		Assertions.assertFalse(dats.isEmpty());
		Assertions.assertEquals(new Datasource("Aalst", false), dats.get(0));
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
		d.setName("Aalst");
		controller.updateDatasource(d, "Aalst", false);		
	}

	
	@Test
	public void testUpdateCategoryWithException(){
		Assertions.assertThrows(InformationRequiredException.class, () -> controller.updateDatasource(new Datasource("", false), "", false));
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
