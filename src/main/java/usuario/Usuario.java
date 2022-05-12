package usuario;

import java.util.List;
import java.util.Scanner;

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

	public void entrarSala(List<Sala> listaDeSalas)
	{
		for(Sala sala:listaDeSalas)
		{
			sala.mostrarSala();
		}
		
		System.out.println("Por favor ingresa el numero de sala al que quieras unirte: ");
		Scanner teclado = new Scanner(System.in);
		
		int numeroSala = teclado.nextInt();
		numeroSala--; //esto se hace para ya tener el nro-1

		while(numeroSala < 0 || numeroSala > listaDeSalas.size()) {
			System.out.println("El numero de sala ingresado es incorrecto, por favor ingrese el numero de sala nuevamente: ");
			teclado = new Scanner(System.in);
			numeroSala = teclado.nextInt();
		}

		teclado.close();
		listaDeSalas.get(numeroSala).agregarUsuario(this);

	}

	public Partida iniciarPartida(Sala sala) throws Exception_RoadFighter {

		if(sala.getListaUsuarios().size() < 2)
			throw new Exception_RoadFighter("Jugadores insuficientes");

		Partida partida = new Partida(sala.getMapaSeleccionado(), sala.getAnfitrion());
		partida.cargarAutos(sala.getListaUsuarios());
		partida.setEstado(true);

		return partida;

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


	public Sala crearSala(){ 
		return new Sala(this);
	}

	public boolean getJugando() {
		return jugando;
	}

	public void setJugando(boolean jugando) {
		this.jugando = jugando;
	}

}
