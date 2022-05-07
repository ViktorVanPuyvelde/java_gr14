package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import domein.Datasource;
import domein.DatasourceController;
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
	private ListView<String> lvDatasource;

	// lokale attributen
	private String name;
	private Sdg sdg;
	private ObservableList<String> type;
	private int doel;
	private Datasource datasource;
	private Boolean foutMeldingDoel = false;

	private ObservableList<Sdg> sdgItemList;
	private ObservableList<Datasource> datasourceItemList;

	private Foutmelding fm;

	public AanmakenMvoPaneelController()
	{
		this.mc = new MvoController();
		this.sc = new SdgController();
		this.dc = new DatasourceController();
		fm = new Foutmelding();
		buildGui();
		setSdgItemList();
		setDatasourceItemList();
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
		lvDatasource.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		sdgItemList.forEach(sdg -> lvSdg.getItems().add(sdg.getName()));
		datasourceItemList.forEach(d -> lvDatasource.getItems().add(d.getName()));
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
			foutMeldingDoel = true;
		} else
		{
			this.doel = Integer.parseInt(txtDoel.getText());
		}
		this.datasource = this.datasourceItemList.get(0);
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
		} else if (foutMeldingDoel)
		{
			fm.toonFoutmelding("Er moet een doel meegegeven worden");
			foutMeldingDoel = false;
		} else
		{
			update();
		}
	}

	private void update()
	{
		this.mc.voegMvoToe(name, sdg, type, doel, datasource);
	}

	private void setSdgItemList()
	{
		List<Sdg> sdgs = this.sc.geefSdgs();
		for (Sdg s : sdgs)
		{
			this.sdgItemList.add(s);
		}
	}

	private void setDatasourceItemList()
	{
		List<Datasource> datasources = this.dc.geefDatasources();
		for (Datasource d : datasources)
		{
			this.datasourceItemList.add(d);
		}
	}

}
