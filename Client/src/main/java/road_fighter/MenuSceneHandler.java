package road_fighter;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import road_fighter.objects.menu.BackgroundMenu;
import road_fighter.utils.GameObjectBuilder;
import util.Action;

public class MenuSceneHandler extends SceneHandler{

	private BackgroundMenu fondoMenu;
	private Group rootGroup;
	

	
	public MenuSceneHandler(RoadFighterGame g) {
		super(g);

	}

	
	
	@Override
	protected void prepareScene() {
		rootGroup = new Group();
		scene = new Scene(rootGroup, Config.ANCHO_FRAME_ESCENA, Config.ALTO_FRAME_ESCENA, Color.BLACK);
	}

	@Override
	protected void defineEventHandlers() {
		keyPressEventHandler = new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case F1:
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
	
	@Override
	public void update(double delta) {
		super.update(delta);

		if(g.getClient() != null && g.getClient().actionPending != null)
		{
			switch (g.getClient().actionPending) {
			case WAIT:
				break;

			case JOIN_ROOM:
				g.getClient().actionPending = Action.WAIT;
				g.startMultiplayer();
				break;

			default:
				break;
			}
		}
	}
	
	public void load() {
		boolean fullStart = true;
		Group root = new Group();
		scene.setRoot(root);
	
		
		///Instancio todos los objectos de la partida
		fondoMenu = new BackgroundMenu(g);

		GameObjectBuilder gameOB = GameObjectBuilder.getInstance();
		gameOB.setRootNode(root);
		gameOB.add(fondoMenu);

		if (fullStart) {
			addTimeEventsAnimationTimer();
			addInputEvents();
		}
	}

	private void cleanData() {
		GameObjectBuilder.getInstance().removeAll();

	}
	
	public void unload() {
		cleanData();
		super.unload();
	}
}