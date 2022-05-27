package road_fighter;
import exception.Exception_RoadFighter;
import javafx.application.Application;
import javafx.stage.Stage;

public class RoadFighterGame extends Application {
	
	private Stage stage;

//	private MenuSceneHandler menuSceneHandler;
//
//	private GameSceneHandler gameSceneHandler;

	public static void main(String[] args) throws InterruptedException, Exception_RoadFighter {

//		Menu menu = new Menu(new GestorLogin("./archivoLogin/usuarios.txt"));
//
//		menu.primeraPantalla();
//		
//		menu.segundaPantalla();
		
		launch();
		
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		this.stage = stage;

//		menuSceneHandler = new MenuSceneHandler(this);
//		Scene scene = menuSceneHandler.getScene();
//		stage.setScene(scene);
//
//		menuSceneHandler.load();
		
		// XXX patron state para controlar paso de escenas?

		// Scale
		// TODO scale and fill to maintain proportion (also center)
		// scale = new Scale();
		// dinamico, cada vez que cambio el tamaÃ±o de ventana
		// scale.setX(scene.getWidth() / WIDTH);
		// scale.setY(scene.getHeight() / HEIGHT);
		// images.getTransforms().add(scale);

//		stage.getIcons().add(new Image("file:src/main/resources/ico/logo.png"));
		stage.setTitle("Road Fighter");
		stage.show();
	}
}
