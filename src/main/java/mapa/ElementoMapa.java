package mapa;

import auto.Auto;
import coordenada.Coordenada;

public abstract class ElementoMapa {
	
	protected Coordenada ubicacion;
	protected final int ancho = 20; 	//ancho del elemento
	protected final int alto = 20;		//alto del elemento
	
	public abstract void accion(Auto auto) throws InterruptedException ;

}
