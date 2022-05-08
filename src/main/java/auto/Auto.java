package auto;

import mapa.Mapa;

public class Auto {
	
	private ModoAuto modoAuto;
	
	public void acelerar() {
		this.modoAuto.acelerar();
	}
	
	public void frenar() {
		this.modoAuto.frenar();
	}
	
	public void desplazarse(Mapa ubicacion) {
		this.modoAuto.desplazarse(ubicacion);
	}
	
	public void colisiona() {
		this.modoAuto.colisiona();
	}
	
}
