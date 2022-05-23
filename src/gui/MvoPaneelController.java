package gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import domein.CategorieController;
import domein.Mvo;
import domein.MvoController;
import domein.SdgController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

public class MvoPaneelController extends HBox implements PropertyChangeListener
{

	/*
	 * 
	 * FXML variabelen
	 * 
	 */

	@FXML
	private ListView<String> MvoCatListView;

	@FXML
	private ListView<String> MvoListView;

	@FXML
	private Button createMVO_btn;

	@FXML
	private Button deleteMVO_btn;

	@FXML
	private Button editMVO_btn;

	@FXML
	private Button viewMVO_btn;

	@FXML
	private Label mvo_Selecteren_lbl;
	
	@FXML
	private Label mvo_create_update_lbl;

	private boolean rechterSchermAanwezig = false;

	/*
	 * 
	 * LOKALE VARIABELEN
	 */

	private CategorieController cc;
	private MvoController mc;
	private SdgController sc;
	private String selectedCategory;
	private Mvo selectedMvo;
	private List<Mvo> mvosVanCategorie;
	private Melding melding;

	/*
	 * 
	 * FXML onAction functies
	 */

	@FXML
	void createMVO_OnAction(ActionEvent event)
	{
		mvo_Selecteren_lbl.setText("");
		if (rechterSchermAanwezig)
		{
			verwijderRechterScherm();
		}
		MvoAanmakenEnWijzigenPaneelContorller aanmakenMVOPaneel = new MvoAanmakenEnWijzigenPaneelContorller(false,
				null);
		this.getChildren().add(aanmakenMVOPaneel);
		rechterSchermAanwezig = true;
	}

	@FXML
	void deleteMVO_OnAction(ActionEvent event)
	{
		mvo_Selecteren_lbl.setText("");
		if (rechterSchermAanwezig)
		{
			verwijderRechterScherm();
		}
		if (MvoListView.getSelectionModel().getSelectedItem() != null)
		{
			MvoVerwijderenPaneelController root = new MvoVerwijderenPaneelController(selectedMvo);
			this.getChildren().add(root);
			rechterSchermAanwezig = true;
		} else
		{
			mvo_Selecteren_lbl.setText("Gelieve eerst een MVO te selecteren!");
		}
	}

	@FXML
	void editMVO_OnAction(ActionEvent event)
	{
		mvo_Selecteren_lbl.setText("");
		if (rechterSchermAanwezig)
		{
			verwijderRechterScherm();
		}
		if (MvoListView.getSelectionModel().getSelectedItem() != null)
		{
			MvoAanmakenEnWijzigenPaneelContorller wijzigenMVOPaneel = new MvoAanmakenEnWijzigenPaneelContorller(true,
					selectedMvo);
			this.getChildren().add(wijzigenMVOPaneel);
			rechterSchermAanwezig = true;
		} else
		{
			mvo_Selecteren_lbl.setText("Gelieve eerst een MVO te selecteren!");
		}
	}

	@FXML
	void raadplegenMVO_OnAction(ActionEvent event)
	{
		mvo_Selecteren_lbl.setText("");
		if (rechterSchermAanwezig)
		{
			verwijderRechterScherm();
		}
		if (MvoListView.getSelectionModel().getSelectedItem() != null)
		{
			MvoRaadplegenPaneelController raadplegenMvoPaneel = new MvoRaadplegenPaneelController(selectedMvo);
			this.getChildren().add(raadplegenMvoPaneel);
			rechterSchermAanwezig = true;
		} else
		{
			mvo_Selecteren_lbl.setText("Gelieve eerst een MVO te selecteren!");
		}
	}

	public MvoPaneelController()
	{
		cc = new CategorieController();
		mc = new MvoController();
		sc = new SdgController();
		melding = new Melding();
		buildGui();
		//initialize();
		mc.addPropertyChangeListener(this);
	}

	private void buildGui()
	{
		try
		{

			FXMLLoader loader = new FXMLLoader(getClass().getResource("MvoPaneel.fxml"));
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

		MvoCatListView.getItems().add("*");
		MvoCatListView.getItems().addAll(cc.geefAlleEchteCategorienNaam());

		MvoCatListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
		{

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2)
			{

				selectedCategory = MvoCatListView.getSelectionModel().getSelectedItem();

				System.out.println(selectedCategory);

				if (selectedCategory != "*")
				{
					mvosVanCategorie = mc.geefMvosVanCategorie(selectedCategory);
				} else
				{
					mvosVanCategorie = mc.geefMvos();
				}

				MvoListView.getItems().clear();

				MvoListView.getItems().addAll(mvosVanCategorie.stream().map(Mvo::getName).collect(Collectors.toList()));
			}

		});

		MvoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
		{

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2)
			{

				String Mvo = MvoListView.getSelectionModel().getSelectedItem();

				if (Mvo != null)
				{
					selectedMvo = mc.geefMvoMetNaam(Mvo);
				}

			}

		});

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
			mvo_create_update_lbl.setText("MVO met succes aangemaakt!");
		} else if (newValue == 2)
		{
			mvo_create_update_lbl.setText("MVO met succes gewijzigd!");
		} else if (newValue == 3)
		{
			mvo_create_update_lbl.setText("MVO met succes verwijderd!");
		} else
		{
			mvo_create_update_lbl.setText("");
		}

		MvoCatListView.getItems().clear();
		MvoListView.getItems().clear();
		initialize();
	}


}
