package auto;

import mapa.Mapa;

public abstract class ModoAuto {
	
	protected int velocidad;
	protected Mapa ubicacion;
	protected int distanciaRecorrida;
	
	public abstract void acelerar();
	
	public abstract void frenar();
	
	public abstract void desplazarse(Mapa ubicacion);
	
	public abstract void colisiona();

}
