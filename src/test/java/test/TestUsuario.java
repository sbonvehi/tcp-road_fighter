package test;
import menu.Menu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Scanner;

import org.junit.Test;

import exception.Exception_RoadFighter;
import login.GestorLogin;
import mapa.Mapa;
import partida.Partida;
import sala.Sala;
import usuario.Usuario;
public class TestUsuario {

	@Test
	public void elegirMapa() {
		Usuario anfitrion = new Usuario("anfitrion", "1234");
		Sala sala= new Sala(anfitrion,"salaTest");
		Mapa mapa = new Mapa("mapa2", 10,100); //mapa existente
		
		anfitrion.elegirMapa(sala, mapa);
		assertEquals(mapa, sala.getMapaSeleccionado());		
	}
	
	@Test
	public void abandonarPartida() throws Exception_RoadFighter {
		Usuario anfitrion = new Usuario("test", "test");
		Usuario _1 = new Usuario("test1", "test1");
		Usuario _2 = new Usuario("test2", "test2");
		Usuario _3 = new Usuario("test3", "test3");
		Sala sala = new Sala(anfitrion, "salaTest");
		Mapa mapa = new Mapa("mapa2", 40, 100); //mapa existente
		sala.agregarUsuario(_1);
		sala.agregarUsuario(_2);
		sala.agregarUsuario(_3);
		anfitrion.elegirMapa(sala, mapa);
		
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
		Usuario anfitrion = new Usuario("test", "test");
		Usuario usuario1 = new Usuario("test1", "test1");
		Usuario usuario2 = new Usuario("test2", "test2");
		Sala sala = new Sala(anfitrion, "salaTest");
		sala.agregarUsuario(usuario1);
		Mapa mapa = new Mapa("mapa2",10,100); //mapa existente
		
		anfitrion.elegirMapa(sala, mapa);
		
		Partida partida = anfitrion.iniciarPartida(sala);
				
		try {
			partida.eliminarUsuario(usuario2);
			anfitrion.abandonarPartida(partida);
		} catch (Exception_RoadFighter e) {
			System.out.println(e.getMessage());
		}
	}	
	
//	@Test(expected = Exception_RoadFighter.class)
//	public void iniciarPartidaConUsuariosInsuficientes() throws Exception_RoadFighter {
//		Usuario anfitrion = new Usuario("test", "1234");
//		Sala sala = new Sala(anfitrion, "salaTest");
//		Mapa mapa = new Mapa("mapa2", 10, 50); //mapa existente
//		anfitrion.elegirMapa(sala, mapa);
//		
//		anfitrion.iniciarPartida(sala);
//	}
	
	@Test
	public void finalizarPartidaPorMenos2Jugadores() throws Exception_RoadFighter {
		Usuario anfitrion = new Usuario("test", "test");
		Usuario us1 = new Usuario("test1", "test1");
		Usuario us2 = new Usuario("test2", "test2");
		
		Sala sala = new Sala(anfitrion, "salaTest");
		Mapa mapa = new Mapa("mapa2",10,100); //mapa existente
		
		sala.agregarUsuario(us1);
		sala.agregarUsuario(us2);
		anfitrion.elegirMapa(sala, mapa);
		
		Partida partida = anfitrion.iniciarPartida(sala);
		us1.abandonarPartida(partida);
		us2.abandonarPartida(partida);
		
		assertFalse(partida.getEstado());
	}
	
	@Test
	public void finalizarPartidaPorAnfitrion() throws Exception_RoadFighter {
		Usuario anfitrion = new Usuario("test", "1234");
		Usuario us1 = new Usuario("user1", "1234");
		Usuario us2 = new Usuario("user2", "1234");
		
		Sala sala = new Sala(anfitrion, "salaTest");
		Mapa mapa = new Mapa("mapa2", 10, 50); //mapa existente
		sala.agregarUsuario(us1);
		sala.agregarUsuario(us2);
		anfitrion.elegirMapa(sala, mapa);
		
		Partida partida = anfitrion.iniciarPartida(sala);
		anfitrion.abandonarPartida(partida);
		
		assertFalse(partida.getEstado());
	}
		
	@Test
	public void testCrearSala() throws Exception_RoadFighter {
		Menu menu = new Menu(new GestorLogin());
		Usuario anfitrion = new Usuario("anfitrion", "1234");
		Sala sala = new Sala(anfitrion, "salaTest");
		
		menu.agregarSala(sala);
		
		assertEquals(1, menu.getListaSalas().size());
	}
	
	@Test(expected = Exception_RoadFighter.class)
	public void testErrorSalaRepetida() throws Exception_RoadFighter {
		Menu menu = new Menu(new GestorLogin());
		Usuario anfitrion = new Usuario("anfitrion", "1234");
		Sala sala = new Sala(anfitrion, "salaTest");
		
		menu.agregarSala(sala);
		menu.agregarSala(sala);
		
		assertEquals(1, menu.getListaSalas().size());
	}
	
	
	
}
