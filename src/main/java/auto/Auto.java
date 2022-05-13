package auto;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import coordenada.Coordenada;
import mapa.Mapa;
import usuario.Usuario;

public class Auto {

	private int velocidad;
	private Coordenada ubicacion;
	private int distanciaRecorrida;
	private Usuario piloto;
	

	public int getVelocidad() {
		return velocidad;
	}


	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}


	public Coordenada getUbicacion() {
		return ubicacion;
	}



	public void setUbicacion(Coordenada ubicacion) {
		this.ubicacion = ubicacion;
	}


	public int getDistanciaRecorrida() {
		return distanciaRecorrida;
	}


	public void setDistanciaRecorrida(int distanciaRecorrida) {
		this.distanciaRecorrida = distanciaRecorrida;
	}


	public Usuario getPiloto() {
		return piloto;
	}
	
	public void setPiloto(Usuario piloto) {
		this.piloto = piloto;
	}
	
	public void perderControl() throws InterruptedException {

		while( velocidad > 1 ) {
			velocidad *= 0.3;
			TimeUnit.SECONDS.sleep(1);
			
//			if(golpe margen) {
//				auto.resetearPosicion();
//			}
		}
		//no explote..
		velocidad = 0;
	}
	
	public void reducirVelocidad() {
		velocidad = (int) ((velocidad > 1 )? velocidad*0.80 : 0);  
    }

    public void aumentarVelocidad() {
        this.velocidad *= 1.20; 
    } 
	
}
