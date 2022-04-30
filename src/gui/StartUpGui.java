package gui;

import domein.DomeinController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartUpGui extends Application
{
	@Override
	public void start(Stage stage)
	{
		DomeinController controller = new DomeinController();
		Scene scene = new Scene(new HomepagePaneelController(controller));

		stage.setScene(scene);
//		stage.setMaximized(true);
		stage.show();
	}

	public static void main(String... args)
	{
		Application.launch(StartUpGui.class, args);
	}
}
