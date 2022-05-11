package gui;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import domein.CategorieController;
import domein.Mvo;
import domein.MvoController;
import domein.SdgController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

public class MvoPaneelController extends AnchorPane{
	
	@FXML
	private ListView<String> MvoCatListView;
	
    @FXML
    private ListView<String> MvoListView;
    
    @FXML
    private Button createMVO_btn;

    @FXML
    private Button deleteMVO_btn;

    @FXML
    private Button editMVO_btn;

    @FXML
    private Button viewMVO_btn;
    
    private boolean rechterSchermAanwezig = false;

    @FXML
    void createMVO_OnAction(ActionEvent event) {
		if (rechterSchermAanwezig) {
			verwijderRechterScherm();			
		}
		AanmakenMvoPaneelController aanmakenMVOPaneel = new AanmakenMvoPaneelController();
		AnchorPane.setRightAnchor(aanmakenMVOPaneel,100.0);
		this.getChildren().add(aanmakenMVOPaneel);
		rechterSchermAanwezig = true;
    }

    @FXML
    void deleteMVO_OnAction(ActionEvent event) {
    	
    }

    @FXML
    void editMVO_OnAction(ActionEvent event) {

    }

    @FXML
    void raadplegenMVO_OnAction(ActionEvent event) {
		if (rechterSchermAanwezig) {
			verwijderRechterScherm();			
		}
		MvoRaadplegenPaneelController raadplegenMvoPaneel = new MvoRaadplegenPaneelController(selectedMvo);
		AnchorPane.setRightAnchor(raadplegenMvoPaneel,100.0);
		this.getChildren().add(raadplegenMvoPaneel);
		rechterSchermAanwezig = true;
    }
    
	private void verwijderRechterScherm() {
		this.getChildren().remove(this.getChildren().size()-1);
		rechterSchermAanwezig = false;
	}
    
    /*
    @FXML
    private Label mvoNaam;
    
    @FXML
    private Label goalValue;
    
    @FXML
    private Label infoMvo;
	
    @FXML
    private ImageView sdgImage;*/
    
	private CategorieController cc;
	private MvoController mc;
	private SdgController sc;
	private String selectedCat;
	private Mvo selectedMvo;
	private List<Mvo> mvosVanCategorie;
	
	public MvoPaneelController()
	{
		cc = new CategorieController();
		mc = new MvoController();
		sc = new SdgController();
		buildGui();
		initialize();
	}
	
	private void buildGui()
	{
		try
		{			

			FXMLLoader loader = new FXMLLoader(getClass().getResource("MvoPaneel.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
		} catch (IOException ex)
		{
			throw new RuntimeException(ex);
		}
	}
	
	private void initialize() {
		
		
		MvoCatListView.getItems().addAll(cc.geefAlleEchteCategorienNaam());
		
		MvoCatListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				
				selectedCat = MvoCatListView.getSelectionModel().getSelectedItem();
				
				System.out.println(selectedCat);
				
				//Currentcat gebruiken om MVO namen op te halen voor bepaalde categorie en deze in de andere ListView te krijgen
				mvosVanCategorie = mc.geefMvosVanCategorie(selectedCat);
				
				for(int i=0;i<mvosVanCategorie.size();i++) {
					System.out.println(mvosVanCategorie.get(i));
				}
				
				MvoListView.getItems().clear();
				
				MvoListView.getItems().addAll(mvosVanCategorie.stream().map(Mvo::getName).collect(Collectors.toList()));
			}
			
		});
		
		
		MvoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				
				String Mvo = MvoListView.getSelectionModel().getSelectedItem();
				
				System.out.println(Mvo);
				
				selectedMvo = mc.geefMvoMetNaam(Mvo);
				
				//Sdg sdg = sc.geefSdgVoorMvo(selectedMvo.getSdg().getId());
				
				//System.out.println(sdg.getImage());
				
				//mvoNaam.setText(mvo.getName());
				
				//goalValue.setText(mvo.getGoalValue()+ "");
				
				//infoMvo.setText(mvo.getInfo());
				
				//Image i = new Image("/images/"+sdg.getImage()+".png");
				
				//sdgImage.setImage(i);
				
				
				
				
				
			}
			
		});
	
	}

}
