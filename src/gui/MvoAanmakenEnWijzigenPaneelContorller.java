package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.TreeMap;

import javax.persistence.EntityNotFoundException;

import domein.Aggregatie;
import domein.Datasource;
import domein.DatasourceController;
import domein.Mvo;
import domein.MvoController;
import domein.MvoData;
import domein.Sdg;
import domein.SdgController;
import exceptions.InformationRequiredException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class MvoAanmakenEnWijzigenPaneelContorller extends GridPane
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
	private Label lblErrorNaam;
	@FXML
	private Label lblErrorSdg;
	@FXML
	private Label lblErrorDoel;
	@FXML
	private Label lblErrorDatabron;
	@FXML
	private Label lblErrorEenheid;
	@FXML
	private Label lblTop;

	// lokale attributen
	private String name;
	private Sdg sdg;
	private ObservableList<String> type;
	private int doel;
	private Datasource datasource;
	private Boolean foutMeldingDoel = false;
	private Mvo superMvo;

	private Mvo selectedMvo;

	private ObservableList<Sdg> sdgItemList;
	private ObservableList<Mvo> superMvoItemList;
	

	private Melding melding;

	private boolean wijzigen;

	public MvoAanmakenEnWijzigenPaneelContorller(boolean wijzigen, Mvo selectedMvo2)
	{
		this.mc = new MvoController();
		this.sc = new SdgController();

		melding = new Melding();
		this.wijzigen = wijzigen;
		this.selectedMvo = selectedMvo2;
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
			if (wijzigen)
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

		if (wijzigen)
			aggregatieBox.getItems().setAll(boxOptions);

		if (wijzigen)
		{
			lblTop.setText("MVO aanpassen");
			txtMvoName.setText(selectedMvo.getName());

			txtDoel.setText(selectedMvo.getGoalValue() + "");

			txtType.setText(selectedMvo.getInfo());

			lvDatasource.getSelectionModel().select(selectedMvo.getDatasource().getName());

			lvSdg.getSelectionModel().select(selectedMvo.getSdg().getName());

			if (selectedMvo.getSuperMvo() != null)
			{
				lvSuperMvo.getSelectionModel().select(selectedMvo.getSuperMvo().getName());
			}
		}
	}

	@FXML
	public void btnMaakMvo(ActionEvent event)
	{
		if (wijzigen)
			collectChangesWijzigen();
		else
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

	private void collectChangesWijzigen()
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

		if (lvSuperMvo.getSelectionModel().getSelectedItem() != null)
		{
			List<String> superMvoNamen = this.superMvoItemList.stream().map(Mvo::getName).collect(Collectors.toList());
			int indexSuperMvo = superMvoNamen.indexOf(lvSuperMvo.getSelectionModel().getSelectedItem());
			if (indexSuperMvo != -1)
			{
				this.superMvo = this.superMvoItemList.get(indexSuperMvo);
			}
		} else
		{
			this.superMvo = null;
		}

		List<String> datasourceNamen = this.datasourceItemList.stream().map(Datasource::getName)
				.collect(Collectors.toList());
		int indexDatasource = datasourceNamen.indexOf(lvDatasource.getSelectionModel().getSelectedItem());
		if (indexDatasource != -1)
		{
			this.datasource = this.datasourceItemList.get(indexDatasource);
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
			if (wijzigen)
				this.mc.update(selectedMvo);
			else
				this.mc.voegMvoToe(name, sdg, type, doel, datasource, superMvo);
			melding.toonBevestiging("De MVO is aangemaakt.");
		} catch (InformationRequiredException e)
		{
			informationRequiredExceptionHandling(e);
		}
	}

	private void informationRequiredExceptionHandling(InformationRequiredException e)
	{
		lblErrorNietAangemaakt.setText(e.getMessage());
		errorLabelsOpvullen(e.getErrorMap());
	}

	private void errorLabelsOpvullen(Map<String, String> errorMap)
	{
		errorMap.entrySet().forEach(entry -> overlopenLabels(entry.getKey(), entry.getValue()));
	}

	private void overlopenLabels(String key, String value)
	{
		checkLabel(lblErrorNaam, key, value);
		checkLabel(lblErrorDoel, key, value);
		checkLabel(lblErrorDatabron, key, value);
		checkLabel(lblErrorSdg, key, value);
		checkLabel(lblErrorEenheid, key, value);
	}

	private void checkLabel(Label label, String key, String value)
	{
		if (label.getId().equals(key))
			label.setText(value);
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
