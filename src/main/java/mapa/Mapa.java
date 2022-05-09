package mapa;

import java.util.ArrayList;
import java.util.List;

import coordenada.Coordenada;

public class Mapa {
	// Momentaneo 
	
	private String nombreMapa; 
	////////////////////
	private int ancho;
	private Coordenada coordenada;
	private List<ElementoMapa> elementosMapa;
	
	public Mapa() {
		this.elementosMapa = new ArrayList<ElementoMapa>();
	}
	
	public Mapa(String nombre) {
		this.nombreMapa = nombre;
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
	public String getNombreMapa() {
		return nombreMapa;
	}

	public void setCoordenada(Coordenada coordenada) {
		this.coordenada = coordenada;
	}

}
