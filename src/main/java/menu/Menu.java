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
	
	public List<Sala> getListaSalas()
	{
		return this.listaSalas;
	}
	
	
	public Usuario registrarUsuario(){
		System.out.println("Ingrese su nombre:");
		Scanner scanner = new Scanner(System.in);
		String nombre = scanner.next();
		System.out.println("Ingrese una contrasenia:");
		String contrasenia = scanner.next();

		Usuario nuevoUsuario = new Usuario(nombre, contrasenia);
		return  nuevoUsuario;
	}
	
	public void agregarSala(Sala sala)
	{
		
		listaSalas.add(sala);
	}


}
