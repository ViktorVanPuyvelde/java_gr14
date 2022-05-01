package gui;

import java.io.IOException;


import domein.DomeinController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;

public class HomepagePaneelController extends AnchorPane{
	private DomeinController dc;
	
	
	@FXML
	private Button aanmelden_btn;
	@FXML
	private Button categorieAanmeken_btn;
	@FXML
	private Button datasourcesRaadplegen_btn;
	



	public HomepagePaneelController(DomeinController dc) {
		this.dc = dc;
		buildGui();

	}
	
	private void buildGui() {
		try{
						
			FXMLLoader loader = new FXMLLoader(getClass().getResource("HomepagePaneel.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
			
			
			
			} 
		catch(IOException ex)
		{
		throw new RuntimeException(ex);
		}
	}
	

		
	
	@FXML
	public void aanmelden_onAction(ActionEvent event) {
		AanmeldPaneelController ns = new AanmeldPaneelController(dc);
		Scene scene = new Scene (ns);
		Stage stage = (Stage) this.getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	@FXML
	public void categorieAanmaken_onAction(ActionEvent event) {
		CategoriePaneelController ns = new CategoriePaneelController();
		Scene scene = new Scene (ns);
		Stage stage = (Stage) this.getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	public void datasourcesRaadplegen_onAction(ActionEvent event) {
		
	}


	
}

