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

public class ColisionObstaculo extends Colision{

	static final int ANCHO_OBSTACULO = 50;
	static final int ALTO_OBSTACULO = 50;
	
	public ColisionObstaculo(int ubicacionX, int ubicacionY) {
		super(ubicacionX, ubicacionY, ANCHO_OBSTACULO, ALTO_OBSTACULO);

		render.setViewport(new Rectangle2D(355, 310, ANCHO_OBSTACULO, ALTO_OBSTACULO));
	}


}
