package auto;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import usuario.Usuario;

////////////////////////////////////////////////
////////////////////////////////////////////////
// Esto es el main de la app.
////////////////////////////////////////////////
////////////////////////////////////////////////
public class Movilidad extends Application{

	Scene currentScene;
	Auto autoJugador;
	
	@Override
	public void start(Stage stage) {

		Group root = new Group();
		currentScene = new Scene(root);
		Usuario usr = new Usuario("test","test");
		autoJugador = new Auto(usr);

		addInputEvents();

	}

	private void addInputEvents() {
		currentScene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
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
		});

		currentScene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
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
		});
	}

	public static void main(String[] args) {
		launch();
	}
	
}
