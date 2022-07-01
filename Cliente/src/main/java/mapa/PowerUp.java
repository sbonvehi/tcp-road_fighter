package mapa;

import coordenada.Coordenada;

public class PowerUp extends ElementoMapa {

	public PowerUp(int x, int y) {
		super();
		Coordenada ubicacion = new Coordenada(15, 50);
		this.ubicacion = ubicacion;
		this.alto = 20;
		this.ancho = 20;
	}

}
