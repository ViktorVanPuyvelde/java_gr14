package gui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import domein.CategorieController;
import domein.DomeinController;
import domein.Sdg;
import domein.SdgController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class CategoriePaneelController extends GridPane
{
	private DomeinController dc;
	private CategorieController cc;
	private SdgController sc;

	@FXML
	private ListView<String> cat_Sdg_List;
	@FXML
	private ListView<String> cat_Rol_List;

	@FXML
	private TextField cat_Name_field;
	@FXML
	private TextField cat_Pictogram_field;

	@FXML
	private Button cat_save_btn;

	@FXML
	private Hyperlink linkIcons;

	private String name;
	private ObservableList<String> sdg;
	private ObservableList<String> rol;
	private String pic;

	private ObservableList<Sdg> sdgItemList;
	private ObservableList<String> rolItemList;

	private Foutmelding fm = new Foutmelding();

	public CategoriePaneelController(DomeinController dc)
	{
		this.dc = dc;
		this.cc = new CategorieController();
		this.sc = new SdgController();
		buildGui();
		setSdgItemList();
		initialize();
	}

	private void buildGui()
	{
		try
		{
			cat_Sdg_List = new ListView<>();
			cat_Rol_List = new ListView<>();
			sdgItemList = FXCollections.observableArrayList(new ArrayList());
			rolItemList = FXCollections.observableArrayList(new ArrayList());
			linkIcons = new Hyperlink();

			FXMLLoader loader = new FXMLLoader(getClass().getResource("CategoriePaneel.fxml"));
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
		instellenHyperLink();
		cat_Sdg_List.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		cat_Rol_List.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		rolItemList.add("gebruiker");
		rolItemList.add("directie");
		rolItemList.add("manager");
		rolItemList.add("co�rdinator");

		// fill with Sdg's
		sdgItemList.forEach(sdg -> cat_Sdg_List.getItems().add(sdg.getName()));

		// fill with Roles
		cat_Rol_List.setItems(rolItemList);
	}

	@FXML
	public void btnCatSaveOnAction(ActionEvent event)
	{
		collectChanges();
		verify();
	}

	private void update()
	{
		List<String> vb = rol.stream().toList();
		cc.voegCategorieToe(name, pic, vb);
	}

	private void verify()
	{
		if (name == null || name.isEmpty())
		{
			fm.toonFoutmelding("geef een naam mee");
		} else if (pic == null || pic.isEmpty())
		{
			fm.toonFoutmelding("geef een pictogram mee");
		} else if (sdg == null || sdg.isEmpty())
		{
			fm.toonFoutmelding("selecteer een MVO");
		} else if (rol == null)
		{
			fm.toonFoutmelding("selecteer een rol");
		} else
		{
			update();
		}
	}

	private void collectChanges()
	{
		name = this.cat_Name_field.getText();
		pic = this.cat_Pictogram_field.getText();
		sdg = cat_Sdg_List.getSelectionModel().getSelectedItems();
		rol = cat_Rol_List.getSelectionModel().getSelectedItems();
	}

	public void setSdgItemList()
	{
		List<Sdg> sdgs = this.sc.geefSdgs();
		for (Sdg s : sdgs)
		{
			this.sdgItemList.add(s);
		}
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
