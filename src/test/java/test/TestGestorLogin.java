package test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.junit.Test;

import login.GestorLogin;
import usuario.Usuario;

public class TestGestorLogin {
	private final String fileRegistrarTest = "./archivoLogin/usuariosTest.txt";
	private final String fileLoginTest = "./archivoLogin/usuariosLoginTest.txt";
	private final String fileLoginTestFail = "./archivoLogin/usuariosTestFail.txt";
	
	@Test
	public void testRegistrarUsarioCorrectamente() throws FileNotFoundException {
		Usuario user = GestorLogin.registrarUsuario(fileRegistrarTest, "test" , "test");
		new PrintWriter(fileRegistrarTest).close();
		assertNotNull(user);
	}
	
	@Test
	public void testRegistrarUsarioExistente() {
		Usuario user = GestorLogin.registrarUsuario(fileLoginTestFail, "testRepetido" , "testRepetido");
		assertNull(user);
	}
	
	@Test
	public void testLoginCorrectamente() throws FileNotFoundException {
		assertTrue(GestorLogin.login(fileLoginTest, "usarioTestLogin" , "1234"));
	}
	
	@Test
	public void testLoginUserIncorrecto() {
		assertFalse(GestorLogin.login(fileLoginTest, "usarioInvalido" , "1234"));
	}
	
	@Test
	public void testLoginPassIncorrecto() {
		assertFalse(GestorLogin.login(fileLoginTest, "usarioTestLogin" , "1111"));
	}
}
