package menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exception.Exception_RoadFighter;
import login.GestorLogin;
import sala.Sala;
import usuario.Usuario;

public class Menu {

	private List<Sala> listaSalas;
	private final String fileLogin = "./archivoLogin/usuarios.txt";

	public Menu() {
		this.listaSalas = new ArrayList<Sala>();
	}

	public List<Sala> getListaSalas() {
		return this.listaSalas;
	}

	public Usuario registrarUsuario(Scanner scanner) {
		System.out.println("Ingrese su nombre:");
		
		String nombre = scanner.next();
		System.out.println("Ingrese una contrasenia:");
		String contrasenia = scanner.next();
		return GestorLogin.registrarUsuario(this.fileLogin, nombre, contrasenia);
	}

	public Usuario loginUsuario(Scanner scanner){
		System.out.println("Ingrese su nombre:");
		String nombre = scanner.next();
		System.out.println("Ingrese una contrasenia:");
		String contrasenia = scanner.next();
		if (!GestorLogin.login(this.fileLogin, nombre, contrasenia)) {
			System.out.println("Usuario o contrasena incorrecta!");
			return null;
		}
		return new Usuario(nombre, contrasenia);
	}

	public boolean agregarSala(Sala sala) throws Exception_RoadFighter {
		if (listaSalas.indexOf(sala) != -1)
			throw new Exception_RoadFighter("Imposible agregar una sala ya existente");

		return listaSalas.add(sala);
	}
	
	public void mostrarOpcionesInicio() {
		System.out.println("1- Registrar Usuario.");
		System.out.println("2- Iniciar Sesion.");
		System.out.println("3- Salir del Juego.");
	}
	
	public void mostrarOpcionesJugar() {
		System.out.println("1- Crear Sala.");
		System.out.println("2- Ingresar a Sala.");
		
	}
	
	public int ingresarOpcionLogIn(int min, int max, Scanner scanner) {
		
		int opcion = 0;
		try {
			do {
				System.out.println("Ingrese opcion: ");
				opcion = scanner.nextInt();
			}while(opcion < min || opcion > max);			
		}
		catch(Exception e){
			System.out.println("Error el valor debe estar entre " + min + " y " + max + ".");
		}
		return opcion;
	}
	
	
	
	public int cerrarJuego() {
		System.out.println("Finalizando ejecucion del juego..");
		return 0;
	}
	
}
