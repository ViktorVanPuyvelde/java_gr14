package gui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import domein.Datasource;
import domein.DatasourceController;
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
	private Label titel_lbl;
    @FXML
    private Button toevoegen_btn;
    
    private ObservableList<Mvo> mvoList;
    private DatasourceController controller;
    private Datasource datasource;
    
    public NieuweDatasourcePaneelController(Datasource d, DatasourceController controller) {
		this.mc = new MvoController();
		this.controller = controller;
		this.datasource = d;
		buildGui();
		setMvoList();

	}

	private void buildGui()
	{
		try
		{
			mvosList = new ListView<>();
			mvosList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

			mvoList = FXCollections.observableArrayList(new ArrayList<Mvo>());

			FXMLLoader loader = new FXMLLoader(getClass().getResource("NieuweDatasourcePaneel.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
			if (datasource != null) {
				toevoegen_btn.setText("Wijzigen");
				titel_lbl.setText("Datasource wijzigen");
				naam_textfield.setText(datasource.getName());
			}
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
	public void toevoegen_wijzigen_OnAction(ActionEvent actionEvent)
	{
		if (datasource == null) {
			controller.voegDatasourceToe(naam_textfield.getText(), false);
			toevoegenLbl.setText("Datasource toegevoegd!");
		}else {
			controller.updateDatasource(datasource, naam_textfield.getText(), false);
			toevoegenLbl.setText("Datasource gewijzigd!");			
		}
		
		toevoegenLbl.setTextFill(Color.GREEN);
		toevoegenLbl.setStyle("-fx-font-weight: bold");
	}
}
