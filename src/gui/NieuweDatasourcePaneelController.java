package gui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import domein.Aggregatie;
import domein.Mvo;
import domein.MvoController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class NieuweDatasourcePaneelController extends GridPane
{

	private MvoController mc;

	@FXML
	private TextField naam_textfield;

	@FXML
	private Button upload_btn;

	@FXML
	private Label fileLbl;

	@FXML
	private ListView<String> mvosList;

	@FXML
	private Label toevoegenLbl;
	
    @FXML
    private Button toevoegen_btn;
    
    private ObservableList<Mvo> mvoList;
    
    public NieuweDatasourcePaneelController() {
		this.mc = new MvoController();
		buildGui();
		setMvoList();

	}

	private void buildGui()
	{
		try
		{

			mvosList = new ListView<>();
			//mvosList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

			mvoList = FXCollections.observableArrayList(new ArrayList<Mvo>());

			FXMLLoader loader = new FXMLLoader(getClass().getResource("NieuweDatasourcePaneel.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();

		} catch (IOException ex)
		{
			throw new RuntimeException(ex);
		}
	}

	private void setMvoList()
	{
		List<Mvo> mvos = mc.geefMvos();
		for (Mvo m : mvos)
		{
			mvoList.add(m);
		}

		mvoList.forEach(m -> mvosList.getItems().add(m.getName()));
	}

	@FXML
	public void upload_OnAction(ActionEvent actionEvent)
	{
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Csv File");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Csv Files", "*.csv"));

		Stage newWindow = new Stage();
		newWindow.setTitle("Kies een csv bestand");
		File selectedFile = fileChooser.showOpenDialog(newWindow);

		if (selectedFile != null)
		{
			fileLbl.setText(String.format("%s geselecteerd", selectedFile.getName()));
			fileLbl.setTextFill(Color.GREEN);
			fileLbl.setStyle("-fx-font-weight: bold");
		}
	}

	@FXML
	public void toevoegen_OnAction(ActionEvent actionEvent)
	{
		collectChanges();
		verify();
		update();
	}

	private void update() {
		
	
	}

	private void verify() {
		
		
		toevoegenLbl.setText("Datasource toegevoegd!");
		toevoegenLbl.setTextFill(Color.GREEN);
		toevoegenLbl.setStyle("-fx-font-weight: bold");
		
	}

	private void collectChanges() {
		
		Aggregatie methode = mvoList.get(0).getMethode();
		verwerkDatasource(methode);
		
	}

	private void verwerkDatasource(Aggregatie methode) {
		// data in dictionary op quarter
		
		switch(methode) {
		  case SOM:
			 verwerkDataAlsSom();
		    break;
		  case GEMIDDELDE:
			  verwerkDataAlsGem();
		    break;
		  default:
			  
		    
		}
		
	}

	private void verwerkDataAlsGem() {
		
		
	}

	private void verwerkDataAlsSom() {
		
		
	}
}
