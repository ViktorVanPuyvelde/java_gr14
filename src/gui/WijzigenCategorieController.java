package gui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;

import domein.Categorie;
import domein.CategorieController;
import domein.Sdg;
import domein.SdgController;
import exceptions.InformationRequiredException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class WijzigenCategorieController extends GridPane
{
	private Categorie c;
	private CategorieController categorieController;
	private SdgController sdgController;

	@FXML
	private Label topLabel;
	@FXML
	private TextField cat_Name_field;
	@FXML
	private TextField cat_Pictogram_field;
	@FXML
	private ListView<String> cat_Sdg_List;
	@FXML
	private ListView<String> cat_Rol_List;
	@FXML
	private Button cat_save_btn;
	@FXML
	private Hyperlink linkIcons;
	@FXML
	private Label lblErrorLabel;

	private String name;
	private ObservableList<String> sdg;
	private ObservableList<String> rol;
	private String pic;
	private List<Sdg> sdgs;

	private ObservableList<Sdg> sdgItemList;
	private ObservableList<String> rolItemList;

	public WijzigenCategorieController(Categorie c, CategorieController catController)
	{
		this.c = c;
		this.categorieController = catController;
		this.sdgController = new SdgController();
		buildGui();
		setSdgItemList();
		initialize();
	}

	private void setSdgItemList()
	{
		List<Sdg> sdgs = this.sdgController.geefSdgs();
		for (Sdg s : sdgs)
		{
			this.sdgItemList.add(s);
		}
	}

	private void buildGui()
	{
		try
		{
			cat_Sdg_List = new ListView<>();
			cat_Rol_List = new ListView<>();
			sdgItemList = FXCollections.observableArrayList(new ArrayList<>());
			rolItemList = FXCollections.observableArrayList(new ArrayList<>());
			linkIcons = new Hyperlink();

			FXMLLoader loader = new FXMLLoader(getClass().getResource("CategorieAanmakenPaneel.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initialize()
	{
		instellenHyperLink();

		cat_Sdg_List.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		cat_Rol_List.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		rolItemList.add("gebruiker");
		rolItemList.add("directie");
		rolItemList.add("manager");
		rolItemList.add("co\u00f6rdinator");

		// fill with Sdg's
		sdgItemList.forEach(sdg -> cat_Sdg_List.getItems().add(sdg.getName()));

		// fill with Roles
		cat_Rol_List.setItems(rolItemList);

		topLabel.setText("Categorie aanpassen");
		this.cat_Name_field.setText(c.getName());
		this.cat_Pictogram_field.setText(c.getIconName());
		this.cat_save_btn.setText("Categorie aanpassen");
		selectDefault(this.c.getSdgs().stream().map(Sdg::getName).toList(), cat_Sdg_List);

		Gson gson = new Gson();
		List<String> rollen = gson.fromJson(this.c.getRoles(), List.class);
		selectDefault(rollen, cat_Rol_List);
	}

	private ListView<String> selectDefault(List<String> list, ListView<String> listView)
	{
		List<Integer> indexes = new ArrayList<>();
		for (String rol : list)
		{
			for (int i = 0; i < listView.getItems().size(); i++)
			{
				if (rol.equals(listView.getItems().get(i)))
				{
					indexes.add(i);
				}
			}
		}

		for (int i : indexes)
		{
			listView.getSelectionModel().select(i);
		}

		return listView;
	}

	@FXML
	public void btnCatSaveOnAction(ActionEvent event)
	{
		collectChanges();
		verify();
	}

	private void collectChanges()
	{
		try
		{
			this.c.setName(this.cat_Name_field.getText());
			this.c.setIconName(this.cat_Pictogram_field.getText());
			sdg = cat_Sdg_List.getSelectionModel().getSelectedItems();
			rol = cat_Rol_List.getSelectionModel().getSelectedItems();

			this.c.setRoles(rol.stream().toList());
			setSdgs(sdg);
			this.c.setSdgs(sdgs);
		} catch (Exception e)
		{
			System.out.println("error categorie");
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
			categorieController.pasCategorieAan(c);
			toonBevestiging("Categorie is met succes aangepast");
		} catch (InformationRequiredException e)
		{
			lblErrorLabel.setText(e.getMessage());
			e.getInformationRequired().forEach(System.out::println);
		}
	}

	private void setSdgs(ObservableList<String> sdg2)
	{
		List<Sdg> sdgDummy = new ArrayList<>();
		for (String s : sdg2)
		{
			sdgDummy.add(this.sdgController.geefSdgDoorNaam(s));
		}

		this.sdgs = sdgDummy;
	}

	private void instellenHyperLink()
	{
		linkIcons.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent arg0)
			{
				try
				{
					Desktop.getDesktop().browse(new URI(linkIcons.getText()));
				} catch (IOException e)
				{
					e.printStackTrace();
				} catch (URISyntaxException e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	private void toonBevestiging(String melding)
	{
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Bevestigen");
		alert.setContentText(melding);
		alert.setHeaderText("Bevestiging");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK)
		{
//			TODO: bespreken met DYlan hoe we dit gaan doen
//			Stage stage = (Stage) getScene().getWindow();
//			stage.close();
//			Actie(stage);
		}
	}
}
