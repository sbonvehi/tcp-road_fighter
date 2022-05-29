package road_fighter.objects;

import java.util.concurrent.TimeUnit;

import coordenada.Coordenada;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Rectangle2D;
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

	private Rectangle collider;
	
	private static final int VELOCIDAD_INICIAL = 0;
	private final int VELOCIDAD_MAX1 = 200;
	private final int TASA_ACELERACION = 2;
	private final int VELOCIDAD_MAX2 = 400;
	private final int TASA_FRENADO = 1;
	private final int POSICION_MITAD_PANTALLA = 600; ///?????
	
	private static int cantAutos = 0;
	private final int VEL_HORIZONTAL = 300; // velocidad desplazamiento hacia los izq y der.

	private static final int posXAuto = 297;// 397
	private static final int posYAuto = 650;// 550

	private int topeVelocidad = VELOCIDAD_MAX1;
	private static int velocidad = VELOCIDAD_INICIAL;
//	public static int velocidad = VELOCIDAD_INICIAL;
//	public static int velocidad = VELOCIDAD_INICIAL;
	private Coordenada ubicacion;
	private Usuario piloto;

	private boolean directionLeft = false;
	private boolean directionRight = false;
	private boolean directionUpSpeed1 = false;
	private boolean directionUpSpeed2 = false;

	private ImageView render;
	private Image spriteNormal;
	private boolean dead;

	public static int getVelocidad() {
		return velocidad;
	}
	
	public Auto(Usuario piloto) {
		collider = new Rectangle(Config.ANCHO_AUTO, Config.ANCHO_AUTO);
		collider.setFill(null);
		collider.setStroke(Color.FUCHSIA);
		
		cantAutos++;
		this.ubicacion = new Coordenada(posXAuto, 0);
		this.piloto = piloto;

		spriteNormal = new Image(Config.CAR_IMG, Config.ANCHO_AUTO, Config.ALTO_AUTO, false, false);
		render = new ImageView(spriteNormal);
		render.setViewport(new Rectangle2D(0, 0, Config.ANCHO_AUTO, Config.ALTO_AUTO));
		render.relocate(-Config.ANCHO_AUTO / 2, -Config.ALTO_AUTO / 2); // render en la mitad del auto..

		setX(posXAuto);		
		render.setY(posYAuto); ///solo hay que asignar una vez este valor, porque el auto no se mueve en el eje Y

	}

	public ImageView getRender() {
		return render;
	}


	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public Coordenada getUbicacion() {
		return ubicacion;
	}

	// Verificar que no haya obstaculo ni nada en esa posicion.
	public void restablecerUbicacion() {
		this.ubicacion.setX(POSICION_MITAD_PANTALLA);
	}

	public Usuario getPiloto() {
		return piloto;
	}

	public void perderControl() throws InterruptedException {

		while (velocidad > 1) {
			velocidad *= 0.3;
			TimeUnit.SECONDS.sleep(1);

//			if(golpe margen) {
//				auto.resetearPosicion();
//			}
		}
		// no explote..
		velocidad = 0;
	}

	public void reducirVelocidadPowerUp() {
		velocidad -= 200;
	}

	public void aumentarVelocidadPowerUp() {
		this.velocidad += 200;
	}

	public void setX(int x) {
		if(!dead) {
		if (ubicacion.getX() < 152) { // 152 margen izq calle
			x = 152;
			die();
		} else if (ubicacion.getX() > 400) { // 400 margen der calle
			x = 400;
			die();
		}

		this.ubicacion.setX(x);
		render.setX(x);
		}
	}

	public void setY(int y) {
		this.ubicacion.setY(y);
	}

	public void update(double deltaTime) { // delta time es el tiempo que paso desde la ultima actualizacion.	
		///acelerado mientras no me pase del limite
		if((directionUpSpeed1 || directionUpSpeed2) && velocidad < topeVelocidad) {
			velocidad += TASA_ACELERACION;			
		}
		
		
		///si estoy en la primer velocidad y vengo de la segunda velocidad, desacelero
		//� si no estoy tocando para acelerar, desacelero
		if( (directionUpSpeed1 && velocidad >= topeVelocidad) ||
			(!directionUpSpeed1 && !directionUpSpeed2)) {
			velocidad -= TASA_FRENADO;
		}
		
		///para que la velocidad no sea negativa
		if(velocidad < VELOCIDAD_INICIAL) {
			velocidad = VELOCIDAD_INICIAL;
		}


		int direction = directionLeft ? -1 : (directionRight ? 1 : 0);
		setX((int) (this.ubicacion.getX() + direction * VEL_HORIZONTAL * deltaTime));
		setY((int) (this.ubicacion.getY() + this.velocidad * deltaTime));
		System.out.println(ubicacion.toString());
	}

	public void setDirectionRight(boolean b) {
		this.directionRight = b;
	}

	public void setDirectionLeft(boolean b) {
		this.directionLeft = b;
	}

	public void setDirectionUpSpeed1(boolean b) {
		topeVelocidad = VELOCIDAD_MAX1;	
		this.directionUpSpeed1 = b;
	}

	// esto esta bien
	public void setDirectionUpSpeed2(boolean b) {
		topeVelocidad = VELOCIDAD_MAX2;
		this.directionUpSpeed2 = b;
	}

	public void die() {

//		Image spriteBoom = new Image(Config.CRASH_CAR, Config.ANCHO_AUTO, Config.ALTO_AUTO, false, false);
//		render = new ImageView(spriteBoom);
//		render.setViewport(new Rectangle2D(0, 0, Config.ANCHO_AUTO, Config.ALTO_AUTO));
//
//		TranslateTransition explotarEnElLugar = new TranslateTransition(Duration.millis(500), render);
//		explotarEnElLugar.setToX(300);
//		explotarEnElLugar.play();

		System.out.println("Mori");

//		this.dead = true;
//		resetearRender();

	}

	// reseteo la posicion en el origen despues de explotar..
	public void resetearRender() {
		
		this.dead = false;
		ubicacion.setX(posXAuto);
		ubicacion.setY(posYAuto);
		render = new ImageView(spriteNormal);
		render.setViewport(new Rectangle2D(0, 0, Config.ANCHO_AUTO, Config.ALTO_AUTO));
		render.relocate(-Config.ANCHO_AUTO / 2, -Config.ALTO_AUTO / 2); // render en la mitad del auto..

		setX(posXAuto);
		setY(posYAuto);

	}

	@Override
	public Shape getCollider() {
		return collider;
	}

	@Override
	public void colisionar(Colisionable colisionable) {
//		System.out.println("COLISION!!: "  + colisionable.getClass());
		if(colisionable.getClass() == Enemy.class) {
//			Auto.velocidad /= 2;
			System.out.println("se redujo la velocidad");
		}
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}