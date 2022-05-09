package usuario;

import java.util.List;

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
	
	public boolean iniciarPartida(Sala sala) {
		Partida partida = new Partida(sala.getMapaSeleccionado());
		partida.cargarAutos(sala.getListaUsuarios());
		partida.setEstado(true);
		
		return partida.getEstado();
		
	} 

}
