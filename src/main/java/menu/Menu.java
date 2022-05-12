package menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import login.GestorLogin;
import sala.Sala;
import usuario.Usuario;

public class Menu {

	private List<Sala> listaSalas;
	private String fileLogin = "./archivoLogin/usuarios.txt";
	
	public Menu() {
		this.listaSalas = new ArrayList<Sala>();
	}
	
	public List<Sala> getListaSalas(){ return this.listaSalas;}
	
	
	public Usuario registrarUsuario(){
		System.out.println("Ingrese su nombre:");
		Scanner scanner = new Scanner(System.in);
		String nombre = scanner.next();
		System.out.println("Ingrese una contrasenia:");
		String contrasenia = scanner.next();
		
		return GestorLogin.registrarUsuario(this.fileLogin, nombre, contrasenia);
	}

	public Usuario loginUsuario() {
		System.out.println("Ingrese su nombre:");
		Scanner scanner = new Scanner(System.in);
		String nombre = scanner.next();
		System.out.println("Ingrese una contrasenia:");
		String contrasenia = scanner.next();

		if( GestorLogin.login(this.fileLogin, nombre, contrasenia)) {
			return new Usuario(nombre, contrasenia);
		}
		
		return null;	
	}
	
	public void agregarSala(Sala sala){ listaSalas.add(sala);}


}
