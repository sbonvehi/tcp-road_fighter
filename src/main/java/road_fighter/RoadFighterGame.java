package road_fighter;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import usuario.Usuario;

public class RoadFighterGame extends Application {

	private Stage stage;

	private LoginSceneHandler loginSceneHandler;
	private MenuSceneHandler menuSceneHandler;
	private GameSceneHandler gameSceneHandler;

	public static Usuario anfitrion;

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
		stage.getIcons().add(new Image(Config.ICON_IMG));
	}

	public static void main(String[] args) {
		launch();
	}

	public void startLogin() {
		gameSceneHandler.unload();
		loginSceneHandler = new LoginSceneHandler(this);
		Scene scene = loginSceneHandler.getScene();
		stage.setScene(scene);
		loginSceneHandler.load();
	}

	public void startMenu() {
		loginSceneHandler.unload();
		menuSceneHandler = new MenuSceneHandler(this);
		Scene scene = menuSceneHandler.getScene();
		stage.setScene(scene);
		menuSceneHandler.load();
	}

	public void menuFromGame() {
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
		RoadFighterGame.anfitrion = new Usuario("JUGADOR 1", "JUGADOR 1");
		loginSceneHandler.unload();
		gameSceneHandler = new GameSceneHandler(this, map);
		Scene scene = gameSceneHandler.getScene();
		stage.setScene(scene);
		gameSceneHandler.load(true);
	}

	public void logout() {
		RoadFighterGame.anfitrion = null;

		menuSceneHandler.unload();
		loginSceneHandler = new LoginSceneHandler(this);
		Scene scene = loginSceneHandler.getScene();
		stage.setScene(scene);
		loginSceneHandler.load();
	}

	public Usuario getAnfitrion() {
		return anfitrion;
	}

	public void setAnfitrion(Usuario anfitrion) {
		RoadFighterGame.anfitrion = anfitrion;
	}
}
