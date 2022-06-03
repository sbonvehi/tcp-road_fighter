package mapa;

import coordenada.Coordenada;

public class Obstaculo extends ElementoMapa { // mancha de aceite

	public Obstaculo(int x, int y) {
		super();
		Coordenada ubicacion = new Coordenada(x, y);
		this.ubicacion = ubicacion;
		this.alto = 20;
		this.ancho = 20;
	}

}
