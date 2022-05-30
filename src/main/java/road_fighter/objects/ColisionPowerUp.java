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

public class ColisionPowerUp extends GameObject implements Actualizable, Renderizable, Colisionable{

	private Image spriteGeneral;
	private ImageView render;
	private Rectangle collider;
	private double posY = 0;
	private int ubicacionX = 100;
	private int ubicacionY = 100;
	
	public ColisionPowerUp(int ubicacionX, int ubicacionY) {
		
		this.ubicacionX = ubicacionX;
		this.ubicacionY = ubicacionY;
		
		collider = new Rectangle(Config.ANCHO_AUTO, Config.ALTO_AUTO);
		collider.setFill(null);
		
		spriteGeneral = new Image(Config.GENERAL_SPRITES_IMG, 328 * 3, 179 * 3, false, false);
		render = new ImageView(spriteGeneral);
		render.setViewport(new Rectangle2D(26 * 3, 34 * 3, Config.ANCHO_AUTO, Config.ALTO_AUTO));
		
		render.setX(ubicacionX);
		render.setY(Auto.posYAutoInicial - ubicacionY *Background.FACTOR_DESPLAZAMIENTO);
		render.setViewOrder(15);
		collider.setStroke(Color.FUCHSIA);
		collider.setX(ubicacionX);
		collider.setY(Auto.posYAutoInicial - ubicacionY *Background.FACTOR_DESPLAZAMIENTO);

			
	}
	
	@Override
	public void update(double deltaTime) {
		posY += Auto.getVelocidad() * deltaTime;
		render.setTranslateY(posY * Background.FACTOR_DESPLAZAMIENTO ); //FACTOR_DESPLAZAMIENTO es un numero que uso para que se mueva el mapa en conjunto con la meta
		collider.setTranslateY(posY * Background.FACTOR_DESPLAZAMIENTO );		
	}

	@Override
	public Shape getCollider() {
		return collider;
	}

	@Override
	public Node getRender() {
		return render;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	
//	26 * 3, 34 * 3,
}
