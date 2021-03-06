package gui;

import javafx.event.ActionEvent;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import com.auth0.exception.APIException;
import com.auth0.exception.Auth0Exception;

import domein.UserController;
import exceptions.IngelogdVerkeerdeRol;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AanmeldPaneelController extends AnchorPane{
	
	private UserController userController;
	
	@FXML
	private TextField gebruikersnaam;
	
	@FXML
	private PasswordField wachtwoord;
	
	@FXML
	private Label wrongLogIn;
	
	@FXML 
	private Button inlogButton;
	

	public AanmeldPaneelController() {
		this.userController = new UserController();
		buildGui();
		initialize();
	}
	

	private void buildGui() {
		try{
						
			FXMLLoader loader = new FXMLLoader(getClass().getResource("AanmeldPaneel.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
			
			} 
		catch(IOException ex)
		{
		throw new RuntimeException(ex);
		}
	}
	
	private void initialize() {
		gebruikersnaam.setText("admin@test.be");	
		wachtwoord.setText("Azertyuiop.123");
	}
	
	
	@FXML
	public void inloggen(ActionEvent event) throws APIException, Auth0Exception {
		try {
			userController.meldAan(gebruikersnaam.getText(), wachtwoord.getText());
			SideBarController ns = new SideBarController(userController);
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Scene scene = new Scene (ns);
			Stage stage = (Stage) this.getScene().getWindow();
			scene.getStylesheets().add(getClass().getResource("Styles.css").toExternalForm());
			stage.setScene(scene);
			stage.setWidth(screenSize.getWidth());
			stage.setHeight(screenSize.getHeight());
			stage.show();
		}catch(IngelogdVerkeerdeRol e) {
			wrongLogIn.setText(e.getMessage());
		}catch(Auth0Exception a) {
			wrongLogIn.setText("Foute gebruikersnaam en/of wachtwoord");
		}
	}

}
