package menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import auto.Auto;
import exception.Exception_RoadFighter;
import login.GestorLogin;
import mapa.Mapa;
import partida.Partida;
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
			Usuario userTest = new Usuario("testIngresarPartida");
			Sala salaPruebaIngresar = new Sala(userTest, "SalaTest");
			agregarSala(salaPruebaIngresar);
			
			mostrarOpcionesJugar();
			int opcionElegida = ingresarOpcion(1, 3);
			
			switch (opcionElegida) {
			case 1:	
				try {
					System.out.println("Ingrese el nombre de la sala: ");
					String nombreSala = _scanner.next();
					Sala sala = crearSala(nombreSala);
					
					agregarSala(sala);
				
					sala.elegirMapa();
					
					Partida partida = configurarSala(sala);
					
					// Agregar validacion de partida si empezo
					System.out.println("3....2....1... GO");
									
					// Muestro los autos, y mostrar el estado de los pilotos,
					// y la ubicacion
					
					List<Auto> listaAutos = partida.getListaAutos();
				
					for (Auto auto : listaAutos ) {
					System.out.println(auto.mostrarNombrePiloto() + " Jugando: " + auto.corriendo());
					}
					
					System.out.println("Carrera en curso...");
	
					TimeUnit.SECONDS.sleep(3);
					
					partida.finalizar();
					
					for (Auto auto : listaAutos ) {
						System.out.println(auto.mostrarNombrePiloto() + " Jugando: " + auto.corriendo());
					}
					
					System.out.println("Fin de la carrera");
					
				} catch (Exception_RoadFighter e) {
					e.printStackTrace();
				}
				
				break;
			case 2:
				entrarSala();
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
	
	private Partida configurarSala(Sala sala) {
			
			boolean empezoPartida = false;
			
			Partida partida = null;
			while(!empezoPartida) {
				System.out.println("Para iniciar partida escriba  \"1\"");
				
				//aca se rompe, igual me parece que no va todo este metodo porque ya no configura nada
				int opcion = _scanner.nextInt();
				
				try {
					if(opcion != 1) //se va
						partida = null;
					else
					{
						empezoPartida = true;
						partida =  sala.iniciarPartida();
					}			
				} catch(Exception_RoadFighter e) {
					e.getMessage();
				}
			}
			
			return partida;
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
		
		return new Usuario(nombre);
	}

	private boolean agregarSala(Sala sala) throws Exception_RoadFighter {
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
	
	private boolean registrarUsuario() {
		System.out.println("Ingrese su nombre:");
		String nombre = _scanner.next();
		
		System.out.println("Ingrese una contrasenia:");
		String contrasenia = _scanner.next();
		
		return _gestorLogin.registrarUsuario(nombre, contrasenia);
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
	
	private Sala crearSala(String nombreSala){ 
		return new Sala(_anfitrion, nombreSala);
	}
	private void entrarSala()
	{
		int i = 1;
		for(Sala sala: listaSalas)
		{	
			System.out.println( i +  " - " + sala.getNombreSala());
			i++;
		}
		
		System.out.println("Por favor ingresa el numero de sala al que quieras unirte: ");
		
		int numeroSala = _scanner.nextInt();
		numeroSala--; //esto se hace para ya tener el nro-1

		while(numeroSala < 0 || numeroSala > listaSalas.size()) {
			System.out.println("El numero de sala ingresado es incorrecto, por favor ingrese el numero de sala nuevamente: ");
			
			numeroSala = _scanner.nextInt();
			numeroSala--; 
		}

		listaSalas.get(numeroSala).agregarUsuario(_anfitrion);

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
