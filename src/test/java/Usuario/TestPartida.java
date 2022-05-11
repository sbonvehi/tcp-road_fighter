package Usuario;

import static org.junit.Assert.*;

import org.junit.Test;

import exception.Exception_RoadFighter;
import partida.Partida;
import sala.Sala;
import usuario.Usuario;

public class TestPartida {

	@Test
	public void testSetearTodosLosUsersEnFalse() throws Exception_RoadFighter{
		Usuario anfitrion = new Usuario("test", "1234");
		Usuario us1 = new Usuario("user1", "1234");
		Usuario us2 = new Usuario("user2", "1234");
		Usuario us3 = new Usuario("user3", "1234");
		Usuario us4 = new Usuario("user4", "1234");
		Usuario us5 = new Usuario("user5", "1234");
		
		Sala sala = new Sala(anfitrion);
		sala.agregarUsuario(us1);
		sala.agregarUsuario(us2);
		sala.agregarUsuario(us3);
		sala.agregarUsuario(us4);
		sala.agregarUsuario(us5);
		
		
		sala.setMapaSeleccionado(anfitrion.elegirMapa(sala.getListaMapas()));
		
		Partida partida = anfitrion.iniciarPartida(sala);
		try {
			anfitrion.abandonarPartida(partida);
		} catch (Exception_RoadFighter e) {
			System.out.println(e.getMessage());
		}
		assertFalse(partida.getEstado());
		assertFalse(us1.getJugando());
		assertFalse(us2.getJugando());
		assertFalse(us3.getJugando());
		assertFalse(us4.getJugando());
		assertFalse(us5.getJugando());
	}

}
