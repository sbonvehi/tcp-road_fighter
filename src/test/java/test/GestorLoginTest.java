package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.*;

import login.GestorLogin;
import usuario.Usuario;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GestorLoginTest {
	private GestorLogin _gestorLogin;
	private static final String fileRegistrarTest = "./archivoLogin/usuariosTest.txt";
	
	public GestorLoginTest()
	{
		this._gestorLogin = new GestorLogin();
	}
	
	@BeforeClass
	public static void SetUp() throws Exception{
		try {
			FileWriter fwOb = new FileWriter(fileRegistrarTest, false); 
	        PrintWriter pwOb = new PrintWriter(fwOb, false);
	        pwOb.flush();
	        pwOb.close();
	        fwOb.close();
	        
	        new File(fileRegistrarTest);
		}
		catch (Exception ex)
		{
			throw ex;
		}
	}
	
	@Test
	public void test1RegistrarUsarioCorrectamente() throws FileNotFoundException {
		boolean result = _gestorLogin.registrarUsuario("usarioTestLogin" , "validPassword");
		
		assertTrue(result);
	}
	
	@Test
	public void test2LoginPassIncorrecto() {
		assertFalse(_gestorLogin.login("usarioTestLogin" , "invalidPassword"));
	}
	
	@Test
	public void test3LoginCorrectamente() throws FileNotFoundException {
		assertTrue(_gestorLogin.login("usarioTestLogin" , "validPassword"));
	}
	
	@Test
	public void test4LoginUserIncorrecto() {
		assertFalse(_gestorLogin.login("usarioInvalido" , "validPassword"));
	}
	
	@Test
	public void test5RegistrarUsarioExistente() {
		boolean result = _gestorLogin.registrarUsuario("usarioTestLogin" , "test123");
		assertFalse(result);
	}	
}
