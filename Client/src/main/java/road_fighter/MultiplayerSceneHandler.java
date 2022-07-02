package road_fighter;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import road_fighter.objects.menu.BackgroundMenu;
import road_fighter.objects.menu.MultiplayerRoom;
import road_fighter.utils.GameObjectBuilder;
import util.Action;

public class MultiplayerSceneHandler extends SceneHandler{

	private MultiplayerRoom fondoMenu;
	private Group rootGroup;
	

	
	public MultiplayerSceneHandler(RoadFighterGame g) {
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
				reset();
				g.getClient().actionPending = Action.WAIT;
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
		fondoMenu = new MultiplayerRoom(g, g.getClient());

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

	private void reset() {
		cleanData();
		load();
	}
	
	public void unload() {
		cleanData();
		super.unload();
	}
}
