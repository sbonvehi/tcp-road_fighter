package mapa;

import java.util.ArrayList;
import java.util.List;

import coordenada.Coordenada;

public class Mapa {

	private String nombreMapa;
	private List<ElementoMapa> elementosMapa;

//	private final int meta = 3000;
	private int margenInicialCalle;
	private int margenFinalCalle;
	private int anchoTotalVentana = 1200;
	private int largoTotal;

	public Mapa(String nombre, int ancho, int largo) {
		this.nombreMapa = nombre;
		this.elementosMapa = new ArrayList<ElementoMapa>();
		if (ancho > anchoTotalVentana)
			ancho = 1200;
		this.margenInicialCalle = (anchoTotalVentana / 2) - (ancho / 2);
		this.margenFinalCalle = (anchoTotalVentana / 2) + (ancho / 2);
		this.largoTotal = largo;
	}

	public List<ElementoMapa> getElementosMapa() {
		return elementosMapa;
	}

	public void agregarElemento(ElementoMapa elemento) {
		elementosMapa.add(elemento);
	}

	public String getNombreMapa() {
		return nombreMapa;
	}

}
