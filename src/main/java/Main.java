import java.util.Scanner;

import login.GestorLogin;
import menu.Menu;
import usuario.Usuario;

public class Main {

	public static void main(String[] args) throws Exception {

		Menu menu = new Menu();
		System.out.println("********************************************");
		System.out.println("*************** Road Fighter ***************");
		System.out.println("********************************************");

		menu.mostrarOpcionesInicio();
		
		Usuario usr;

		boolean estaLogueado = false;
		
		while( ! estaLogueado) {
			int opcionElegida = menu.ingresarOpcionLogIn(1, 3);
			switch (opcionElegida) {
			case 1:
				usr = menu.registrarUsuario();
				break;
			case 2:
				usr = menu.loginUsuario();
				if (usr != null)
					estaLogueado = true;				
				break;
			case 3:
				System.out.println("Finalizando ejecucion del juego..");
				return;
			}	
		}
		
		
		
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
