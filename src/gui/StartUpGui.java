package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartUpGui extends Application
{
	@Override
	public void start(Stage stage)
	{
		Scene scene = new Scene(new AanmeldPaneelController());
		stage.setScene(scene);
		stage.show();
		stage.setScene(scene);
//		stage.setMaximized(true);
		stage.show();
	}

	public static void main(String... args)
	{
		Application.launch(StartUpGui.class, args);
	}
}
