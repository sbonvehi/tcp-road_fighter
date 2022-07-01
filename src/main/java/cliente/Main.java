package cliente;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import road_fighter.GameSceneHandler;
import road_fighter.SceneHandler;

public class Main extends Application {
	private Stage stage;
	public Cliente cliente;

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		stage.getIcons().add(new Image("file:src/main/resources/img/icon.png"));
		stage.setTitle("Road Fighter");
		stage.setResizable(false);
		stage.show();	
	}
	
	public static void main(String[] args) {
		launch();
	}
	
	public void startScene() {
		SceneHandler escena = new GameSceneHandler(this);
		if (escena != null) {
			Scene scene = escena.getScene();
			scene.getStylesheets().add(ClassLoader.getSystemResource("stylesheet.css").toString());
			this.stage.setScene(scene);
			escena.load(true);
		}
	}
}
