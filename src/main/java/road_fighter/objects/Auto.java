package road_fighter.objects;

import java.util.concurrent.TimeUnit;

import coordenada.Coordenada;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import road_fighter.Config;
import usuario.Usuario;

public class Auto {

	private static final int VELOCIDAD_INICIAL = 0;
	private static final int VELOCIDAD_MAXIMA = 400;
	private static final int TASA_ACELERACION = 70;
	private static final int TASA_FRENADO = 50;
	private static final int POSICION_MITAD_PANTALLA = 600; // el ancho total de la pantalla es 1200.. desp lo
															// mejoramos..
	private static int cantAutos = 1;
	private final int speedSides = 300; // velocidad desplazamiento hacia los izq y der.

	private static final int posXAuto = 297;// 397
	private static final int posYAuto = 650;// 550

	private int velocidad;
	private Coordenada ubicacion;
	private Usuario piloto;

	private boolean directionLeft = false;
	private boolean directionRight = false;
	private boolean directionUpSpeed1 = false;
	private boolean directionUpSpeed2 = false;

	private ImageView render;
	private Image spriteNormal;
	private boolean dead;

	public Auto(Usuario piloto) {
		this.velocidad = 0;

		Coordenada ubicacionInicial = new Coordenada(posXAuto, cantAutos * 15);
		this.ubicacion = ubicacionInicial;
		this.piloto = piloto;

		spriteNormal = new Image(Config.CAR_IMG, Config.ANCHO_AUTO, Config.ALTO_AUTO, false, false);
		render = new ImageView(spriteNormal);
		render.setViewport(new Rectangle2D(0, 0, Config.ANCHO_AUTO, Config.ALTO_AUTO));
//		render.relocate(posXAuto, posYAuto); 
		render.relocate(-Config.ANCHO_AUTO / 2, -Config.ALTO_AUTO / 2); // render en la mitad del auto..

		setX(posXAuto);
		setY(posYAuto);

		cantAutos++;
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
		render.setY(y);
	}

	public void update(double deltaTime) { // delta time es el tiempo que paso desde la ultima actualizacion.

		int direction = directionLeft ? -1 : (directionRight ? 1 : 0);
		setX((int) (this.ubicacion.getX() + direction * speedSides * deltaTime));
		setY((int) (this.ubicacion.getY() + this.velocidad * deltaTime));

	}

	public void setDirectionRight(boolean b) {
		this.directionRight = b;
		System.out.println("Posicion actual: " + this.ubicacion.toString());
	}

	public void setDirectionLeft(boolean b) {
		this.directionLeft = b;
		System.out.println("Posicion actual: " + this.ubicacion.toString());
	}

	// esto esta bien
	public void setDirectionUpSpeed1(boolean b) {

		this.directionUpSpeed1 = b;
		System.out.println("Posicion actual: " + this.ubicacion.toString());
	}

	// esto esta bien
	public void setDirectionUpSpeed2(boolean b) {

		this.directionUpSpeed2 = b;
		if (directionUpSpeed2)
			this.directionUpSpeed1 = false;
		System.out.println("Posicion actual: " + this.ubicacion.toString());
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

}