package test;

import static org.junit.Assert.*;

import org.junit.Test;

import exception.Exception_RoadFighter;
import mapa.Mapa;
import sala.Sala;
import usuario.Usuario;

public class TestSala {


	@Test
	public void eliminarSalaPorAnfitrion() throws Exception_RoadFighter{
		Usuario anfitrion = new Usuario("test", "1234");
		Usuario us1 = new Usuario("user1", "1234");
		Usuario us2 = new Usuario("user2", "1234");
		Usuario us3 = new Usuario("user3", "1234");
		Usuario us4 = new Usuario("user4", "1234");
		Usuario us5 = new Usuario("user5", "1234");
		
		Sala sala = new Sala(anfitrion, "SalaTest");
		sala.agregarUsuario(us1);
		sala.agregarUsuario(us2);
		sala.agregarUsuario(us3);
		sala.agregarUsuario(us4);
		sala.agregarUsuario(us5);
		
		anfitrion.abandonarSala(sala);		
		
		assertEquals(0, sala.getListaUsuarios().size());
		
	}
	
	@Test
	public void eliminarSala() throws Exception_RoadFighter{
		Usuario anfitrion = new Usuario("test", "1234");
		Usuario us1 = new Usuario("user1", "1234");
		Usuario us2 = new Usuario("user2", "1234");
		Usuario us3 = new Usuario("user3", "1234");
		Usuario us4 = new Usuario("user4", "1234");
		Usuario us5 = new Usuario("user5", "1234");
		
		Sala sala = new Sala(anfitrion, "SalaTest");
		sala.agregarUsuario(us1);
		sala.agregarUsuario(us2);
		sala.agregarUsuario(us3);
		sala.agregarUsuario(us4);
		sala.agregarUsuario(us5);
		
		sala.eliminarSala();	
		
		assertEquals(0, sala.getListaUsuarios().size());
		
	}
	
	@Test
	public void agregarUsuario() throws Exception_RoadFighter{
		Usuario anfitrion = new Usuario("test", "1234");
		Usuario us1 = new Usuario("user1", "1234");
		Usuario us2 = new Usuario("user2", "1234");
		Usuario us3 = new Usuario("user3", "1234");
		Usuario us4 = new Usuario("user4", "1234");
		Usuario us5 = new Usuario("user5", "1234");
		
		Sala sala = new Sala(anfitrion, "SalaTest");
		sala.agregarUsuario(us1);
		sala.agregarUsuario(us2);
		sala.agregarUsuario(us3);
		sala.agregarUsuario(us4);
		sala.agregarUsuario(us5);
		
		assertEquals(6, sala.getListaUsuarios().size());
		
	}
	
	@Test
	public void elegirMapa(){
		Usuario anfitrion = new Usuario("test", "1234");
		Sala sala = new Sala(anfitrion, "SalaTest");
		Mapa mapa = new Mapa("mapa2", 10, 50); 
		
		
		sala.setMapaSeleccionado(mapa);
		assertEquals(mapa, sala.getMapaSeleccionado());	
	}
	
}
