package road_fighter.objects;

import java.util.concurrent.TimeUnit;

import animation.SpriteAnimation;
import colision.colisionObstaculo;
import coordenada.Coordenada;
import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import road_fighter.Config;
import road_fighter.interfaces.Actualizable;
import road_fighter.interfaces.Colisionable;
import road_fighter.interfaces.Colisionador;
import road_fighter.interfaces.Renderizable;
import road_fighter.utils.GameObject;
import usuario.Usuario;

public class Auto extends GameObject implements Actualizable, Renderizable, Colisionador {

	private static int cantAutos = 0;

	private static final double VELOCIDAD_INICIAL = 0.0000000001;
	private final int VELOCIDAD_MAX1 = 200;
	private final int VELOCIDAD_MAX2 = 400;
	private final int TASA_ACELERACION = 2;
	private final int TASA_FRENADO = 1;
	private final int VEL_HORIZONTAL = 300;

	private Image spriteImages;
	private SpriteAnimation crash;
	private int multiplic = 3;
	private int anchoAuto = 14;
	private int altoAuto = 19;
	private int espaciador = 2;
	private int anchoImagen = 328;
	private int altoImagen = 179;

	public static final int posXAutoInicial = 270;
	public static final int posYAutoInicial = 650;
	private boolean flagFueraDeMapa = false;

	private int topeVelocidad = VELOCIDAD_MAX1;
	private static double velocidad = VELOCIDAD_INICIAL;

	private Coordenada ubicacion;
	private Usuario piloto;

	private boolean directionLeft = false;
	private boolean directionRight = false;
	private boolean directionUpSpeed1 = false;
	private boolean directionUpSpeed2 = false;

	private Rectangle collider;
	private ImageView render;
	private boolean tieneModificadorVelocidad = false;
	private boolean dead;

	public static double getVelocidad() {
		return velocidad;
	}

	public Auto(Usuario piloto) {
		collider = new Rectangle(Config.ANCHO_AUTO, Config.ANCHO_AUTO);
		collider.setFill(Color.DARKBLUE);
		collider.setStroke(Color.FUCHSIA);

		cantAutos++;
		this.ubicacion = new Coordenada(posXAutoInicial, 0);
		this.piloto = piloto;

		spriteImages = new Image(Config.GENERAL_SPRITES_IMG, anchoImagen * multiplic, altoImagen * multiplic, false,
				false);
		render = new ImageView(spriteImages);
		render.setViewport(new Rectangle2D(espaciador, espaciador, anchoAuto * multiplic, altoAuto * multiplic));
		render.setViewOrder(5);

		render.relocate(-Config.ANCHO_AUTO / 2, -Config.ALTO_AUTO / 2); // Ancla del render en la mitad del auto..

		initSpriteAnimations();

		/// ubicacion inicial
		render.setX(posXAutoInicial);
		render.setY(posYAutoInicial);
		collider.setX(posXAutoInicial);
		collider.setY(posYAutoInicial);

	}

	private void initSpriteAnimations() { /// estan bien cargados los sprites.
		crash = new SpriteAnimation(render, Duration.millis(1000), 3, 3, 41 * 3, 34 * 3, 3 * 3, 14 * 3, 19 * 3);
        crash.setCycleCount(Animation.INDEFINITE);
	}

	private void resetViewPort() {
		render.setViewport(new Rectangle2D(espaciador, espaciador, anchoAuto * multiplic, altoAuto * multiplic));
	}

	public Node getRender() {
		return render;
	}

	@Override
	public void colisionar(Colisionable colisionable) {
		if (colisionable.getClass() == Enemy.class) {
			Auto.velocidad /= 2;
			System.out.println("choque contra auto NPC");
		}
		/// Si "colisiono" con la calle es que estoy bien, si dejo de colisionar
		/// entonces me fui del mapa
		if (colisionable.getClass() == Background.class) {
			flagFueraDeMapa = false;
		}

		if (colisionable.getClass() == FinishLine.class) {
			Auto.velocidad = 0;
			System.out.println("GANASTE PAPUU");
			/// aca habria que llamar a una funcion para que termine la partida / saque al
			/// auto de la competencia
		}

		if (colisionable.getClass() == ColisionPowerUp.class) {
			this.aumentarVelocidadPowerUp();
			System.out.println("COLISION CON POWERUP");
		}
		if (colisionable.getClass() == ColisionObstaculo.class) {
			this.reducirVelocidadObstaculo();
			System.out.println("COLISION CON OBSTACULO");
		}

	}

