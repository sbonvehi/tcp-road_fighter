package colision;

import auto.Auto;

public class colisionObstaculo extends modoColision {

	@Override
	public void accion(Auto auto) {
		try {
			auto.perderControl();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
