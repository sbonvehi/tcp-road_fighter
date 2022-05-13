package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import exception.Exception_RoadFighter;
import menu.Menu;
import sala.Sala;
import usuario.Usuario;

public class TestMenu {
	
//	@Test(expected = Exception.class)
//	public void testLoginUsuarioNoExistente() throws Exception {
//		Menu menu = new Menu();
//		menu.loginUsuario(); //modificar valores para probar los casos
//	}
	
	@Test
	public void testRegistrarUsuario() throws Exception {
		Menu menu = new Menu();		
		Usuario usuario = menu.registrarUsuario();
			
		assertNotNull(usuario);
	}
	
//	@Test
//	public void testRegistrarUsuarioDuplicado() {
//		Menu menu = new Menu();		
//		Usuario usuario = menu.registrarUsuario();
//			
//		assertNull(usuario);
//	}
//	
//	@Test
//	public void testLoginValido() {
//		Menu menu = new Menu();		
//		Usuario user = menu.loginUsuario();
//		
//		assertNotNull(user);
//	}
//	

//	@Test
//	public void testLoginNombreNoValido() {
//		Menu menu = new Menu();		
//		Usuario user = menu.loginUsuario();
//		
//		assertNull(user);
//	}
//	
//	@Test
//	public void testLoginPassNoValido() {
//		Menu menu = new Menu();		
//		Usuario user = menu.loginUsuario();
//		
//		assertNull(user);
//	}
//
//	@Test(expected = Exception.class)
//	public void testLoginContraseñaIncorrecta() throws Exception {
//		Menu menu = new Menu();
//		menu.loginUsuario("Sebastian", "contraseña_falsa"); 
//	}
//	
//	@Test
//	public void testLoginUsuarioExistente() throws Exception {
//		Menu menu = new Menu();
//		Usuario u = menu.loginUsuario("Sebastian", "hola"); 
//		assertTrue(u != null);
//	}
//	
//	///
//	
//	@Test(expected = Exception_RoadFighter.class)
//	public void testRegistrarUsuarioExistente() throws Exception {
//		Menu menu = new Menu();
//		Usuario u = menu.registrarUsuario("Sebastian", "hola"); 
//		assertTrue(u != null);
//	}
//	
//	@Test(expected = Exception_RoadFighter.class)
//	public void testAgregarSalaExistente() throws Exception {
//		Menu menu = new Menu();
//		Usuario u = new Usuario("Facu", "12345");
//		Sala sala = new Sala(u);
//		
//		menu.agregarSala(sala);
//		menu.agregarSala(sala);
//	}
//	
//	@Test
//	public void testAgregarSalaNoExistente() throws Exception {
//		Menu menu = new Menu();
//		Usuario u = new Usuario("Facu", "12345");
//		Sala sala = new Sala(u);
//		
//		assertTrue(menu.agregarSala(sala));
//	}
	
}
