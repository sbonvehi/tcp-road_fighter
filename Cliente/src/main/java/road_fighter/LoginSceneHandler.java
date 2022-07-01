package road_fighter;


import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import road_fighter.objects.LoginForm;
import road_fighter.objects.menu.BackgroundLogin;
import road_fighter.utils.AudioResources;
import road_fighter.utils.GameObjectBuilder;

public class LoginSceneHandler extends SceneHandler {


	private BackgroundLogin fondoLogin;
	private LoginForm form;
	
	private Group rootGroup;
	private AudioClip startAudio;
	
	public LoginSceneHandler(RoadFighterGame g) {
		super(g);
	}
	
	private void initAudios() {	
		startAudio = AudioResources.getStartAudio();
		startAudio.setVolume(0.15);
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
					g.startGameWithoutLogin(Config.MAP_IMG);
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
		
		
		initAudios();
		startAudio.play();
		
		///Instancio todos los objectos de la partida
		fondoLogin = new BackgroundLogin();
		form = new LoginForm(g);
		
		GameObjectBuilder gameOB = GameObjectBuilder.getInstance();
		gameOB.setRootNode(root);
		gameOB.add(fondoLogin, form);

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
