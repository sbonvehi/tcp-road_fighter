package road_fighter;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import road_fighter.objects.menu.BackgroundLogin;
import road_fighter.objects.menu.BackgroundMenu;
import road_fighter.utils.GameObjectBuilder;

public class MenuSceneHandler extends SceneHandler{

	private BackgroundMenu fondoMenu;
	
	public MenuSceneHandler(RoadFighterGame g) {
		super(g);

	}

	@Override
	protected void prepareScene() {
		Group rootGroup = new Group();
		scene = new Scene(rootGroup, Config.ANCHO_FRAME_ESCENA, Config.ALTO_FRAME_ESCENA, Color.BLACK);
	}

	@Override
	protected void defineEventHandlers() {
		// TODO Auto-generated method stub
		
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
//		ended = false;
//		started = false;
//		Config.baseSpeed = 250;
	}
	
	public void unload() {
		cleanData();
		super.unload();
	}
}
