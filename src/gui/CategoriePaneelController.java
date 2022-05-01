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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class CategoriePaneelController extends GridPane{
	//private DomeinController dc;
	
	@FXML
	private ListView<String> cat_Mvo_List;
	@FXML
	private ListView<String> cat_Rol_List;
	
	@FXML
	private TextField cat_Name_field;
	
	@FXML
	private Button cat_save_btn;
	
	private ObservableList<Mvo> mvoItemList;
	private ObservableList<String> rolItemList;
	//private DomeinController dc;
	



	public CategoriePaneelController() {
		
		buildGui();
		initialize();
	}
	
	private void buildGui() {
		
	
		 
		cat_Mvo_List = new ListView<>();
		cat_Rol_List = new ListView<>();
		mvoItemList = FXCollections.observableArrayList(new ArrayList());
		rolItemList = FXCollections.observableArrayList(new ArrayList());
		
		  FXMLLoader loader = new FXMLLoader(getClass().getResource("CategoriePaneel.fxml"));
		  loader.setController(this); 
		  loader.setRoot(this); 
		  try { 
			  loader.load(); 
			  }
		  catch (IOException e) { 
		  e.printStackTrace(); 
		  }
	}
	
	private void initialize() {
		
		

		
		cat_Mvo_List.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		cat_Rol_List.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		mvoItemList.add(new Mvo("MVO 1",null));
		mvoItemList.add(new Mvo("MVO 2",null));
		mvoItemList.add(new Mvo("MVO 3",null));
		rolItemList.add("rol 1");
		rolItemList.add("rol 2");
		
		//fill with Mvo's
		mvoItemList.forEach(mvo -> cat_Mvo_List.getItems().add(mvo.getName()));
		
		
		//fill with Roles
		cat_Rol_List.setItems(rolItemList);
		System.out.println(cat_Mvo_List.getItems());
		
		
		
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

