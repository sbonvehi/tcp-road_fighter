import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import auto.Auto;
import exception.Exception_RoadFighter;
import login.GestorLogin;
import menu.Menu;
import partida.Partida;
import sala.Sala;
import usuario.Usuario;

public class Main {

	public static void main(String[] args) throws Exception_RoadFighter, InterruptedException {

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
		
		Usuario userTest = new Usuario("testIngresarPartida", "1234");
		Sala salaPruebaIngresar = new Sala(userTest, "SalaTest");
		menu.agregarSala(salaPruebaIngresar);
		
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
				
				Partida partida = usr.configurarSala(sala, scanner);
				
				System.out.println("3....2....1... GO");
								
				// Muestro los autos, y mostrar el estado de los pilotos,
				// y la ubicacion
				
				List<Auto> listaAutos = partida.getListaAutos();
			
				for (Auto auto : listaAutos ) {
				System.out.println(auto.getPiloto().getNombre() + " Jugando: " + auto.getPiloto().getJugando());
				}
				
				System.out.println("Carrera en curso...");

				TimeUnit.SECONDS.sleep(3);
				
				partida.finalizar();
				
				for (Auto auto : listaAutos ) {
					System.out.println(auto.getPiloto().getNombre() + " Jugando: " + auto.getPiloto().getJugando());
				}
				
				System.out.println("Fin de la carrera");
				
				break;
			case 2:
				usr.entrarSala(menu.getListaSalas(), scanner);
				System.out.println("Cantidad de Juegadores: " + salaPruebaIngresar.getListaUsuarios().size());
				opcionValida = true;
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
		
		boolean partidaIniciada = false;
		
		
		
		scanner.close();
	}
}
