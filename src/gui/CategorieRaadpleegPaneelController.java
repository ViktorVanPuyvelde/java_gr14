package gui;

import java.io.IOException;
import domein.Categorie;
import domein.CategorieController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class CategorieRaadpleegPaneelController extends VBox {

	private Categorie categorie;
	private CategorieController controller;
	
	@FXML
	private Label id_lbl;
	@FXML
	private Label naam_lbl;
	@FXML
	private Label icon_lbl;
	@FXML
	private ListView<String> sdg_list;
	
	public CategorieRaadpleegPaneelController(Categorie c, CategorieController controller) {
		this.controller = controller;
		this.categorie = c;
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
	}
	
	private void initialize() {
		id_lbl.setText(categorie.getId());
		naam_lbl.setText(categorie.getName());
		icon_lbl.setText(categorie.getIconName());

	}

}
