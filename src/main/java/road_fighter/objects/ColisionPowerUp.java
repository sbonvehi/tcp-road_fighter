package road_fighter.objects;

import javafx.geometry.Rectangle2D;
import road_fighter.Config;
import road_fighter.utils.GameObjectBuilder;

public class ColisionPowerUp extends Colision {

	static final int ANCHO_POWERUP = Config.ANCHO_AUTO;
	static final int ALTO_POWERUP = Config.ALTO_AUTO;

	public ColisionPowerUp(int ubicacionX, int ubicacionY) {
		super(ubicacionX, ubicacionY, ANCHO_POWERUP, ALTO_POWERUP);
		render.setViewport(new Rectangle2D(26 * 3, 34 * 3, ANCHO_POWERUP, ALTO_POWERUP));
	}

	public void remove() {
		GameObjectBuilder.getInstance().remove(this);
	}

}
