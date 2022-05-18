package gui;

import java.io.IOException;

import domein.Categorie;
import domein.CategorieController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class CategorieVerwijderenPaneelController extends AnchorPane
{
	private Categorie c;
	private CategorieController categorieController;
	private Melding melding = new Melding();

	@FXML
	private Label lblTitel;
	@FXML
	private Label lblQuestion;

	public CategorieVerwijderenPaneelController(Categorie c, CategorieController controller)
	{
		this.c = c;
		this.categorieController = controller;
		buildGui();
		initialize();
	}

	private void buildGui()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("CategorieVerwijderenPaneel.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
		} catch (IOException ex)
		{
			throw new RuntimeException(ex);
		}
	}

	private void initialize()
	{
		this.lblQuestion
				.setText(String.format("Weet u zeker dat u de categorie \"%s\" wilt verwijderen?", this.c.getName()));
	}

	@FXML
	public void btnConfirmDeleteAction(ActionEvent event)
	{
		try
		{
			String naam = c.getName();
			this.categorieController.verwijderCategorie(c);
			this.melding.toonBevestiging(String.format("De categorie \"%s\" is verwijderd.", naam));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
