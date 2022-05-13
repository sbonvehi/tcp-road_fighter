package auto;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import coordenada.Coordenada;
import mapa.Mapa;
import usuario.Usuario;

public class Auto {

	private static final int VELOCIDAD_INICIAL = 0;
	private static final int VELOCIDAD_MAXIMA = 400;
	private static final int TASA_ACELERACION = 70;
	private static final int TASA_FRENADO = 50;
	private static final int POSICION_MITAD_PANTALLA = 600; // el ancho total de la pantalla es 1200.. desp lo mejoramos..
	private static int cantAutos = 1;
	
	private final int ancho = 20; 		//ancho del auto
	private final int alto = 40;		//alto del auto
	private final int speedSides = 300; // velocidad desplazamiento hacia los izq y der.
	
	private int velocidad;
	private Coordenada ubicacion;
	private Usuario piloto;
	
	private boolean directionLeft = false;
	private boolean directionRight = false;
	private boolean directionUpSpeed1 = false;
	private boolean directionUpSpeed2 = false;
	
	public Auto(Usuario piloto) {
		this.velocidad = 0;
		this.ubicacion.setY(0);
		this.ubicacion.setX( cantAutos * 15);
		this.piloto = piloto;
		
		cantAutos++;
	}
	
	public int getAnchoOcupado() {
		return this.ancho;
	}

	public int getAltosOcupado() {
		return this.alto;
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
		velocidad -= 200 ;
	}

	public void aumentarVelocidadPowerUp() {
		this.velocidad += 200;
	}

	public void setX(int x) {
		this.ubicacion.setX(x);
	}

	public void setY(int y) {
		this.ubicacion.setY(y);
	}

	public void update(double deltaTime) { //delta time es el tiempo que paso desde la ultima actualizacion.
		
		int direction = directionLeft ? -1 : (directionRight ? 1 : 0);
		
		setX((int) (this.ubicacion.getX() + direction * speedSides * deltaTime));
		setY((int) (this.ubicacion.getY() + this.velocidad * deltaTime));
	}

	public void setDirectionRight(boolean b) {
		this.directionRight = b;
		System.out.println("Posicion actual: " + this.ubicacion.toString());
//		chechHorizontal(); no necesario ahora.
	}

	public void setDirectionLeft(boolean b) {
		this.directionLeft = b;
		System.out.println("Posicion actual: " + this.ubicacion.toString());
//		chechHorizontal(); no necesario ahora.
	}

	public void setDirectionUpSpeed1(boolean b) {
		this.directionUpSpeed1 = b;
		System.out.println("Posicion actual: " + this.ubicacion.toString());
	}

	public void setDirectionUpSpeed2(boolean b) {
		this.directionUpSpeed2 = b;
		if(directionUpSpeed2)
			this.directionUpSpeed1 = false;
		System.out.println("Posicion actual: " + this.ubicacion.toString());
	}
	
	
	
	
}