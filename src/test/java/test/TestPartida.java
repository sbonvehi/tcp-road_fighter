package test;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

import exception.Exception_RoadFighter;
import mapa.Mapa;
import partida.Partida;
import sala.Sala;
import usuario.Usuario;

public class TestPartida {

	@Test
	public void testFinPartidaAnfitrionAbandono() throws Exception_RoadFighter{
		Usuario anfitrion = new Usuario("test", "1234");
		Usuario us1 = new Usuario("user1", "1234");
		Usuario us2 = new Usuario("user2", "1234");
		Usuario us3 = new Usuario("user3", "1234");
		Usuario us4 = new Usuario("user4", "1234");
		Usuario us5 = new Usuario("user5", "1234");
		
		Sala sala = new Sala(anfitrion,"SalaTest");
		sala.agregarUsuario(us1);
		sala.agregarUsuario(us2);
		sala.agregarUsuario(us3);
		sala.agregarUsuario(us4);
		sala.agregarUsuario(us5);
		
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

	@Test
	public void testFinPartidaJugadoresInsuficientes() throws Exception_RoadFighter{
		Usuario anfitrion = new Usuario("test", "1234");
		Usuario us1 = new Usuario("user1", "1234");
		Usuario us2 = new Usuario("user2", "1234");
		
		Sala sala = new Sala(anfitrion,"SalaTest");
		sala.agregarUsuario(us1);
		sala.agregarUsuario(us2);
		
		Partida partida = anfitrion.iniciarPartida(sala);
		
		us1.abandonarPartida(partida);
		us2.abandonarPartida(partida);
		
		assertFalse(partida.getEstado());
		assertFalse(anfitrion.getJugando());
		assertFalse(us1.getJugando());
		assertFalse(us2.getJugando());
	}

	@Test
	public void testIniciarParitda() throws Exception_RoadFighter{
		Usuario anfitrion = new Usuario("test", "1234");
		Usuario us1 = new Usuario("user1", "1234");
		Usuario us2 = new Usuario("user2", "1234");
		
		Sala sala = new Sala(anfitrion,"SalaTest");
		sala.agregarUsuario(us1);
		sala.agregarUsuario(us2);
		
		Partida partida = anfitrion.iniciarPartida(sala);
		
		assertTrue(partida.getEstado());
		assertTrue(anfitrion.getJugando());
		assertTrue(us1.getJugando());
		assertTrue(us2.getJugando());
	}
	

	@Test
	public void testIniciarParitdaLuegoFinalizar() throws Exception_RoadFighter{
		Usuario anfitrion = new Usuario("test", "1234");
		Usuario us1 = new Usuario("user1", "1234");
		Usuario us2 = new Usuario("user2", "1234");
		
		Sala sala = new Sala(anfitrion,"SalaTest");
		sala.agregarUsuario(us1);
		sala.agregarUsuario(us2);
		
		Partida partida = anfitrion.iniciarPartida(sala);
		
		assertTrue(partida.getEstado());
		assertTrue(anfitrion.getJugando());
		assertTrue(us1.getJugando());
		assertTrue(us2.getJugando());
		
		partida.finalizar();
		
		assertFalse(partida.getEstado());
		assertFalse(anfitrion.getJugando());
		assertFalse(us1.getJugando());
		assertFalse(us2.getJugando());
	}
	
	@Test
	public void testCargarAutos() throws Exception_RoadFighter{
		Usuario anfitrion = new Usuario("test", "1234");
		Usuario us1 = new Usuario("user1", "1234");
		Usuario us2 = new Usuario("user2", "1234");
		
		Sala sala = new Sala(anfitrion,"SalaTest");
		sala.agregarUsuario(us1);
		sala.agregarUsuario(us2);
		
		Partida partida = anfitrion.iniciarPartida(sala);

		assertEquals(anfitrion.getNombre(), partida.getListaAutos().get(0).getPiloto().getNombre());
		assertEquals(us1.getNombre(), partida.getListaAutos().get(1).getPiloto().getNombre());
		assertEquals(us2.getNombre(), partida.getListaAutos().get(2).getPiloto().getNombre());
	}
	
}
