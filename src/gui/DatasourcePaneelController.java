package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import domein.Categorie;
import domein.Datasource;
import domein.DatasourceController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class DatasourcePaneelController extends HBox{
	private DatasourceController datasourceCon;
	
	@FXML
	private ListView<String> datasource_List;
	
	@FXML
	private Button create_btn;
	@FXML
	private Button edit_btn;
	@FXML
	private Button delete_btn;
	@FXML
	private Label datasource_Selecteren_lbl;
	
	private ObservableList<Datasource> datasourceItemList;
	
    private boolean rechterSchermAanwezig = false;
	
	
	public DatasourcePaneelController() {
		this.datasourceCon = new DatasourceController();
		buildGui();
		setDatasourceList();
		initialize();
	}

	private void initialize() {
		datasourceItemList.forEach(ds -> datasource_List.getItems().add(ds.getName()));
		
	}

	private void buildGui() {
		
		try {
			
			datasource_List = new ListView<>();
			datasourceItemList = FXCollections.observableArrayList(new ArrayList<>());
			
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RaadplegenDatasourcesPaneel.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		loader.load();
		} 
		catch(IOException ex)
		{
		throw new RuntimeException(ex);
		}
		
	}
	
	private void setDatasourceList() {
		List<Datasource> datasources = datasourceCon.geefDatasources();
		for (Datasource d : datasources) {
			this.datasourceItemList.add(d);
		}
	}
	
	@FXML
	public void create_OnAction(ActionEvent event) {
		datasource_Selecteren_lbl.setText("");
		if (rechterSchermAanwezig) {
            verwijderRechterScherm();            
        }
        NieuweDatasourcePaneelController nieuweDatasourcePaneel = new NieuweDatasourcePaneelController(null, datasourceCon);
        this.getChildren().add(nieuweDatasourcePaneel);
        rechterSchermAanwezig = true;
	}
	
	@FXML
	public void edit_OnAction(ActionEvent event) {
		datasource_Selecteren_lbl.setText("");
		if (rechterSchermAanwezig) {
            verwijderRechterScherm();            
        }
		String naam = datasource_List.getSelectionModel().getSelectedItem();
		Datasource d = datasourceItemList.stream().filter(dat -> dat.getName().equals(naam)).findAny().orElse(null);
		if (d != null) {
			NieuweDatasourcePaneelController root = new NieuweDatasourcePaneelController(d, datasourceCon);
			this.getChildren().add(root);
			rechterSchermAanwezig = true;			
		}else {
			datasource_Selecteren_lbl.setText("Gelieve eerst een datasource te selecteren!");
		}
	}
		
	@FXML
	public void delete_OnAction(ActionEvent event) {		
	}
	
	private void verwijderRechterScherm() {
        this.getChildren().remove(this.getChildren().size()-1);
        rechterSchermAanwezig = false;
    }

}
