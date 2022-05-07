package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import domein.Datasource;
import domein.MvoController;
import domein.Sdg;
import domein.SdgController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AanmakenMvoPaneelController extends GridPane
{
	private MvoController mc;
	private SdgController sc;

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

	// lokale attributen
	private String name;
	private Sdg sdg;
	private ObservableList<String> type;
	private int doel;
	private Datasource datasource;

	private ObservableList<Sdg> sdgItemList;
	private ObservableList<Datasource> datasourceItemList;

	private Foutmelding fm;

	public AanmakenMvoPaneelController()
	{
		this.mc = new MvoController();
		this.sc = new SdgController();
		fm = new Foutmelding();
		buildGui();
		setSdgItemList();
		initialize();
	}

	private void buildGui()
	{
		try
		{
			type = FXCollections.observableArrayList(new ArrayList());
			sdgItemList = FXCollections.observableArrayList(new ArrayList());
			datasourceItemList = FXCollections.observableArrayList(new ArrayList());
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

		sdgItemList.forEach(sdg -> lvSdg.getItems().add(sdg.getName()));
	}

	@FXML
	public void btnMaakMvo(ActionEvent event)
	{
		collectChanges();
		verify();
	}

	private void collectChanges()
	{
		this.name = txtMvoName.getText();
		this.sdg = this.sdgItemList.get(0);
		this.type.add(txtType.getText());
		if (txtDoel.getText() == null || txtDoel.getText().isEmpty())
		{
			fm.toonFoutmelding("Er moet een doel meegegeven worden aan de nieuwe MVO.");
		} else
		{
			this.doel = Integer.parseInt(txtDoel.getText());
		}
//		this.datasource = txtData.getText();
	}

	private void verify()
	{
		if (name == null || name.isEmpty())
		{
			fm.toonFoutmelding("Naam mag niet leeg zijn.");
		} else if (sdg == null)
		{
			fm.toonFoutmelding("Er moet een SDG toegewezen zijn aan de nieuwe MVO.");
		} else if (type == null || type.isEmpty())
		{
			fm.toonFoutmelding("Type mag niet leeg zijn");
		} else if (datasource == null)
		{
			fm.toonFoutmelding("Er moet een datasource meegegeven worden ");
		} else
		{
			update();
		}
	}

	private void update()
	{
		this.mc.voegMvoToe(name, sdg, type, doel, datasource);
	}

	public void setSdgItemList()
	{
		List<Sdg> sdgs = this.sc.geefSdgs();
		for (Sdg s : sdgs)
		{
			this.sdgItemList.add(s);
		}
	}

}
