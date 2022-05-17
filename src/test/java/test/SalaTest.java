package test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import exception.Exception_RoadFighter;
import mapa.Mapa;
import sala.Sala;
import usuario.Usuario;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SalaTest {

	private Sala _sala;
	private ArrayList<Usuario> _listUsuario;
	
	public SalaTest()
	{
		Usuario anfitrion = new Usuario("test");
		_listUsuario = new ArrayList<Usuario>() {{
			add(new Usuario("user1"));
			add(new Usuario("user2"));
			add(new Usuario("user3"));
			add(new Usuario("user4"));
			add(new Usuario("user5"));
		}};
		
		_sala = new Sala(anfitrion, "SalaTest");
	}
	
	@Test
	public void sala1_agregarUsuario() throws Exception_RoadFighter{
		
		for(Usuario user: _listUsuario)
		{
			_sala.agregarUsuario(user);
		}
		
		assertEquals(6, _sala.getListaUsuarios().size());
	}
	
//	@Test
//	public void eliminarSalaPorAnfitrion() throws Exception_RoadFighter{
//		Usuario anfitrion = new Usuario("test");
//		Usuario us1 = new Usuario("user1");
//		Usuario us2 = new Usuario("user2", "1234");
//		Usuario us3 = new Usuario("user3", "1234");
//		Usuario us4 = new Usuario("user4", "1234");
//		Usuario us5 = new Usuario("user5", "1234");
//		
//		Sala sala = new Sala(anfitrion, "SalaTest");
//		sala.agregarUsuario(us1);
//		sala.agregarUsuario(us2);
//		sala.agregarUsuario(us3);
//		sala.agregarUsuario(us4);
//		sala.agregarUsuario(us5);
//		
//		anfitrion.abandonarSala(sala);		
//		
//		assertEquals(0, sala.getListaUsuarios().size());
//		
//	}
	
	@Test
	public void elegirMapa(){
		final Mapa mapa = new Mapa("mapa2", 10, 50); 
		
		_sala.setListaMapas(new ArrayList<Mapa>() {{ add(mapa); }});
		
		InputStream sysInBackup = System.in; // backup System.in to restore it later
		ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
		System.setIn(in);

		// do your thing
		_sala.elegirMapa();
		
		assertEquals(mapa, _sala.getMapaSeleccionado());	
		
		// optionally, reset System.in to its original
		System.setIn(sysInBackup);
		
	}
	
	@Test
	public void sala4_eliminarSala() throws Exception_RoadFighter{
		_sala.eliminarSala();	
		
		assertEquals(0, _sala.getListaUsuarios().size());
		
	}
}
