package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import com.auth0.exception.APIException;
import com.auth0.exception.Auth0Exception;

import domein.UserController;
import exceptions.IngelogdVerkeerdeRol;

class TestUser {

	UserController controller;
	
	@BeforeEach
	public void before() {
		controller = new UserController();
	}
	
	@Test
	public void testAanmeldenGoed() throws APIException, Auth0Exception {
		controller.meldAan("admin@test.be", "Azertyuiop.123");
		
		Assertions.assertEquals("admin", controller.getUserInfo());
	}

	@Test
	public void testAanmeldenVerkeerdeRol() {
		Assertions.assertThrows(IngelogdVerkeerdeRol.class, () -> controller.meldAan("test@test.be", "Azertyuiop.123"));
	}
	
	@ParameterizedTest
	@CsvSource({"test, test", "test@t, test123"})
	public void testAanmeldenVerkeerdeNaamOfWW(String email, String ww) throws APIException, Auth0Exception {
		Assertions.assertThrows(Auth0Exception.class, () -> controller.meldAan(email, ww));
	}	
}
