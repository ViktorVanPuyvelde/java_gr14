package gui;


import java.io.IOException;

import domein.DomeinController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	@FXML
	private Button log_Out_Btn;
	
	private DomeinController dc;
	private FXMLLoader loader;
	


	public SideBarController(DomeinController dc) {
		this.dc = dc;
		buildGui();
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		
	}

	private void buildGui() {
		loader = new FXMLLoader(getClass().getResource("HomePagePaneel.fxml"));
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
	private void mvo(MouseEvent event) {
		
	}
	@FXML
	private void categorie(MouseEvent event) {
		CategoriePaneelController root = new CategoriePaneelController();
		bp.setCenter(root);
	}
	@FXML
	private void datasource(MouseEvent event) {
		DatasourcePaneelController root = new DatasourcePaneelController(dc);
		bp.setCenter(root);
	}
	
	@FXML
	private void logout(MouseEvent event) {
		AanmeldPaneelController ns = new AanmeldPaneelController(dc);
		Scene scene = new Scene (ns);
		Stage stage = (Stage) this.getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	
	
	
	
}
