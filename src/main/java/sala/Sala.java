package sala;

import java.util.ArrayList;
import java.util.List;

import mapa.Mapa;
import usuario.Usuario;

public class Sala {

	private boolean estado;
	private Usuario anfitrion;
	private List<Usuario> listaUsuarios;
	private List<Mapa> listaMapas;
	
	public Sala() {
		this.listaUsuarios = new ArrayList<Usuario>();
		this.listaMapas = new ArrayList<Mapa>();
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Usuario getAnfitrion() {
		return anfitrion;
	}

	public void setAnfitrion(Usuario anfitrion) {
		this.anfitrion = anfitrion;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public List<Mapa> getListaMapas() {
		return listaMapas;
	}

	public void setListaMapas(List<Mapa> listaMapas) {
		this.listaMapas = listaMapas;
	}
}
