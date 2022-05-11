package usuario;

import java.util.List;

import mapa.Mapa;

public class Anfitrion extends Usuario {

	public Anfitrion(String nombre, String contrasenia) {
		super(nombre, contrasenia);
	}

	public Mapa elegirMapa(List<Mapa> listaDeMapas) {		
		return listaDeMapas.get(1);
	}
	
	public void iniciarPartida() {
		
	}
}
