package cliente;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import road_fighter.GameSceneHandler;
import road_fighter.LoginSceneHandler;
import road_fighter.MenuSceneHandler;
import road_fighter.RoadFighterGame;
import road_fighter.SceneHandler;

public class Main extends Application {
	private Stage stage;
	public Cliente cliente;

	private LoginSceneHandler loginSceneHandler;
	private MenuSceneHandler menuSceneHandler;
	private GameSceneHandler gameSceneHandler;
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
	
	public void startScene() throws Exception {
		RoadFighterGame juego = new RoadFighterGame();
		loginSceneHandler = new LoginSceneHandler(juego);
		Scene scene = loginSceneHandler.getScene();
		stage.setScene(scene);
		loginSceneHandler.load();
		juego.start(stage);
		
		//		SceneHandler escena = new GameSceneHandler(this);
//		if (escena != null) {
//			Scene scene = escena.getScene();
//			this.stage.setScene(scene);
//			escena.load(true);
//		}
	}
}
