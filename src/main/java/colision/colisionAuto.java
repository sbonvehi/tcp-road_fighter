package colision;

import auto.Auto;

public class colisionAuto extends modoColision{

	@Override
	public void accion(Auto auto) {
		
		auto.setVelocidad(auto.getVelocidad()-40);
	}
	
	

}
