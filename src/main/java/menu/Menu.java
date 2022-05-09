package menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sala.Sala;
import usuario.Usuario;

public class Menu {

	private List<Sala> listaSalas;
	
	public Menu() {
		this.listaSalas = new ArrayList<Sala>();
	}

	public Usuario registrarUsuario(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Ingrese su nombre:");
		String nombre = sc.next();
		System.out.println("Ingrese una contrasenia:");
		String contrasenia = sc.next();

		Usuario nuevoUsuario = new Usuario(nombre, contrasenia);
		return  nuevoUsuario;
	}

	/* public static void crearNuevaSala(){
		Sala sala = new Sala();
		listaSalas.add(sala);
	}*/


}
