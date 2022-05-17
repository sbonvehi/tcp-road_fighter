package usuario;

import java.util.List;
import java.util.Scanner;

import exception.Exception_RoadFighter;
import mapa.Mapa;
import partida.Partida;
import sala.Sala;

public class Usuario {

	private String nombre;
	private boolean jugando = false;

	public Usuario(String nombre){
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public boolean getJugando() {
		return jugando;
	}

	public void setJugando(boolean jugando) {
		this.jugando = jugando;
	}
				
	public void abandonarSala(Sala sala) throws Exception_RoadFighter
	{	
		if (this.getNombre() == sala.getAnfitrion().getNombre()) {
			sala.eliminarSala();
		}else {

			boolean encontrado = false;

			List<Usuario>listaUsuarios=sala.getListaUsuarios();
			int i=0;
			while(i < listaUsuarios.size()&&!encontrado) {
				Usuario usuario = listaUsuarios.get(i);
				if(this.nombre == usuario.nombre)
					encontrado=true;
				i++;
			}

			if(encontrado)
			{
				sala.getListaUsuarios().remove(i);
				System.out.println("Abandonaste la sala");
			}
			else
				throw new Exception_RoadFighter("El usuario no pertenece a la sala");
		}
	}	
	public void abandonarPartida(Partida partida) throws Exception_RoadFighter {

		if(!partida.eliminarUsuario(this))
			throw new Exception_RoadFighter("Error al abandonar partida");

		this.jugando = false;

		if(partida.getListaAutos().size() < 2 || !partida.getAnfitrion().jugando) 			
			partida.finalizar();
	}


}
