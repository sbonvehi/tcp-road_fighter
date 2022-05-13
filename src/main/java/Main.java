import java.util.Scanner;

import exception.Exception_RoadFighter;
import login.GestorLogin;
import menu.Menu;
import sala.Sala;
import usuario.Usuario;

public class Main {

	public static void main(String[] args) {

		Menu menu = new Menu();
		System.out.println("********************************************");
		System.out.println("*************** Road Fighter ***************");
		System.out.println("********************************************");

		Usuario usr = null;

		boolean estaLogueado = false;
		Scanner scanner = new Scanner(System.in);

		while (!estaLogueado) {
			menu.mostrarOpcionesInicio();
			int opcionElegida = menu.ingresarOpcion(1, 3, scanner);
			switch (opcionElegida) {
			case 1:
				usr = menu.registrarUsuario(scanner);
				break;
			case 2:
				usr = menu.loginUsuario(scanner);
				if (usr != null)
					estaLogueado = true;
				break;
			case 3:
				System.out.println("Finalizando ejecucion del juego..");
				scanner.close();
				return;
			
			default:
				scanner.next();
				break;
			}
			System.out.println("");
		}
		
		boolean opcionValida = false;
		while (!opcionValida) {
			
			menu.mostrarOpcionesJugar();
			int opcionElegida = menu.ingresarOpcion(1, 3, scanner);
			
			switch (opcionElegida) {
			case 1:
				Sala sala = usr.crearSala(scanner);
				
				try {
					menu.agregarSala(sala);
				} catch (Exception_RoadFighter e) {
					e.printStackTrace();
				}
				
				break;
			case 2:
				
				break;
			case 3:
				System.out.println("Finalizando ejecucion del juego..");
				scanner.close();
				return;
			
			default:
				scanner.next();
				break;
			}
			System.out.println("");
		}
		
		scanner.close();

		//public void mostrarOpcionesJugar() {
//			System.out.println("1- Crear Sala.");
//			System.out.println("2- Ingresar a Sala.");
//			
//		}

//		menu.mostrarOpcionesJugar();

//		return GestorLogin.registrarUsuario(this.fileLogin, nombre, contrasenia);

	}

}
