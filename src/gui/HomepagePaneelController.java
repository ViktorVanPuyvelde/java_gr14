package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import domein.Categorie;
import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import repository.CategorieDaoJpa;
import repository.GenericDaoJpa;

public class HomepagePaneelController extends AnchorPane
{
	private DomeinController dc;

	@FXML
	private Button aanmelden_btn;
	@FXML
	private Button categorieAanmeken_btn;
	@FXML
	private Button datasourcesRaadplegen_btn;

	public HomepagePaneelController(DomeinController dc)
	{
		this.dc = dc;
		buildGui();
	}

	private void buildGui()
	{
		try
		{

			FXMLLoader loader = new FXMLLoader(getClass().getResource("HomepagePaneel.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();

		} catch (IOException ex)
		{
			throw new RuntimeException(ex);
		}
	}
	
	private void populateDB() {
			CategorieDaoJpa cj = new CategorieDaoJpa();
			CategorieDaoJpa.startTransaction();
			
			List<String> rollen = new ArrayList<>();
			
			rollen.add("gebruiker");
			
			Categorie c = new Categorie("Profit", "Aap", rollen, true);
			Categorie c1 = new Categorie("Planet", "Aap", rollen, true);
			Categorie c2 = new Categorie("People", "Aap", rollen, true);
			
			CategorieDaoJpa.commitTransaction();
			
			
	}

	@FXML
	public void aanmelden_onAction(ActionEvent event)
	{
		AanmeldPaneelController ns = new AanmeldPaneelController(dc);
		Scene scene = new Scene(ns);
		Stage stage = (Stage) this.getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	public void categorieAanmaken_onAction(ActionEvent event) {
		CategoriePaneelController ns = new CategoriePaneelController();
		Scene scene = new Scene (ns);
		Stage stage = (Stage) this.getScene().getWindow();
		stage.setScene(scene);
//		stage.setMaximized(true);
		stage.show();
	}

	@FXML
	public void datasourcesRaadplegen_onAction(ActionEvent event)
	{

	}

}
