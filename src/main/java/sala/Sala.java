package sala;

import java.util.ArrayList;
import java.util.List;

import exception.Exception_RoadFighter;
import mapa.Mapa;
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

		/// Cargamos unos mapas.
		listaMapas.add(new Mapa("mapa1", 30, 100));
		listaMapas.add(new Mapa("mapa2", 40, 100));
		listaMapas.add(new Mapa("mapa3", 50, 100));

		/// Mostramos por default el primer mapa.
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
		this.getListaUsuarios().add(nuevoUsuario);
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

	public void setMapaSeleccionado(Mapa _mapaSeleccionado) {
		this.mapaSeleccionado = _mapaSeleccionado;
	}

	public Usuario getAnfitrion() {
		return anfitrion;
	}

	public String getNombreSala() {
		return this.nombreSala;
	}

	public void detalleSala() {
		System.out.println("estado: " + estado);
		System.out.println("anfitrion: " + anfitrion.getNombre());
		System.out.println("Cantidad de jugadores en la sala: " + listaUsuarios.size());
		System.out.println("mapa seleccionado: " + mapaSeleccionado.getNombreMapa());
	}

	public Sala eliminarSala() {
		this.listaUsuarios.clear();
		return this;
	}
}
