package auto;

import mapa.Mapa;
import usuario.Usuario;

public class Auto {
	
	private ModoAuto modoAuto = new AutoJugador();
	
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
	
	public void setModo() {
		this.modoAuto.setModo();
	}
	
	public void setPiloto(Usuario piloto) {
		this.modoAuto.setPiloto(piloto);
	}
}
