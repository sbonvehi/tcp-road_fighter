package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import menu.Menu;
import usuario.Usuario;

public class TestMenu {

//	@Test
//	public void testRegistrarUsuarioNoExistente() throws Exception {
//		Menu menu = new Menu();
//		Usuario u = menu.registrarUsuario("", ""); //modificar valores para probar los casos
//		assertTrue(u != null);
//	}
//	
//	@Test(expected = Exception.class)
//	public void testRegistrarUsuarioExistente() throws Exception {
//		Menu menu = new Menu();
//		menu.registrarUsuario("", ""); //modificar valores para probar los casos
//	}
	
	@Test(expected = Exception.class)
	public void testLoginUsuarioNoExistente() throws Exception {
		Menu menu = new Menu();
		menu.loginUsuario("Sebastian", "asdfadsfadfafdfewrhola"); //modificar valores para probar los casos
	}
	
	@Test
	public void testLoginUsuarioExistente() throws Exception {
		Menu menu = new Menu();
		Usuario u = menu.loginUsuario("Sebastian", "hola"); //modificar valores para probar los casos
		assertTrue(u != null);
	}
	
}
