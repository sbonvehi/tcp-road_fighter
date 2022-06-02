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

public class FinishLine extends GameObject implements Actualizable, Renderizable, Colisionable{
	
	private Rectangle collider;
	private Image imagen;
	private ImageView render;
	
	private double posY = 0;
	
	private int ANCHO_FINISH_LINE = Background.ANCHO_CALLE + 45;
	private int ALTO_FINISH_LINE = 50;
	
	public FinishLine() {
		collider = new Rectangle(ANCHO_FINISH_LINE, ALTO_FINISH_LINE);
		collider.setFill(null);
		
		
		imagen = new Image(Config.FINISH_LINE_IMG, ANCHO_FINISH_LINE, ALTO_FINISH_LINE, false, false);
		render = new ImageView(imagen);
		render.setViewport(new Rectangle2D(0, 0, ANCHO_FINISH_LINE, ALTO_FINISH_LINE));
		
		
		render.setX(Background.MARGEN_IZQ_CALLE - 15);
		render.setY(Auto.posYAutoInicial - Background.LARGO_MAPA*Background.FACTOR_DESPLAZAMIENTO );
		render.setViewOrder(25);
		collider.setStroke(Color.FUCHSIA);
		collider.setX(Background.MARGEN_IZQ_CALLE - 15);
		collider.setY(Auto.posYAutoInicial - Background.LARGO_MAPA*Background.FACTOR_DESPLAZAMIENTO - Config.ALTO_AUTO);
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

	
}
