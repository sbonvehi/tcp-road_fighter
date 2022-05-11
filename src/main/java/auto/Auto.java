package auto;

import coordenada.Coordenada;
import mapa.Mapa;
import usuario.Usuario;

public class Auto {

	private int velocidad;
<<<<<<< HEAD
	private Coordenada ubicacion;
=======
	private Mapa ubicacion;
>>>>>>> a0d28f3f044f09d34de3c74ba6e8f5747fe9e7c1
	private int distanciaRecorrida;
	private Usuario piloto;

	public int getVelocidad() {
		return velocidad;
	}


	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}


<<<<<<< HEAD
	public Coordenada getUbicacion() {
=======
	public Mapa getUbicacion() {
>>>>>>> a0d28f3f044f09d34de3c74ba6e8f5747fe9e7c1
		return ubicacion;
	}


<<<<<<< HEAD
	public void setUbicacion(Coordenada ubicacion) {
=======
	public void setUbicacion(Mapa ubicacion) {
>>>>>>> a0d28f3f044f09d34de3c74ba6e8f5747fe9e7c1
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
