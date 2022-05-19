package gui;

import java.io.IOException;
import java.util.List;

import domein.Categorie;
import domein.CategorieController;
import domein.Sdg;
import domein.SdgController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class CategorieVerwijderenPaneelController extends AnchorPane
{
	private Categorie c;
	private CategorieController categorieController;
	private SdgController sdgController;
	private Melding melding = new Melding();

	@FXML
	private Label lblTitel;
	@FXML
	private Label lblQuestion;

	public CategorieVerwijderenPaneelController(Categorie c, CategorieController controller)
	{
		this.c = c;
		this.categorieController = controller;
		this.sdgController = new SdgController();
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
		List<Sdg> backup = this.c.getSdgs();
		try
		{
			ontkoppelSdg();
			String naam = c.getName();
			this.categorieController.verwijderCategorie(c);
			this.melding.toonBevestiging(String.format("De categorie \"%s\" is verwijderd.", naam));
		} catch (Exception e)
		{
			e.printStackTrace();
			if (this.c != null)
			{
				koppelSdgBackup(backup);
			}
		}
	}

	private void ontkoppelSdg()
	{
		this.c.getSdgs().forEach(s -> this.sdgController.updateCategorieIdSdg(s.getId(), null));
		this.c.setSdgs(null);
	}

	private void koppelSdgBackup(List<Sdg> backup)
	{
		this.c.setSdgs(backup);
		this.c.getSdgs().forEach(s -> this.sdgController.updateCategorieIdSdg(s.getId(), this.c.getId()));
	}
}
