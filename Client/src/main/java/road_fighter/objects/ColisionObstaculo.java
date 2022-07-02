package road_fighter.objects;

import javafx.geometry.Rectangle2D;


public class ColisionObstaculo extends Colision{

	static final int ANCHO_OBSTACULO = 50;
	static final int ALTO_OBSTACULO = 50;
	
	public ColisionObstaculo(int ubicacionX, int ubicacionY) {
		super(ubicacionX, ubicacionY, ANCHO_OBSTACULO, ALTO_OBSTACULO);
		render.setViewport(new Rectangle2D(355, 310, ANCHO_OBSTACULO, ALTO_OBSTACULO));
	}

}
