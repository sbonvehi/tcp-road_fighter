package auto;

import coordenada.Coordenada;
import mapa.Mapa;
import usuario.Usuario;

public class Auto {

	private int velocidad;
	private Coordenada ubicacion;
	private int distanciaRecorrida;
	private Usuario piloto;

	public int getVelocidad() {
		return velocidad;
	}


	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}


	public Coordenada getUbicacion() {
		return ubicacion;
	}



	public void setUbicacion(Coordenada ubicacion) {
		this.ubicacion = ubicacion;
	}


	public int getDistanciaRecorrida() {
		return distanciaRecorrida;
	}


	public void setDistanciaRecorrida(int distanciaRecorrida) {
		this.distanciaRecorrida = distanciaRecorrida;
	}


	public Usuario getPiloto() {
		return piloto;
	}
	
	public void setPiloto(Usuario piloto) {
		this.piloto = piloto;
	}
}
