package partida;

import java.util.ArrayList;
import java.util.List;

import auto.Auto;
import exception.Exception_RoadFighter;
import mapa.Mapa;
import usuario.Usuario;

public class Partida {

	private boolean estado;
	private List<Auto> listaAutos;
	private Mapa mapa;
	private Usuario anfitrion;
	
	public Partida(Mapa mapaPartida, Usuario anfitrion) {
		mapa = mapaPartida;
		this.anfitrion = anfitrion;
		this.listaAutos = new ArrayList<Auto>();
	}
	
	public void cargarAutos(List<Usuario> jugadores) {
		for(Usuario usuario : jugadores) {
			Auto auto = new Auto();	
			auto.setPiloto(usuario);
			listaAutos.add(auto);
			usuario.setJugando(true);
		}
	}

	public List<Auto> getListaAutos() {
		return listaAutos;
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

	public boolean eliminarUsuario(Usuario usuario) throws Exception_RoadFighter {
		
		boolean encontrado = false;
		
		for(int i = 0; i < this.listaAutos.size() && !encontrado; i++) {
			if(this.listaAutos.get(i).getPiloto().getNombre().equals(usuario.getNombre())) {
				this.listaAutos.remove(this.listaAutos.get(i));
				encontrado = true;
			}
		}
		
		if(!encontrado)
			throw new Exception_RoadFighter("Error al eliminar usuario.");
		
		return encontrado;
		
	}

	public Usuario getAnfitrion() {
		return anfitrion;
	}

	public void setAnfitrion(Usuario anfitrion) {
		this.anfitrion = anfitrion;
	}
	
	
}
