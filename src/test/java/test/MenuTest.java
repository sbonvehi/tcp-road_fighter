package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mockito.Mockito;

import login.GestorLogin;
import menu.Menu;
import usuario.Usuario;

public class MenuTest {

	private Menu _menu;
	private GestorLogin _mockGestorLogin;
	
	public MenuTest()
	{
		_mockGestorLogin = Mockito.mock(GestorLogin.class);
		_menu = new Menu(_mockGestorLogin);
	}

//	
//	@Test
//	public void primeraPantalla_registrarUsuario() throws Exception {
//		Menu menu = new Menu();
//		menu.loginUsuario("Sebastian", "asdfadsfadfafdfewrhola"); //modificar valores para probar los casos
//	}
	
//	@Test
//	public void testLoginUsuarioExistente() throws Exception {
//		Menu menu = new Menu();
//		Usuario u = menu.loginUsuario("Sebastian", "hola"); //modificar valores para probar los casos
//		assertTrue(u != null);
//	}
	
}