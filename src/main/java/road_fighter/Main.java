package road_fighter;
import exception.Exception_RoadFighter;
import login.GestorLogin;
import menu.Menu;

public class Main {

	public static void main(String[] args) throws InterruptedException, Exception_RoadFighter {

		Menu menu = new Menu(new GestorLogin("./archivoLogin/usuarios.txt"));

		menu.primeraPantalla();
		
		menu.segundaPantalla();
	}
}
