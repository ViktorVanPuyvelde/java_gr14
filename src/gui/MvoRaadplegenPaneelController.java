package gui;

import java.io.IOException;

import domein.Datasource;
import domein.DatasourceController;
import domein.Mvo;
import domein.Sdg;
import domein.SdgController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class MvoRaadplegenPaneelController extends AnchorPane{
	
	private Mvo selectedMvo;
	
	private SdgController sc;
	
	private DatasourceController dc;
	
	public MvoRaadplegenPaneelController(Mvo mvo) {
		this.selectedMvo=mvo;
		this.sc=new SdgController();
		this.dc=new DatasourceController();
		buildGui();
		initialize();
	}
	
    @FXML
    private Label goalValue;

    @FXML
    private Label mvoNaam;

    @FXML
    private ImageView sdgImage;
    
    @FXML
    private Label sdg_id;

    @FXML
    private Label sdg_naam;
    
    @FXML
    private Label datasource_id;

    @FXML
    private Label datasource_name;

    
	private void buildGui()
	{
		try
		{			

			FXMLLoader loader = new FXMLLoader(getClass().getResource("MvoRaadplegenPaneel.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
		} catch (IOException ex)
		{
			throw new RuntimeException(ex);
		}
	}
	
	private void initialize() {
		
		goalValue.setText(selectedMvo.getGoalValue()+"");
		
		mvoNaam.setText(selectedMvo.getName() + "     (" + selectedMvo.getEenheid()+")");
		
		Sdg sdg = sc.geefSdgVoorMvo(selectedMvo.getSdg().getId(),selectedMvo.getId());
		
		sdg_id.setText(sdg.getId());
		
		sdg_naam.setText(sdg.getName());
		
		if(selectedMvo.getDatasource() != null) {
			Datasource datasource = dc.geefDatasourceMetId(selectedMvo.getDatasource().getId());
			
			datasource_id.setText(selectedMvo.getDatasource().getId());
			datasource_name.setText(datasource.getName());
		}

		
		Image i = new Image("/images/"+sdg.getImage());
		
		sdgImage.setImage(i);
		
	}
	

}
