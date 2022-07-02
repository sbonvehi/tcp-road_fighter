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

//	public void elegirMapa(Sala sala, Mapa mapaElegido){	
//		sala.setMapaSeleccionado(mapaElegido);
//	}
//	
	public void elegirMapa(Sala sala, Scanner scanner){	
		System.out.println("Listado de mapas: ");
		int i = 1;
		for (Mapa mapa  : sala.getListaMapas()) {
			System.out.println(i + " - " + mapa.getNombreMapa());
			i++;
		}
		
		int numeroMapa;
		do {
			System.out.println("Ingrese el numero de mapa: ");
			numeroMapa = scanner.nextInt();
			
		} while(numeroMapa < 1 || numeroMapa > i - 1);
		
		sala.setMapaSeleccionado( sala.getListaMapas().get(numeroMapa - 1));	
		
	
//		sala.setMapaSeleccionado(mapaElegido);
	}
	
	public void elegirMapa(Sala sala, Mapa mapa){
		sala.setMapaSeleccionado(mapa);		
	}
	
	public void entrarSala(List<Sala> listaDeSalas, Scanner scanner)
	{
		int i = 1;
		for(Sala sala:listaDeSalas)
		{	
			System.out.println( i +  " - " + sala.getNombreSala());
			i++;
		}
		
		System.out.println("Por favor ingresa el numero de sala al que quieras unirte: ");
		
		int numeroSala = scanner.nextInt();
		numeroSala--; //esto se hace para ya tener el nro-1

		while(numeroSala < 0 || numeroSala > listaDeSalas.size()) {
			System.out.println("El numero de sala ingresado es incorrecto, por favor ingrese el numero de sala nuevamente: ");
			scanner = new Scanner(System.in);
			numeroSala = scanner.nextInt();
			numeroSala--; 
		}

		listaDeSalas.get(numeroSala).agregarUsuario(this);

	}

	public Partida configurarSala(Sala sala, Scanner scanner) {
		elegirMapa(sala, scanner);
		
		boolean empezoPartida = false;
		
		Partida partida = null;
		while(!empezoPartida) {
			System.out.println("Para iniciar partida escriba  \"1\"");
			int opcion = scanner.nextInt();
			
			if(opcion != 1) //se va
				partida = null;
			
			try {
				empezoPartida = true;
				partida =  iniciarPartida(sala);
				
			} catch(Exception_RoadFighter e) {
				e.getMessage();
			}
		}
		
		return partida;
	}
	
	public Partida iniciarPartida(Sala sala) throws Exception_RoadFighter {

//		if(sala.getListaUsuarios().size() < 2)
		if(sala.getListaUsuarios().size() < 1) //para probar jugando solo
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


	public Sala crearSala(String nombreSala){ 
		return new Sala(this, nombreSala);
	}
	

	public boolean getJugando() {
		return jugando;
	}

	public void setJugando(boolean jugando) {
		this.jugando = jugando;
	}

}
