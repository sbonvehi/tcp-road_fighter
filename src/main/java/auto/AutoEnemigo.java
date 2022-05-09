package auto;

import mapa.Mapa;
import usuario.Usuario;

public class AutoEnemigo extends ModoAuto {

	private static final int VELOCIDAD_INICIAL = 0;

	@Override
	public void acelerar() {
		this.velocidad += VELOCIDAD_INICIAL + 100; //pongo valor ejemplo
	}

	@Override
	public void frenar() {
		//corresponde que el enemigo frene?
	}

	@Override
	public void desplazarse(Mapa ubicacion) {
		this.ubicacion = ubicacion;
	}

	@Override
	public void colisiona() {
		//que pasaria aca?
	}

	
	@Override
	public void setPiloto(Usuario piloto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ModoAuto setModo() {
		// TODO Auto-generated method stub
		return new AutoJugador();
	}

}
