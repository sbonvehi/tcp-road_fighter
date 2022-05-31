package colision;

import java.util.concurrent.TimeUnit;

import road_fighter.objects.Auto;

public class colisionPowerUp extends modoColision {

	@Override
	public void accion(Auto auto) {
		auto.aumentarVelocidadPowerUp();

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		auto.reducirVelocidadPowerUp();
	}

}
