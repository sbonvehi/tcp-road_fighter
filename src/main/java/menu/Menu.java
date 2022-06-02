package menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import exception.Exception_RoadFighter;
import javafx.scene.media.AudioClip;
import login.GestorLogin;
import partida.Partida;
import road_fighter.objects.Auto;
import road_fighter.utils.AudioResources;
import sala.Sala;
import usuario.Usuario;

public class Menu {

	private GestorLogin _gestorLogin;
	private List<Sala> listaSalas;
	private Scanner _scanner;
	private Usuario _anfitrion;
	
	public Menu(GestorLogin gestorLogin) {
		_gestorLogin = gestorLogin;
		listaSalas = new ArrayList<Sala>();
		 
		_scanner = new Scanner(System.in);
	}

	public void primeraPantalla()
	{	
		mostrarTitulo();
		boolean estaLogueado = false;
		Usuario user = null;
		
		while (!estaLogueado) {
			mostrarOpcionesInicio();
			int opcionElegida = ingresarOpcion(1, 3);
			switch (opcionElegida) {
			case 1:
				registrarUsuario();
				break;
			case 2:
				user = loginUsuario();
				if (user != null)
				{
					estaLogueado = true;
					_anfitrion = user;
				}
					
				break;
			case 3:
				System.out.println("Finalizando ejecucion del juego..");
				_scanner.close();
				System.exit(0);
			
			default:
				_scanner.next();
				break;
			}
			System.out.println("");
		}
	}

	public void segundaPantalla() throws InterruptedException, Exception_RoadFighter
	{
		boolean opcionValida = false;
		
		while (!opcionValida) {
			Usuario userTest = new Usuario("testIngresarPartida", "1234");
			Sala salaPruebaIngresar = new Sala(userTest, "SalaTest");
			agregarSala(salaPruebaIngresar);
			
			mostrarOpcionesJugar();
			int opcionElegida = ingresarOpcion(1, 3);
			
			switch (opcionElegida) {
			case 1:	
				//Sala sala = _anfitrion.crearSala(_scanner);
				
				
				
				//Partida partida = _anfitrion.configurarSala(sala, _scanner);
				
				System.out.println("3....2....1... GO");
								
				// Muestro los autos, y mostrar el estado de los pilotos,
				// y la ubicacion
				
				//List<Auto> listaAutos = partida.getListaAutos();
			
//				for (Auto auto : listaAutos ) {
//				System.out.println(auto.getPiloto().getNombre() + " Jugando: " + auto.getPiloto().getJugando());
//				}
//				
//				System.out.println("Carrera en curso...");
//
//				TimeUnit.SECONDS.sleep(3);
//				
//				partida.finalizar();
//				
//				for (Auto auto : listaAutos ) {
//					System.out.println(auto.getPiloto().getNombre() + " Jugando: " + auto.getPiloto().getJugando());
//				}
//				
				System.out.println("Fin de la carrera");
				
				break;
			case 2:
				_anfitrion.entrarSala(getListaSalas(), _scanner);
				System.out.println("Cantidad de Juegadores: " + salaPruebaIngresar.getListaUsuarios().size());
				opcionValida = true;
				break;
				
			case 3:
				System.out.println("Finalizando ejecucion del juego..");
				_scanner.close();
				System.exit(0);
			
			default:
				_scanner.next();
				break;
			}
			System.out.println("");
		}
		
		boolean partidaIniciada = false;
		_scanner.close();
	}
	
	private boolean registrarUsuario() {
		System.out.println("Ingrese su nombre:");
		String nombre = _scanner.next();
		
		System.out.println("Ingrese una contrasenia:");
		String contrasenia = _scanner.next();
		
		return _gestorLogin.registrarUsuario(nombre, contrasenia);
	}

	private Usuario loginUsuario(){
		System.out.println("Ingrese su nombre:");
		String nombre = _scanner.next();
		
		System.out.println("Ingrese una contrasenia:");
		String contrasenia = _scanner.next();
		
		if (!_gestorLogin.login(nombre, contrasenia)) {
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
	
	private void mostrarTitulo() {
		System.out.println("********************************************");
		System.out.println("*************** Road Fighter ***************");
		System.out.println("********************************************");
	}
	
	private void mostrarOpcionesInicio() {
		System.out.println("1- Registrar Usuario.");
		System.out.println("2- Iniciar Sesion.");
		System.out.println("3- Salir del Juego.");
	}
	
	private void mostrarOpcionesJugar() {
		System.out.println("1- Crear Sala.");
		System.out.println("2- Ingresar a Sala.");
		System.out.println("3- Salir del Juego.");
	}
	
	private int ingresarOpcion(int min, int max) {
		
		int opcion = 0;
		try {
			do {
				System.out.println("Ingrese opcion: ");
				opcion = _scanner.nextInt();
			}while(opcion < min || opcion > max);			
		}
		catch(Exception e){
			System.out.println("Error el valor debe estar entre " + min + " y " + max + ".");
		}
		return opcion;
	}
	
	
	public void borrarSala(Sala sala) {
		listaSalas.remove(sala);
	}
	
	public int cerrarJuego() {
		System.out.println("Finalizando ejecucion del juego..");
		return 0;
	}
	
	//Getters & Setters
	
	public List<Sala> getListaSalas() {
		return this.listaSalas;
	}
	
}
