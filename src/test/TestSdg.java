package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import domein.Sdg;
import domein.SdgController;
import repository.SdgDao;

@ExtendWith(MockitoExtension.class)
class TestSdg {

	@Mock
	private SdgDao sdgRepo;
	
	@InjectMocks
	private SdgController controller;
		
	@Test
	public void testRaadpleegSdgs() {
		List<Sdg> sdgs = controller.geefSdgs();
		Assertions.assertFalse(sdgs.isEmpty());
		Assertions.assertEquals(new Sdg("Geen Armoede", "sdg1", new ArrayList<>(), null), sdgs.get(0));
	}

	@Test
	public void testRaadpleegSdg() {
	}

	@Test
	public void testRaadpleegSdgVoorMvo() {
	}
}
