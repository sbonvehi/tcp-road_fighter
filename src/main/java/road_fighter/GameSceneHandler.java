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
import road_fighter.objects.AutoCompetidor;
import road_fighter.objects.Background;
import road_fighter.objects.BarraProgresoCarrera;
import road_fighter.objects.ColisionObstaculo;
import road_fighter.objects.ColisionPowerUp;
import road_fighter.objects.Enemy;
import road_fighter.objects.FinishLine;
import road_fighter.objects.ScoreBoard;
import road_fighter.utils.AudioResources;
import road_fighter.utils.GameObjectBuilder;


public class GameSceneHandler extends SceneHandler {
	
	private Auto autoJugador1;
	private AutoCompetidor autoCompetidor;
	private Enemy autoNPC1;
	private Enemy autoNPC2;
	private Background fondo;
	private FinishLine finishLine;
	private ColisionPowerUp powerUp1;
	private ColisionPowerUp powerUp2;
	private ColisionObstaculo obstaculo1;
	private ColisionObstaculo obstaculo2;
	private ColisionObstaculo obstaculo3;
	private BarraProgresoCarrera barraProgreso;
	private ScoreBoard scoreBoard;

	
	private static long cronometro;
	private String mapNombre;
	static private AudioClip finishAudio;
	static private AudioClip gameOverAudio;
	private AudioClip countdownAudio;
	public static MediaPlayer raceMusic;

	public static final int MARGEN_IZQ_CALLE = 155;
	public static final int MARGEN_DER_CALLE = 378;
	public static final int LARGO_MAPA = 10000;
	
	
	
	private void initAudios() {
		
		Media media = new Media(Paths.get(Config.RACE_MUSIC).toUri().toString()); 
		raceMusic = new MediaPlayer(media); 
		raceMusic.setCycleCount(MediaPlayer.INDEFINITE);
		raceMusic.setVolume(0.13);
		
		countdownAudio = AudioResources.getCountdownAudio();
		countdownAudio.setVolume(0.15);
		
		finishAudio = AudioResources.getFinishAudio();
		finishAudio.setVolume(0.15);
		
		gameOverAudio = AudioResources.getGameOverAudio();
		gameOverAudio.setVolume(0.15);

	}
	
	public static void reproducirMusicaVictoria() {
		finishAudio.play();
		new java.util.Timer().schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				gameOverAudio.play();
			}
		}, 7000);
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
					autoJugador1.setDireccionDerecha(true);
					break;
				case A:
					autoJugador1.setDireccionIzquierda(true);
					break;
				case V:
					autoJugador1.setDireccionVelocidad1(true); // velocidad de 0 a 200.
					break;
				case B:
					autoJugador1.setDireccionVelocidad2(true); // velocidad de 200 a 400.
					break;
					
				case RIGHT:
					autoCompetidor.setDireccionDerecha(true);
					break;
				case LEFT:
					autoCompetidor.setDireccionIzquierda(true);
					break;
				case K:
					autoCompetidor.setDireccionVelocidad1(true); // velocidad de 0 a 200.
					break;

				case L:
					autoCompetidor.setDireccionVelocidad2(true); // velocidad de 200 a 400.
					break;
									
				case Q:
				case ESCAPE:
					g.menuFromGame();
					break;
				default:
					break;

				}
			}
		};
		
		keyReleaseEventHandler = new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {

	 			switch (event.getCode()) {
				case D:
					autoJugador1.setDireccionDerecha(false);
					break;
				case A:
					autoJugador1.setDireccionIzquierda(false);
					break;
				case V:
					autoJugador1.setDireccionVelocidad1(false);
					break;
				case B: // voy disminuyendo la velocidad de y hasta cero.
					autoJugador1.setDireccionVelocidad2(false);
					break;
					
				case RIGHT:
					autoCompetidor.setDireccionDerecha(false);
					break;
				case LEFT:
					autoCompetidor.setDireccionIzquierda(false);
					break;
				case K:
					autoCompetidor.setDireccionVelocidad1(false);
					break;
				case L: // voy disminuyendo la velocidad de y hasta cero.
					autoCompetidor.setDireccionVelocidad2(false);
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
		
		
		initAudios();
		countdownAudio.play();
		new java.util.Timer().schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				if (fullStart) {
					addInputEvents();
				}
				raceMusic.play();
			 	GameSceneHandler.cronometro = System.currentTimeMillis();
				
			}
		}, 5000);
		
		///Instancio todos los objectos de la partida.
		
		autoJugador1 = new Auto(RoadFighterGame.anfitrion, -40);
		autoCompetidor = new AutoCompetidor("JUGADOR 2", 30, 650, 10);
		fondo = new Background(mapNombre);
		autoNPC1 = new Enemy(0,-50, 100);
		autoNPC2 = new Enemy(10,-300, 110);
			
		powerUp1 = new ColisionPowerUp(getRandomX(), getRandomY());
		powerUp2 = new ColisionPowerUp(getRandomX(), getRandomY());
		
		obstaculo1 = new ColisionObstaculo(getRandomX(), getRandomY());
		obstaculo2 = new ColisionObstaculo(getRandomX(), getRandomY());
		obstaculo3 = new ColisionObstaculo(getRandomX(), getRandomY());
				
		finishLine = new FinishLine();
		barraProgreso = new BarraProgresoCarrera();
		scoreBoard = new ScoreBoard();
		
		
		GameObjectBuilder gameOB = GameObjectBuilder.getInstance();
		gameOB.setRootNode(root);
		gameOB.add(autoJugador1, autoCompetidor, fondo, autoNPC1, autoNPC2, finishLine, powerUp1, powerUp2, obstaculo1, obstaculo2,obstaculo3, barraProgreso, scoreBoard);
	
		if (fullStart) {
			addTimeEventsAnimationTimer();
		}
	}
	
	
	public void update(double delta) {
		super.update(delta);
		checkColliders();
	}
	
	public static String getTiempoMeta() {
		long time2 = System.currentTimeMillis();
	      double tiempo = time2 - cronometro;
	      tiempo /= 1000;
	      tiempo %= 3600;
	      long minutos =(long)(tiempo / 60);  
	      long segundos =(long)( tiempo % 60);
	      tiempo %= 60;
	      tiempo -= segundos;
	      long miliSegundos = (long)(tiempo * 100) % 60;	      
	      return String.format("%02d:%02d:%02d", minutos, segundos, miliSegundos); 
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
	
	public int getRandomX() {
		return (int)(Math.random()*(MARGEN_DER_CALLE-MARGEN_IZQ_CALLE+1)+MARGEN_IZQ_CALLE);
	}
	
	public int getRandomY() {
		return (int)(Math.random()*(LARGO_MAPA-300+1)+300);
	}
}
