package Usuario;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import exception.Exception_RoadFighter;
import partida.Partida;
import sala.Sala;
import usuario.Usuario;

public class TestUsuario {

	@Test
	public void abandonarPartida() throws Exception_RoadFighter {
		Usuario anfitrion = new Usuario("test", "1234");
		Usuario _1 = new Usuario("tessft", "1234");
		Usuario _2 = new Usuario("tesdsaft", "123124");
		Usuario _3 = new Usuario("tesadf13st", "121234");
		Sala sala = new Sala(anfitrion);
		sala.agregarUsuario(_1);
		sala.agregarUsuario(_2);
		sala.agregarUsuario(_3);
		
		sala.setMapaSeleccionado(anfitrion.elegirMapa(sala.getListaMapas()));
		
		Partida partida = anfitrion.iniciarPartida(sala);
		try {
			_3.abandonarPartida(partida);
		} catch (Exception_RoadFighter e) {
			System.out.println(e.getMessage());
		}
		
		assertEquals(3, partida.getListaAutos().size());
		assertFalse(_3.getJugando());
	}
	
	@Test
	public void eliminarUsuarioSinPartida() throws Exception_RoadFighter {
		Usuario anfitrion = new Usuario("test", "1234");
		Usuario usuario1 = new Usuario("tessdfadt", "asdfa34");
		Usuario usuario2 = new Usuario("tesdt", "12asdfa34");
		Sala sala = new Sala(anfitrion);
		sala.agregarUsuario(usuario1);
		
		sala.setMapaSeleccionado(anfitrion.elegirMapa(sala.getListaMapas()));
		
		Partida partida = anfitrion.iniciarPartida(sala);
				
		try {
			partida.eliminarUsuario(usuario2);
			anfitrion.abandonarPartida(partida);
		} catch (Exception_RoadFighter e) {
			System.out.println(e.getMessage());
		}
	}	
	
	@Test(expected = Exception_RoadFighter.class)
	public void iniciarPartidaConUsuariosInsuficientes() throws Exception_RoadFighter {
		Usuario anfitrion = new Usuario("test", "1234");
		Sala sala = new Sala(anfitrion);
		
		sala.setMapaSeleccionado(anfitrion.elegirMapa(sala.getListaMapas()));
		
		anfitrion.iniciarPartida(sala);
	}
	
	@Test
	public void finalizarPartidaPorMenos2Jugadores() throws Exception_RoadFighter {
		Usuario anfitrion = new Usuario("test", "1234");
		Usuario us1 = new Usuario("test", "1234");
		Usuario us2 = new Usuario("testdsfa", "123sdfa4");
		Sala sala = new Sala(anfitrion);
		sala.agregarUsuario(us1);
		sala.agregarUsuario(us2);
		
		sala.setMapaSeleccionado(anfitrion.elegirMapa(sala.getListaMapas()));
		
		Partida partida = anfitrion.iniciarPartida(sala);
		us1.abandonarPartida(partida);
		us2.abandonarPartida(partida);
		
		assertFalse(partida.getEstado());
	}
	
	@Test
	public void finalizarPartidaPorAnfitrion() throws Exception_RoadFighter {
		Usuario anfitrion = new Usuario("test", "1234");
		Usuario us1 = new Usuario("test", "1234");
		Usuario us2 = new Usuario("testdsfa", "123sdfa4");
		Sala sala = new Sala(anfitrion);
		sala.agregarUsuario(us1);
		sala.agregarUsuario(us2);
		
		sala.setMapaSeleccionado(anfitrion.elegirMapa(sala.getListaMapas()));
		
		Partida partida = anfitrion.iniciarPartida(sala);
		anfitrion.abandonarPartida(partida);
		
		assertFalse(partida.getEstado());
	}

}
