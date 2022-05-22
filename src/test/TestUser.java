package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.auth0.exception.APIException;
import com.auth0.exception.Auth0Exception;

import domein.UserController;
import exceptions.IngelogdVerkeerdeRol;
import io.github.cdimascio.dotenv.Dotenv;

class TestUser {

	UserController controller;
	
	@BeforeEach
	public void before() {
		controller = new UserController();
	}
	
	@Test
	public void testAanmeldenGoed() throws APIException, Auth0Exception {
		Dotenv dotenv = Dotenv.load();

		controller.meldAan(dotenv.get("USERTEST_GOED"), dotenv.get("USERTEST_GOED_WW"));
		
		Assertions.assertEquals("admin", controller.getUserInfo());
	}

	@Test
	public void testAanmeldenVerkeerdeRol() {
		Dotenv dotenv = Dotenv.load();

		Assertions.assertThrows(IngelogdVerkeerdeRol.class, () -> controller.meldAan(dotenv.get("USERTEST_FOUT"), dotenv.get("USERTEST_FOUT_WW")));
	}
	
	@ParameterizedTest
	@CsvSource({"test, test", "test@t, test123"})
	public void testAanmeldenVerkeerdeNaamOfWW(String email, String ww) throws APIException, Auth0Exception {
		Assertions.assertThrows(Auth0Exception.class, () -> controller.meldAan(email, ww));
	}	
	
	@Test
	public void testAfmelden() throws APIException, Auth0Exception {
		Dotenv dotenv = Dotenv.load();

		controller.meldAan(dotenv.get("USERTEST_GOED"), dotenv.get("USERTEST_GOED_WW"));
		controller.meldAf();
		
		Assertions.assertEquals(null, controller.getCoordinator());
	}

}
