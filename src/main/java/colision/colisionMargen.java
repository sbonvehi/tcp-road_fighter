package colision;

import auto.Auto;
import coordenada.Coordenada;

public class colisionMargen extends modoColision{

	@Override
	public void accion(Auto auto) {
		Coordenada actual=auto.getUbicacion();
		actual.setX(actual.getX()-10);
		auto.setUbicacion(actual);
	}

}
