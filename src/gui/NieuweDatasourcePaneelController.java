package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class NieuweDatasourcePaneelController extends GridPane {

	private DomeinController dc;
	
	@FXML
    private TextField naam_textfield;

	@FXML
	private Button upload_btn;
	
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
    
    @FXML
    public void upload_OnAction(ActionEvent actionEvent) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Open Csv File");
    	//fileChooser.showOpenDialog(null);
    }
    
    @FXML
    public void toevoegen_OnAction(ActionEvent actionEvent) {
    	
    }
}
