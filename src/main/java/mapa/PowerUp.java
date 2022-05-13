package mapa;

import auto.Auto;

public class PowerUp extends ElementoMapa {

	
	public PowerUp(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}	
	
	@Override
	public void accion(Auto auto) throws InterruptedException {
		auto.aumentarVelocidad();
	}

}
