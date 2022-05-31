package road_fighter.objects;

import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import road_fighter.Config;
import road_fighter.interfaces.Actualizable;
import road_fighter.interfaces.Colisionable;
import road_fighter.interfaces.Renderizable;
import road_fighter.utils.GameObject;

public class ColisionPowerUp extends Colision{
	
	static final int ANCHO_POWERUP = Config.ANCHO_AUTO;
	static final int ALTO_POWERUP = Config.ALTO_AUTO;
	
	public ColisionPowerUp(int ubicacionX, int ubicacionY) {
		super(ubicacionX, ubicacionY, ANCHO_POWERUP, ALTO_POWERUP);

		render.setViewport(new Rectangle2D(26 * 3, 34 * 3, ANCHO_POWERUP, ALTO_POWERUP));		
	}
	

}
