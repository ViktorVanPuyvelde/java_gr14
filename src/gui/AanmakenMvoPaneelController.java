package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import domein.Aggregatie;
import domein.Datasource;
import domein.DatasourceController;
import domein.Mvo;
import domein.MvoController;
import domein.Sdg;
import domein.SdgController;
import exceptions.InformationRequiredException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AanmakenMvoPaneelController extends GridPane
{
	private MvoController mc;
	private SdgController sc;
	private DatasourceController dc;

	// FXML attributen
	@FXML
	private TextField txtMvoName;
	@FXML
	private ListView<String> lvSdg;
	@FXML
	private TextField txtType;
	@FXML
	private TextField txtDoel;
	@FXML
	private TextField txtData;
	@FXML
	private GridPane gpAanmakenMvo;
	@FXML
	private Node rowConstraint0;
	@FXML
	private ListView<String> lvSuperMvo;
	@FXML
	private Label lblErrorNietAangemaakt;
	@FXML
	private ChoiceBox<Aggregatie> aggregatieBox;

	// lokale attributen
	private String name;
	private Sdg sdg;
	private ObservableList<String> type;
	private int doel;
	private Datasource datasource;
	private Boolean foutMeldingDoel = false;
	private Mvo superMvo;
	private Aggregatie methode;

	private ObservableList<Sdg> sdgItemList;
	private ObservableList<Mvo> superMvoItemList;
	private ObservableList<Aggregatie> boxOptions;

	private Melding melding;

	public AanmakenMvoPaneelController()
	{
		this.mc = new MvoController();
		this.sc = new SdgController();

		melding = new Melding();
		buildGui();
		setSdgItemList();
		setSuperMvoItemList();
		initialize();
	}

	private void buildGui()
	{
		try
		{
			type = FXCollections.observableArrayList(new ArrayList<>());
			sdgItemList = FXCollections.observableArrayList(new ArrayList<>());

			superMvoItemList = FXCollections.observableArrayList(new ArrayList<>());
			boxOptions = FXCollections.observableArrayList(Aggregatie.values());
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MvoAanmakenPaneel.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
		} catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}

	private void initialize()
	{
		lvSdg.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		lvSuperMvo.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		sdgItemList.forEach(sdg -> lvSdg.getItems().add(sdg.getName()));

		superMvoItemList.forEach(m -> lvSuperMvo.getItems().add(m.getName()));
		
		aggregatieBox.getItems().setAll(boxOptions);
	}

	@FXML
	public void btnMaakMvo(ActionEvent event)
	{
		collectChanges();
		verify();
	}

	private void collectChanges()
	{
		try
		{
			this.name = txtMvoName.getText();
			this.sdg = this.sc.geefSdgDoorNaam(this.lvSdg.getSelectionModel().getSelectedItem());
			this.type.add(txtType.getText());
			if (txtDoel.getText() == null || txtDoel.getText().isEmpty())
			{
				foutMeldingDoel = true;
			} else
			{
				this.doel = Integer.parseInt(txtDoel.getText());
			}

			this.superMvo = this.mc.geefMvoMetNaam(this.lvSuperMvo.getSelectionModel().getSelectedItem());
			
		} catch (EntityNotFoundException e)
		{
			System.out.println("error");
		}
	}

	private void verify()
	{
		update();
	}

	private void update()
	{
		try
		{
			this.mc.voegMvoToe(name, sdg, type, doel, datasource, superMvo);
			melding.toonBevestiging("De MVO is aangemaakt.");
		} catch (InformationRequiredException e)
		{
			lblErrorNietAangemaakt.setText(e.getMessage());
			e.getInformationRequired().forEach(System.out::println);
		}
	}

	private void setSdgItemList()
	{
		List<Sdg> sdgs = this.sc.geefSdgs();
		for (Sdg s : sdgs)
			this.sdgItemList.add(s);
	}


	private void setSuperMvoItemList()
	{
		List<Mvo> superMvos = this.mc.geefMvos();
		for (Mvo m : superMvos)
			this.superMvoItemList.add(m);
	}
}
