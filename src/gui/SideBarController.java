package gui;


import java.io.IOException;

import domein.DomeinController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SideBarController extends BorderPane{

	@FXML
	private AnchorPane apScene;
	@FXML
	private BorderPane bp;
	@FXML
	private VBox sideBar;
	
	private DomeinController dc;
	
	FXMLLoader loader;

	public SideBarController(DomeinController dc) {
		this.dc = dc;
		buildGui();
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		
	}

	private void buildGui() {
		loader = new FXMLLoader(getClass().getResource("HomePagePaneel2.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		try {
			loader.load();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	
	@FXML
	private void home(MouseEvent event) {
		bp.setCenter(apScene);
	}
	@FXML
	private void page1(MouseEvent event) {
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("page1.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bp.setCenter(root);
		
	}
	@FXML
	private void page2(MouseEvent event) {
		CategoriePaneelController root = new CategoriePaneelController();
		bp.setCenter(root);

		
	}
	
	
	
	
}
