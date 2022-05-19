package gui;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.TreeMap;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;  
import java.io.FileInputStream;  
import java.util.Iterator;  


import domein.Aggregatie;
import domein.Datasource;
import domein.DatasourceController;
import domein.Mvo;
import domein.MvoController;
import domein.MvoDataController;
import exceptions.InformationRequiredException;
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
	private Label titel_lbl;
    @FXML
    private Button toevoegen_btn;
    
    private ObservableList<Mvo> mvoList;
    private DatasourceController controller;
    private Datasource datasource;
    
    private File selectedFile;
    HashMap<Double, List<List<Object>>> allDataFromFileMap = new HashMap<>();
    TreeMap<Double, List<Object>> verwerkteData;

	private MvoDataController mdc;
	Mvo mvo;
    
    
    public NieuweDatasourcePaneelController(Datasource d , DatasourceController controller) {
		this.mc = new MvoController();
		this.mdc = new MvoDataController();
		this.controller = controller;
		this.datasource = d;
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
			if (datasource != null) {
				toevoegen_btn.setText("Wijzigen");
				titel_lbl.setText("Datasource wijzigen");
				naam_textfield.setText(datasource.getName());
			}
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
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel files (*.xlsx)", "*.xlsx");
        fileChooser.getExtensionFilters().add(extFilter);

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
	public void toevoegen_wijzigen_OnAction(ActionEvent actionEvent)
	{
		collectChanges();
		verify();
		update();
	}

	private void update() {
		
		verwerkteData.forEach((key,val) -> System.out.println(val));
		verwerkteData.forEach((key,val) ->{
			try {
				mdc.voegMvoDataToe(
						mvo ,
						String.valueOf(((OptionalDouble) val.get(0)).getAsDouble()),
						(Date) val.get(1),
						(int)( Double.parseDouble( val.get(2).toString()))
						);
			} catch (InformationRequiredException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
	}

	private void verify() {
		
		toevoegenLbl.setText("Datasource toegevoegd!");
		if (datasource == null) {
			controller.voegDatasourceToe(naam_textfield.getText(), false);
			toevoegenLbl.setText("Datasource toegevoegd!");
		}else {
			controller.updateDatasource(datasource, naam_textfield.getText(), false);
			toevoegenLbl.setText("Datasource gewijzigd!");			
		}
		
		toevoegenLbl.setTextFill(Color.GREEN);
		toevoegenLbl.setStyle("-fx-font-weight: bold");
		
	}

	private void collectChanges() {
		dataOpnemen();
		mvo = mvoList.get(mvosList.getSelectionModel().getSelectedIndex());
		Aggregatie methode = Aggregatie.GEMIDDELDE;
		String naam = naam_textfield.getText();
		verwerkteData = verwerkDatasource(methode);
		
		
	}

	private void dataOpnemen() {
		try {
			
 
            XSSFWorkbook workbook = new XSSFWorkbook(selectedFile);
 
            XSSFSheet sheet = workbook.getSheetAt(0);
 
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            //loop each row
            while (rowIterator.hasNext()) 
            {
            	List<Object> data = new ArrayList<>();
  
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                
                //fill datalist from excel cells
                //-----
                Double dataCel = cellIterator.next().getNumericCellValue();
                Date dateCel = cellIterator.next().getDateCellValue();
                Double quarterCel = cellIterator.next().getNumericCellValue();
                data.add(dataCel);
                data.add(dateCel);
                data.add(quarterCel);
                //------

                //add datalist to hashmap
                //-------------------------
                if(allDataFromFileMap.get(quarterCel) != null) {

                	allDataFromFileMap.get(quarterCel).add(data);
                }else {
                	//first dataList with column names
                	List<Object> firstDataList = new ArrayList<>(data);
                	
                	//new List with first dataList for new key
                	List<List<Object>> filledStarterList = new ArrayList<>();
                	filledStarterList.add(firstDataList);

                	allDataFromFileMap.put(quarterCel,filledStarterList);
                }
               //-----------------------------              
            }           
            workbook.close();
        }  catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			
			e.printStackTrace();
		}
		
	}

	private  TreeMap<Double, List<Object>> verwerkDatasource(Aggregatie methode) {
		
		switch(methode) {
		  case SOM:
			 return verwerkDataAlsSom(allDataFromFileMap);
		  
		  case GEMIDDELDE:
			  return verwerkDataAlsGem(allDataFromFileMap);
		   	    
		}
		return null;
		
	}

	private  TreeMap<Double, List<Object>> verwerkDataAlsGem(HashMap<Double, List<List<Object>>> dataMap) {
		//datalijst = [waarde, datum, quarter]
		
		//begin Treemap voor uiteindelijke verwerkte data lijsten
				TreeMap<Double,List<Object>> verwerkteDataTree = new TreeMap<>();
		
		// per key (quarter) de data lijsten ophalen
				dataMap.entrySet().stream().forEach(dataLijstenPerKey -> {
					
		//elke datalijst waarde opslaan in nieuwe lijst (in treemap)
					Double key = dataLijstenPerKey.getKey();
					List<Double> waardeLijst = new ArrayList<>();
					dataLijstenPerKey.getValue().stream().forEach(datalijst -> {
						
						waardeLijst.add((double) datalijst.get(0));
					});
					
		//bereken het gemmidelde van alle waarden per quarter
				OptionalDouble gem = waardeLijst.stream().mapToDouble(a -> a).average();
				
		//nieuwe entry maken voor treemap met gemmidelde
				List<Object> newDataList = new ArrayList<>();
				newDataList.add(gem);
				newDataList.add(dataLijstenPerKey.getValue().get(0).get(1));
				newDataList.add(key);
				
				verwerkteDataTree.put(key,newDataList);
				});
				
				
		return verwerkteDataTree;
	}

	private TreeMap<Double, List<Object>> verwerkDataAlsSom(HashMap<Double, List<List<Object>>> dataMap) {
		//datalijst = [waarde, datum, quarter]
		
		//begin Treemap voor uiteindelijke verwerkte data lijsten
		TreeMap<Double,List<Object>> verwerkteDataTree = new TreeMap<>();
		
		// per key (quarter) de data lijsten ophalen
		dataMap.entrySet().stream().forEach(dataLijstenPerKey -> {
			
		//elke datalijst waarde optellen bij vorig totaal (in treemap)
			dataLijstenPerKey.getValue().stream().forEach(datalijst -> 
			{
				Double key = dataLijstenPerKey.getKey();
				List<Object> treeValueLijst = verwerkteData.get(key);
				
				// er zit al data voor deze quarter in de treemap
				if(treeValueLijst != null) {
					
					//waarde optellen bij treemap waarde
					treeValueLijst.set(0,(double)treeValueLijst.get(0) + (double) datalijst.get(0));								
					verwerkteDataTree.put(key, treeValueLijst);
				}
				// er zit nog geen data voor deze quarter in de treemap -> nieuwe entr
				else {
					
					verwerkteDataTree.put(key, datalijst);
				}
			});
			
		});
		
		return verwerkteDataTree;
	}
}
