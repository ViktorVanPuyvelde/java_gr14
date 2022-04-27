package gui;

import java.io.IOException;
import java.util.ArrayList;

import domein.CategorieController;
import domein.DomeinController;
import domein.Mvo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class CategoriePaneelController extends GridPane
{
	private DomeinController dc;
	private CategorieController cc;

	@FXML
	private ListView<String> cat_Mvo_List;
	@FXML
	private ListView<String> cat_Rol_List;

	@FXML
	private TextField cat_Name_field;

	@FXML
	private Button cat_save_btn;

	private String name;
	private String mvo;
	private String rol;

	private ObservableList<Mvo> mvoItemList;
	private ObservableList<String> rolItemList;

	private Foutmelding fm = new Foutmelding();

	public CategoriePaneelController(DomeinController dc)
	{
		this.dc = dc;
		buildGui();
		initialize();
	}

	private void buildGui()
	{
		try
		{
			cc = new CategorieController();
			cat_Mvo_List = new ListView<>();
			cat_Rol_List = new ListView<>();
			mvoItemList = FXCollections.observableArrayList(new ArrayList());
			rolItemList = FXCollections.observableArrayList(new ArrayList());

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
		cat_Mvo_List.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		cat_Rol_List.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		mvoItemList.add(new Mvo("MVO 1", null));
		mvoItemList.add(new Mvo("MVO 2", null));
		mvoItemList.add(new Mvo("MVO 3", null));
		rolItemList.add("rol 1");
		rolItemList.add("rol 2");

		// fill with Mvo's
		mvoItemList.forEach(mvo -> cat_Mvo_List.getItems().add(mvo.getName()));

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
		cc.voegCategorieToe(name, null /* nog te implementeren */, null);
	}

	private void verify()
	{
		if (name == null || name.isEmpty())
		{
			fm.toonFoutmelding("geef een naam mee");
		} else if (mvo == null || mvo.isEmpty())
		{
			fm.toonFoutmelding("selecteer een MVO");
		} else if (rol == null || rol.isEmpty())
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
		mvo = cat_Mvo_List.getSelectionModel().getSelectedItem();
		rol = cat_Rol_List.getSelectionModel().getSelectedItem();
	}
}
