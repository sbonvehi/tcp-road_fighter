import java.util.Scanner;

import login.GestorLogin;
import menu.Menu;
import usuario.Usuario;

public class Main {

	public static void main(String[] args) {

		Menu menu = new Menu();
		System.out.println("********************************************");
		System.out.println("*************** Road Fighter ***************");
		System.out.println("********************************************");

		Usuario usr;

		boolean estaLogueado = false;
		Scanner scanner = new Scanner(System.in);

		while (!estaLogueado) {
			menu.mostrarOpcionesInicio();
			int opcionElegida = menu.ingresarOpcionLogIn(1, 3, scanner);
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
				return;
			
			default:
				scanner.next();
				break;
			}
			System.out.println("");
		}

		scanner.close();

//		public void mostrarOpcionesInicio() {
//			System.out.println("1- Registrar Usuario.");
//			System.out.println("2- Iniciar Sesion.");
//			System.out.println("3- Salir del Juego.");
//		}
//		
//		public void mostrarOpcionesJugar() {
//			System.out.println("1- Crear Sala.");
//			System.out.println("2- Ingresar a Sala.");
//			
//		}

//		menu.mostrarOpcionesJugar();

//		return GestorLogin.registrarUsuario(this.fileLogin, nombre, contrasenia);

	}

}
