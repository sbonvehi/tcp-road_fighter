package Usuario;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import mapa.Mapa;
import sala.Sala;
import usuario.Usuario;

public class TestUsuario {

	@Test
	public void testIniciarPartida() {
		Usuario anfitrion = new Usuario("test", "1234");
		Sala sala = new Sala(anfitrion);
				
		assertTrue(anfitrion.iniciarPartida(sala));
		
	}	

}
