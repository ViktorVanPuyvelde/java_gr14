package gui;

import java.io.IOException;
import java.util.ArrayList;

import domein.Datasource;
import domein.DomeinController;
import domein.Mvo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

public class DatasourcePaneelController extends AnchorPane{
	private DomeinController dc;
	
	@FXML
	private ListView<String> datasourceList;
	
	private ObservableList<Datasource> datasourceItemList;
	
	
	public DatasourcePaneelController(DomeinController dc) {
		this.dc = dc;
		buildGui();
		initialize();
	}

	private void initialize() {
		datasourceItemList.add(new Datasource("Datasource 1"));
		datasourceItemList.add(new Datasource("Datasource 2"));
		
		datasourceItemList.forEach(ds -> datasourceList.getItems().add(ds.getName()));
		
	}

	private void buildGui() {
		
		try {
			
			datasourceList = new ListView<>();
			
			datasourceItemList = FXCollections.observableArrayList(new ArrayList());
			
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RaadplegenDatasourcePaneel.fxml"));
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
