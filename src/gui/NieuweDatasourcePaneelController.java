package gui;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import domein.DomeinController;
import domein.Mvo;
import domein.MvoController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class NieuweDatasourcePaneelController extends GridPane {

	private DomeinController dc;
	private MvoController mc;
	
	@FXML
    private TextField naam_textfield;

	@FXML
	private Button upload_btn;
	
	@FXML
	private ChoiceBox<String> cbMvos;
	
    @FXML
    private Button toevoegen_btn;
    
    private ObservableList<Mvo> mvoList;
    
    public NieuweDatasourcePaneelController(DomeinController dc) {
		this.dc = dc;
		this.mc = new MvoController();
		buildGui();
		setMvoList();

	}
    
    private void buildGui() {
		try{
						
			cbMvos = new ChoiceBox<>();
			
			mvoList = FXCollections.observableArrayList(new ArrayList<Mvo>());
			
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
    
    private void setMvoList() {
    	List<Mvo> mvos = null; // nog aanpassen naar mc.geefMvos();
//    	for (Mvo m : mvos) {
//    		mvoList.add(m);
//    	}
    	
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
