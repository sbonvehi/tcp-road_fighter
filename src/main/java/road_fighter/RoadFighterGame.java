package road_fighter;
import exception.Exception_RoadFighter;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import road_fighter.objects.Auto;
import road_fighter.objects.Background;
import road_fighter.objects.Enemy;
import road_fighter.utils.AudioResources;
import usuario.Usuario;


public class RoadFighterGame extends Application {
	
	private Stage stage;


	private LoginSceneHandler loginSceneHandler;
	private MenuSceneHandler menuSceneHandler;
	private GameSceneHandler gameSceneHandler;
	
	protected Usuario anfitrion;


	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		
		loginSceneHandler = new LoginSceneHandler(this);
		Scene scene = loginSceneHandler.getScene();
		stage.setScene(scene);
		loginSceneHandler.load();	
		
		stage.setResizable(false);
		stage.setTitle("Road Fighter");
		stage.show();
	}
	
	public static void main(String[] args){
		launch();
	}
	
	public void startLogin() {
		gameSceneHandler.unload();
		loginSceneHandler = new LoginSceneHandler(this);
		Scene scene = loginSceneHandler.getScene();
		stage.setScene(scene);
		loginSceneHandler.load();
	}
	
	public void startMenu()
	{	
		loginSceneHandler.unload();
		menuSceneHandler = new MenuSceneHandler(this);
		Scene scene = menuSceneHandler.getScene();
		stage.setScene(scene);
		menuSceneHandler.load();
	}
	
	public void menuFromGame()
	{
		gameSceneHandler.unload();
		menuSceneHandler = new MenuSceneHandler(this);
		Scene scene = menuSceneHandler.getScene();
		stage.setScene(scene);
		menuSceneHandler.load();
	}
	
	public void startGame(String map) {
		menuSceneHandler.unload();
		gameSceneHandler = new GameSceneHandler(this, map);
		Scene scene = gameSceneHandler.getScene();
		stage.setScene(scene);
		gameSceneHandler.load(true);
	}
	
	public void startGameWithoutLogin(String map) {
		loginSceneHandler.unload();
		gameSceneHandler = new GameSceneHandler(this, map);
		Scene scene = gameSceneHandler.getScene();
		stage.setScene(scene);
		gameSceneHandler.load(true);
	}
	
	public void logout()
	{
		this.anfitrion = null;
		
		menuSceneHandler.unload();
		loginSceneHandler = new LoginSceneHandler(this);
		Scene scene = loginSceneHandler.getScene();
		stage.setScene(scene);
		loginSceneHandler.load();
	}
	
	public Usuario getAnfitrion()
	{
		return anfitrion;
	}
	
	public void setAnfitrion(Usuario anfitrion)
	{
		this.anfitrion = anfitrion;
	}
}
