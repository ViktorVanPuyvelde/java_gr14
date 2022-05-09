package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import domein.Categorie;
import domein.CategorieController;
import domein.Sdg;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

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
		sdg_list = new ListView<>();
		
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
		id_lbl.setText(String.format("%d", categorie.getId()));
		naam_lbl.setText(categorie.getName());
		icon_lbl.setText(categorie.getIconName());

		List<Sdg> sdgs = FXCollections.observableArrayList(new ArrayList<>(controller.geefSdgsVoorCategorie(categorie)));
		sdgs.forEach(s -> sdg_list.getItems().add(s.toString()));
	}
}
