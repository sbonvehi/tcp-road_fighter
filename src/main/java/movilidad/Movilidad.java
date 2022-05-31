package movilidad;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import road_fighter.Config;
import road_fighter.GameSceneHandler;
import road_fighter.LoginSceneHandler;
import road_fighter.objects.Auto;
import road_fighter.objects.Background;
import road_fighter.objects.Enemy;
import usuario.Usuario;

////////////////////////////////////////////////
////////////////////////////////////////////////
// Esto ERA el main, ahora el main est� en RoadFighterGame
////////////////////////////////////////////////
////////////////////////////////////////////////
public class Movilidad extends Application{

	private Scene currentScene;
	private Auto autoJugador;
	Enemy autoNPC;
	private Background fondo;
	private long previousNanoFrame;
	

	@Override 
	public void start(Stage stage) {

//		Group root = new Group();
//		currentScene = new Scene(root, Config.ANCHO_FRAME_ESCENA , Config.ALTO_FRAME_ESCENA);
//		Usuario usr = new Usuario("test","test");
//		
		
		
//		fondo = new Background();
//		root.getChildren().add(fondo.getRender());
//		
//		autoJugador = new Auto(usr);
//		root.getChildren().add(autoJugador.getRender());
//		
//		autoNPC = new Enemy(100,100);
//		root.getChildren().add(autoNPC.getRender());
//		 
//		addInputEvents();
//		
//		addUpdateEachFrameTimer();
		
		
//		stage.setResizable(false);
//		stage.setTitle("Road Fighter");
//		stage.setScene(currentScene);
//		stage.show();
//		
		
	}
	

	
//	private void addUpdateEachFrameTimer() {
//		previousNanoFrame = System.nanoTime();
//		AnimationTimer gameTimer = new AnimationTimer() {
//			@Override
//			public void handle(long currentNano) {
//				//update Tick
//				update((currentNano - previousNanoFrame) / 1_000_000_000.0);
//				previousNanoFrame = currentNano;
//			}
//		};
//		
//		gameTimer.start();		
//	}
	
//
//	public void update(double deltaTime) {
//		double velocidadAuto = autoJugador.getVelocidad();
//		
//		
//		autoJugador.update(deltaTime);
//		fondo.update(deltaTime, velocidadAuto);
//		autoNPC.update(deltaTime, velocidadAuto);
//		
//	}
//	
	
//	private void addInputEvents() {
//		currentScene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
//			public void handle(KeyEvent event) {
//
//				switch (event.getCode()) {
//				case RIGHT:
//				case D:
//					autoJugador.setDirectionRight(true);
//					break;
//
//				case LEFT:
//				case A:
//					autoJugador.setDirectionLeft(true);
//					break;
//
//				case Z:
//					autoJugador.setDirectionUpSpeed1(true); // velocidad de 0 a 200
//					break;
//
//				case X:
//					autoJugador.setDirectionUpSpeed2(true); // velocidad de 200 a 400
//					break;
//
//				default:
//					break;
//
//				}
//			}
//		});
//
//		currentScene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
//			public void handle(KeyEvent event) {
//
//				switch (event.getCode()) {
//				case RIGHT:
//				case D:
//					autoJugador.setDirectionRight(false);
//					break;
//
//				case LEFT:
//				case A:
//					autoJugador.setDirectionLeft(false);
//					break;
//
//				case X:
//				case Z: // voy disminuyendo la velocidad de y hasta cero..
//					autoJugador.setDirectionUpSpeed1(false);
//					autoJugador.setDirectionUpSpeed2(false);
//					break;
//
//				default:
//					break;
//
//				}
//			}
//		});
//	}

//	public static void main(String[] args) {
//		launch();
//	}
	
}
