package gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import domein.Categorie;
import domein.CategorieController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

public class CategoriePaneelController extends HBox implements PropertyChangeListener
{
	@FXML
	private Button createCategorie_btn;
	@FXML
	private ListView<String> categorie_List;
	@FXML
	private Button viewCategorie_btn;
	@FXML
	private Button editCategorie_btn;
	@FXML
	private Button deleteCategorie_btn;
	@FXML
	private Label catSelecteren_lbl;
	@FXML
	private Label cat_create_update_lbl;
	@FXML
	private HBox hBox;

	private CategorieController catController;
	private ObservableList<Categorie> catItemList;
	private boolean rechterSchermAanwezig = false;

	public CategoriePaneelController()
	{
		this.catController = new CategorieController();
		buildGui();
		setCategorieList();
		catController.addPropertyChangeListener(this);
	}

	private void initialize()
	{
		catItemList.forEach(c -> categorie_List.getItems().add(c.getName()));
	}

	private void buildGui()
	{

		try
		{
			categorie_List = new ListView<>();

			catItemList = FXCollections.observableArrayList(new ArrayList<>());

			FXMLLoader loader = new FXMLLoader(getClass().getResource("CategoriePaneel.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
		} catch (IOException e)
		{	
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}

	}

	private void setCategorieList()
	{
		List<Categorie> cats = catController.geefCategorien();
		for (Categorie c : cats)
		{
			this.catItemList.add(c);
		}
	}

	// Event Listener on Button[#createCategorie_btn].onAction
	@FXML
	public void createCategorie_OnAction(ActionEvent event)
	{
		catSelecteren_lbl.setText("");
		cat_create_update_lbl.setText("");
		if (rechterSchermAanwezig)
		{
			verwijderRechterScherm();
		}
		categorie_List.getSelectionModel().clearSelection();
		System.out.println("hier");
		CategorieAanmakenEnWijzigenPaneelController catAanmakenPaneel = new CategorieAanmakenEnWijzigenPaneelController(
				null, catController, false);
		this.getChildren().add(catAanmakenPaneel);
		rechterSchermAanwezig = true;
	}

	// Event Listener on Button[#raadpleegCategorie_btn].onAction
	@FXML
	public void raadplegenCategorie_OnAction(ActionEvent event)
	{
		catSelecteren_lbl.setText("");
		cat_create_update_lbl.setText("");
		if (rechterSchermAanwezig)
		{
			verwijderRechterScherm();
		}
		String naam = categorie_List.getSelectionModel().getSelectedItem();
		Categorie c = catItemList.stream().filter(cat -> cat.getName().equals(naam)).findAny().orElse(null);
		if (c != null)
		{
			CategorieRaadpleegPaneelController root = new CategorieRaadpleegPaneelController(c, catController);
			this.getChildren().add(root);
			rechterSchermAanwezig = true;
		} else
		{
			catSelecteren_lbl.setText("Gelieve eerst een categorie te selecteren!");
		}
	}

	// Event Listener on Button[#editCategorie_btn].onAction
	@FXML
	public void editCategorie_OnAction(ActionEvent event)
	{
		cat_create_update_lbl.setText("");
		catSelecteren_lbl.setText("");
		if (rechterSchermAanwezig)
		{
			verwijderRechterScherm();
		}

		String naam = categorie_List.getSelectionModel().getSelectedItem();
		Categorie c = catItemList.stream().filter(cat -> cat.getName().equals(naam)).findAny().orElse(null);

		if (c != null)
		{
			CategorieAanmakenEnWijzigenPaneelController controller = new CategorieAanmakenEnWijzigenPaneelController(c,
					this.catController, true);
			this.getChildren().add(controller);
			rechterSchermAanwezig = true;
		} else
		{
			catSelecteren_lbl.setText("Gelieve eerst een categorie te selecteren!");
		}
	}

	// Event Listener on Button[#deleteCategorie_btn].onAction
	@FXML
	public void deleteCategorie_OnAction(ActionEvent event)
	{
		cat_create_update_lbl.setText("");
		catSelecteren_lbl.setText("");
		if (rechterSchermAanwezig)
		{
			verwijderRechterScherm();
		}

		String naam = categorie_List.getSelectionModel().getSelectedItem();
		Categorie c = catItemList.stream().filter(cat -> cat.getName().equals(naam)).findAny().orElse(null);

		if (c != null)
		{
			CategorieVerwijderenPaneelController controller = new CategorieVerwijderenPaneelController(c,
					this.catController);
			this.getChildren().add(controller);
			rechterSchermAanwezig = true;
		} else
		{
			catSelecteren_lbl.setText("Gelieve eerst een categorie te selecteren!");
		}
	}

	private void verwijderRechterScherm()
	{
		this.getChildren().remove(this.getChildren().size() - 1);
		rechterSchermAanwezig = false;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt)
	{
		if (rechterSchermAanwezig)
		{
			verwijderRechterScherm();
		}
		int newValue = (int) evt.getNewValue();
		if (newValue == 1)
		{
			cat_create_update_lbl.setText("Categorie is met succes aangemaakt!");
		} else if (newValue == 2)
		{
			cat_create_update_lbl.setText("Categorie is met succes gewijzigd!");
		} else if (newValue == 3)
		{
			cat_create_update_lbl.setText("Categorie is met succes verwijderd!");
		} else
		{
			cat_create_update_lbl.setText("");
		}

		catItemList = FXCollections.observableArrayList(catController.geefCategorien());
		categorie_List.getItems().clear();
		initialize();
	}
}
