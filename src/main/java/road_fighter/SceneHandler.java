package road_fighter;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import road_fighter.interfaces.Actualizable;
import road_fighter.utils.GameObjectBuilder;

public abstract class SceneHandler {

	protected final long NANOS_IN_SECOND = 1_000_000_000;
	protected final double NANOS_IN_SECOND_D = 1_000_000_000.0;	
	
	protected int frames = 0;
	protected int last_fps_frame = 0;
	protected AtomicInteger fps = new AtomicInteger(0);
	
	
	protected AnimationTimer gameTimer;
	protected long previousNanoFrame;
	protected long previousNanoSecond;
	protected RoadFighterGame g;

	protected Scene scene;
	
	protected EventHandler<KeyEvent> keyPressEventHandler;
	protected EventHandler<KeyEvent> keyReleaseEventHandler;
	protected EventHandler<MouseEvent> mouseEventHandler;

	public SceneHandler(RoadFighterGame g) {
			this.g = g;
			prepareScene();
			defineEventHandlers();
	}
	
	public void oneSecondUpdate(double delta) {
		fps.set(frames - last_fps_frame);
		last_fps_frame = frames;
	}

	public Scene getScene() {
		return scene;
	}
	
	public void update(double deltaTime) {
		frames++;
		List<Actualizable> actualizables = GameObjectBuilder.getInstance().getUpdatables();
		
		for (Actualizable actualizable : actualizables) {
			actualizable.update(deltaTime);		
		}

		
	}

	protected void addTimeEventsAnimationTimer() {
		previousNanoFrame = System.nanoTime();
		gameTimer = new AnimationTimer() {
			@Override
			public void handle(long currentNano) {
				//update Tick
				update((currentNano - previousNanoFrame) / 1_000_000_000.0);
				previousNanoFrame = currentNano;
			}
		};
		
		gameTimer.start();	
	}

	protected void addInputEvents() {
		scene.addEventHandler(KeyEvent.KEY_PRESSED, keyPressEventHandler);
		scene.addEventHandler(KeyEvent.KEY_RELEASED, keyReleaseEventHandler);
	}

	protected void removeInputEvents() {
		scene.removeEventHandler(KeyEvent.KEY_PRESSED, keyPressEventHandler);
		scene.removeEventHandler(KeyEvent.KEY_RELEASED, keyReleaseEventHandler);
	}
	
	protected abstract void prepareScene();
	protected abstract void defineEventHandlers();

	protected void unload() {
		GameObjectBuilder.getInstance().removeAll();
		gameTimer.stop();
		removeInputEvents();
	}
	
}
