package mapa;

import coordenada.Coordenada;

public abstract class ElementoMapa {
	
	protected Coordenada ubicacion;
	protected int subtipo; //TODO: verificar tipo de dato
	
	public abstract void accion();

}
