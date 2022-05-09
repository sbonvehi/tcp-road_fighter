package usuario;

import java.util.List;

import exception.Exception_RoadFighter;
import mapa.Mapa;
import partida.Partida;
import sala.Sala;

public class Usuario {

	private String nombre;
	private String contrasenia;
	private boolean jugando = false;

	public Usuario(String nombre, String contrasenia){
		this.nombre = nombre;
		this.contrasenia = contrasenia;
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getContrasenia() {
		return contrasenia;
	}


	public Mapa elegirMapa(List<Mapa> listaDeMapas) {		
		return listaDeMapas.get(1);
	}
	
	public Partida iniciarPartida(Sala sala) throws Exception_RoadFighter {
		
		if(sala.getListaUsuarios().size() < 2)
			throw new Exception_RoadFighter("Jugadores insuficientes");
		
		Partida partida = new Partida(sala.getMapaSeleccionado(), sala.getAnfitrion());
		partida.cargarAutos(sala.getListaUsuarios());
		partida.setEstado(true);
		
		return partida;
		
	}
	
	public void abandonarPartida(Partida partida) throws Exception_RoadFighter {
			
		if(!partida.eliminarUsuario(this))
			throw new Exception_RoadFighter("Error al abandonar partida");
		
		this.jugando = false;
		
		if(partida.getListaAutos().size() < 2 || !partida.getAnfitrion().jugando) {
			partida.setEstado(false); //finaliza la partida
			//TODO: eliminar todos los jugadores de la partida (jugando = false)
		}
			
	}
	
	public boolean getJugando() {
		return jugando;
	}

	public void setJugando(boolean jugando) {
		this.jugando = jugando;
	}

}
