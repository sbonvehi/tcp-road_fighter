package colision;

import coordenada.Coordenada;
import road_fighter.objects.Auto;

public abstract class modoColision {

//	protected double velocidad;
	protected Coordenada posicion;
//	protected int distanciaRecorrida;

	public abstract void accion(Auto auto);

}
