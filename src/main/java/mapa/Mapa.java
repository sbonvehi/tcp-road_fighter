package mapa;

import java.util.ArrayList;
import java.util.List;

import coordenada.Coordenada;

public class Mapa {

	// Momentaneo 
	private String nombreMapa; 
	////////////////////
	private Coordenada coordenada;
	private List<ElementoMapa> elementosMapa;
	private int meta;//Esta va a ser la coordenada en el eje y de la meta

	private int anchoTotal;
	public int getAnchoTotal() {
		return anchoTotal;
	}

	public void setAnchoTotal(int anchoTotal) {
		this.anchoTotal = anchoTotal;
	}

	public int getLargoTotal() {
		return largoTotal;
	}

	public void setLargoTotal(int largoTotal) {
		this.largoTotal = largoTotal;
	}

	private int largoTotal;
	
	
	public Mapa(int ancho, int largo) {
		this.elementosMapa = new ArrayList<ElementoMapa>();
		this.anchoTotal = ancho;
		this.largoTotal = largo;
	}
	
	public Mapa(String nombre) {
		this.nombreMapa = nombre;
	}
	
	public List<ElementoMapa> getElementosMapa() {
		return elementosMapa;
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
	
	public void agregarElemento(ElementoMapa elemento) {
		elementosMapa.add(elemento);
	}
	

	
	
}
