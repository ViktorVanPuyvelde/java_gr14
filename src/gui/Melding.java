package gui;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Melding
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

	public void toonBevestiging(String melding)
	{
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Bevestigen");
		alert.setContentText(melding);
		alert.setHeaderText("Bevestiging");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK)
		{
//			TODO: bespreken met DYlan hoe we dit gaan doen
//			Stage stage = (Stage) getScene().getWindow();
//			stage.close();
//			Actie(stage);
		}
	}
}
