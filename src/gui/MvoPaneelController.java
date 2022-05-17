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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

public class MvoPaneelController extends HBox{
	
	/*
	 * 
	 * FXML variabelen
	 * 
	 */
	
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
    
    @FXML
    private Label mvo_Selecteren_lbl;
    
    private boolean rechterSchermAanwezig = false;
    
    
    /*
     * 
     * LOKALE VARIABELEN
     */
    
	private CategorieController cc;
	private MvoController mc;
	private SdgController sc;
	private String selectedCat;
	private Mvo selectedMvo;
	private List<Mvo> mvosVanCategorie;
	private Melding melding;
	
	/*
	 * 
	 * FXML onAction functies
	 */

    @FXML
    void createMVO_OnAction(ActionEvent event) {
    	mvo_Selecteren_lbl.setText("");
		if (rechterSchermAanwezig) {
			verwijderRechterScherm();			
		}
		AanmakenMvoPaneelController aanmakenMVOPaneel = new AanmakenMvoPaneelController();
		this.getChildren().add(aanmakenMVOPaneel);
		rechterSchermAanwezig = true;
    }

    @FXML
    void deleteMVO_OnAction(ActionEvent event) {
    	System.out.println("Verwijder: "+selectedMvo.getName()+" // " + selectedMvo.getId());
    	mc.delete(selectedMvo);
    	melding.toonBevestiging("MVO doelstelling succesvol verwijderd");
    }

    @FXML
    void editMVO_OnAction(ActionEvent event) {
    	mvo_Selecteren_lbl.setText("");
		if (rechterSchermAanwezig) {
			verwijderRechterScherm();			
		}
		WijzigenMvoPaneelController wijzigenMVOPaneel = new WijzigenMvoPaneelController(selectedMvo);
		this.getChildren().add(wijzigenMVOPaneel);
		rechterSchermAanwezig = true;
    }

    @FXML
    void raadplegenMVO_OnAction(ActionEvent event) {
    	mvo_Selecteren_lbl.setText("");
		if (rechterSchermAanwezig) {
			verwijderRechterScherm();			
		}
		if (MvoListView.getSelectionModel().getSelectedItem() != null) {
			MvoRaadplegenPaneelController raadplegenMvoPaneel = new MvoRaadplegenPaneelController(selectedMvo);
			this.getChildren().add(raadplegenMvoPaneel);
			rechterSchermAanwezig = true;			
		}else {
			mvo_Selecteren_lbl.setText("Gelieve eerst een MVO te selecteren!");
		}
    }
    
    
	
	public MvoPaneelController()
	{
		cc = new CategorieController();
		mc = new MvoController();
		sc = new SdgController();
		melding = new Melding();
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
				
		MvoCatListView.getItems().add("*");
		MvoCatListView.getItems().addAll(cc.geefAlleEchteCategorienNaam());
		
		MvoCatListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				
				selectedCat = MvoCatListView.getSelectionModel().getSelectedItem();
				
				System.out.println(selectedCat);
				
				//Currentcat gebruiken om MVO namen op te halen voor bepaalde categorie en deze in de andere ListView te krijgen
				if(selectedCat != "*") {
					mvosVanCategorie = mc.geefMvosVanCategorie(selectedCat);
				}else {
					mvosVanCategorie = mc.geefMvos();
				}
				
				for(int i=0;i<mvosVanCategorie.size();i++) {
					System.out.println(mvosVanCategorie.get(i).getName());
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
				
				if(Mvo != null) {
					selectedMvo = mc.geefMvoMetNaam(Mvo);
				}	
				
				System.out.println(selectedMvo.getId());
				
				
			}
			
		});
	
	}
	
	private void verwijderRechterScherm() {
		this.getChildren().remove(this.getChildren().size()-1);
		rechterSchermAanwezig = false;
	}
   

}
