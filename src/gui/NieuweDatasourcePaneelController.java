package gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import domein.Aggregatie;
import domein.Datasource;
import domein.DatasourceController;
import domein.Mvo;
import domein.MvoController;
import domein.MvoData;
import domein.MvoDataController;
import exceptions.InformationRequiredException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
	private ChoiceBox<Aggregatie> aggregatieBox;

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
	HashMap<Double, List<tempMvoData>> allDataFromFileMap = new HashMap<>();
	HashMap<Double, tempMvoData> verwerkteData;

	private MvoDataController mdc;
	Mvo mvo;


	public NieuweDatasourcePaneelController(Datasource d, DatasourceController controller)
	{
		this.mc = new MvoController();
		this.mdc = new MvoDataController();
		this.controller = controller;
		this.datasource = d;
		buildGui();
		setMvoList();

	}

	public NieuweDatasourcePaneelController() {
		
	}

	private void buildGui()
	{
		try
		{
			mvosList = new ListView<>();
			// mvosList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

			mvoList = FXCollections.observableArrayList(new ArrayList<Mvo>());

			FXMLLoader loader = new FXMLLoader(getClass().getResource("NieuweDatasourcePaneel.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
			if (datasource != null)
			{
				toevoegen_btn.setText("Wijzigen");
				titel_lbl.setText("Datasource wijzigen");
				naam_textfield.setText(datasource.getName());
			}
			
			aggregatieBox.getItems().setAll(Aggregatie.values());
			aggregatieBox.setValue(Aggregatie.SOM);
			
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
		} else
		{
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
	
	  

	private void update()
	{

		
		verwerkteData.forEach((key, val) ->
		{
			try
			{
				System.out.print("toeveogen : ");
				System.out.println(val);
				System.out.print("aan :");
				System.out.println(mvo.getName());
				mdc.voegMvoDataToe(mvo, String.valueOf(val.getWaarde()),val.getDate(), (int) val.getQuarter());
			} catch (InformationRequiredException e)
			{

				toevoegenLbl.setText(String.format("%s", e.getMessage()));
				toevoegenLbl.setTextFill(Color.RED);
				toevoegenLbl.setStyle("-fx-font-weight: bold");
			}
		});
		updateSuperMvo();
		
	}

	private void verify()
	{

		toevoegenLbl.setText("Datasource toegevoegd!");
		if (datasource == null) {
			try {
				controller.voegDatasourceToe(naam_textfield.getText(), false);
				Datasource dezeDatasource = controller.geefDatasourceDoorNaam(naam_textfield.getText());
				mvo.setDatasource(dezeDatasource);
				mc.update(mvo);
				toevoegenLbl.setText("Datasource toegevoegd!");
			} catch (InformationRequiredException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				controller.updateDatasource(datasource, naam_textfield.getText(), false);
				toevoegenLbl.setText("Datasource gewijzigd!");	
			} catch (InformationRequiredException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		toevoegenLbl.setTextFill(Color.GREEN);
		toevoegenLbl.setStyle("-fx-font-weight: bold");

	}

	private void collectChanges()
	{
		dataOpnemen();
		mvo = mvoList.get(mvosList.getSelectionModel().getSelectedIndex());
		Aggregatie methode = aggregatieBox.getSelectionModel().getSelectedItem();
		String naam = naam_textfield.getText();
		verwerkteData = verwerkDatasource(methode);

	}

	private void dataOpnemen()
	{
		try
		{

			XSSFWorkbook workbook = new XSSFWorkbook(selectedFile);

			XSSFSheet sheet = workbook.getSheetAt(0);

			Iterator<Row> rowIterator = sheet.iterator();
			//first row with colmnnames
			rowIterator.next();
			
			// loop each row 
			while (rowIterator.hasNext())
			{
				

				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();

				// fill datalist from excel cells
				// -----
				Double dataCel = cellIterator.next().getNumericCellValue();
				Date dateCel = cellIterator.next().getDateCellValue();
				Double quarterCel = cellIterator.next().getNumericCellValue();
				// ------

				tempMvoData data = new tempMvoData(dataCel,dateCel,quarterCel);
				// add data to hashmap
				// -------------------------
				if (allDataFromFileMap.get(quarterCel) != null)
				{

					allDataFromFileMap.get(quarterCel).add(data);
				} else
				{
					// new List with first dataList for new key
					List<tempMvoData> filledStarterList = new ArrayList<>();
					filledStarterList.add(data);

					allDataFromFileMap.put(quarterCel, filledStarterList);
				}
				// -----------------------------
			}
			workbook.close();
		} catch (FileNotFoundException e)
		{

			toevoegenLbl.setText(String.format("Bestand niet gevonden"));
			toevoegenLbl.setTextFill(Color.RED);
			toevoegenLbl.setStyle("-fx-font-weight: bold");
		} catch (IOException e)
		{

			toevoegenLbl.setText(String.format("Error in bestand"));
			toevoegenLbl.setTextFill(Color.RED);
			toevoegenLbl.setStyle("-fx-font-weight: bold");
		} catch (InvalidFormatException e)
		{

			toevoegenLbl.setText(String.format("Data heeft niet het juiste formaat"));
			toevoegenLbl.setTextFill(Color.RED);
			toevoegenLbl.setStyle("-fx-font-weight: bold");
		}

	}

	private HashMap<Double, tempMvoData> verwerkDatasource(Aggregatie methode)
	{

		switch (methode)
		{
		case SOM:
			return verwerkDataAlsSom(allDataFromFileMap);

		case GEMIDDELDE:
			return verwerkDataAlsGem(allDataFromFileMap);

		}
		return null;

	}

	public  HashMap<Double, tempMvoData> verwerkDataAlsGem(HashMap<Double, List<tempMvoData>> dataMap)
	{
		// tempMvoData = {waarde, datum, quarter}

		
		
		Map<Double, tempMvoData> aggregatedData = dataMap.entrySet()
		        .stream()
		        .collect(
		            Collectors.toMap(
		                Map.Entry::getKey, 
		                e -> {
		                	AtomicInteger count = new AtomicInteger();
		                	return new tempMvoData(
		                		e.getValue().stream().reduce( 0.0, (subtotal, element) -> { count.incrementAndGet(); return subtotal + element.getWaarde();}, Double::sum) / count.get(),
		                		e.getValue().get(0).getDate(),
		                		e.getKey());
		                }
		            		)
		                
		        );
		System.out.println(aggregatedData);
		return (HashMap<Double, tempMvoData>) aggregatedData;
	}

	public HashMap<Double, tempMvoData> verwerkDataAlsSom(HashMap<Double, List<tempMvoData>> dataMap)
	{
		// datalijst = [waarde, datum, quarter]

		Map<Double, tempMvoData> aggregatedData = dataMap.entrySet()
		        .stream()
		        .collect(
		            Collectors.toMap(
		                Map.Entry::getKey, 
		                e -> new tempMvoData(
		                		e.getValue().stream().reduce( 0.0, (subtotal, element) -> subtotal + element.getWaarde(), Double::sum),
		                		e.getValue().get(0).getDate(),
		                		e.getKey()))
		                
		        );

		System.out.println(aggregatedData);
		return (HashMap<Double, tempMvoData>) aggregatedData;
	}
	
	
	private void updateSuperMvo() {
		Mvo superMvo = mvo.getSuperMvo();
		if(superMvo != null) {
			//treemap voor al bestaande mvoData in superMvo
			TreeMap<Integer,MvoData> tree = new TreeMap<>();
			
			List<MvoData> superMvoDataList = superMvo.getMvo_data();
			List<MvoData> subMvoDataList = mvo.getMvo_data();
			
			// treemap opvullen
			if(superMvoDataList != null) {
			superMvoDataList.stream().forEach(superMvoData -> tree.put(superMvoData.getQuarter(), superMvoData));
			};
			
			subMvoDataList.stream().forEach(subMvoData -> {
				int quarter = subMvoData.getQuarter();
				// er is al data voor dit kwartaal -> nieuwe data optellen bij bestaande data
				if(tree.containsKey(quarter)) {			
					MvoData newSuperMvoData = tree.get(quarter);
					newSuperMvoData.setMvo_id(superMvo);
					newSuperMvoData.setDatum(subMvoData.getDatum());
					newSuperMvoData.setWaardeInt(subMvoData.getWaardeInt() + tree.get(quarter).getWaardeInt());
					newSuperMvoData.setQuarter(quarter);
					System.out.println("super mvo update");
					mdc.update(newSuperMvoData);
				}else {
					// er is nog geen data voor dit kwartaal -> nieuwe MvoData
					try {
						System.out.println("nieuw voor super");
						mdc.voegMvoDataToe(superMvo,String.valueOf(subMvoData.getWaardeInt()),subMvoData.getDatum(),quarter);
					} catch (InformationRequiredException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
		
	}
	
	public class tempMvoData {

		private Double waarde;
		private Date date;
		private Double quarter;

		private tempMvoData(Double dataCel, Date dateCel, Double quarterCel) {
			this.waarde = dataCel;
			this.date = dateCel;
			this.quarter = quarterCel;
		}
		

		private void setWaarde(Double waarde) {
			this.waarde = waarde;
		}
		private void setDate(Date date) {
			this.date = date;
		}
		private void setQuarter(Double quarter) {
			this.waarde = quarter;
		}
		private double getQuarter() {
			return this.quarter;
		}
		private Date getDate() {
			return this.date;
		}
		private double getWaarde() {
			return this.waarde;
		}
		
		public String toString() {
			return waarde.toString() + date.toString()+ quarter.toString();
		}

	} 
}
