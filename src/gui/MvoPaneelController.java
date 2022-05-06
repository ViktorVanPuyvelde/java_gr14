package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.event.ChangeEvent;

import domein.CategorieController;
import domein.DomeinController;
import domein.Mvo;
import domein.MvoController;
import domein.Sdg;
import domein.SdgController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class MvoPaneelController extends GridPane{
	
	@FXML
	private ListView<String> MvoCatListView;
	
    @FXML
    private ListView<String> MvoListView;
    
    @FXML
    private Label mvoNaam;
    
    @FXML
    private Label goalValue;
    
    @FXML
    private Label infoMvo;
	
    @FXML
    private ImageView sdgImage;
    
	private CategorieController cc;
	private MvoController mc;
	private SdgController sc;
	private String selectedCat;
	private String selectedMvo;
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
				
				selectedMvo = MvoListView.getSelectionModel().getSelectedItem();
				
				System.out.println(selectedMvo);
				
				Mvo mvo = mc.geefMvoMetNaam(selectedMvo);
				
				Sdg sdg = sc.geefSdgVoorMvo(mvo.getS().getId());
				
				System.out.println(sdg.getImage());
				
				mvoNaam.setText(mvo.getName());
				
				goalValue.setText(mvo.getGoalValue()+ "");
				
				infoMvo.setText(mvo.getInfo());
				
				Image i = new Image("/images/"+sdg.getImage()+".png");
				
				sdgImage.setImage(i);
				
				
				
				
				
			}
			
		});
	
	}

}
