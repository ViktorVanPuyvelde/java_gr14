package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import domein.UserController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class SideBarController extends BorderPane
{

	@FXML
	private AnchorPane apScene;
	@FXML
	private GridPane CenterGrid;
	@FXML
	private BorderPane bp;
	@FXML
	private VBox sideBar;
	@FXML
	private Button log_Out_Btn;
	@FXML
	private Button home_Btn;
	@FXML
	private Button mvo_Btn;
	@FXML
	private Button categorie_Btn;
	@FXML
	private Button datasource_Btn;
	@FXML	
	private Label userInfo_lbl;
	@FXML	
	private Circle circle;
	
	private UserController userController;
	private FXMLLoader loader;
	
	final String IDLE_BUTTON_STYLE = "-fx-background-color: #37465D;";
	final String ACTIVCATION_BUTTON_STYLE = "-fx-background-color: #465a77;";
	private List<Button> sidebarBtns;

	public SideBarController(UserController controller) {
		this.userController = controller;
		buildGui();
		initialize();
	}

	private void initialize() {
		sidebarBtns = new ArrayList<>();
		sidebarBtns.add(home_Btn);
		sidebarBtns.add(mvo_Btn);
		sidebarBtns.add(categorie_Btn);
		sidebarBtns.add(datasource_Btn);
		Image im = new Image(getClass().getResourceAsStream("/icons/avatar_logo.jpg"));
		circle.setFill(new ImagePattern(im));
		userInfo_lbl.setText(userController.getUserInfo());
		makeBtnActive(home_Btn);
	}


	private void buildGui() {
		loader = new FXMLLoader(getClass().getResource("SideBarPaneel.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		
		try {
			loader.load();
		} catch (IOException e)
		{

			e.printStackTrace();
		}

	}

	@FXML
	private void home(MouseEvent event) {
		toHome();
	}
	
	private void toHome() {
		bp.getChildren().remove(bp.getCenter());
		bp.setCenter(CenterGrid);
		makeBtnActive(home_Btn);
	}

	@FXML
	private void mvo(MouseEvent event) {
		toMvo();
	}
	

	private void toMvo() {
		MvoPaneelController root = new MvoPaneelController();
		bp.getChildren().remove(bp.getCenter());
		bp.setCenter(root);
		makeBtnActive(mvo_Btn);
	}
	
	@FXML
	private void categorie(MouseEvent event) {
		toCategorie();
	}
	
	private void toCategorie() {
		CategoriePaneelController root = new CategoriePaneelController();
		bp.getChildren().remove(bp.getCenter());
		bp.setCenter(root);
		makeBtnActive(categorie_Btn);
	}

	@FXML
	private void datasource(MouseEvent event) {
		toDatasource();
	}
	
	/*
	 */
	
	private void toDatasource() {
		DatasourcePaneelController root = new DatasourcePaneelController();
		bp.getChildren().remove(bp.getCenter());
		bp.setCenter(root);
		makeBtnActive(datasource_Btn);
	}

	@FXML
	private void logout(MouseEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("uitloggen");
		alert.setHeaderText("Zeker dat u wilt uitloggen?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			userController.meldAf();
			Scene scene = new Scene (new AanmeldPaneelController());
			Stage stage = (Stage) this.getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} else {
		   
		}
	}
	
	private void makeBtnActive(Button btn) {
		sidebarBtns.stream().forEach(b -> b.setStyle(IDLE_BUTTON_STYLE));
		btn.setStyle(ACTIVCATION_BUTTON_STYLE);
		
	}
	
	public BorderPane getBp() {
		return this.bp;
	}
	
	
}
