package menu;

import java.util.ArrayList;
import java.util.List;

import exception.Exception_RoadFighter;
import login.GestorLogin;
import sala.Sala;
import usuario.Usuario;

public class Menu {

	private List<Sala> listaSalas;
	private String fileLogin = "./archivoLogin/usuarios.txt";

	public Menu() {
		this.listaSalas = new ArrayList<Sala>();
	}

	public List<Sala> getListaSalas(){ 
		return this.listaSalas;
	}

	public Usuario registrarUsuario(String nombre, String contrasenia) throws Exception {
		return GestorLogin.registrarUsuario(this.fileLogin, nombre, contrasenia);
	}

	public Usuario loginUsuario(String nombre, String contrasenia) throws Exception {
		if(!GestorLogin.login(this.fileLogin, nombre, contrasenia))
			throw new Exception("Usuario no encontrado!");
		
			return new Usuario(nombre, contrasenia);
	}

	public boolean agregarSala(Sala sala) throws Exception_RoadFighter {
		if(listaSalas.indexOf(sala) != -1)
			throw new Exception_RoadFighter("Imposible agregar una sala ya existente");
		
		return listaSalas.add(sala);
	}

}
