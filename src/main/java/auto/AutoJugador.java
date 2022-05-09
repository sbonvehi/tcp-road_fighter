package auto;

import mapa.Mapa;
import usuario.Usuario;

public class AutoJugador extends ModoAuto {

	private static final int VELOCIDAD_INICIAL = 0;

	@Override
	public void acelerar() {
		this.velocidad += VELOCIDAD_INICIAL + 180;
	}

	@Override
	public void frenar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desplazarse(Mapa ubicacion) {
		this.ubicacion = ubicacion;
	}

	@Override
	public void colisiona() {
		// TODO Auto-generated method stub
		
	}
	
	public void setPiloto(Usuario piloto) {
		this.piloto = piloto;
	}

	@Override
	public ModoAuto setModo() {
		// TODO Auto-generated method stub
		return new AutoJugador();
	}
}
