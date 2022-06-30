package road_fighter.objects;

import coordenada.Coordenada;
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

public class Enemy extends GameObject implements Actualizable, Renderizable, Colisionable {

	protected Image carImage = new Image(Config.BLUE_CAR, Config.ANCHO_AUTO, Config.ALTO_AUTO, false, false);
	protected Coordenada ubicacion;
	protected double velocidad;

	protected ImageView render;
	protected Rectangle collider;

	protected static final int posXAutoInicial = 260;
	protected static final int posYAutoInicial = 260;

	protected double OFFSET_X_POSICION;
	protected double OFFSET_Y_POSICION;

	public Enemy(double x, double y, double velocidad) {
		OFFSET_X_POSICION = x;
		OFFSET_Y_POSICION = y;
		ubicacion = new Coordenada(OFFSET_X_POSICION, OFFSET_Y_POSICION);
		this.velocidad = velocidad;

		collider = new Rectangle(Config.ANCHO_AUTO, Config.ALTO_AUTO);
		collider.setFill(null);
		collider.setStroke(Color.FUCHSIA);

		render = new ImageView(carImage);
		render.setViewport(new Rectangle2D(0, 0, Config.ANCHO_AUTO, Config.ALTO_AUTO));

		/// ubicacion inicial (tiene por defecto una posicion y se le ajusta segun los
		/// parametros del constructor)

		render.setX(posXAutoInicial + OFFSET_X_POSICION);
		render.setY(posYAutoInicial + OFFSET_Y_POSICION);
		collider.setX(posXAutoInicial + OFFSET_X_POSICION);
		collider.setY(posYAutoInicial + OFFSET_Y_POSICION);
	}

	public Node getRender() {
		return render;
	}

	public void setY(double y) {
		this.ubicacion.setY(y);
		render.setY(y);
		collider.setY(y);

	}

	@Override
	public void update(double deltaTime) {
		double difVelocidadAutos = velocidad - Auto.getVelocidad();
		// FACTOR_DESPLAZAMIENTO es para que el autoNPC se mueva proporcionalmente en
		// relacion al mapa
		setY(this.ubicacion.getY() - difVelocidadAutos * deltaTime * Background.FACTOR_DESPLAZAMIENTO);
	}

	@Override
	public void destroy() {
	}

	@Override
	public Shape getCollider() {
		return collider;
	}

}