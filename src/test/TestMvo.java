package test;

import org.junit.jupiter.api.Assertions;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import domein.Mvo;
import domein.MvoController;
import repository.MvoDao;

@ExtendWith(MockitoExtension.class)
class TestMvo {
	
	@Mock
	private MvoDao mvoRepo;
	
	@InjectMocks
	private MvoController controller;
	
	@Test
	public void testCreateMvo() {
		Mvo mvo = new Mvo("mvo", null, null, 0, null, null);

		Assertions.assertFalse(controller.geefMvos().contains(mvo));
		controller.voegMvoToe("mvo", null, null, 0, null, null);
		Assertions.assertTrue(controller.geefMvos().contains(mvo));		
	}
	
	@Test
	public void testRaadpleegMvo() {
		List<Mvo> mvos = controller.geefMvos();
		Assertions.assertFalse(mvos.isEmpty());
		Assertions.assertEquals(new Mvo("Waterverbruik", null, null, 0, null, null), mvos.get(0));
	}

}
