package mapa;

public class AutoBot extends ElementoMapa {

	private final int velocidad = 140;
	private final int speedSides = 80;

	public AutoBot(int x, int y) {
		super();
		this.ubicacion.setX(x);
		this.ubicacion.setY(y);
		this.alto = 40;
		this.ancho = 20;
	}

	public void avanzar() {
		// esto tiene que correr todo el tiempo como con un hilo a parte del resto.
		this.ubicacion.setY((int) (this.ubicacion.getY() + this.velocidad));
	}

	public void avanzarYDesplazarse() { // falta pulirlo..
		int direction = -1;
		double deltaTime = 1;
		this.ubicacion.setX((int) (this.ubicacion.getX() + direction * this.speedSides * deltaTime));
		direction = 1;
		this.ubicacion.setX((int) (this.ubicacion.getX() + direction * this.speedSides * deltaTime));
		this.ubicacion.setY((int) (this.ubicacion.getY() + this.velocidad));
	}

}
