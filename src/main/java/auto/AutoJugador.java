package auto;

import mapa.Mapa;
import usuario.Usuario;

public class AutoJugador extends ModoAuto {
	
	
	private final int speedSides = 300; // velocidad desplazamiento hacia los izq y der.
	private double x;
	private double y;
	private boolean directionLeft = false;
	private boolean directionRight = false;
	private boolean directionUpSpeed1 = false;
	private boolean directionUpSpeed2 = false;
	
	public void setX(double d) {
		this.x = d;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void update(double deltaTime) { //delta time es el tiempo que paso desde la ultima actualizacion.
		
		int direction = directionLeft ? -1 : (directionRight ? 1 : 0);
		
		setX( x + direction * speedSides * deltaTime);
	}

	public void setDirectionRight(boolean b) {
		this.directionRight = b;
		System.out.println("Posicion actual: [ " + this.x + " ; " + this.y + " ]");
//		chechHorizontal(); no necesario ahora.
	}

	public void setDirectionLeft(boolean b) {
		this.directionLeft = b;
		System.out.println("Posicion actual: [ " + this.x + " ; " + this.y + " ]");
//		chechHorizontal(); no necesario ahora.
	}

	public void setDirectionUpSpeed1(boolean b) {
		this.directionUpSpeed1 = b;
		System.out.println("Posicion actual: [ " + this.x + " ; " + this.y + " ]");
	}

	public void setDirectionUpSpeed2(boolean b) {
		this.directionUpSpeed2 = b;
		if(directionUpSpeed2)
			this.directionUpSpeed1 = false;
		System.out.println("Posicion actual: [ " + this.x + " ; " + this.y + " ]");
	}
	
	@Override
	public void acelerar() {
		velocidad += TASA_ACELERACION;			
		if(velocidad > VELOCIDAD_MAXIMA) {
			velocidad = VELOCIDAD_MAXIMA;
		}
	}

	@Override
	public void frenar() {
		velocidad -= TASA_FRENADO;			
		if(velocidad < VELOCIDAD_INICIAL) {
			velocidad = VELOCIDAD_INICIAL;
		}
		
	}
		
	
	
	
	
	
	
}
