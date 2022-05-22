package gui;

import java.io.IOException;

import domein.CategorieController;
import domein.Mvo;
import domein.MvoController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class MvoVerwijderenPaneelController extends AnchorPane{
	
	private CategorieController catContr;
	private MvoController mvoContr;
	private Mvo mvo;
    Color groen = Color.GREEN;
    Color rood = Color.RED;
	
    @FXML
    private Button btnConfirm;

    @FXML
    private Label lblFout;

    @FXML
    private Label lblQuestion;

    @FXML
    private Label lblTitel;

    @FXML
    void btnConfirmDeleteAction(ActionEvent event) {
		if(catContr.CatCountMvo(mvo) == 0) {
			mvoContr.delete(mvo);
			lblFout.setTextFill(groen);
			lblFout.setText(String.format("MVO Doelstelling %s succesvol verwijderd", mvo.getName()));
		}else {
			lblFout.setTextFill(rood);
			lblFout.setText("Verwijderen mislukt: er is 1 categorie gekoppeld");
		}
    }
    
    public MvoVerwijderenPaneelController(Mvo m) {
    	this.mvo = m;
    	this.catContr = new CategorieController();
    	this.mvoContr = new MvoController();
    	buildGui();
    	initialize();
    }

    
    private void buildGui()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MvoVerwijderenPaneel.fxml"));
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
		this.lblQuestion
				.setText(String.format("Weet u zeker dat u de MVO doelstelling \"%s\" wilt verwijderen?", this.mvo.getName()));
	}
}
