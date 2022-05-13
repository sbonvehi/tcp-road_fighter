package mapa;

import auto.Auto;

public class Obstaculo extends ElementoMapa {
	
	public Obstaculo(int x, int y) {
		super();
		this.ubicacion.setX(x);
		this.ubicacion.setY(y);
	}	
	
	@Override
	public void accion(Auto auto) throws InterruptedException {
		auto.perderControl();
		// no se si iba auto.perderControl() o reducirVelocidad()
	}


}
