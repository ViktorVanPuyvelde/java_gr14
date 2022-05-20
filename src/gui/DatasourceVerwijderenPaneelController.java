package gui;

import java.io.IOException;

import domein.Datasource;
import domein.DatasourceController;
import domein.MvoController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class DatasourceVerwijderenPaneelController extends AnchorPane{	

    @FXML
    private Button btnConfirm;

    @FXML
    private Label lblQuestion;

    @FXML
    private Label lblTitel;
    
    @FXML
    private Label lblFout;

    @FXML
    void btnConfirmDeleteAction(ActionEvent event) {
		if(mvoCon.geefCountMVODatasource(datasource) == 0) {
			dataCont.delete(datasource);
			lblFout.setTextFill(groen);
			lblFout.setText(String.format("Datasource %s succesvol verwijderd", datasource.getName()));
		}else {
			lblFout.setTextFill(rood);
			lblFout.setText("Verwijderen mislukt: 1 of meerdere MVO doelstellingen gekoppeld");
		}
    }
    
    private MvoController mvoCon;
    private DatasourceController dataCont;
    private Datasource datasource;
    Color groen = Color.GREEN;
    Color rood = Color.RED;

    
    public DatasourceVerwijderenPaneelController(Datasource d) {
    	this.datasource=d;
    	this.mvoCon = new MvoController();
    	this.dataCont = new DatasourceController();
    	buildGui();
    	initialize();
    }
    
    
	private void buildGui()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("DatasourceVerwijderenPaneel.fxml"));
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
				.setText(String.format("Weet u zeker dat u de datasource \"%s\" wilt verwijderen?", this.datasource.getName()));
	}

}
