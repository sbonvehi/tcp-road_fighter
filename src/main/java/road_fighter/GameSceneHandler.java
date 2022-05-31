package road_fighter;

import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import road_fighter.interfaces.Colisionable;
import road_fighter.interfaces.Colisionador;
import road_fighter.objects.Auto;
import road_fighter.objects.Background;
import road_fighter.objects.Enemy;
import road_fighter.objects.FinishLine;
import road_fighter.utils.GameObjectBuilder;
import usuario.Usuario;


public class GameSceneHandler extends SceneHandler {
	
	private Auto autoJugador;
	private Enemy autoNPC1;
	private Enemy autoNPC2;
	private Background fondo;
	private FinishLine finishLine;
	
	public GameSceneHandler(RoadFighterGame g) {
		super(g);
	}

	protected void prepareScene() {
		Group rootGroup = new Group();
		scene = new Scene(rootGroup, Config.ANCHO_FRAME_ESCENA, Config.ALTO_FRAME_ESCENA, Color.BLACK);
	}
	
	@Override
	protected void defineEventHandlers() {
		keyPressEventHandler = new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {

				switch (event.getCode()) {
				case RIGHT:
				case D:
					autoJugador.setDirectionRight(true);
					break;

				case LEFT:
				case A:
					autoJugador.setDirectionLeft(true);
					break;

				case Z:
					autoJugador.setDirectionUpSpeed1(true); // velocidad de 0 a 200
					break;

				case X:
					autoJugador.setDirectionUpSpeed2(true); // velocidad de 200 a 400
					break;

				default:
					break;

				}
			}
		};
		
		keyReleaseEventHandler = new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {

				switch (event.getCode()) {
				case RIGHT:
				case D:
					autoJugador.setDirectionRight(false);
					break;

				case LEFT:
				case A:
					autoJugador.setDirectionLeft(false);
					break;

				case X:
				case Z: // voy disminuyendo la velocidad de y hasta cero..
					autoJugador.setDirectionUpSpeed1(false);
					autoJugador.setDirectionUpSpeed2(false);
					break;
				default:
					break;

				}
			}
		};
	}
	
	public void load(boolean fullStart) {
		Group root = new Group();
		scene.setRoot(root);
		
		
		///Instancio todos los objectos de la partida
		Usuario usr = new Usuario("test","test");
		fondo = new Background();
		autoJugador = new Auto(usr);
		autoNPC1 = new Enemy(0,-50, 100);
		autoNPC2 = new Enemy(10,-300, 110);
		finishLine = new FinishLine();
	
		GameObjectBuilder gameOB = GameObjectBuilder.getInstance();
		gameOB.setRootNode(root);
		gameOB.add(autoJugador, fondo, autoNPC1, autoNPC2, finishLine);

		if (fullStart) {
			addTimeEventsAnimationTimer();
			addInputEvents();
		}
	}
	
	public void update(double delta) {
		super.update(delta);
		checkColliders();
	}
	
	private void checkColliders() {
		List<Colisionador> collidators = GameObjectBuilder.getInstance().getCollidators();
		List<Colisionable> collideables = GameObjectBuilder.getInstance().getCollideables();

		for (int i = 0; i < collidators.size(); i++) {
			Colisionador collidator = collidators.get(i);
			for (int j = i + 1; j < collidators.size(); j++) {
				Colisionador otherCollidator = collidators.get(j);
				Shape intersect = Shape.intersect(collidator.getCollider(), otherCollidator.getCollider());
				if (intersect.getBoundsInLocal().getWidth() != -1) {
					collidator.colisionar(otherCollidator);
					otherCollidator.colisionar(collidator);
				}
			}

			for (int j = 0; j < collideables.size(); j++) {
				Colisionable collideable = collideables.get(j);
				Shape intersect = Shape.intersect(collidator.getCollider(), collideable.getCollider());

				if (intersect.getBoundsInLocal().getWidth() != -1) {
					collidator.colisionar(collideable);
				} else {
					// Check contains
					Bounds collideableBounds = collideable.getCollider().getBoundsInLocal();
					Bounds collidatorBounds = collidator.getCollider().getBoundsInLocal();
					if (collideableBounds.contains(collidatorBounds.getCenterX(), collidatorBounds.getCenterY())) {
						collidator.colisionar(collideable);
					}
				}
			}
		}
	}

	public void restart() {
		cleanData();
		load(false);
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
