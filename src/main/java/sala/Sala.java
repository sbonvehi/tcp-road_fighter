package sala;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exception.Exception_RoadFighter;
import mapa.Mapa;
import partida.Partida;
import usuario.Usuario;

public class Sala {
	
	private String nombreSala;
	private boolean estado;
	private Usuario anfitrion;
	private List<Usuario> listaUsuarios;
	private List<Mapa> listaMapas;
	private Mapa mapaSeleccionado;
	
	public Sala(Usuario anfitrion, String nombreSala) {
		this.nombreSala = nombreSala;
		this.anfitrion = anfitrion;
		this.listaUsuarios = new ArrayList<Usuario>();
		this.listaMapas = new ArrayList<Mapa>();

		listaUsuarios.add(this.anfitrion);

		/// Cargamos unos mapas
		CargarMapas();

		/// Mostramos por default el primer mapa 
		this.mapaSeleccionado = listaMapas.get(0);
	}

	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void agregarUsuario(Usuario nuevoUsuario) {
		listaUsuarios.add(nuevoUsuario);
	}

	public List<Mapa> getListaMapas() {
		return listaMapas;
	}

	public void setListaMapas(List<Mapa> listaMapas) {
		this.listaMapas = listaMapas;
	}

	public Mapa getMapaSeleccionado() {
		return mapaSeleccionado;
	}

	public Usuario getAnfitrion() {
		return anfitrion;
	}
	
	public String getNombreSala() {
		return this.nombreSala;
	}
	
	public Partida iniciarPartida() throws Exception_RoadFighter {

//		if(sala.getListaUsuarios().size() < 2)
		if(listaUsuarios.size() < 1) //para probar jugando solo
			throw new Exception_RoadFighter("Jugadores insuficientes");

		Partida partida = new Partida(mapaSeleccionado, anfitrion);
		partida.cargarAutos(listaUsuarios);

		return partida;

	}
	
	public void detalleSala() {
		System.out.println("estado: " + estado);
		System.out.println("anfitrion: " + anfitrion.getNombre());
		System.out.println("Cantidad de jugadores en la sala: " + listaUsuarios.size());
		System.out.println("mapa seleccionado: " + mapaSeleccionado.getNombreMapa());
	}

	public void elegirMapa(){	
		System.out.println("Listado de mapas: ");
		int i = 1;
		for (Mapa mapa  : listaMapas) {
			System.out.println(i + " - " + mapa.getNombreMapa());
			i++;
		}
		
		int numeroMapa;
		do {
			System.out.println("Ingrese el numero de mapa: ");
			Scanner scanner = new Scanner(System.in);
			numeroMapa = scanner.nextInt();
			scanner.close();
		} while(numeroMapa < 1 || numeroMapa > i - 1);
		
		mapaSeleccionado = listaMapas.get(numeroMapa - 1);
	}
	
	private void CargarMapas()
	{
		listaMapas.add(new Mapa("mapa1", 30, 100));
		listaMapas.add(new Mapa("mapa2", 40, 100));
		listaMapas.add(new Mapa("mapa3", 50, 100));
	}
	
	// Creo no hace falta esto, lista usuario es una propiedad por composicion, por ende si se "borra" la sala, la lista se "elimina" automaticamente
	public Sala eliminarSala() {
		this.listaUsuarios.clear();
		return this;
	}
}