	public Coordenada getUbicacion() {
		return ubicacion;
	}

	public Usuario getPiloto() {
		return piloto;
	}

	public void perderControl() throws InterruptedException {

	}

	public void reducirVelocidadObstaculo() {
		Auto.velocidad /= 1.1;

	}

	public void aumentarVelocidadPowerUp() {
		tieneModificadorVelocidad = true;
		topeVelocidad = VELOCIDAD_MAX2 + 200;

		System.out.println("antes del delay");

		new java.util.Timer().schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				topeVelocidad = VELOCIDAD_MAX2;
				Auto.velocidad = topeVelocidad;
				tieneModificadorVelocidad = false;
			}
		}, 10000);
	}

	/**
	 * Cuando choca contra los costados de la calle se podricen estas animaciones..
	 */
	public void die() {
		if (!dead) {
			setDirectionRight(false);
			setDirectionLeft(false);
			setDirectionUpSpeed1(false);
			setDirectionUpSpeed2(false);
			collider.setX(posXAutoInicial);
			this.dead = true;
			Auto.velocidad = VELOCIDAD_INICIAL;
			crash.play();
			new java.util.Timer().schedule(new java.util.TimerTask() {
				@Override
				public void run() {
					crash.stop();
					resetViewPort();
					// reseteo la pos x del auto..
					Auto.this.dead = false;
					setX(posXAutoInicial);
				}
			}, 1100);

			System.out.println("Mori");
		}
	}

	public void setX(double x) {
		if (!dead) {
			collider.setX(x);
			this.ubicacion.setX(x);
			render.setX(x);
		}
		System.out.println("Posicion actual: " + this.ubicacion.toString());

	}

	public void setY(double y) {
		if (!dead) {
			this.ubicacion.setY(y); /// esta es la unica Y que se va cambiar, porque es la que determina que tan
		} /// avanzado esta un auto respecto desde que arranc� la carrera

	}

	public void update(double deltaTime) {
		if (dead) {
			return;
		}

		if (flagFueraDeMapa) {
			die();
//			System.out.println("me fui del mapa :(");
		}

		/// acelerando mientras no me pase del limite
		flagFueraDeMapa = true;

		if ((directionUpSpeed1 || directionUpSpeed2) && velocidad < topeVelocidad) {
			velocidad += TASA_ACELERACION;
		}

		/// si estoy en la primer velocidad y vengo de la segunda velocidad, desacelero
		// � si no estoy tocando para acelerar, desacelero
		if ((directionUpSpeed1 && velocidad >= topeVelocidad) || (!directionUpSpeed1 && !directionUpSpeed2)) {
			velocidad -= TASA_FRENADO;
		}

		/// para que la velocidad no sea negativa
		if (velocidad < VELOCIDAD_INICIAL) {
			velocidad = VELOCIDAD_INICIAL;
		}

		int direction = directionLeft ? -1 : (directionRight ? 1 : 0);
		setX(this.ubicacion.getX() + direction * VEL_HORIZONTAL * deltaTime);
		setY(this.ubicacion.getY() + Auto.velocidad * deltaTime);
	}

	public void setDirectionRight(boolean b) {
		this.directionRight = b;
	}

	public void setDirectionLeft(boolean b) {
		this.directionLeft = b;
	}

	public void setDirectionUpSpeed1(boolean b) {

		if (!tieneModificadorVelocidad) {
			topeVelocidad = VELOCIDAD_MAX1;
		}

		this.directionUpSpeed1 = b;
	}

	public void setDirectionUpSpeed2(boolean b) {

		if (!tieneModificadorVelocidad) {
			topeVelocidad = VELOCIDAD_MAX2;
		}
		this.directionUpSpeed2 = b;
	}

	@Override
	public Shape getCollider() {
		return collider;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
}