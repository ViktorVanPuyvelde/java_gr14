package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class NieuweDatasourcePaneelController extends VBox {

	private DomeinController dc;
	
	@FXML
    private TextField naam_textfield;

    @FXML
    private Button toevoegen_btn;
    
    public NieuweDatasourcePaneelController(DomeinController dc) {
		this.dc = dc;
		buildGui();

	}
    
    private void buildGui() {
		try{
						
			FXMLLoader loader = new FXMLLoader(getClass().getResource("NieuweDatasourcePaneel.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
			
			
			
			} 
		catch(IOException ex)
		{
		throw new RuntimeException(ex);
		}
	}
}
