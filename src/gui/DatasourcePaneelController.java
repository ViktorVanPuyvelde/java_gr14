package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import domein.Datasource;
import domein.DatasourceController;
import domein.DomeinController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DatasourcePaneelController extends AnchorPane{
	private DomeinController dc;
	private DatasourceController datasourceCon;
	
	@FXML
	private ListView<String> datasource_List;
	
	@FXML
	private Button create_btn;
	@FXML
	private Button view_btn;
	@FXML
	private Button edit_btn;
	@FXML
	private Button delete_btn;
	
	private ObservableList<Datasource> datasourceItemList;
	
	
	public DatasourcePaneelController(DomeinController dc) {
		this.dc = dc;
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
			
			datasourceItemList = FXCollections.observableArrayList(new ArrayList());
			
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
		//collectChanges();
		//verify();
		//update();
		NieuweDatasourcePaneelController ns = new NieuweDatasourcePaneelController(dc);
//		Scene scene = new Scene (ns);
//		Stage stage = (Stage) this.getScene().getWindow();
//		stage.setScene(scene);
//		stage.show();
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("NieuweDatasourcesPaneel.fxml"));
			loader.setController(ns);
			loader.setRoot(this);
			loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

//
//	@FXML
//	public void view_OnAction(ActionEvent event) {
//		collectChanges();
//		verify();
//		update();
//	}
//	
//	@FXML
//	public void edit_OnAction(ActionEvent event) {
//		collectChanges();
//		verify();
//		update();
//	}
//	
//	@FXML
//	public void delete_OnAction(ActionEvent event) {
//		collectChanges();
//		verify();
//		update();
//	}
	
	private void update() {
		// TODO Auto-generated method stub
		
	}

	private void verify() {
		// TODO Auto-generated method stub
		
	}

	private void collectChanges() {
		// TODO Auto-generated method stub
		
	}
}
