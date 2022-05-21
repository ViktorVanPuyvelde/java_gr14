package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domein.Datasource;
import domein.DatasourceController;
import domein.Mvo;
import domein.MvoController;
import domein.Sdg;
import domein.SdgController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class MvoWijzigenPaneelController extends GridPane{
	
    @FXML
    private GridPane gpAanmakenMvo;

    @FXML
    private Label lblData;

    @FXML
    private Label lblDoel;

    @FXML
    private Label lblMvoName;

    @FXML
    private Label lblSdg;

    @FXML
    private Label lblSuperMvo;

    @FXML
    private Label lblType;

    @FXML
    private ListView<String> lvDatasource;

    @FXML
    private ListView<String> lvSdg;

    @FXML
    private ListView<String> lvSuperMvo;

    @FXML
    private TextField txtDoel;

    @FXML
    private TextField txtMvoName;

    @FXML
    private TextField txtType;

    @FXML
    void btnMaakMvo(ActionEvent event) {
		collectChanges();
		verify();
    }
    
    
	// lokale attributen
	private String name;
	private Sdg sdg;
	private List<String> type;
	private int doel;
	private Datasource datasource;
	private Boolean foutMeldingDoel = false;
	private Mvo superMvo;

	private ObservableList<Sdg> sdgItemList;
	private ObservableList<Datasource> datasourceItemList;
	private ObservableList<Mvo> superMvoItemList;
	
	private MvoController mc;
	private SdgController sc;
	private DatasourceController dc;
	
	private Melding melding;
    private Mvo selectedMvo;

	
	public MvoWijzigenPaneelController(Mvo mvo) {
		this.selectedMvo=mvo;
		this.mc = new MvoController();
		this.sc = new SdgController();
		this.dc = new DatasourceController();
		melding = new Melding();
		buildGui();
		setSdgItemList();
		setDatasourceItemList();
		setSuperMvoItemList();
		initialize();
	}
	
	private void buildGui()
	{
		try
		{
			type = FXCollections.observableArrayList(new ArrayList<>());
			sdgItemList = FXCollections.observableArrayList(new ArrayList<>());
			datasourceItemList = FXCollections.observableArrayList(new ArrayList<>());
			superMvoItemList = FXCollections.observableArrayList(new ArrayList<>());
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MvoWijzigenPaneel.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
		} catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	private void initialize() {
		
		lvSdg.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		lvDatasource.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		lvSuperMvo.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		sdgItemList.forEach(sdg -> lvSdg.getItems().addAll(sdg.getName()));
		datasourceItemList.forEach(d -> lvDatasource.getItems().add(d.getName()));
		superMvoItemList.forEach(m -> lvSuperMvo.getItems().add(m.getName()));
		
		txtMvoName.setText(selectedMvo.getName());
		
		txtDoel.setText(selectedMvo.getGoalValue()+"");
		
		txtType.setText(selectedMvo.getInfo());
		
		lvDatasource.getSelectionModel().select(selectedMvo.getDatasource().getName());
		
		lvSdg.getSelectionModel().select(selectedMvo.getSdg().getName());
		
		if(selectedMvo.getSuperMvo() != null) {
			lvSuperMvo.getSelectionModel().select(selectedMvo.getSuperMvo().getName());
		}
		
	}
	
	private void collectChanges()
	{
		this.name = txtMvoName.getText();
		
		List<String> sdgNamen = this.sdgItemList.stream().map(Sdg::getName).collect(Collectors.toList());
		int indexSdg = sdgNamen.indexOf(lvSdg.getSelectionModel().getSelectedItem());
		this.sdg = this.sdgItemList.get(indexSdg);
		
		this.type.add(txtType.getText());
		
		if (txtDoel.getText() == null || txtDoel.getText().isEmpty())
		{
			foutMeldingDoel = true;
		} else
		{
			this.doel = Integer.parseInt(txtDoel.getText());
		}
		
		if(lvSuperMvo.getSelectionModel().getSelectedItem() != null) {
			List<String> superMvoNamen = this.superMvoItemList.stream().map(Mvo::getName).collect(Collectors.toList());
			int indexSuperMvo = superMvoNamen.indexOf(lvSuperMvo.getSelectionModel().getSelectedItem());
			if(indexSuperMvo != -1) {
				this.superMvo = this.superMvoItemList.get(indexSuperMvo);
			}
		}else {
			this.superMvo=null;
		}

		
		List<String> datasourceNamen = this.datasourceItemList.stream().map(Datasource::getName).collect(Collectors.toList());
		int indexDatasource = datasourceNamen.indexOf(lvDatasource.getSelectionModel().getSelectedItem());
		if(indexDatasource != -1) {
			this.datasource = this.datasourceItemList.get(indexDatasource);
		}
	}

	private void verify()
	{
		if (name == null || name.isEmpty())
		{
			melding.toonFoutmelding("Naam mag niet leeg zijn.");
		} else if (sdg == null)
		{
			melding.toonFoutmelding("Er moet een SDG toegewezen zijn aan de nieuwe MVO.");
		} else if (type == null || type.isEmpty())
		{
			melding.toonFoutmelding("Type mag niet leeg zijn");
		} else if (datasource == null)
		{
			melding.toonFoutmelding("Er moet een datasource meegegeven worden ");
		} else if (foutMeldingDoel)
		{
			melding.toonFoutmelding("Er moet een doel meegegeven worden");
			foutMeldingDoel = false;
		} else
		{
			update();
		}
	}

	private void update()
	{
		selectedMvo.setName(name);
		selectedMvo.setSuperMvo(superMvo);
		selectedMvo.setSdg(sdg);
		selectedMvo.setGoalValue(doel);
		selectedMvo.setDatasource(datasource);
		selectedMvo.setInfo(type);
		mc.update(selectedMvo);
		melding.toonBevestiging("De MVO is bijgewerkt.");
	}
	
	private void setSdgItemList()
	{
		List<Sdg> sdgs = this.sc.geefSdgs();
		for (Sdg s : sdgs)
			this.sdgItemList.add(s);
	}

	private void setDatasourceItemList()
	{
		List<Datasource> datasources = this.dc.geefDatasources();
		for (Datasource d : datasources)
			this.datasourceItemList.add(d);
	}

	private void setSuperMvoItemList()
	{
		List<Mvo> superMvos = this.mc.geefMvos();
		for (Mvo m : superMvos)
			this.superMvoItemList.add(m);
	}

}
