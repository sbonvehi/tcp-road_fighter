package road_fighter;

import java.nio.file.Paths;
import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import road_fighter.interfaces.Colisionable;
import road_fighter.interfaces.Colisionador;
import road_fighter.objects.Auto;
import road_fighter.objects.Background;
import road_fighter.objects.BarraProgresoCarrera;
import road_fighter.objects.ColisionObstaculo;
import road_fighter.objects.ColisionPowerUp;
import road_fighter.objects.Enemy;
import road_fighter.objects.FinishLine;
import road_fighter.objects.ScoreBoard;
import road_fighter.utils.AudioResources;
import road_fighter.utils.GameObjectBuilder;
import usuario.Usuario;

public class GameSceneHandler extends SceneHandler {

	private Auto autoJugador;
	private Auto autoJugador2;
	private Enemy autoNPC1;
	private Enemy autoNPC2;
	private Background fondo;
	private FinishLine finishLine;
	private ColisionPowerUp powerUp1;
	private ColisionObstaculo obstaculo1;
	private BarraProgresoCarrera barraProgreso;
	private ScoreBoard scoreBoard;

	private String mapNombre;
	private AudioClip finishAudio;
	private AudioClip countdownAudio;
	public static MediaPlayer raceMusic;
	private boolean pausado = false;

	private void initAudios() {

		Media media = new Media(Paths.get(Config.RACE_MUSIC).toUri().toString());
		raceMusic = new MediaPlayer(media);
		raceMusic.setCycleCount(MediaPlayer.INDEFINITE);
		raceMusic.setVolume(0.13);

		countdownAudio = AudioResources.getCountdownAudio();
		countdownAudio.setVolume(0.15);

		finishAudio = AudioResources.getFinishAudio();
		finishAudio.setVolume(0.15);

	}

	public GameSceneHandler(RoadFighterGame g, String mapNombre) {
		super(g);
		this.mapNombre = mapNombre;
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
				case D:
					autoJugador.setDirectionRight(true);
					break;
				case A:
					autoJugador.setDirectionLeft(true);
					break;
				case V:
					if (pausado == false) {
						autoJugador.setDirectionUpSpeed1(true); // velocidad de 0 a 200
					}
					break;
				case B:
					if (pausado == false) {
						
					autoJugador.setDirectionUpSpeed2(true); // velocidad de 200 a 400
					}
					break;

				case RIGHT:
					autoJugador2.setDirectionRight(true);
					break;
				case LEFT:
					autoJugador2.setDirectionLeft(true);
					break;
				case K:
					autoJugador2.setDirectionUpSpeed1(true); // velocidad de 0 a 200
					break;

				case L:
					autoJugador2.setDirectionUpSpeed2(true); // velocidad de 200 a 400
					break;

				case Q:
					g.startMenu();
					break;
				case ESCAPE:

					if (pausado == false) {

						autoJugador.pausar();
//						if(autoJugador2)
//						autoJugador2.pausar();
						autoNPC1.pausar();
						autoNPC2.pausar();
						barraProgreso.setPausado(true);
						fondo.setPausado(true);
						raceMusic.pause();
						pausado = true;

					}

//					private Background fondo;
//					private FinishLine finishLine;
//					private ColisionPowerUp powerUp1;
//					private ColisionObstaculo obstaculo1;
//					private BarraProgresoCarrera barraProgreso;
//					private ScoreBoard scoreBoard;

					break;

				case ENTER:

					if (pausado) {
						autoJugador.resumir();
//						autoJugador2.resumir();
						autoNPC1.resumir();
						autoNPC2.resumir();
						barraProgreso.setPausado(false);
						fondo.setPausado(false);
						pausado = false;
						raceMusic.pause();
					}
//					g.startMenu();
					break;

				case T: // teletransportar el auto 2 a la pantalla donde lo vea tamb el player 1..
					// setY(auto2);
					break;
				default:
					break;

				}
			}
		};
		if (pausado == false) {
			
		keyReleaseEventHandler = new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {

				switch (event.getCode()) {
				case D:
					autoJugador.setDirectionRight(false);
					break;
				case A:
					autoJugador.setDirectionLeft(false);
					break;
				case V:
					autoJugador.setDirectionUpSpeed1(false);
					break;
				case B: // voy disminuyendo la velocidad de y hasta cero..
					autoJugador.setDirectionUpSpeed2(false);
					break;

				case RIGHT:
					autoJugador2.setDirectionRight(false);
					break;
				case LEFT:
					autoJugador2.setDirectionLeft(false);
					break;
				case K:
					autoJugador2.setDirectionUpSpeed1(false);
					break;
				case L: // voy disminuyendo la velocidad de y hasta cero..
					autoJugador2.setDirectionUpSpeed2(false);
					break;

				default:
					break;

				}
			}
		};
	}
}

	public void load(boolean fullStart) {
		Group root = new Group();
		scene.setRoot(root);

		initAudios();
		countdownAudio.play();
		new java.util.Timer().schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				if (fullStart) {
					addInputEvents();
				}
				raceMusic.play();
			}
		}, 5000);

		/// Instancio todos los objectos de la partida
		Usuario usr = new Usuario("test", "test");
		fondo = new Background(mapNombre);
		autoJugador = new Auto(usr);
		autoNPC1 = new Enemy(0, -50, 100);
		autoNPC2 = new Enemy(10, -300, 110);
		powerUp1 = new ColisionPowerUp(250, 650);
		obstaculo1 = new ColisionObstaculo(300, 200);

		finishLine = new FinishLine();
		barraProgreso = new BarraProgresoCarrera();
		scoreBoard = new ScoreBoard();

		GameObjectBuilder gameOB = GameObjectBuilder.getInstance();
		gameOB.setRootNode(root);
		gameOB.add(autoJugador, fondo, autoNPC1, autoNPC2, finishLine, powerUp1, obstaculo1, barraProgreso, scoreBoard);

		if (fullStart) {
			addTimeEventsAnimationTimer();
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
	}

	public void unload() {
		raceMusic.stop();
		cleanData();
		super.unload();
	}

	public static void apagarMusica() {
		raceMusic.stop();
	}

}
