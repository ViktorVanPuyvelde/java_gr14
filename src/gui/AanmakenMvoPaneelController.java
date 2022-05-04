package gui;

import java.io.IOException;

import domein.MvoController;
import domein.SdgController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
	private TextField txtSdg;
	@FXML
	private TextField txtType;
	@FXML
	private TextField txtDoel;
	@FXML
	private TextField txtData;

	// lokale attributen
	private String name;
	private String sdg;
	private String type;
	private int doel;
	private String datasource;

	private Foutmelding fm;

	public AanmakenMvoPaneelController()
	{
		this.mc = new MvoController();
		this.sc = new SdgController();
		fm = new Foutmelding();
		buildGui();
		initialize();
	}

	private void buildGui()
	{
		try
		{
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
		this.sdg = txtSdg.getText();
		this.type = txtType.getText();
		if (txtDoel.getText() == null || txtDoel.getText().isEmpty())
		{
			fm.toonFoutmelding("Er moet een doel meegegeven worden aan de nieuwe MVO.");
		} else
		{
			this.doel = Integer.parseInt(txtDoel.getText());
		}
	}

	private void verify()
	{
		if (name == null || name.isEmpty())
		{
			fm.toonFoutmelding("Naam mag niet leeg zijn.");
		} else if (sdg == null || sdg.isEmpty())
		{
			fm.toonFoutmelding("Er moet een SDG toegewezen zijn aan de nieuwe MVO.");
		} else if (type == null || type.isEmpty())
		{
			fm.toonFoutmelding("Type mag niet leeg zijn");
		} else
		{
			update();
		}
	}

	private void update()
	{
		this.mc.voegMvoToe(name, sdg, type, doel, datasource);
	}

}
