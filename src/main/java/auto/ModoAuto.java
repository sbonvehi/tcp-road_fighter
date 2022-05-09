package auto;

import mapa.Mapa;
import usuario.Usuario;

public abstract class ModoAuto {
	
	protected int velocidad;
	protected Mapa ubicacion;
	protected int distanciaRecorrida;
	protected Usuario piloto;
	
	
	public abstract void acelerar();
	
	public abstract void frenar();
	
	public abstract void desplazarse(Mapa ubicacion);
	
	public abstract void colisiona();
	
	public abstract void setPiloto(Usuario piloto);

	public abstract ModoAuto setModo();
}
