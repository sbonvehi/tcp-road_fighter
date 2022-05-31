package road_fighter;


import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import road_fighter.objects.menu.BackgroundLogin;
import road_fighter.utils.GameObjectBuilder;

public class LoginSceneHandler extends SceneHandler {


	private BackgroundLogin fondoLogin;
	
	private Group rootGroup;

	public LoginSceneHandler(RoadFighterGame g) {
		super(g);
	}

	@Override
	protected void prepareScene() {
		Group rootGroup = new Group();
		scene = new Scene(rootGroup, Config.ANCHO_FRAME_ESCENA, Config.ALTO_FRAME_ESCENA, Color.BLACK);
	}

	@Override
	protected void defineEventHandlers() {
		keyPressEventHandler = new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case ENTER:
					g.startGame(Config.MAP_IMG);
					break;
				case ESCAPE:
					System.exit(0);
					break;
				}
			}
		};
		
		keyReleaseEventHandler = new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
					///vacio para que funcione
			}
		};
		
	}
	
	public void load() {
		boolean fullStart = true;
		Group root = new Group();
		scene.setRoot(root);
		
		///Instancio todos los objectos de la partida
		fondoLogin = new BackgroundLogin(g);
		
		GameObjectBuilder gameOB = GameObjectBuilder.getInstance();
		gameOB.setRootNode(root);
		gameOB.add(fondoLogin);

		if (fullStart) {
			addTimeEventsAnimationTimer();
			addInputEvents();
		}
	}
	
	private void cleanData() {
		GameObjectBuilder.getInstance().removeAll();
//		ended = false;
//		started = false;
//		Config.baseSpeed = 250;
	}
	
	public void unload() {
		cleanData();
		super.unload();
	}

}
