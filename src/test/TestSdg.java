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

import domein.Mvo;
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
		Assertions.assertEquals(new Sdg("Geen Armoede", "sdg_1.png", new ArrayList<>(), null), sdgs.get(0));
	}

	private static Stream<Arguments> sdgs(){
		return Stream.of(
				Arguments.of(new Sdg("sdg1", "img1", new ArrayList<>(), null)),
				Arguments.of(new Sdg("sdg2", "img1", new ArrayList<>(), null)),
				Arguments.of(new Sdg("sdg3", "img1", new ArrayList<>(), null))
			);
	}
	
	@ParameterizedTest
	@MethodSource("sdgs")
	public void testRaadpleegSdg(Sdg s) {
		Mockito.when(sdgRepo.get(s.getId())).thenReturn(s);
		
		Assertions.assertEquals(s, controller.geefSdg(s.getId()));
		
		Mockito.verify(sdgRepo).get(s.getId());
		
	}

	private static Stream<Arguments> sdgsMvos(){
		return Stream.of(
				Arguments.of(new Mvo[]{new Mvo("mvo", null, null, 0, null, null), new Mvo("mvo2", null, null, 0, null, null)}, new Sdg("sdg", "img", null, null)),
				Arguments.of(new Mvo[]{new Mvo("mvo", null, null, 0, null, null)}, new Sdg("sdg", "img", null, null))
			);
	}
	
	@ParameterizedTest
	@MethodSource("sdgsMvos")
	public void testRaadpleegSdgVoorMvo(Mvo[] array, Sdg s) {
		s.setMvos(Arrays.asList(array));
		s.getMvos().get(0).setSdg(s);
		
		Mockito.when(sdgRepo.geefSdgVoorMvo(s.getId(), s.getMvos().get(0).getId())).thenReturn(s);
		
		Assertions.assertEquals(s, controller.geefSdgVoorMvo(s.getId(), s.getMvos().get(0).getId()));
		
		Mockito.verify(sdgRepo).geefSdgVoorMvo(s.getId(), s.getMvos().get(0).getId());
	}
	
	@ParameterizedTest
	@MethodSource("sdgs")
	public void testRaadpleegSdgVoorMvo(Sdg s) {
		Mockito.when(sdgRepo.geefSdgDoorNaam(s.getName())).thenReturn(s);
		
		Assertions.assertEquals(s, controller.geefSdgDoorNaam(s.getName()));
		
		Mockito.verify(sdgRepo).geefSdgDoorNaam(s.getName());
	}
}
