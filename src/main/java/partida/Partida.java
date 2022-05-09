package partida;

import java.util.ArrayList;
import java.util.List;

import auto.Auto;
import mapa.Mapa;
import usuario.Usuario;

public class Partida {

	private boolean estado;
	private List<Auto> listaAutos;
	private Mapa mapa;
	
	public Partida(Mapa mapaPartida) {
		mapa = mapaPartida;
		this.listaAutos = new ArrayList<Auto>();
	}
	
	public void cargarAutos(List<Usuario> jugadores) {
		for(Usuario usuario : jugadores) {
			Auto auto = new Auto();	
			auto.setPiloto(usuario);
			listaAutos.add(auto);
			
		}
	}

	public Mapa getMapa() {
		return mapa;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public boolean getEstado() {
		return estado;
	}
	
	
}
