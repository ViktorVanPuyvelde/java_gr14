package gui;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.io.File;  
import java.io.FileInputStream;  
import java.util.Iterator;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  

import domein.Aggregatie;
import domein.Mvo;
import domein.MvoController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class NieuweDatasourcePaneelController extends GridPane
{

	private MvoController mc;

	@FXML
	private TextField naam_textfield;

	@FXML
	private Button upload_btn;

	@FXML
	private Label fileLbl;

	@FXML
	private ListView<String> mvosList;

	@FXML
	private Label toevoegenLbl;
	
    @FXML
    private Button toevoegen_btn;
    
    private ObservableList<Mvo> mvoList;
    
    private File selectedFile;
    
    
    public NieuweDatasourcePaneelController() {
		this.mc = new MvoController();
		buildGui();
		setMvoList();

	}

	private void buildGui()
	{
		try
		{

			mvosList = new ListView<>();
			//mvosList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

			mvoList = FXCollections.observableArrayList(new ArrayList<Mvo>());

			FXMLLoader loader = new FXMLLoader(getClass().getResource("NieuweDatasourcePaneel.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();

		} catch (IOException ex)
		{
			throw new RuntimeException(ex);
		}
	}

	private void setMvoList()
	{
		List<Mvo> mvos = mc.geefMvos();
		for (Mvo m : mvos)
		{
			mvoList.add(m);
		}

		mvoList.forEach(m -> mvosList.getItems().add(m.getName()));
	}

	@FXML
	public void upload_OnAction(ActionEvent actionEvent)
	{
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Excel File");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel file", "xls", "xlsx"));

		Stage newWindow = new Stage();
		newWindow.setTitle("Kies een Excel bestand");
		selectedFile = fileChooser.showOpenDialog(newWindow);

		if (selectedFile != null)
		{
			fileLbl.setText(String.format("%s geselecteerd", selectedFile.getName()));
			fileLbl.setTextFill(Color.GREEN);
			fileLbl.setStyle("-fx-font-weight: bold");
		}else {
			fileLbl.setText(String.format("geselecteerd bestand is fout"));
			fileLbl.setTextFill(Color.RED);
			fileLbl.setStyle("-fx-font-weight: bold");
		}
	}

	@FXML
	public void toevoegen_OnAction(ActionEvent actionEvent)
	{
		collectChanges();
		verify();
		update();
	}

	private void update() {
		
	
	}

	private void verify() {
		
		toevoegenLbl.setText("Datasource toegevoegd!");
		toevoegenLbl.setTextFill(Color.GREEN);
		toevoegenLbl.setStyle("-fx-font-weight: bold");
		
	}

	private void collectChanges() {
		
		Aggregatie methode = mvoList.get(0).getMethode();
		verwerkDatasource(methode);
		dataOpnemen();
		
	}

	private void dataOpnemen() {
		try {  
		 
			FileInputStream fis = new FileInputStream(selectedFile);   //obtaining bytes from the file  
			//creating Workbook instance that refers to .xlsx file  
			XSSFWorkbook wb = new XSSFWorkbook(fis);   
			XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object  
			Iterator<Row> itr = sheet.iterator();    //iterating over excel file  
			while (itr.hasNext())                 
			{  
			Row row = itr.next();  
			Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column  
			while (cellIterator.hasNext())   
			{  
			Cell cell = cellIterator.next();  
			switch (cell.getCellType())               
			{  
			case Cell.CELL_TYPE_STRING:    //field that represents string cell type  
			System.out.print(cell.getStringCellValue() + "\t\t\t");  
			break;  
			case Cell.CELL_TYPE_NUMERIC:    //field that represents number cell type  
			System.out.print(cell.getNumericCellValue() + "\t\t\t");  
			break;  
			default:  
			}  
			}  
			System.out.println("");  
			}  
			}   catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
		
	}

	private void verwerkDatasource(Aggregatie methode) {
		// data in dictionary op quarter
		
		switch(methode) {
		  case SOM:
			 verwerkDataAlsSom();
		    break;
		  case GEMIDDELDE:
			  verwerkDataAlsGem();
		    break;
		  default:
			  
		    
		}
		
	}

	private void verwerkDataAlsGem() {
		
		
	}

	private void verwerkDataAlsSom() {
		
		
	}
}
