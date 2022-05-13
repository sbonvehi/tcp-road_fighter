package mapa;

public class Obstaculo extends ElementoMapa { // mancha de aceite

	public Obstaculo(int x, int y) {
		super();
		this.ubicacion.setX(x);
		this.ubicacion.setY(y);
		this.alto = 20;
		this.ancho = 20;
	}

}
