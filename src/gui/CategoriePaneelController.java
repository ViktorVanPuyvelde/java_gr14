package gui;

import java.io.IOException;
import java.util.ArrayList;

import domein.DomeinController;
import domein.Mvo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class CategoriePaneelController extends GridPane{
	private DomeinController dc;
	
	@FXML
	private ListView<Mvo> cat_Mvo_List;
	@FXML
	private ListView<String> cat_Rol_List;
	
	@FXML
	private TextField cat_Name_field;
	
	@FXML
	private Button cat_save_btn;
	
	private ObservableList<Mvo> mvoItemList;
	private ObservableList<String> rolItemList;



	public CategoriePaneelController(DomeinController dc) {
		this.dc = dc;
		cat_Mvo_List = new ListView<>();
		cat_Rol_List = new ListView<>();
		mvoItemList = FXCollections.observableArrayList(new ArrayList());
		rolItemList = FXCollections.observableArrayList(new ArrayList());
		buildGui();
		
	}
	
	private void buildGui() {
		try{
			
			
			mvoItemList.add(new Mvo("nieuwe MVO",null));
			rolItemList.add("Een rol");
			
			//fill with non-binded Mvo's
			cat_Mvo_List.setItems(mvoItemList);
			
			
			//fill with Roles
			cat_Rol_List.setItems(rolItemList);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("CategoriePaneel.fxml"));
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
	public void btnCatSaveOnAction(ActionEvent event) {
		collectChanges();
		verify();
		update();
	}

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

