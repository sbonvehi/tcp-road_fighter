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

	public Usuario registrarUsuario() {
		System.out.println("Ingrese su nombre:");
		Scanner scanner = new Scanner(System.in);
		
		String nombre = scanner.next();
		System.out.println("Ingrese una contrasenia:");
		String contrasenia = scanner.next();

		scanner.close();
		return GestorLogin.registrarUsuario(this.fileLogin, nombre, contrasenia);
	}

	public Usuario loginUsuario() throws Exception {
		System.out.println("Ingrese su nombre:");
		Scanner scanner = new Scanner(System.in);
		String nombre = scanner.next();
		System.out.println("Ingrese una contrasenia:");
		String contrasenia = scanner.next();

		scanner.close();
		if (!GestorLogin.login(this.fileLogin, nombre, contrasenia))
			throw new Exception("Usuario no encontrado!");
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
	
	public int ingresarOpcionLogIn(int min, int max) {
		
		int opcion;
		do {
		System.out.println("Ingrese opcion: ");
		Scanner scanner2 = new Scanner(System.in);
		opcion = scanner2.nextInt();
		scanner2.close();
		}while(opcion < min || opcion > max);
		return opcion;
	}
	
	
	
	public int cerrarJuego() {
		System.out.println("Finalizando ejecucion del juego..");
		return 0;
	}
	
}
