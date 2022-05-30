package road_fighter;
import exception.Exception_RoadFighter;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import road_fighter.objects.Auto;
import road_fighter.objects.Background;
import road_fighter.objects.Enemy;
import usuario.Usuario;

public class RoadFighterGame extends Application {
	
	private Stage stage;


	private MenuSceneHandler menuSceneHandler;
	private GameSceneHandler gameSceneHandler;


	
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		
		menuSceneHandler = new MenuSceneHandler(this);
		Scene scene = menuSceneHandler.getScene();
		stage.setScene(scene);

		menuSceneHandler.load();
				
		stage.setResizable(false);
		stage.setTitle("Road Fighter");
		stage.show();
	}
	
	public static void main(String[] args){
		launch();
	}
	
	public void startGame() {
		menuSceneHandler.unload();
		gameSceneHandler = new GameSceneHandler(this);
		Scene scene = gameSceneHandler.getScene();
		stage.setScene(scene);
		gameSceneHandler.load(true);
	}
	
	public void startMenu() {
		gameSceneHandler.unload();
		menuSceneHandler = new MenuSceneHandler(this);
		Scene scene = menuSceneHandler.getScene();
		stage.setScene(scene);
		menuSceneHandler.load();
	}
	
}
