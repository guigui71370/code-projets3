package org.openjfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.openjfx.view.Viewplateau;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {
	private static Scene scene;





	public static void main(String[] args) {
		///org.openjfx.model.Partie test =new org.openjfx.model.Partie();
		//Viewplateau view=new Viewplateau();
		launch();
		
	}

	@Override
	public void start(Stage stage) throws Exception {
		scene = new Scene(loadFXML("view/rootContent"));
		stage.setScene(scene);
		stage.setMinHeight(700);
		stage.setMinWidth(1200);
		stage.setMaxHeight(700);
		stage.setMaxWidth(1200);
		stage.show();

	}

	static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	private static Parent loadFXML(String fxml) throws IOException {
		URL resource = App.class.getResource(fxml + ".fxml");
		FXMLLoader fxmlLoader = new FXMLLoader(resource);
		return fxmlLoader.load();
	}
}
