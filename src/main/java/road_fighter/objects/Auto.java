package road_fighter.objects;

import java.util.concurrent.TimeUnit;

import animation.SpriteAnimation;
import coordenada.Coordenada;
import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import road_fighter.Config;
import usuario.Usuario;

public class Auto {

	private final int VELOCIDAD_INICIAL = 0;
	private final int VELOCIDAD_MAX1 = 200;
	private final int TASA_ACELERACION = 2;
	private final int VELOCIDAD_MAX2 = 400;
	private final int TASA_FRENADO = 1;
	private final int POSICION_MITAD_PANTALLA = 600; ///?????
	
	private static int cantAutos = 0;
	private final int VEL_HORIZONTAL = 300; // velocidad desplazamiento hacia los izq y der.

	private static final int posXAuto = 270;// 397
	private static final int posYAuto = 650;// 550

	private int topeVelocidad = VELOCIDAD_MAX1;
	private int velocidad = VELOCIDAD_INICIAL;
	private Coordenada ubicacion;
	private Usuario piloto;

	private boolean directionLeft = false;
	private boolean directionRight = false;
	private boolean directionUpSpeed1 = false;
	private boolean directionUpSpeed2 = false;

	private ImageView render;
//	private Image spriteNormal;
	private Image spriteImages;
	private SpriteAnimation crash1;
	private SpriteAnimation crash2;
	
	private boolean dead;

	public Auto(Usuario piloto) {

		cantAutos++;
		this.ubicacion = new Coordenada(posXAuto, 0);
		this.piloto = piloto;

		initImages();
//		render = new ImageView(spriteNormal);
		render = new ImageView(spriteImages);
		render.setViewport(new Rectangle2D(3, 3, 14*3, 19*3));
		render.relocate(-Config.ANCHO_AUTO / 2, -Config.ALTO_AUTO / 2); // render en la mitad del auto..

		setX(posXAuto);		
		render.setY(posYAuto); ///solo hay que asignar una vez este valor, porque el auto no se mueve en el eje Y
		
	}
	
	private void initImages() {
//		spriteNormal = new Image(Config.CAR_IMG, Config.ANCHO_AUTO, Config.ALTO_AUTO, false, false);
		spriteImages = new Image(Config.GENERAL_SPRITES_IMG, 328 * 3, 179 * 3, false, false); 		
	}
	
	private void initSpriteAnimations() {
		crash1 = new SpriteAnimation(render,Duration.millis(200), 3, 3, 40, 34, 0, 50, 43);
//		crash2 = 
	}
	
	public ImageView getRender() {
		return render;
	}

	public int getVelocidad() {
		return velocidad;
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
		if (ubicacion.getX() < 139) { // 142 margen izq calle
			x = 139;
			die();
		} else if (ubicacion.getX() > 395) { // 396 margen der calle
			x = 395;
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
		
	}

	public void setDirectionRight(boolean b) {
		this.directionRight = b;
		System.out.println();
		System.out.println("Posicion actual: " + this.ubicacion.toString());

	}

	public void setDirectionLeft(boolean b) {
		this.directionLeft = b;
		System.out.println("Posicion actual: " + this.ubicacion.toString());

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
		
//		IndividualSpriteAnimation individualSpriteAnimation = new IndividualSpriteAnimation(
//				new Image[] { imageUp, imageBase, imageDown }, render, Duration.millis(500));
//		individualSpriteAnimation.setCustomFrames(new int[] { 0, 1, 2, 1 });
//		individualSpriteAnimation.setCycleCount(Animation.INDEFINITE);
//		individualSpriteAnimation.play();
//		return individualSpriteAnimation;
//		
//		render.setViewport(new Rectangle2D(0, 0, Config.ANCHO_AUTO, Config.ALTO_AUTO));

		System.out.println("Mori");

		this.dead = true;
		resetearRender();

	}
	
	// reseteo la posicion en el origen despues de explotar..
	public void resetearRender() {
		
		this.dead = false;
//		ubicacion.setX(posXAuto);
//		ubicacion.setY(posYAuto);
////		render = new ImageView(spriteNormal);
//		render = new ImageView(spriteImages);
//		render.setViewport(new Rectangle2D(0, 0, Config.ANCHO_AUTO, Config.ALTO_AUTO));
//		render.relocate(-Config.ANCHO_AUTO / 2, -Config.ALTO_AUTO / 2); // render en la mitad del auto..
//
//		setX(posXAuto);
//		setY(posYAuto);

	}

}