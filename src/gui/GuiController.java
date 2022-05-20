package gui;

import domein.CategorieController;

public class GuiController {
	
	private static GuiController GuiInstantie = new GuiController();
	
	private GuiController() {
		
	}
	
	public static GuiController getInstance() {
		return GuiInstantie;
	}
	
	private AanmakenMvoPaneelController aanmakenPaneel = new AanmakenMvoPaneelController();
	private AanmeldPaneelController aanmeldPaneel;
	private CategorieAanmakenPaneelController catAanmakenPaneel;
	private CategoriePaneelController catPaneel;
	private CategorieRaadpleegPaneelController catRaadpleegPaneel;
	
	



	public AanmakenMvoPaneelController getAanmakenPaneel() {
		return aanmakenPaneel;
	}

	
	
	
	
	
	

}
