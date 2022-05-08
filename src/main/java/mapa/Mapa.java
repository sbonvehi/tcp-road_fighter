package mapa;

import java.util.ArrayList;
import java.util.List;

import coordenada.Coordenada;

public class Mapa {
	
	private int ancho;
	private Coordenada coordenada;
	private List<ElementoMapa> elementosMapa;
	
	public Mapa() {
		this.elementosMapa = new ArrayList<ElementoMapa>();
	}
	
	public int getAncho() {
		return ancho;
	}
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}
	public Coordenada getCoordenada() {
		return coordenada;
	}
	public void setCoordenada(Coordenada coordenada) {
		this.coordenada = coordenada;
	}

}
