package gui;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Foutmelding
{
	public void toonFoutmelding(String melding)
	{
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Foutmelding");
		alert.setContentText(melding);
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK)
		{
//			Stage stage = (Stage) getScene().getWindow();
//			stage.close();
		}

	}
}
