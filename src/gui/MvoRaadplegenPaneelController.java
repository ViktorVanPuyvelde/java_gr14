package gui;

import java.io.IOException;

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
	
	public MvoRaadplegenPaneelController(Mvo mvo) {
		this.selectedMvo=mvo;
		this.sc=new SdgController();
		buildGui();
		initialize();
	}
	
    @FXML
    private Label goalValue;

    @FXML
    private Label mvoNaam;

    @FXML
    private ImageView sdgImage;
    
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
		
		mvoNaam.setText(selectedMvo.getName());
		
		Sdg sdg = sc.geefSdgVoorMvo(selectedMvo.getSdg().getId(),selectedMvo.getId());
		
		System.out.println(sdg.getImage());
		
		Image i = new Image("/images/"+sdg.getImage()+".png");
		
		sdgImage.setImage(i);
		
	}
	

}
