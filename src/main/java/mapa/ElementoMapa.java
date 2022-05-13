package mapa;

import auto.Auto;
import coordenada.Coordenada;

public abstract class ElementoMapa {
	
//	protected Coordenada ubicacion;
	protected int x;
	protected int y;
//	protected int subtipo; //TODO: verificar tipo de dato
	
	public abstract void accion(Auto auto) throws InterruptedException ;

}
