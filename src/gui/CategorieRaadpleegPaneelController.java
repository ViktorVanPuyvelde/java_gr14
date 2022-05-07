package gui;

import java.io.IOException;
import java.util.ArrayList;

import domein.Categorie;
import domein.DomeinController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;

public class CategorieRaadpleegPaneelController extends GridPane
{
	@FXML
	private RowConstraints firstRowGrid;

	private DomeinController dc;
	private ObservableList<Categorie> cats;
	private int rij;

	public CategorieRaadpleegPaneelController(DomeinController dc)
	{
		this.dc = dc;
		this.rij = 2;
		buildGui();
		initialize();
	}

	private void buildGui()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("CategorieRaadpleegPaneel.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
		} catch (IOException ex)
		{
			throw new RuntimeException(ex);
		}
		this.setVgap(25);
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(10, 10, 10, 10));
		firstRowGrid.setMaxHeight(15);
		firstRowGrid.setMinHeight(15);
	}

	private void initialize()
	{
		cats = FXCollections.observableArrayList(new ArrayList(dc.geefAlleCategories()));

		cats.stream().forEach(c ->
		{

			Label id = new Label(c.getId());
			Label naam = new Label(c.getName());
			Label icon = new Label(c.getIconName());
			id.setFont(Font.font(14));
			naam.setFont(Font.font(14));
			icon.setFont(Font.font(14));

			GridPane.setConstraints(id, 0, rij);
			GridPane.setConstraints(naam, 1, rij);
			GridPane.setConstraints(icon, 2, rij);

			Button btn = new Button("Raadplegen");
			GridPane.setConstraints(btn, 3, rij);

			this.getChildren().addAll(id, naam, icon, btn);
			rij = rij + 1;
		});

	}

}
